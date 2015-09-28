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
                 <tr>
                    <td>
                   Book ID <input type="text" name="bookId" value="${bookId}"/>
                    </td>
                </tr>
                
                <tr>
                    <td>
                   Title <input type="text" name="title" value="${title}"/>
                    </td>
                </tr>
                <tr>
                    <td>
            Author <input type="text" name="author" value="${author}"/>
                    </td>
            </tr>
            <tr>
                <td>
            Page Count <input type="text" name="pageCount" value="${pageCount}"/>
                </td>
            </tr>
               <tr>
                <td>
            Date Published <input type="text" name="pubDate" value= "${b.pubDate}"/>
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
