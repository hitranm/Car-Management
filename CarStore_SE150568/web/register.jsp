<%-- 
    Document   : register
    Created on : Aug 11, 2021, 8:03:17 PM
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

        <title>Register</title>


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
        <div class="container" style="min-height: 770px">
            <div class="col-12">
                <div class="mt-4">
                    <h3 class="text-center">REGISTER</h3>
                </div>
                <form action="DispatchServlet" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="userID">User ID</label>

                            <input type="text" class="form-control" id="citizenID" name="userid" aria-describedby="citizenIDHelp" value="${param.userid}" required="true">
                            <c:if test="${not empty requestScope.ERROR.userIdError}">
                                <div class="alert alert-danger" role="alert">
                                    ${requestScope.ERROR.userIdError}
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="fullName">Full name</label>
                            <input type="text" class="form-control" id="fullName" value="${param.fullName}" name="fullName" required="true">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" aria-describedby="passwordHelp" name="password" required="true">
                                <small id="passwordHelp" class="form-text text-muted">Password must have at least 6 characters</small>
                            </div>
                            <c:if test="${not empty requestScope.ERROR.passwordError}">
                                <div class="alert alert-danger" role="alert">
                                    ${requestScope.ERROR.passwordError}
                                </div>
                            </c:if>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label for="cpassword">Confirm password</label>
                                <input type="password" class="form-control" id="cpassword" name="cpassword" required="true">
                            </div>

                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="${param.email}" required="true">

                            <c:if test="${not empty requestScope.ERROR.emailError}">
                                <div class="alert alert-danger" role="alert">
                                    ${requestScope.ERROR.emailError}
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="phoneNumber">Phone number</label>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNum" value="${param.phoneNum}" required="true">

                            <c:if test="${not empty requestScope.ERROR.phoneError}">
                                <div class="alert alert-danger" role="alert">
                                    ${requestScope.ERROR.phoneError}
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-12">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" id="address" name="address" value="${param.address}">
                        </div>
                    </div>
                    <input type="hidden" name="roleID" value="US">
                    <div class="text-center">
                        <button type="submit" name="btAction" value="Register" class="btn loginbutton">Register</button>
                    </div>                          
                </form>
            </div>
        </div>        
        <!-- footer section -->
        <footer class="footer_section">
            <div class="container">
                <p>
                    &copy; <span id="displayYear"></span> All Rights Reserved By
                    TranNTB - SE150568
                </p>
            </div>
        </footer>
        <!-- footer section -->
    </body>
</html>
