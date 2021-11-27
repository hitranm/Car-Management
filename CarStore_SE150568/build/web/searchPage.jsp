<%-- 
    Document   : searchPage
    Created on : Aug 15, 2021, 11:04:50 PM
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

        <title>Cars store | Product</title>


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

        <!-- product section -->

        <section class="product_section layout_padding">
            <div class="container" style="min-height: 630px">
                <div class="heading_container heading_center">
                    <h2>
                        Our Products
                    </h2>
                </div>
                <div class="row">
                    <c:set var="product" value="${requestScope.PRODUCT_LIST}"/>
                    <c:if test="${not empty product}">
                        <c:forEach var="dto" items="${product}">
                            <div class="col-sm-6 col-lg-4">
                                <div class="box">
                                    <div class="img-box">
                                        <img src="images/${dto.image}" alt="">
                                        <a href="" class="add_cart_btn">
                                            <span>
                                                Add To Cart
                                            </span>
                                        </a>
                                    </div>
                                    <div class="detail-box">
                                        <h5>
                                            <a href="ProductDetailServlet?id=${dto.productID}">${dto.productName}</a>
                                        </h5>
                                        <div class="product_info">
                                            <h5>
                                                <span>$</span> ${dto.price}
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty product}">
                        Cannot find such product!
                    </c:if>
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
