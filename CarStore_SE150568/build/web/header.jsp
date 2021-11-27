<%-- 
    Document   : header
    Created on : Aug 11, 2021, 5:05:20 PM
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
        <div>
            <header class="header_section">
                <div class="header_top">
                    <div class="container-fluid">
                        <div class="top_nav_container">
                            <div class="contact_nav">
                                <a href="">
                                    <i class="" aria-hidden="true"></i>
                                    <span>

                                    </span>
                                </a>
                                <a href="">
                                    <i class="" aria-hidden="true"></i>
                                    <span>

                                    </span>
                                </a>
                            </div>

                            <div class="user_option_box">
                                <c:if test="${sessionScope.USER!=null}">
                                    <c:if test="${sessionScope.USER.roleID eq 'US'}">
                                        <a href="cartPage.jsp" class="cart-link">
                                            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                            <span>
                                                Cart
                                            </span>
                                        </a>
                                    </c:if>
                                    <a href="#" class="account-link">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                        <span>
                                            HI, ${sessionScope.USER.fullName}
                                        </span>
                                    </a>
                                    <a href="DispatchServlet?btAction=Logout" class="logout-link">
                                        <span>
                                            Log out
                                        </span>
                                    </a>
                                </c:if>
                                <c:if test="${sessionScope.USER eq null}">
                                    <a href="cartPage.jsp" class="cart-link">
                                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                        <span>
                                            Cart
                                        </span>
                                    </a>
                                    <a href="login.jsp" class="account-link">
                                        <span>
                                            Login
                                        </span>
                                    </a>
                                    <a href="register.jsp" class="account-link">
                                        <span>
                                            Register
                                        </span>
                                    </a>
                                </c:if>

                            </div>
                        </div>

                    </div>
                </div>
                <div class="header_bottom">
                    <div class="container-fluid">
                        <nav class="navbar navbar-expand-lg custom_nav-container ">
                            <a class="navbar-brand" href="homePage.jsp">
                                <span>
                                    CARS STORE
                                </span>
                            </a>

                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class=""> </span>
                            </button>
                            <form class="search_form" action="SearchProductServlet">
                                <input type="text" name="txtSearchValue" class="form-control" placeholder="Search here..." value="${param.txtSearchValue}">
                                <button class="" type="submit">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </button>
                            </form>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav ">
                                    <li class="nav-item">
                                        <a class="nav-link" href="homePage.jsp">Home <span class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="DispatchServlet?btAction=Product">Products</a>
                                    </li>
                                    <c:if test="${sessionScope.USER.roleID eq 'AD'}">
                                        <li class="nav-item">
                                            <a class="nav-link" href="DispatchServlet?btAction=Manage">Manage</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </header>
        </div>
    </body>
</html>
