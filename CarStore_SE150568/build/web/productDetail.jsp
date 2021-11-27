<%-- 
    Document   : productDetail
    Created on : Aug 15, 2021, 10:57:53 AM
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
        <c:set var="product" value="${requestScope.PRODUCT}"/>
        <title>${product.productName} | Cars store</title>

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
        <c:set var="brand" value="${requestScope.BRAND}"/>
        <c:set var="cate" value="${requestScope.CATEGORY}"/>
        <div class="container" style="min-height: 765px">
            <div class="row mt-4">
                <div class="col-5">
                    <h1>${product.productName}</h1>
                    <div style="font-size: 2rem">${product.price}$</div>
                    <h5>Brand: 
                        <c:forEach items="${brand}" var="dto">
                            <c:if test="${product.brandID eq dto.brandID}">
                                ${dto.brandName}
                            </c:if>
                        </c:forEach>
                    </h5>
                    <c:forEach items="${cate}" var="dto">
                        <c:if test="${product.categoryID eq dto.categoryID}">
                            Category: ${dto.categoryName}
                        </c:if>
                    </c:forEach>
                    <br>
                    Available in stock: ${product.quantity}
                    <br><br>
                    <c:if test="${sessionScope.USER.roleID != 'AD'}">
                        <a href="AddToCartServlet?id=${product.productID}&url=ProductDetailServlet?id=${product.productID}" class="add_btn mt-5">
                            <span>
                                Add To Cart
                            </span>
                        </a>
                    </c:if>
                </div>
                <div class="col-7 mt-5 thumnail">
                    <img src="./images/${product.image}"/>
                </div>
            </div>
            <c:if test="${sessionScope.USER.roleID eq 'AD'}">
                <div class="mt-5 action text-center">
                    <a class="col-4 btn loginbutton" href="LoadProductServlet?id=${product.productID}">Update</a>
                    <a class="col-4 btn btn-danger" href="DispatchServlet?btAction=DeleteProduct&id=${product.productID}">Delete</a>
                </div>
            </c:if>
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
