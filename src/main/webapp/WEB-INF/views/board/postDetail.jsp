<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>postDetail</title>
</head>
<style>

</style>
<body>
<h1>Detail</h1>
<table>
    <tr>
        <td>Title ${postInfo.post_title}</td>
        <td>Date ${postInfo.post_date}</td>
    </tr>
    <tr>
        <td>Writer ${sessionId}</td>
        <td>Count ${postInfo.post_cnt}</td>
    </tr>
    <tr>
        <td>Content ${postInfo.post_content}</td>
    </tr>
</table>
<button type="button" onclick="location.href='/board/postEdit?n=${postInfo.post_number}'">Edit</button><button type="button">Delete</button><br>
${sessionId}<br>
<input type="text" id="commentInput" placeholder="댓글을 입력하세요."><button type="button">Write</button>
<hr>
</body>
</html>