<%-- 
    Document   : page2
    Created on : Sep 29, 2010, 8:21:51 PM
    Author     : instlogin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Demo :: Page 2</title>
    </head>
    <!--
        Here the EL expression automatically retrieves the value of the
        session object key. If this were a request object key it would work 
        the same way. Same is true for an application scope object. But what 
        happens when two or more scope objects have the same key -- say a 
        session scope object and a request scope object. Well, then you would
        have to specify the one you want. Here we could change 'color' to
        'sessionScope.color'
    -->
    <body bgcolor="${color}">
        <h1>Session Demo -- Page color comes from session</h1>
        <p><a href="BookController?action=list">Back to Home</a></p>
        <p><a href="BookController?action=session&end&list">End Session</a></p>
        <h3 style="color: ${fontColor};">For comparison, this font color comes from application scope</h3>
    </body>
</html>
