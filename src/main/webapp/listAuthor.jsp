<%-- 
    Document   : listAuthor
    Created on : Oct 20, 2015, 12:32:15 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List Page</title>
    </head>
    <body>
             <div><a href="AuthorController?action=addButton">Add an Author</a></div>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Name </th>
                <th align="right" class="tableHead">Date Added</th>
              
            </tr>
        <c:forEach var="a" items="${authors}" varStatus="rowCount">
            <c:choose>
                <c:when test="${rowCount.count % 2 == 0}">
                    <tr style="background-color: white;">
                </c:when>
                <c:otherwise>
                    <tr style="background-color: #ccffff;">
                </c:otherwise>
            </c:choose>
            <td align="left">${a.authorId}</td>
            <td align="left">${a.name}</td>
            <td align="right">
                <fmt:formatDate pattern="dd/MM/yyyy" value="${a.dateAdded}"></fmt:formatDate>
            </td>
             
           <!-- <td><a href="AuthorController?action=editDeleteButton&authorId=${a.authorId}&title=${a.name}&addedDate=${a.dateAdded}">Edit or Delete</a></td> -->
            
        </tr>
        </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
        </c:if>
    </body>
</html>
