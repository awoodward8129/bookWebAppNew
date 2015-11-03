<%-- 
    Document   : editDeleteBook
    Created on : Sep 28, 2015, 11:13:55 AM
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
        <title>Edit/Delete Book</title>
    </head>
    <body>
          <form method="POST" action="BookController?action=delete">
            <table>
              
                <c:choose>
                    <c:when test="${not empty book}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">ID</td>
                            <td align="left"><input type="text" value="${book.bookId}" name="bookId" readonly/></td>
                        </tr>         
                    </c:when>
                </c:choose>
                        
                <tr>
                    <td style="background-color: black;color:white;" align="left">Title</td>
                    <td align="left"><input type="text" value="${book.title}" name="title" /></td>
                </tr>
                <tr>
                    <td style="background-color: black;color:white;" align="left">Publish Date</td>
                    <td align="left"><input type="text" value="${book.publishDate}" name="pubDate" /></td>
                </tr>
                   <tr>
                    <td style="background-color: black;color:white;" align="left">Author</td>
                    <td align="left">
                    <select id="authorDropDown" name="authorId">
                    <c:choose>
                        <c:when test="${not empty book.authorId}">
                            <option value="">None</option>
                            <c:forEach var="author" items="${authors}">                                       
                                <option value="${author.authorId}" <c:if test="${book.authorId.authorId == author.authorId}">selected</c:if>>${author.name}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="author" items="${authors}" varStatus="rowCount">                                       
                                <option value="${author.authorId}" <c:if test="${rowCount.count == 1}">selected</c:if>>${author.name}</option>
                            </c:forEach>
                         </c:otherwise>
                    </c:choose>
                    </select>
                </td>
                </tr>
            
            
            <tr>
                <td>
                    <button id="submit" name="submit" value="delete" type="submit" >delete</button>
                </td>
                 <td>
                    <button id="submit" name="submit" value="update" type="submit" >update</button>
                </td>
            </tr>
             </table>
        </form>
    </body>
</html>
