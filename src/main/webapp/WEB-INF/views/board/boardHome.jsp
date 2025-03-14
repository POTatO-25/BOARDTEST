<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>boardHome</title>
</head>
<style>
    th, td {
        text-align: center;
        padding-right: 30px;
        padding-bottom: 10px;
    }
</style>
<body>
<c:if test="${sessionId != null}">
    <a href="/board/logout">logout</a>
</c:if>
<h1>Board</h1>
<hr>
<table>
    <tr>
        <th scope="col">No</th>
        <th scope="col">Title</th>
        <th scope="col">Writer</th>
        <th scope="col">Date</th>
        <th scope="col">Cnt</th>
    </tr>
    <c:forEach var="post" items="${postList}">
    <tr onclick="location.href='postDetail?n=${post.post_number}'" style="cursor: pointer;">
        <td>${post.post_number}</td>
        <td> ${post.post_title}</td>
        <td>${post.post_writer}</td>
        <td>${post.post_date}</td>
        <td>${post.post_cnt}</td>
    </tr>
    </c:forEach>
</table>
<c:if test="${sessionId != null}">
    <button type="button" onclick="location.href='/board/postWrite'">write</button>
</c:if>
</body>
</html>