<%-- 
    Document   : addProduct
    Created on : Aug 14, 2021, 6:31:51 PM
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

        <title>Add new car</title>


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
        <c:if test="${empty sessionScope.USER}">
            <c:set var="DID_LOGIN" scope="request" value="You need to login to do this action"/>
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${sessionScope.USER.roleID != 'AD'}">
            <c:redirect url="accessDenied.jsp"/>
        </c:if>
        <c:set var="err" value="${requestScope.ADD_PRODUCT_ERROR}"/>
        <jsp:useBean id="brand" class="web.models.tblCarBrand.CarBrandDAO" scope="request"/>
        <jsp:useBean id="cate" class="web.models.tblCategory.CategoryDAO" scope="request"/>
        <div class="container">
            <h1 class="mt-4 text-center">Add new car</h1>
            <form action="DispatchServlet" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product">Car Name: </label>
                    <input class="form-control" type="text" name="txtProduct" value="" />
                    <c:if test="${not empty err.productNameErr}">
                        <small class="text-danger">${err.productNameErr}</small>
                    </c:if>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="brand">Brand</label>
                        <select class="form-control" name="brand" id="brand">
                            <c:forEach items="${brand.getBrandList()}" var="dto">
                                <option value="${dto.brandID}">${dto.brandName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="price">Price</label>
                        <input class="form-control" type="text" name="txtPrice" value="${param.txtTitle}" id="title"/>
                        <c:if test="${not empty err.priceErr}">
                            <small class="text-danger">${err.priceErr}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="category">Category</label>
                        <select class="form-control" name="category" id="category">
                            <c:forEach items="${cate.getCategoryList()}" var="dto">
                                <option value="${dto.categoryID}">${dto.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="quantity">Quantity</label>
                        <input class="form-control" type="text" name="txtQuantity" value="${param.txtTitle}" id="title"/>
                        <c:if test="${not empty err.quantityErr}">
                            <small class="text-danger">${err.quantityErr}</small>
                        </c:if>
                    </div>
                </div>    
                <div class="form-group">
                    <label for="image">Picture</label>
                    <input type="file" class="form-control" name="image">
                    <c:if test="${not empty err.imgErr}">
                        <div class="text-danger">
                            <small>${err.imgErr}</small>
                        </div>
                    </c:if>
                </div>
                <div class="text-center">                
                    <button class="btn loginbutton col-6 col-md-3" type="submit" name="btAction" value="AddProduct" >Add</button>
                    <a class="btn btn-secondary col-6 col-md-3" onclick="return cancelConfirm()" href="DispatchServlet?btAction=Manage">Cancel</a>
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
