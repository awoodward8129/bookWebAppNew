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
    <body>
        <h1>Book List</h1>
        <div><a href="BookController?action=addButton">Add a Book</a></div>
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
                <fmt:formatDate pattern="M/d/yyyy" value="${b.publishDate}"></fmt:formatDate>
            </td>
            <td><a href="BookController?action=editDeleteButton&bookId=${b.bookId}&title=${b.title}&author=${b.author}&pageCount=${b.pageCount}&pubDate=${pubDate}">Edit or Delete</a></td>
            
        </tr>
        </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
        </c:if>
    </body>
</html>
