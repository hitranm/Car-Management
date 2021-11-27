<%-- 
    Document   : verifyOrder
    Created on : Aug 22, 2021, 10:01:02 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <title>Order Confirmation | Cars store</title>


        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

        <!-- fonts style -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet"> <!-- range slider -->

        <!-- font awesome style -->
        <link href="css/font-awesome.min.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="row mt-5">
                <div class="col-md-6">
                    <img src="./images/istockphoto-1211154399-612x612.jpg" alt="Image" class="img-fluid">
                </div>
                <div class="col-md-6 contents">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="mb-4">
                                <h3>Confirmation code has been sent to your email. Please check it out.</h3>
                            </div>
                            <form action="DispatchServlet" method="post">
                                <div class="form-group first">
                                    <label for="code">Confirmation Code</label>
                                    <input type="text" name="code" value="" required="true" class="form-control" id="email">
                                </div>

                                <c:set var="WRONG_CODE" value="${requestScope.WRONG_CODE}"/>
                                <c:if test="${WRONG_CODE!=null}">
                                    <div class="alert alert-danger" role="alert">
                                        ${requestScope.WRONG_CODE}
                                    </div>
                                </c:if>
                                <input type="hidden" name="txtFullName" value="${param.txtFullName}">
                                <input type="hidden" name="txtPhone" value="${param.txtPhone}">
                                <input type="hidden" name="txtAddress" value="${param.txtAddress}">
                                <input type="hidden" name="totalPay" value="${param.totalPay}">    
                                <input type="submit" class="btn btn-success" value="Confirm" name="btAction" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
