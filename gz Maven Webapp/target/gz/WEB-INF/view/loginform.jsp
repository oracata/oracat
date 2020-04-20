<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="loginform.do" method="post">


    <table align="center">
        <br><br><br><br><br><br><br><br><br><br><br><br><br>
        <tr>
            <td><label>用户名：</label> <input type="text" id="loginname" name="userName"></td>
        </tr>

        <tr>
            <td><label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label><input type="password" id="password" name="userPassword"></td>
        </tr>



        <tr>
            <td><input type="submit" value="登录"></td>
        </tr>

        <tr>
            <td><label name="loginmessage" >${message.loginmessage}</label></td>
        </tr>


    </table>

</form>
</body>
</html>
