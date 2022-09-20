<%@ page import="books.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="books.model.UserRole" %><%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 14.09.2022
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
</head>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    User user = new User();
%>
<body>
<table border="1">
    <tr>
        <th>profile_pic</th>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th hidden>password</th>
        <th>user_role</th>
        <%if(user != null && user.getUserRole() == UserRole.ADMIN) {%>
        <th>action</th>
     <%}%>
    </tr>

    <%for (User user1 : users) {%>

    <tr>
        <td><%
            if (user1.getProfilePic() == null || user1.getProfilePic().length() == 0) {
        %>
            <img src="/profilePic/Screenshot_6.png" width="100"/>
            <%} else {%>
            <img src="/getImage?profilePic=<%=user.getProfilePic()%>" width="100"/>
            <%}%>
        </td>
        <td><%=user1.getId()%>
        </td>
        <td><%=user1.getName()%>
        </td>
        <td><%=user1.getSurname()%>
        </td>
        <td><%=user1.getEmail()%>
        </td>
        <td style="display: none"><%=user1.getPassword()%>
        </td>
        <td><%=user1.getUserRole().name()%>
        </td>
        <%if(user != null && user.getUserRole() == UserRole.ADMIN) {%>
        <td><a href="/users/delete?userId=<%=user.getId()%>">delete</a>
            <a href="/users/edit?userId=<%=user.getId()%>">edit</a>
        </td>
    <%}%>

    </tr>
    <%}%>
</table>
</body>
</html>
