<%@ page import="com.vashajava.http.dto.TicketDto" %>
<%@ page import="com.vashajava.http.service.TicketService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: Anton Shatkovskiy
  Date: 29.11.2024
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>

<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
--%>

<html>
<head>
    <title>Title</title>
</head>

<body>
<%-- <h1>"Hello World!"</h1>> --%>
<%-- Копируем из сервлета ТикетсСервлет этот отрывок кода --%>

<%-- Добавляем, что коллекция не пустая --%>
<c:if test="${not empty requestScope.tickets}">
<h1>Купленные билеты</h1>
<ul>

    <c:forEach var="ticket" items="${requestScope.tickets}">
        <%-- <li>${ticket.seatNo}</li> --%>
        <li>${n:toLowerCase(ticket.seatNo)}</li>
    </c:forEach>

    <%-- Тут код к нечалу изучения jstl
    <%
        // это есть кусочек кода, из сервлета
        // обращаемся через рекуест с параметром флайАйди
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        // обращаемся к ТикетСЕрвис и кладем в лист
        List<TicketDto> tickets = TicketService.getInstance().findAllByFindId(flightId);
        for(TicketDto ticket : tickets) {
            out.write(String.format("<li>%s</li>", ticket.getSeatNo()));
        }

        // перечисляем все значения списка, с преобразованием ticketa в строку
    //    ticketService.findAllByFindId(flightId).forEach(ticketDTO -> printWriter.write("""
    //            <li>
    //            %s
    //            </li>
    //
    //            """.formatted(ticketDTO.getSeatNo())));

    %>
    --%>
</ul>
</c:if>
</body>
</html>
