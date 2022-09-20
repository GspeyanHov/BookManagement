<%@ page import="java.util.List" %>
<%@ page import="books.model.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 05.09.2022
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books page</title>
</head>
<body>
<%
    List<Book> bookList = (List<Book>) request.getAttribute("book");

%>
<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>author_id</th>
        <th>price</th>
        <th>count</th>
        <th>genre</th>
        <th>added_date</th>
        <th>description</th>
        <th>action</th>

    </tr>


    <% for (Book book : bookList) { %>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getAuthor().getId()%>
            <%=book.getAuthor().getName()%>
            <%=book.getAuthor().getSurname()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getCount()%>
        </td>
        <td><%=book.getGenre()%>
        </td>
        <td><%=book.getAddedDate()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td>
            <a href="/book/delete?bookId=<%=book.getId()%>">delete</a> |
            <a href="/book/edit?bookId=<%=book.getId()%>">edit</a>

        </td>

    </tr>
    <% } %>


</table>
</body>
</html>
