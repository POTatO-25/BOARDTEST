package com.tech.boardtest.board.dao;

public class PagingModel {
    // 한 페이지에 표시할 행 개수
    public static final int DEFAULT_ROW_COUNT_PER_PAGE = 10;

    private final int totalRowCount; // 전체 행 개수(총 게시글 개수)
    private final int requestPage; // 요청된 페이지 번호
    private final int rowCountPerPage; // 한 페이지에 표시할 행 개수

    public PagingModel(int totalRowCount, int requestPage) {
        // 전체 행 개수 설정
        this.totalRowCount = totalRowCount;
        // 요청된 페이지 번호가 1보다 작으면 1로 설정
        this.requestPage = requestPage < 1 ? 1 : requestPage;
        // 기본 행 개수 설정
        this.rowCountPerPage = DEFAULT_ROW_COUNT_PER_PAGE;
    }

    // 현재 페이지에서의 시작 인덱스(0부터 시작)
    public int getStartIndex() {
        return (requestPage - 1) * rowCountPerPage;
    }

    // 한 페이지에 표시할 행 개수
    public int getRowCountPerPage() {
        return rowCountPerPage;
    }
}
