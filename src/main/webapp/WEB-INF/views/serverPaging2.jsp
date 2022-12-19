<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Server Paging</title>

    <link rel="stylesheet" type="text/css" href="../../resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/home.css">
    <link rel="stylesheet" type="text/css" href="../../resources/codebase/grid.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="../../resources/codebase/grid.js" type="text/javascript"></script>

</head>
<body>
<div class="form">
    <div class="assignment-form">

        <h2 style="text-align: center; padding-bottom: 30px">Server Paging2</h2>

        <div id="success_container" style="height:452px; max-width:100%"></div>

        <div id="pagingArea" align="center">
            <div id="startPage"><<</div>
            <div id="prevPage"> <</div>
            <div id="pageNum"></div>
            <div id="nextPage"> ></div>
            <div id="maxPage"> >></div>
        </div>

        <button class="btn-back" onclick="location.href='/'">뒤로가기</button>

    </div>
</div>
</body>
<script>

    const startPage = $("#startPage");
    const prevPage = $("#prevPage");
    const nextPage = $("#nextPage");
    const maxPage = $("#maxPage");
    let pageNum = $("#pageNum");
    let selectCriteria = null;

    let success_table = new dhx.Grid("success_container", {
        columns: [
            {id: "id", header: [{text: "id"}]},
            {id: "pwd", header: [{text: "pwd"}]},
            {id: "name", header: [{text: "name"}]},
            {id: "level", header: [{text: "level"}]},
            {id: "description", header: [{text: "description"}]},
            {id: "reg_date", header: [{text: "reg_date"}]},
        ],
        headerRowHeight: 50,
        adjust: true,
    });

    // 데이터 조회 PageNo별로 Ajax 요청
    function dataList(pageNo) {

        $.ajax({

            url: "/user/" + pageNo,
            type: "GET",
            contentType: "application/json; charset:UTF-8",

            success: function (data) {

                const jsonData = JSON.parse(data);
                success_table.data.parse(jsonData.userList);
                // 페이지 설정 정보 담겨있는 변수
                selectCriteria = jsonData.selectCriteria;

                let number = "";
                pageNum.html("");

                for (let i = selectCriteria.startPage; i <= selectCriteria.endPage; i++) {

                    if (i == selectCriteria.pageNo) {

                        number = "<div class='bold'>" + i + "</div>";
                    } else {

                        number = "<div>" + i + "</div>";
                    }

                    pageNum.append(number);
                }

            }
        });
    };

    // 최초 실행
    $(document).ready(function () {
        dataList(1);
    });

    // 페이지 Number 이동
    $(document).on('click', '#pageNum > div', function () {

        // 클릭 해당 num text 반환
        let num = $(this).text();

        dataList(num);
    })

    // 시작페이지
    $(startPage).click(function () {

        dataList(1);
    });

    // 전페이지
    $(prevPage).click(function () {

        dataList(Math.max(1, selectCriteria.pageNo - 1));
    });

    // 다음 페이지
    $(nextPage).click(function () {

        dataList(Math.min(selectCriteria.pageNo + 1, selectCriteria.maxPage));
    });

    // 마지막 페이지
    $(maxPage).click(function () {

        dataList(selectCriteria.maxPage);
    })

</script>
</html>