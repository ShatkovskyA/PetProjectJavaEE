<%--
  Created by IntelliJ IDEA.
  User: Anton Shatkovskiy
  Date: 27.03.2024
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="birthday">Birthday:
        <input type="date" name="birthday" id="birthday" required>
    </label><br>

    <%-- для загрузки файлов --%>
    <label for="imageId">Image:
        <input type="file" name="image" id="imageId" required>
    </label><br>
    <label for="emailId">Email:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <%-- выпадающая менюшка - в строке такие параметры ?role=USER --%>
    <label for="role">Role:
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
            <%-- <option value="USER">USER</option> --%>
            <%-- <option value="ADMIN">ADMIN</option> --%>
        </select><br>
    </label><br>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}"> ${gender}
    </c:forEach>
    <br>
    <%-- галочки --%>
    <%-- <input type="radio" name="gender" value="MALE"> Male --%>
    <%-- <br> --%>
    <%-- <input type="radio" name="gender" value="FEMALE"> Female --%>
    <%-- <br> --%>
    <button type="submit">Send</button>
    <%-- красивое отображение странички ошибок, если они есть --%>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: orangered">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>
</form>

</body>
</html>
