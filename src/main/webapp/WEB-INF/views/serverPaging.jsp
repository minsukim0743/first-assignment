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

    <script src="../../resources/codebase/grid.js" type="text/javascript"></script>
</head>
<body>
<div class="form">
    <div class="assignment-form">

        <h2 style="text-align: center; padding-bottom: 30px">Server Paging</h2>

        <div id="success_container" style="height:452px; max-width:100%"></div>
        <div class="pagingArea" align="center">

            <!-- 맨 앞으로 이동 버튼 -->
            <button id="startPage" class="btn btn-default"><<</button>

            <!-- 이전 페이지 버튼 -->
            <c:if test="${ selectCriteria.pageNo <= 1 }">
                <button disabled class="btn btn-default"> <</button>
            </c:if>
            <c:if test="${ selectCriteria.pageNo > 1 }">
                <button id="prevPage" class="btn btn-default"> <</button>
            </c:if>

            <!-- 숫자 버튼 -->
            <c:forEach var="p" begin="${ selectCriteria.startPage }" end="${ selectCriteria.endPage }" step="1">
                <c:if test="${ selectCriteria.pageNo eq p }">
                    <button disabled class="btn btn-default"><c:out value="${ p }"/></button>
                </c:if>
                <c:if test="${ selectCriteria.pageNo ne p }">
                    <button onclick="pageButtonAction(this.innerText);" style="padding: 20px 8px 0 8px"><c:out
                            value="${ p }"/></button>
                </c:if>
            </c:forEach>

            <!-- 다음 페이지 버튼 -->
            <c:if test="${ selectCriteria.pageNo >= selectCriteria.maxPage }">
                <button disabled class="btn btn-default">></button>
            </c:if>
            <c:if test="${ selectCriteria.pageNo < selectCriteria.maxPage }">
                <button id="nextPage" class="btn btn-default"> ></button>
            </c:if>

            <!-- 마지막 페이지로 이동 버튼 -->
            <button id="maxPage" class="btn btn-default"> >></button>

        </div>

        <button class="btn-back" onclick="location.href='/'">뒤로가기</button>
    </div>
</div>
</body>
<script type="text/javascript">

    const userList = ${userList};
    const link = "/serverPaging";

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
        data: userList,
    });

    if (document.getElementById("startPage")) {
        const startPage = document.getElementById("startPage");
        startPage.onclick = function () {
            location.href = link + "?currentPage=1";
        }
    }

    if (document.getElementById("prevPage")) {
        const prevPage = document.getElementById("prevPage");
        prevPage.onclick = function () {
            location.href = link + "?currentPage=${ selectCriteria.pageNo - 1 }";
        }
    }

    if (document.getElementById("nextPage")) {
        const nextPage = document.getElementById("nextPage");
        nextPage.onclick = function () {
            location.href = link + "?currentPage=${ selectCriteria.pageNo + 1 }";
        }
    }

    if (document.getElementById("maxPage")) {
        const maxPage = document.getElementById("maxPage");
        maxPage.onclick = function () {
            location.href = link + "?currentPage=${ selectCriteria.maxPage }";
        }
    }

    function pageButtonAction(text) {
        location.href = link + "?currentPage=" + text;
    }
</script>
</html>