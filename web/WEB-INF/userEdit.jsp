<%@ page import="books.model.User" %><%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 16.09.2022
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<%
    User user = (User) request.getAttribute("user");
%>
<body>
Please input user's data you want to edit
<form action="/users/edit" method="post" enctype="multipart/form-data">

    <input type="hidden" name="userId" value="<%=user.getId()%>">
    <input type="text" name="name" value="<%=user.getName()%>"><br>
    <input type="text" name="surname" value="<%=user.getSurname()%>"><br>
    <input type="email" name="email" value="<%=user.getEmail()%>"><br>
    <input type="password" name="password" value="<%=user.getPassword()%>"><br>
    UserRole:<br>
    <select name="user_role">
        <option value="ADMIN">Admin</option>
        <option value="USER">User</option>
    </select>
    <input type="file" name="profile_pic" value="<%=user.getProfilePic()%>"><br>

    <input type="submit" value="edit">

</form>
</body>
</html>
