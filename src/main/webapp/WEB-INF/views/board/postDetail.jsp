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
<button type="button" onclick="location.href='/board/boardHome'">Home</button><button type="button" onclick="location.href='/board/postEdit?n=${postInfo.post_number}'">Edit</button><button type="button" id="deleteBtn">Delete</button><br>
${sessionId}<br>
<input type="text" id="commentInput" placeholder="댓글을 입력하세요."><button type="button">Write</button>
<hr>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $("#deleteBtn").on('click', function () {
        let postNumber = ${postInfo.post_number};
        const deleteAnswer = confirm("정말로 삭제하시겠습니까?");

        if (deleteAnswer) {
            // ajax 요청
            $.ajax({
                url: '/board/postDelete',
                method: 'POST',
                data: { postNumber: postNumber },
                // 콜백 함수
                success: function (response) {
                    if(response.status === "success") {
                        window.location.href = '/board/boardHome';
                    }
                },
                error: function(error) {
                    console.log(error)
                }
            });
        }
    })
</script>
</html>