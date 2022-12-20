package com.furence.assignment.common;

public class Pagenation {

    public SelectCriteria getSelectCriteria(int pageNo, int totalCount) {

        int maxPage;            // 전체 페이지에서 가장 마지막 페이지
        int startPage;            // 한번에 표시될 페이지 버튼의 시작할 페이지
        int endPage;            // 한번에 표시될 페이지 버튼의 끝나는 페이지
        int startRow;           // 행 시작 수
        int endRow;             // 마지막 행 수
        int limit = 10;         // 한 페이지당 조회 개수
        int buttonAmount = 5;   // 버튼 개수

        // 총 페이지 수 계산
        maxPage = (int) Math.ceil((double) totalCount / limit);

        // 현재 페이지에 보여줄 시작 페이지 수
        startPage = (int) (Math.ceil((double) pageNo / buttonAmount) - 1) * buttonAmount + 1;

        // 목록 아래쪽에 보여질 마지막 페이지 수
        endPage = startPage + buttonAmount - 1;

        // max 페이지가 더 작은 경우 endPage 가 max페이지.
        if (maxPage < endPage) {
            endPage = maxPage;
        }

        // 행이 아무 것도 존재하지 않으면 startPage를 보여줌
        if (maxPage == 0 && endPage == 0) {
            maxPage = startPage;
            endPage = startPage;
        }

        // 조회할 시작 번호와 마지막 행 번호 계산.
        startRow = (pageNo - 1) * limit + 1;
        endRow = startRow + limit - 1;

        SelectCriteria selectCriteria = SelectCriteria.builder()
                .pageNo(pageNo)
                .totalCount(totalCount)
                .limit(limit)
                .buttonAmount(buttonAmount)
                .maxPage(maxPage)
                .startPage(startPage)
                .endPage(endPage)
                .startRow(startRow)
                .endRow(endRow)
                .build();

        return selectCriteria;
    }

}
