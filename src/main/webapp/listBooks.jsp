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
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
    </head>
    <body >
        
        <h1>Book List</h1>
        
         <sec:csrfInput />
            
           <sec:authorize access="hasAnyRole('ROLE_MGR')"> 
        <div><a href="BookController?action=addButton">Add a Book</a></div>
         </sec:authorize>
            
    
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Book Title</th>
                <th align="left" class="tableHead">Page Count</th>
                <th align="right" class="tableHead">Date Added</th>
                   <th align="right" class="tableHead">Author Id</th>
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
            <td align="left">${b.pageCount}</td>
            <td align="right">
                <fmt:formatDate pattern="dd/MM/yyyy" value="${b.publishDate}"></fmt:formatDate>
            </td>
             <td align="left">${b.authorId.name}</td>
                   <sec:authorize access="hasAnyRole('ROLE_MGR')"> 
            <td><a href="BookController?action=editDeleteButton&bookId=${b.bookId}">Edit or Delete</a></td>
            </sec:authorize>
        </tr>
        </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
        </c:if>
    </body>
</html>
