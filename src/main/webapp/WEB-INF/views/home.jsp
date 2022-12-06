<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<br/>
<h3>file 업로드하기</h3>
    <form action="/insert" method="post" encType="multipart/form-data">
        파일 : <input type="file" name="dbfile" accept=".dbfile"/><br>
        <input type="submit">
    </form>

<%--<div class="table-area">--%>
<%--    <table align="center" id="listArea">--%>
<%--        <tr>--%>
<%--            <th>아이디</th>--%>
<%--            <th width="100px">비밀번호</th>--%>
<%--            <th width="100px">이름</th>--%>
<%--            <th>레벨</th>--%>
<%--            <th width="100px">상태</th>--%>
<%--            <th width="100px">날짜</th>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${ userList }" var="userList">--%>
<%--            <tr>--%>
<%--                <td><c:out value="${ userList.id }"/></td>--%>
<%--                <td><c:out value="${ userList.pwd }"/></td>--%>
<%--                <td><c:out value="${ userList.name }"/></td>--%>
<%--                <td><c:out value="${ userList.level }"/></td>--%>
<%--                <td><c:out value="${ userList.description }"/></td>--%>
<%--                <td><c:out value="${ userList.reg_date }"/></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</div>--%>
</body>
</html>