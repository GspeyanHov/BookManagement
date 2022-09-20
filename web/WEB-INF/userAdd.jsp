<%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 13.09.2022
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>

    <%
        String msg =(String) request.getAttribute("msg");
    %>
    <%
        if(msg != null) {
    %>
    <p style="color: red"><%=msg%>
    </p>
    <%}%>
</head>
<body>
please input user's data
<form action="/users/add" method="post" enctype="multipart/form-data">

    <input type="text" name="name" placeholder="please input name"/><br>
    <input type="text" name="surname" placeholder="please input surname"/><br>
    <input type="email" name="email" placeholder="please input email"/><br>
    <input type="password" name="password" placeholder="please input password"/><br>
    <select name="user_role">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
    </select>
    Profile picture:
    <input type="file" name="profile_pic"><br>
    <input type="submit" value="register">
</form>
</body>
</html>
