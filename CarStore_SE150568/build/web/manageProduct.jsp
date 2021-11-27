<%-- 
    Document   : manageProduct
    Created on : Aug 20, 2021, 12:50:11 AM
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

        <title>Management</title>

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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:if test="${empty sessionScope.USER}">
            <c:set var="DID_LOGIN" scope="request" value="You need to login to do this action"/>
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${sessionScope.USER.roleID != 'AD'}">
            <c:redirect url="accessDenied.jsp"/>
        </c:if>
        <div class="container" style="min-height: 740px">
            <div class="body-top mt-5 mb-4 text-center">
                <h1>PRODUCT MANAGEMENT</h1>
            </div>
            <a href="addProduct.jsp" class="btn btn-success text-right mb-3">ADD NEW CAR</a>
            <c:if test="${requestScope.PRODUCT_LIST!=null}">
                <c:if test="${not empty requestScope.PRODUCT_LIST}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">ProductID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Status</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" varStatus="counter" items="${requestScope.PRODUCT_LIST}">                            
                                <tr>
                                    <td>${product.productID}</td>
                                    <td>${product.productName}</td>
                                    <td>${product.price}</td>
                                    <td>${product.quantity}</td>
                                    <td>
                                        <c:if test="${product.status eq true}">
                                            Available
                                        </c:if>
                                        <c:if test="${product.status eq false}">
                                            Deleted
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="LoadProductServlet?id=${product.productID}">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="DispatchServlet?btAction=DeleteProduct&id=${product.productID}">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
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
