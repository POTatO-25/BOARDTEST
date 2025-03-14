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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private PostDao postDao;

    @GetMapping("/boardHome")
    public String boardHome(HttpSession session, Model model) {
        System.out.println("boardHomeController");

        String sessionId = (String) session.getAttribute("session_user_id");
        model.addAttribute("sessionId", sessionId);

        // post 테이블에 존재하는 데이터 가져와서 List<PostDto> 형태로 받아서 model에 저장
        List<PostDto> postList = postDao.getPost();

        model.addAttribute("postList", postList);

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
}
