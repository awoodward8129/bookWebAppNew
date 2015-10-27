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
                 <tr>
                    <td>
                   Author ID <input type="text" name="authorId" value="${authorId}"/>
                    </td>
                </tr>
                
                <tr>
                    <td>
                  Name <input type="text" name="name" value="${name}"/>
                    </td>
                </tr>
             
                       <tr>
                <td>
                    
            Date Added <input type="text" name="dateAdded" value= "${dateAdded}"/>
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

