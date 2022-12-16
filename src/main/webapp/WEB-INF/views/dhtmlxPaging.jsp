<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>DHTMLX Paging</title>

    <link rel="stylesheet" type="text/css" href="../../resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/home.css">
    <link rel="stylesheet" type="text/css" href="../../resources/dhtmlxSuite/codebase/dhtmlx.css">
    <link rel="stylesheet" type="text/css" href="../../resources/dhtmlxSuite/skins/skyblue/dhtmlx.css">

    <script src="../../resources/dhtmlxSuite/codebase/dhtmlx.js" type="text/javascript"></script>

</head>
<body>
<div class="form">
    <div class="assignment-form">

        <h2 style="text-align: center; padding-bottom: 30px">DHTMLX Paging</h2>

        <div id="gridbox"></div>
        <div id="pagingArea"></div>

        <button style="width: 530px;padding: 10px 0 10px 440px" onclick="mygrid.toExcel('https://dhtmlxgrid.appspot.com/export/excel');">
            <img src="../../resources/images/excelImg.png"
                 style="width: 40px; display: inline-block; float: right;">Excel Export</button>
        <button class="btn-back" onclick="location.href='/'">뒤로가기</button>
    </div>
</div>
</body>

<script type="text/javascript">

    const userList = ${userList};

    mygrid = new dhtmlXGridObject('gridbox');
    //the path to images required by grid
    mygrid.setImagePath("../../resources/dhtmlxSuite/codebase/imgs/");
    mygrid.setHeader("id,pwd,name,level,description,reg_date");//the headers of columns
    mygrid.setInitWidths("60, 50, 70, 40, 90, 190");          //the widths of columns
    mygrid.setColSorting("str,str,str,str,str,date");          //the sorting types
    mygrid.enablePaging(true, 10, 5, "pagingArea", true, "recinfoArea");
    mygrid.setPagingSkin("toolbar", "dhx_skyblue");
    mygrid.init();      //finishes initialization and renders the grid on the page

    console.log(userList);

    const user = {
        rows: []
    };

    for (let idx in userList) {

        user.rows.push({
            id: idx,
            data: [userList[idx].id, userList[idx].pwd, userList[idx].name, userList[idx].level, userList[idx].description, userList[idx].reg_date]
        })
    };

    mygrid.parse(user, "json");

</script>
</html>