<%-- 
    Document   : listAuthors
    Created on : Sep 21, 2015, 9:36:05 PM
    Author     : jlombardo
    Purpose    : display list of author records and (in the future) provide
                 a way to add/edit/delete records
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
    </head>
    <body bgcolor="${color}">
        <h1>Session Demo</h1>
        <form method="POST" action="BookController?action=session">
            Enter page background color (per user): <input name="color" value="" /> <br>
            Enter font color (per application): <input name="fontColor" value="" /> <br>
            <input name="submit" value="Submit" type="submit">
        </form>
        <p><a href="page2.jsp">Click here</a> to go to Page 2</p>
        <p><a href="testsession.jsp">Click here</a> for Session Status</p>
        <h3 style='color: ${fontColor};'>For comparison, this font color comes from application scope</h3>
        
        
        <h1>Book List</h1>
        <div><a href="BookController?action=addButton">Add a Book</a></div>
        <div><a href="BookController?action=redirect">Redirect Test</a></div>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Book Title</th>
                <th align="left" class="tableHead">Author</th>
                <th align="left" class="tableHead">Page Count</th>
                <th align="right" class="tableHead">Date Added</th>
            </tr>
        <c:forEach var="b" items="${books}" varStatus="rowCount">
            <c:choose>
                <c:when test="${rowCount.count % 2 == 0}">
                    <tr style="background-color: white;">
                </c:when>
                <c:otherwise>
                    <tr style="background-color: #ccffff;">
                </c:otherwise>
            </c:choose>
            <td align="left">${b.bookId}</td>
            <td align="left">${b.title}</td>
            <td align="left">${b.author}</td>
            <td align="left">${b.pageCount}</td>
            <td align="right">
                <fmt:formatDate pattern="dd/MM/yyyy" value="${b.publishDate}"></fmt:formatDate>
            </td>
            <td><a href="BookController?action=editDeleteButton&bookId=${b.bookId}&title=${b.title}&author=${b.author}&pageCount=${b.pageCount}&pubDate=${b.publishDate}">Edit or Delete</a></td>
            
        </tr>
        </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
        </c:if>
    </body>
</html>
