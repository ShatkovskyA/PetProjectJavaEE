<%--
  Created by IntelliJ IDEA.
  User: Anton Shatkovskiy
  Date: 07.05.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- добавляесм страничку
<%@ include file="header.jsp" %>>
<h1>Списко перелетов:</h1>
<ul>
  <c:forEach var="flight" items="${requestScope.flights}">
    <li>
      <a href="${pageContext.request.contextPage}/tickets?flightId=${flight.id}"></a>
    </li>
  </c:forEach>
</ul>
--%>
<h1>Списко перелетов:</h1>
<ul>
  <c:forEach var="flight" items="${requestScope.flights}">
    <li>
        <%-- Без динамической составляющей app --%>
    <%-- <a href="/tickets?flightId=${flight.id}">${flight.description}</a> --%>
            <%-- c динамической составляющей app --%>
    <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a>
  </li>
</c:forEach>
</ul>
<%--
var printWriter = resp.getWriter()
// генерируем страничку
printWriter.write("<h1>Списко перелетов тут:</h1>");
// ul - означает список
printWriter.write("<ul>");
// возвращаем все перелеты, генерируя элементы списка
flightService.findAll().forEach(flightDto -> {
// li - означает каждое значение списка
printWriter.write("""
<li>
  <a href="/tickets?flightId=%d">%s</a>
</li>
""".formatted(flightDto.getId(), flightDto.getDescription()));
// передаем Id и Description, href="/tickets?flightId=%d - ссылка на доп. сервлет
});
printWriter.write("</ul>");
--%>
</body>
</html>
