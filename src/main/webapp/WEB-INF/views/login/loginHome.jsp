<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginHome</title>
</head>
<body>
<h1>Login</h1>

<form action="/board/login" method="post">
<input type="text" name="id" placeholder="id"><br>
<input type="password" name="pwd" placeholder="password"><br>
<button id="loginBtn" type="submit">Login</button>
    <a href="/board/joinHome">Join</a>
</form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $("#loginBtn").on('click', function () {
        const idInput = $('input[name="id"]').val();
        const pwdInput = $('input[name="pwd"]').val();

        if (idInput.trim() === '' || pwdInput.trim() === '') {
            $('input[name="id"]').focus();
            $('input[name="pwd"]').focus();
            alert("아이디 또는 비밀번호를 입력하세요.");
        }
    });
</script>
</html>