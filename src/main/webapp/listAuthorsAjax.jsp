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
        <title>Author List (Ajax)</title>
        <link href="resources/css/app.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="authorList">
        <h1>Author List (Ajax)</h1>
        <p style="width: 50%;">Note that only the table rows, after the header, are updated. There 
            is no complete page refresh because we're using Ajax to get the 
            data in JSON format from the server and then use client-side 
            JavaScript to take that data and append rows to the table.
        </p>

        <table id="authorListTable" style="width: 50%;" border="1" cellspacing="0" cellpadding="4">
            <thead>
                <tr style="background-color: black;color:white;">
                    <th align="left" class="tableHead">ID</th>
                    <th align="left" class="tableHead">Author Name</th>
                    <th align="right" class="tableHead">Date Added</th>
                </tr>
            </thead>
            <tbody id="authorTblBody">
                
            </tbody>
        </table>
        
        <table id="addEditAuthor" style="display:none;" width="500" border="1" cellspacing="0" cellpadding="4">
            <tr>
                <td style="background-color: black;color:white;" align="left">ID</td>
                <td align="left"><input type="text" value="" id="authorId" name="authorId" readonly/></td>
            </tr>         
            <tr>
                <td style="background-color: black;color:white;" align="left">Name</td>
                <td align="left"><input type="text" value="" id="authorName" name="authorName" /></td>
            </tr>
            <tr>
                <td style="background-color: black;color:white;" align="left">Date Added</td>
                <td align="left"><input type="text" value="" id="dateAdded" name="dateAdded" readonly /></td>
            </tr>         
        </table>

        <sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">
            Logged in as: <sec:authentication property="principal.username"></sec:authentication> ::
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
        </sec:authorize>   


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
       <!-- <script src="resources/js/app.js" type="text/javascript"></script> -->
       <script>
           (function ($, window, document) {
    $(function () {
        // ==================================================================
        // Private properties
        // ==================================================================

        // normal properties
        var curDate = new Date();
        var authorsBaseUrl = "AuthorController";

        // properties that cache JQuery selectors
        var $document = $(document);
        var $body = $('body');
        var $authorTableBody = $('#authorTblBody');
        

        // ==================================================================
        // Private event handlers and functions
        // ==================================================================

        /*
         * This is a JQuery-specific event that only fires after all HTML 
         * is loaded, except images, and the DOM is ready. You must be careful 
         * to only act on DOM elements from JavaScript AFTER they have been 
         * loaded.
         * 
         * Gets a collection of author objects as a JSON array from the server.
         */
        $document.ready(function () {
            console.log("document ready event fired!");

            // Make sure we only do this on pages with an author list
            if ($body.attr('class') === 'authorList') {
                $.ajax({
                    type: 'GET',
                    url: authorsBaseUrl + "?action=listAjax",
                    success: function (authors) {
                        displayAuthors(authors);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("Could not get authors for this user due to: " + errorThrown.toString());
                    }
                });
            }
        });
                /*
         * Loops over the authors collection returned by the server and 
         * extracts individual author objects and their properties, then 
         * builds table rows and columns using this data. Each row is 
         * dynamically appended to the table body DOM element.
         */
        function displayAuthors(authors) {
            $.each(authors, function (index, author) {
                var row = '<tr class="authorListRow">' +
                        '<td>' + author.authorId + '</td>' +
                        '<td>' + author.authorName + '</td>' +
                        '<td>' + author.dateAdded + '</td>' +
                        '</tr>';
                $authorTableBody.append(row);
            });
        }


        $authorTableBody.on('click', 'tr', function () {
            console.log('row click event fired');
            var authorId = $(this).find("td").contents()[0].data;
            console.log(authorId);
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: authorsBaseUrl + "?action=findByIdAjax",
                dataType: "json",
                data: JSON.stringify({"authorId": authorId}),
                success: function (author) {
                    $('#addEditAuthor').show();
                    $('#authorId').val(author.authorId);
                    $('#authorName').val(author.authorName);
                    $('#dateAdded').val(author.dateAdded);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Could not get author by id due to: " + errorThrown.toString());
                }
            });
        });

        $authorTableBody.on('mouseover', 'tr', function () {
            console.log('row mouseover event fired');
            $(this).toggleClass('hover');
        });

        $authorTableBody.on('mouseout', 'tr', function () {
            console.log('row mouseout event fired');
            $(this).toggleClass('hover');
        });

    });
}(window.jQuery, window, document)); </script>
    </body>
</html>
