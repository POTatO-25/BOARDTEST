package com.tech.boardtest.board.controller;

import com.tech.boardtest.board.dao.PostDao;
import com.tech.boardtest.board.dto.PostDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    private PostDao postDao;

    @GetMapping("/boardHome")
    public String boardHome(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                            HttpSession session, Model model) {
        System.out.println("boardHomeController");

        String sessionId = (String) session.getAttribute("session_user_id");
        model.addAttribute("sessionId", sessionId);

        int totalCount = postDao.selectPostCount(); // 전체 게시글 개수
        int totalPage = (int) Math.ceil((double)totalCount / pageSize); // 총 페이지 수

        //  현재 페이지 범위 보정
        if(page < 1) page = 1;
        else if(page > totalPage) page = totalPage;

        // 오프셋(시작 위치) 계산
        int offset = (page - 1) * pageSize;

        // 해당 페이지 목록 조회
        List<PostDto> postList = postDao.getPost(offset, pageSize);

        model.addAttribute("postList", postList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);

        return "board/boardHome";
    }

    @GetMapping("/postWrite")
    public String postWrite() {
        System.out.println("postWriteController");

        return "board/postWrite";
    }

    @PostMapping("/write")
    public String write(@RequestParam("title") String title, @RequestParam("content") String content, HttpSession session) {
        System.out.println("writeController");
        String sessionId = (String) session.getAttribute("session_user_id");

        if(title.isEmpty() || content.isEmpty()){
            return "redirect:/postWrite";
        }

        // 현재 날짜 구하기 및 포맷 적용
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(dateFormatter);

        // 현재 시간 구하기 및 포맷 적용
        LocalTime nowTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = nowTime.format(timeFormatter);

        String writeDate = date + " " + time;
        System.out.println("writeDate: " + writeDate);

        postDao.insertPost(sessionId, title, writeDate, content);

        return "redirect:/boardHome";
    }

    @GetMapping("/postDetail")
    public String postDetail(@RequestParam("n") int postNumber, Model model, HttpSession session) {
        System.out.println("postDetailController");
        String sessionId = (String) session.getAttribute("session_user_id");

        postDao.updatePostCnt(postNumber);
        PostDto postDto = postDao.getPostInfo(postNumber);

        model.addAttribute("sessionId", sessionId);
        model.addAttribute("postInfo", postDto);

        return "board/postDetail";
    }

    @GetMapping("/postEdit")
    public String postEdit(@RequestParam("n") int postNumber, Model model) {
        System.out.println("postEditController");

        PostDto postDto = postDao.getPostTitleContent(postNumber);

        model.addAttribute("post", postDto);
        model.addAttribute("postNumber", postNumber);

        return "board/postEdit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("n") int postNumber, @RequestParam("title") String title, @RequestParam("content") String content) {
        System.out.println("editController");

        postDao.editPost(postNumber, title, content);
        System.out.println("DB 업데이트 성공");

        return "redirect:/boardHome";
    }

    @PostMapping("/postDelete")
    @ResponseBody
    public Map<String, String> postDelete(@RequestParam("postNumber") int postNumber) {
        System.out.println("postDeleteController");

        postDao.deletePost(postNumber);
        System.out.println("DB 삭제 완료");

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");

        return response;
    }
}
