<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-10-28
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="loginform.do" method="post">
    <table>
        <tr>
            <td><label>用户名：</label> <input type="text" id="loginname" name="loginname"></td>
        </tr>

        <tr>
            <td><label>密码：</label><input type="text" id="password" name="password"></td>
        </tr>

        <tr>
            <td><input type="submit" value="登录"></td>
        </tr>
    </table>

</form>
</body>
</html>
