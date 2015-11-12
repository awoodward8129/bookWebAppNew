<%-- 
    Document   : editDeleteAuthor
    Created on : Oct 27, 2015, 1:36:14 PM
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
        <title>Edit/Delete Author</title>
    </head>
    <body>
          <form method="POST" action="AuthorController?action=delete">
            <table>
                   <c:choose>
                    <c:when test="${not empty author}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">ID</td>
                            <td align="left"><input type="text" value="${author.authorId}" name="authorId" readonly/></td>
                        </tr>         
                    </c:when>
                </c:choose>
                        
                 <c:choose>
                    <c:when test="${not empty author}">
                <tr>
                    <td>
                  Name <input type="text" name="name" value="${author.name}"/>
                    </td>
                </tr>
               
                  </c:when>
                </c:choose>
              

                <c:choose>
                    <c:when test="${not empty author.bookSet}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Books</td>
                            <td>
                                <select id="booksDropDown" name="bookId">
                                    <c:forEach var="book" items="${author.bookSet}" varStatus="rowCount">                                       
                                        <option value="${book.bookId}">${book.title}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Books</td>
                            <td>None</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                
                   
                     <c:choose>
                    <c:when test="${not empty author}">
                       <tr>
                <td>
                    
            Date Added <input type="text" name="dateAdded" value= "${author.dateAdded}"/>
                </td>
            </tr>
                 </c:when>
                </c:choose>
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



