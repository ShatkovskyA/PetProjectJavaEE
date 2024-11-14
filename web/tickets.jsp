<%@ page import="com.vashajava.http.dto.TicketDto" %>
<%@ page import="com.vashajava.http.service.TicketService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Anton Shatkovskiy
  Date: 06.11.2024
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- <h1>"Hello World!"</h1>> --%>
<%-- Копируем из сервлета ТикетсСервлет этот отрывок кода --%>

<h1>Купленные билеты</h1>
<ul>
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
</ul>
</body>
</html>