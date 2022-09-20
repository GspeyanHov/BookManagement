<%@ page import="java.util.List" %>
<%@ page import="books.model.Book" %>
<%@ page import="books.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 06.09.2022
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Author</title>
</head>
<%
    Author author = (Author) request.getAttribute("authors");
%>
<body>
Please input author's data you want to edit
<form action="/author/edit" method="post">
    <input type="hidden" name="authorId" value="<%=author.getId()%>">
    <input type="text" name="name" value="<%=author.getName()%>"><br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"><br>
    <input type="text" name="email" value="<%=author.getEmail()%>"><br>
    <input type="number" name="age" value="<%=author.getAge()%>"><br>

    <input type="submit" value="edit">

</form>
</body>
</html>
