<%-- 
    Document   : testsession
    Created on : Oct 8, 2010, 9:49:47 AM
    Author     : jlombardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Demo :: Status</title>
    </head>
    <body bgcolor="${color}">
        <h1>Testing if session is  null</h1>
        <%
            HttpSession s = request.getSession();
            if(s == null) {
                out.println("No session yet created");
            } else if(s.isNew()) {
                out.println("Session exits, but is new");
            } else {
                out.println("Session exists, but is old");
            }
        %>
        <br>
        <p><a href="index.jsp">Back to Home</a></p>
        <p><a href="FrontController?action=end&dest=page2">Click here</a> to end session</p>
        <h3 style="color: ${fontColor};">For comparison, this font color comes from application scope</h3>
    </body>
</html>
