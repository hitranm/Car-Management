<%-- 
    Document   : updateProduct
    Created on : Aug 16, 2021, 12:09:45 AM
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
        <c:if test="${empty sessionScope.USER}">
            <c:set var="DID_LOGIN" scope="request" value="You need to login to do this action"/>
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${sessionScope.USER.roleID != 'AD'}">
            <c:redirect url="accessDenied.jsp"/>
        </c:if>
        <c:set var="err" value="${requestScope.ERROR}"/>
        <c:set var="brand" value="${requestScope.BRAND}"/>
        <c:set var="cate" value="${requestScope.CATEGORY}"/>

        <div class="container">
            <h1 class="mt-4 text-center">UPDATE</h1>
            <form action="DispatchServlet" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="txtProductID" value="${product.productID}" />
                <div class="form-group">
                    <label for="product">Car Name: </label>
                    <input class="form-control" type="text" name="txtProduct" value="${product.productName}" />
                    <c:if test="${not empty err.productNameErr}">
                        <small class="text-danger">${err.productNameErr}</small>
                    </c:if>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="brand">Brand</label>
                        <select class="form-control" name="brand" id="brand">
                            <c:forEach items="${brand}" var="dto">
                                <c:if test="${dto.brandID eq product.brandID}">
                                    <option value="${dto.brandID}" selected>${dto.brandName}</option>
                                </c:if>
                                <c:if test="${dto.brandID != product.brandID}">
                                    <option value="${dto.brandID}">${dto.brandName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="price">Price</label>
                        <input class="form-control" type="text" name="txtPrice" value="${product.price}" id="title"/>
                        <c:if test="${not empty err.priceErr}">
                            <small class="text-danger">${err.priceErr}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="category">Category</label>
                        <select class="form-control" name="category" id="category">
                            <c:forEach items="${cate}" var="dto">
                                <c:if test="${dto.categoryID eq product.categoryID}">
                                    <option value="${dto.categoryID}" selected>${dto.categoryName}</option>
                                </c:if>
                                <c:if test="${dto.categoryID != product.categoryID}">
                                    <option value="${dto.categoryID}">${dto.categoryName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="quantity">Quantity</label>
                        <input class="form-control" type="text" name="txtQuantity" value="${product.quantity}" id="title"/>
                        <c:if test="${not empty err.quantityErr}">
                            <small class="text-danger">${err.quantityErr}</small>
                        </c:if>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="status">Status</label>
                        <select class="form-control" name="status" id="status">
                            <option value="true">Available</option>
                            <option value="false">Deleted</option>
                        </select>
                    </div>
                </div>    
                <div class="form-group">
                    <label for="image">Picture</label>
                    <input type="file" class="form-control" name="image">
                    <small id="imgHelp" class="form-text text-muted">
                        The system automatically keeps old photo if not upload new one
                    </small>
                </div>
                <div class="text-center">                
                    <button class="btn loginbutton col-6 col-md-3" type="submit" name="btAction" value="UpdateProduct" >Update</button>
                    <a class="btn btn-secondary col-6 col-md-3" onclick="return cancelConfirm()" href="ProductDetailServlet?id=${product.productID}">Cancel</a>
                </div>
            </form>
        </div>
        <script>
            function cancelConfirm() {
                var r = confirm("Change will not be saved. Are you sure to cancel?");
                return r;
            }
        </script>

    </body>
</html>
