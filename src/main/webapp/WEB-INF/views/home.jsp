<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<br/>
<h3>file 업로드하기</h3>
<form action="multi-file" method="post" encType="multipart/form-data">
    파일 : <input type="file" name="multiFiles" multiple><br>
    <input type="submit">
</form>
</body>
</html>