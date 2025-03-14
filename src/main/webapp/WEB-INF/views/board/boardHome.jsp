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
<c:set var="cp" value="${currentPage}" />
<c:set var="tp" value="${totalPage}" />

<c:if test="${cp > 1}">
    <a href="/board/boardHome?page=${cp - 1}">이전</a>
</c:if>
<c:forEach var="i" begin="1" end="${tp}">
    <c:choose>
        <c:when test="${i == cp}">
            <b>${i}</b> &nbsp;
        </c:when>
        <c:otherwise>
            <a href="/board/boardHome?page=${i}"></a>&nbsp;
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${cp < tp}">
    <a href="/board/boardHome?page=${cp + 1}">다음</a>
</c:if>
</body>
</html>