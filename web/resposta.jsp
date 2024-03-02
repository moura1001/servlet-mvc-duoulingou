
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Duoulingou</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty erro}">
                <h1>A tradução da palavra ${param.palavra} em inglês é ${traducao}</h1>
            </c:when>
            <c:otherwise>
                <h1>500 status code: ${erro}</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
