<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginHome</title>
</head>
<style>
    html, body {
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
    }
    a:active, :visited, :link {
        text-decoration: none;
        color: black;
    }
    a:hover {
        color: lightslategray;
    }
    #loginBtn {
        background-color: #69AAFF;
        color: whitesmoke;
        border: none;
        padding: 5px;
        width: 182px;
        height: 30px;
        font-size: 18px;
        font-weight: bold;
        border-radius: 7px;
    }
    .home {
        width: 100%;
        height: 100%;
        display: flex;       /* flex layout 사용 */
        justify-content: center;  /* 가로 중앙 */
        align-items: center;
    }
    .logo {
        text-align: center;
    }
    .inputField {
        margin-bottom: 10px;
    }
    input {
        margin-bottom: 10px;
        width: 170px;
        border: #F0F0F0;
        background-color: #F0F0F0;
        color: black;
        height: 30px;
        padding-left: 10px;
        border-radius: 7px;
    }
    .content {
        display: flow;
        justify-content: center;
        margin-bottom: 100px;
    }
    .buttonArea {
        display: flow;
    }
    .joinArea {
        margin-top: 5px;
        display: flex;
        justify-content: right;
    }
</style>
<body>
<div class="home">
    <div class="content">
        <div class="logo">
            <h1>Login</h1>
        </div>
        <div class="inputField">
            <input type="text" name="id" placeholder="id"><br>
            <input type="password" name="pwd" placeholder="password"><br>
        </div>
        <div class="buttonArea">
            <button id="loginBtn" type="button">Login</button><br>
            <div class="joinArea">
                <a href="/board/joinHome">Join</a>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $("#loginBtn").on('click', function () {
        const idVal = $('input[name="id"]').val();
        const pwdVal = $('input[name="pwd"]').val();

        $.ajax({
            url: "/board/login",
            type: "POST",
            data: {id: idVal, pwd: pwdVal},

            // 콜백 함수
            success: function(response) {
                if(response.status === "noIdOrPwd") {
                    alert("아이디와 비밀번호를 모두 입력하세요.");
                    $('input[name="id"]').focus();
                    $('input[name="pwd"]').focus();
                } else if(response.status === "pwdError") {
                    alert("비밀번호가 일치하지 않습니다.");
                    $('input[name="pwd"]').val("");
                } else if (response.status === "userError") {
                    alert("존재하지 않는 사용자입니다.");
                    $('input[name="id"]').val("");
                    $('input[name="pwd"]').val("");
                } else if(response.status === "success") {
                    window.location.href = '/board/boardHome';
                }
            }
        })
    });
</script>
</html>