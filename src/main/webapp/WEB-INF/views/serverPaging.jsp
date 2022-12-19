<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Server Paging</title>

    <link rel="stylesheet" type="text/css" href="../../resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/home.css">
    <link rel="stylesheet" type="text/css" href="../../resources/dhtmlxSuite/skins/skyblue/dhtmlx.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="../../resources/dhtmlxSuite/codebase/dhtmlx.js" type="text/javascript"></script>

</head>
<body>
<div class="form">
    <div class="assignment-form">

        <h2 style="text-align: center; padding-bottom: 30px">Server Paging</h2>

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

    const userList = ${userList};

    const startPage = $("#startPage");
    const prevPage = $("#prevPage");
    const nextPage = $("#nextPage");
    const maxPage = $("#maxPage");
    let selectCriteria = null;

    mygrid = new dhtmlXGridObject('success_container');
    pagetoolbar = new dhtmlXToolbarObject('pagingArea', 'dhx_skyblue');
    //the path to images required by grid
    mygrid.setImagePath("../../resources/dhtmlxSuite/codebase/imgs/");
    mygrid.setHeader("id,pwd,name,level,description,reg_date");//the headers of columns
    mygrid.setInitWidths("60, 50, 70, 40, 90, 190");          //the widths of columns
    mygrid.setColSorting("str,str,str,str,str,date");          //the sorting types
    mygrid.init();

    // 데이터 조회 PageNo별로 Ajax 요청
    function dataList(pageNo) {

        $.ajax({

            url: "/user/" + pageNo,
            type: "GET",
            contentType: "application/json; charset:UTF-8",

            success: function (data) {

                const jsonData = JSON.parse(data);
                mygrid.clearAll();

                const user = {
                    rows: []
                };

                // 페이지 설정 정보 담겨있는 변수
                selectCriteria = jsonData.selectCriteria;

                for (let idx in jsonData.userList) {

                    user.rows.push({
                        id: idx,
                        data: [jsonData.userList[idx].id, jsonData.userList[idx].pwd, jsonData.userList[idx].name, jsonData.userList[idx].level, jsonData.userList[idx].description, jsonData.userList[idx].reg_date]
                    })
                };

                mygrid.parse(user,"json");
                refreshButtons(selectCriteria);

                console.log(user);
            }
        });
    };

    function refreshButtons(selectCriteria){

        let idx = 0;
        pagetoolbar.clearAll();
        // 시작 페이지 버튼
        pagetoolbar.addButton('startPage', idx++, '<<', null, null);
        // 이전 페이지 버튼
        pagetoolbar.addButton('prevPage', idx++, '<', null, null);
        // 현재 페이지가 1 이면 시작, 이전페이지 강조
        if (selectCriteria.pageNo === 1) {
            pagetoolbar.disableItem('startPage');
            pagetoolbar.disableItem('prevPage');
        };

        // 숫자 버튼 생성
        for (let i = selectCriteria.startPage; i <= selectCriteria.endPage; i++) {
            pagetoolbar.addButton(i, idx++, i, null, null);
            // 현재 페이지 강조 버튼
            if (i === selectCriteria.pageNo) {
                pagetoolbar.disableItem(i);
            }
        }
        // 다음 페이지 버튼 생성
        pagetoolbar.addButton('nextPage', idx++, '>', null, null);
        // 마지막 페이지 버튼 생성
        pagetoolbar.addButton('maxPage', idx++, '>>', null, null);
        // 현재 페이지가 마지막 페이지면 다음, 마지막 페이지 강조
        if (selectCriteria.pageNo === selectCriteria.maxPage) {
            pagetoolbar.disableItem('nextPage');
            pagetoolbar.disableItem('maxPage');
        };

        // Exccel Export 버튼 생성
        pagetoolbar.addButton('Excel Export', idx++, 'Excel Export', null, null);
    };

    // 최초 실행
    $(document).ready(function () {
        dataList(1);
    });

    pagetoolbar.attachEvent("onClick", function(id){

        if(id == 'startPage'){

            // 시작 페이지
            dataList(1);
            refreshButtons();
        } else if(id == 'prevPage'){

            // 전 페이지
            dataList(Math.max(1, selectCriteria.pageNo - 1));
        } else if(id == 'nextPage'){

            // 다음 페이지
            dataList(Math.min(selectCriteria.pageNo + 1, selectCriteria.maxPage));
        } else if(id == 'maxPage'){

            // 마지막 페이지
            dataList(selectCriteria.maxPage);
        } else if(id == 'Excel Export'){

            // Excel download
            mygrid.toExcel('https://dhtmlxgrid.appspot.com/export/excel');
        } else {

            // Number 페이지
            dataList(id);
        }

    });

</script>
</html>