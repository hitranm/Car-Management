<%-- 
    Document   : accessDenied
    Created on : Aug 20, 2021, 11:40:24 PM
    Author     : DELL
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Niramit&display=swap" rel="stylesheet">

    </head>
    <body>

        <jsp:include page="header.jsp"/>
        <div class="noti  px-5">
            <div class="alert alert-danger my-5 text-center col-12" role="alert">
                you do not have access to this page
            </div>
        </div>

    </body>
</html>