package com.tech.boardtest.board.dao;

import com.tech.boardtest.board.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao {
    public List<PostDto> getPost(int offset, int limit);
    public int selectPostCount();
    public void insertPost(String writer, String title, String date, String content);
    public PostDto getPostInfo(int postNumber);
    public void editPost(int postNumber, String title, String content);
    public PostDto getPostTitleContent(int postNumber);
    public void updatePostCnt(int postNumber);
    public void deletePost(int postNumber);
}
