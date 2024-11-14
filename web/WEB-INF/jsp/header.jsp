<%--
  Created by IntelliJ IDEA.
  User: Anton Shatkovskiy
  Date: 26.03.2024
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- добавляем логику показа кнопочки, если пользователь залогинен --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <h1>Header. Заголовок </h1> --%>

<%-- реализация logout --%>
<div>
    <%-- добавляем логику показа кнопочки, если пользователь залогинен --%>
    <c:if test="${not empty sessionScope.user}">
        <%-- тут добавляем logout - это при реализации интернационализации --%>
    <div id="logout">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
        </c:if>
        <%-- реализация интернационализации и кнопок замены языка - пишем доп. сервлет --%>
        <div id="locale">
            <%-- метод POST - т. к. это изм. данных для нашего пользователя --%>
            <form action="${pageContext.request.contextPath}/locale" method="post">
                <%-- добавляем кнопки --%>
                <button type="submit" name="lang" value="ru_RU">RU</button>
                <button type="submit" name="lang" value="en_US">EN</button>
        </div>
        <%-- Тут интернационализация приложения --%>
        <%-- <fmt:setLocale value="ru_RU"/> --%>
        <%-- Тут код с проверкой --%>
        <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>
        <%-- получаем бандл --%>
        <fmt:bundle basename="translations"/>
    </div>