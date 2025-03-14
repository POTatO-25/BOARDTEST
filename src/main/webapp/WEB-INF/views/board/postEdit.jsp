<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>postEdit</title>
</head>
<style>

</style>
<body>
<h1>Edit</h1>
<form action="/board/edit?n=${postNumber}" method="post">
    <table>
        <tr>
            <td>Title <input type="text" name="title" id="title" value="${post.post_title}"></td>
        </tr>
        <tr>
            <td>Content <input type="text" name="content" id="content" value="${post.post_content}"></td>
        </tr>
    </table>
    <button type="submit">Edit</button>
</form>
</body>
</html>