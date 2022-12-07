<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<style>

    th{
        width: 100px;
        text-align: center;
    }

    tr{
        width: 100px;
        text-align: center;
    }
</style>
<body>
<br/>
<h3>file 업로드하기</h3>
    <form action="/insert" method="post" encType="multipart/form-data" onsubmit="return _onSubmit()">
        파일 : <input type="file" name="dbfile" id="dbfile" accept=".dbfile" onchange="checkFile(this)"/><br>
        <input type="submit">
    </form>

<button id="json">정보 조회하기</button>

<div class="table-area">
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

<script type="text/javascript">

    // userList ajax 서버 요청
    $("#json").click(function(){

        $.ajax({

            url: "/user",
            type : "GET",
            contentType : "application/json; charset:UTF-8",

            success : function(data){

                let userList = JSON.parse(data);
                console.log(userList);

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
    function _onSubmit(){

        if($("#dbfile").val() == "" || $("#dbfile").val() == null){
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
        if(!/\.(dbflie)$/i.test(file[1].name)) alert('dbflie 파일만 선택해 주세요.\n\n 현재 파일 : ' + file[1].name);

        // 체크를 통과했다면 종료.
        else return;

        // 오류 시 파일 초기화
        f.outerHTML = f.outerHTML;
    }

</script>
</body>
</html>