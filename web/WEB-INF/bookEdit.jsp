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
    <title>Edit book</title>
</head>
<%
    Book book = (Book) request.getAttribute("books");
%>
<body>
Please input book's data you want to edit
<form action="/book/edit" method="post">
    <input type="hidden" name="id" value="<%=book.getId()%>">
    <input type="text" name="title" value="<%=book.getTitle()%>"><br>
    <select name="authorId">
            <%   List<Author> authors = (List<Author>) request.getAttribute("authors");
        for (Author author : authors) {%>
        <option value="<%=author.getId()%>"><%=author.getName() + " " + author.getSurname()%>
        </option>
            <%}%>
        <input type="number" name="price" value="<%=book.getPrice()%>"><br>
        <input type="number" name="count" value="<%=book.getCount()%>"><br>
        <input type="text" name="genre" value="<%=book.getGenre()%>"><br>
        <input type="text" name="description" value="<%=book.getDescription()%>" size="23%"><br>
        <input type="date" name="addedDate" value="<%=book.getAddedDate()%>"><br>
        <input type="submit" value="edit">

</form>
</body>
</html>
