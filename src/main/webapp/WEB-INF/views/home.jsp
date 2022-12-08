<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <link rel="stylesheet" type="text/css" href="../../resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/home.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
    <div class="form">
        <div class="assignment-form">

            <h1>1차 과제</h1>

            <form action="/insert" id="file-form" method="post" enctype="multipart/form-data" onsubmit="return onSubmit()">
                <input type="file" name="dbFile" id="dbFile" accept=".dbfile" onchange="checkFile(this)"/><br>
                <button type="submit" id="button-file">전송</button>
            </form>

            <div class="table-count">
                <table align="center">
                    <thead>
                    <tr>
                        <th>성공 건수</th>
                        <th>실패 건수</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="padding: 15px 0px 15px 0px"><c:out value="${ successCount }"/> 건</td>
                        <td style="padding: 15px 0px 15px 0px"><c:out value="${ failCount }"/> 건</td>
                    </tr>
                </table>
            </div>

            <div class="table-userFailInfo">
                <table align="center" id="failArea">
                    <thead>
                    <tr>
                        <th>실패 라인</th>
                        <th>실패 텍스트</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>

            <div class="table-userList">
                <table align="center" id="listArea">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>PWD</th>
                        <th>NAME</th>
                        <th>LEVEL</th>
                        <th width="100px">DESC</th>
                        <th width="100px">REG_DATE</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>

            <button id="json">정보 조회하기</button>
            <button id="back" onclick="location.href='/'">뒤로가기</button>
        </div>
    </div>
<script type="text/javascript">

    const file = '${file}';
    const failCount = '${failCount}';
    const ajaxButton = $("#json");
    const listArea = $("#listArea");
    const tableCount = $(".table-count");
    const tableUserFailInto = $(".table-userFailInfo");
    const tableUserList = $(".table-userList");
    const fileForm = $("#file-form");
    const back = $("#back");

    tableCount.css("display", "none");
    tableUserFailInto.css("display", "none");
    tableUserList.css("display", "none");
    ajaxButton.css("display", "none");
    back.css("display", "none");

    if(file.length > 0 && file !== null){

        if(failCount != 0){

            tableCount.css("display", "block");
            tableUserFailInto.css("display", "block");
            back.css("display", "block");
            fileForm.css("display", "none")

            let $table = $("#failArea tbody");
            $table.html("");

            const userList = file.replace("{", "").replace("}", "").split(", ");

            for(let idx in userList){

                let user = userList[idx].split("=");

                let $tr = $("<tr>");

                let $lineNumber = $("<td>").text(user[0]);
                let $userInfo = $("<td>").text(user[1]);

                $tr.append($lineNumber);
                $tr.append($userInfo);

                $table.append($tr);

            }
        } else {

            fileForm.css("display", "none")
            tableCount.css("display", "block");
            ajaxButton.css("display", "block");
            back.css("display", "block");

        }
    }

    // userList ajax 서버 요청
    $("#json").click(function(){

        $.ajax({

            url: "/user",
            type : "GET",
            contentType : "application/json; charset:UTF-8",

            success : function(data){

                let userList = JSON.parse(data);
                console.log(userList);
                tableUserList.css("display", "block");

                let $table = $("#listArea tbody");
                $table.html("");

                for(let idx in userList){

                    let $tr = $("<tr>");
                    let $id = $("<td>").text(userList[idx].id);
                    let $pwd = $("<td>").text(userList[idx].pwd);
                    let $name = $("<td>").text(userList[idx].name);
                    let $level = $("<td>").text(userList[idx].level);
                    let $description = $("<td>").text(userList[idx].description);
                    let $reg_date = $("<td>").text(userList[idx].reg_date);

                    $tr.append($id);
                    $tr.append($pwd);
                    $tr.append($name);
                    $tr.append($level);
                    $tr.append($description);
                    $tr.append($reg_date);

                    $table.append($tr);

                }
            },

            error : function(){

                alert("userList 조회 오류 발생");
            }
        });
    });

    // 업로드 파일 null 경우 alert
    function onSubmit(){

        if($("#dbFile").val() == "" || $("#dbFile").val() == null){
            alert("파일을 업로드해주세요.");
            return false;
        }

        return true;
    }

    // 파일 확장자 체크
    function checkFile(f){

        // files 로 해당 파일 정보 얻기.
        var file = f.files;

        // 정규식으로 확장자 체크
        if(!/\.(dbfile)$/i.test(file[0].name)) alert('dbfile 확장자만 업로드가 가능합니다.');

        else return;

        // dbfile 확장자가 아니면 업로드 파일 초기화
        f.outerHTML = f.outerHTML;
    }
</script>
</body>
</html>