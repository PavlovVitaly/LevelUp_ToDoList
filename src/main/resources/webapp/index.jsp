<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="usersBean" type="com.levelup.web.UsersBean" scope="application"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello!</h1>

<p>${header['User-Agent']}</p>

<div>
    <table>
        <tbody>

        <c:forEach var="user" items="${usersBean.users}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.secondName}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</div>
</body>
</html>