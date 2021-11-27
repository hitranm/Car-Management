<%-- 
    Document   : homePage
    Created on : Aug 11, 2021, 1:23:56 AM
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

        <title>Cars store</title>


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

        <div class="hero_area">
            <jsp:include page="header.jsp"/>
            <jsp:useBean id="product" class="web.models.tblProduct.ProductDAO" scope="request"/>
            <c:set var="list" value="${product.get3Product()}"/>
            <!-- slider section -->
            <section class="slider_section ">
                <div id="customCarousel1" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="container ">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="detail-box">
                                            <h1>
                                                Welcome to Cars Store
                                            </h1>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="img-box">
                                            <img src="images/maserati-quattroporte-2020.png" alt="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty list}">
                            <c:forEach var="car" items="${list}">
                                <div class="carousel-item">
                                    <div class="container ">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="detail-box">
                                                    <h1>
                                                        ${car.productName}
                                                    </h1>
                                                    <a href="ProductDetailServlet?id=${car.productID}">
                                                        Detail
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="img-box">
                                                    <img src="images/${car.image}" alt="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="carousel_btn_box">
                        <a class="carousel-control-prev" href="#customCarousel1" role="button" data-slide="prev">
                            <i class="fa fa-angle-left" aria-hidden="true"></i>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#customCarousel1" role="button" data-slide="next">
                            <i class="fa fa-angle-right" aria-hidden="true"></i>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </section>
            <!-- end slider section -->
        </div>


        <!-- product section -->

        <section class="product_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2>
                        Our Products
                    </h2>
                </div>
                <div class="row">
                    <c:forEach var="car" items="${list}">
                        <div class="col-sm-6 col-lg-4">
                            <div class="box">
                                <div class="img-box">
                                    <img src="images/${car.image}" alt="">
                                    <c:if test="${sessionScope.USER.roleID != 'AD'}">
                                        <a href="AddToCartServlet?id=${car.productID}&url=homePage.jsp" class="add_cart_btn">
                                            <span>
                                                Add To Cart
                                            </span>
                                        </a>
                                    </c:if>
                                </div>
                                <div class="detail-box">
                                    <h5>
                                        <a href="ProductDetailServlet?id=${car.productID}">${car.productName}</a> 
                                    </h5>
                                    <div class="product_info">
                                        <h5>
                                            <span>$</span> ${car.price}
                                        </h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="btn_box">
                    <a href="DispatchServlet?btAction=Product" class="view_more-link">
                        View More
                    </a>
                </div>
            </div>
        </section>

        <!-- end product section -->



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

        <!-- jQery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>


    </body>

</html>
