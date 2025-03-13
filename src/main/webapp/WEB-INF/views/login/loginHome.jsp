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
<button type="submit">Login</button>
    <a href="/board/joinHome">Join</a>
</form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    ${loginError}
</script>
</html>