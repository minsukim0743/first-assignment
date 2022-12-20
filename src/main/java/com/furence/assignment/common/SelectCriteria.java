package com.furence.assignment.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class SelectCriteria {

    private int pageNo;                    // 요청한 페이지 번호
    private int totalCount;                // 전체 게시물 수
    private int limit;                    // 한 페이지에 보여줄 게시물 수
    private int buttonAmount;            // 한 번에 보여줄 페이징 버튼의 갯수
    private int maxPage;                // 가장 마지막 페이지
    private int startPage;                // 시작하는 페이지
    private int endPage;                // 마지막 페이지
    private int startRow;                // 조회해야 하는 행의 시작 수
    private int endRow;                    // 조회해야 하는 행의 마지막 수

}
