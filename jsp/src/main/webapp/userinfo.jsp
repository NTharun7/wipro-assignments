<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>User Info</title></head>
<body>
<h2>User Information Received:</h2>
<ul>
    <li><b>User Name:</b> ${username}</li>
    <li><b>Password:</b> ${password}</li>
    <li><b>Address:</b> ${address}</li>
    <li><b>Subscribe:</b> ${subscribe}</li>
    <li><b>Gender:</b> ${gender}</li>
    <li><b>Favorite Number:</b> ${favnum}</li>
    <li><b>Frameworks:</b>
        <ul>
            <c:forEach var="fw" items="${frameworks}">
                <li>${fw}</li>
            </c:forEach>
        </ul>
    </li>
</ul>
</body>
</html>
