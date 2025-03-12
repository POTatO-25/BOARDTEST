<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>joinHome</title>
</head>
<body>
<h1>Join</h1>
<form action="/board/join" method="post">
    <input type="text" name="name" placeholder="name"><br>
    <input type="text" name="id" id="id" placeholder="id"><button type="button" onclick="duplicationCheck($('#id').val());">중복 확인</button><br>
    <span id="id-check"></span><br>
    <input type="password" name="pwd" placeholder="password"><br>
    <input type="password" name="pwdCheck" placeholder="password check"><br>
    <input type="email" name="email" placeholder="email"><br>
    <button type="submit">Join</button>
</form>
</body>
<style>
    #id-check { font-size: 10px; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function duplicationCheck(id) {
        // ajax 요청
        $.ajax({
            url: "/board/duplicationCheck",
            type: "POST",
            data: { id: id },

            // 콜백 함수
            success: function(response) {
                if(response.status === "사용 가능") {
                    alert("아이디 사용 가능");
                    $("#id-check").text("사용 가능한 아이디").css("color", "#008000");
                } else {
                    alert("아이디 사용 불가능");
                    $("#id").val("");
                    $("#id-check").text("이미 사용중인 아이디").css("color", "#FF6C6C");
                }
            }
        })
    }
</script>
</html>