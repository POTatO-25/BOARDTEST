<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>postWrite</title>
</head>
<body>
<h1>Write</h1>
<form action="/board/write" method="post">
    title <input type="text" id="title" name="title"><br>
    content <input type="text" id="content" name="content"><br>
    <button id="writeBtn" type="submit">write</button>
</form>
</body>
<script>
    /* 제목이나 내용을 비운 상태로 write 버튼 눌렀을 경우 포커스 처리 */
    $("#writeBtn").on('click', function () {
        const titleInput = $('input[name="title"]').val();
        const contentInput = $('input[name="content"]').val();

        if (titleInput === null || contentInput === null) {
            $('input[name="title"]').focus();
            $('input[name="content"]').focus();
            alert("Hellllll");
        }
    });
</script>
</html>