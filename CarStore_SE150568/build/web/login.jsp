<%-- 
    Document   : login
    Created on : Aug 11, 2021, 5:01:04 PM
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

        <title>Login</title>


        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
            <div class="mt-4">
                <h1 class="text-center">LOGIN</h1>
            </div>
            <c:if test="${requestScope.DID_LOGIN!=null}">
                <div class="alert alert-danger text-center" role="alert">
                    ${requestScope.DID_LOGIN}
                </div>
            </c:if>
            <div class="row">
                <div class="col-6 mt-4">
                    <form action="DispatchServlet" method="post">
                        <div class="form-group col-12 first">
                            <label for="userid">User ID</label>
                            <input type="text" class="form-control" name="userid" value="${param.userid}" required/>
                        </div>
                        <div class="form-group col-12 last mb-4">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" value="" required/>
                        </div>
                        <c:if test="${requestScope.ERROR!=null}">
                            <div class="alert alert-danger" role="alert">
                                ${requestScope.ERROR}
                            </div>
                        </c:if>
                        <div class="d-flex mb-4 align-items-center">
                            <a href="register.jsp" style="font-size: 16px">Register</a>
                        </div>
                        <div class="text-center "> 
                            <button type="submit" name="btAction" value="Login" class="btn loginbutton">Login</button>
                            <h5>or</h5>
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/CarStore_SE150568/LoginGoogleServlet&response_type=code&client_id=412192120002-fhsalu92rol4m4kh33ef74ucrldi35re.apps.googleusercontent.com&approval_prompt=force" class="btn btn-danger">Login with Google</a>
                        </div>                              
                    </form>

                </div>
                <div class="col-6">
                    <img src="images/istockphoto-1211154399-612x612.jpg"/>
                </div>
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
