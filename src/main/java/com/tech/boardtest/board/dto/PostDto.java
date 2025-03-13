package com.tech.boardtest.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int post_number;
    private String post_writer;
    private String post_title;
    private String post_date;
    private String post_content;
    private int post_cnt;
}