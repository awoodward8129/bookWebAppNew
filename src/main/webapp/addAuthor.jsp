<%-- 
    Document   : addAuthor
    Created on : Oct 20, 2015, 4:20:05 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form method="POST" action="AuthorController?action=add">
            <table>
                <tr>
                    <td>
                   Name <input type="text" name="name" value=""/>
                    </td>
                </tr>
           
            <tr>
                <td>
            Date Added<input type="text" name="dateAdded" value=""/>
                </td>
            </tr>
                     <tr>
                <td>
                      <button type="submit">submit</button>
                </td>
            </tr>
             </table>
        </form>
    </body>
</html>
