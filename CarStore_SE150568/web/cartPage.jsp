<%-- 
    Document   : cartPage
    Created on : Aug 16, 2021, 10:32:45 PM
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

        <title>CART | Cars store</title>


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

        <c:if test="${sessionScope.USER.roleID eq 'AD'}">
            <c:redirect url="accessDenied.jsp"/>
        </c:if>
        <div class="container" style="min-height: 775px">
            <h1 class="text-center mt-4">CART</h1>
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${empty cart}">
                <div class="text-center">There is no items in your cart!</div>
            </c:if>
            <c:if test="${not empty cart}">
                <div class="table-responsive shopping-cart">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Quantity</th>
                                <th class="text-center">Subtotal</th>
                                <th class="text-center"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cart.getCart().values()}">
                                <tr>
                                    <td>
                                        <div class="product-item row">
                                            <a class="product-thumb" href="#"><img style="width: 265px; height: auto" src="images/${item.image}" alt="Product"></a>
                                            <div class="product-info">
                                                <h5 class="product-title"><a href="ProductDetailServlet?id=${item.productID}">${item.productName}</a></h5>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-center text-lg text-medium">
                                        $${item.price}
                                    </td>
                                    <td class="text-center">
                                        <div class="count-input">
                                            <c:if test="${item.cartQuantity != 1}">
                                                <a href="DecreaseItemServlet?id=${item.productID}"><span class="fa fa-minus mr-2"></span></a>
                                                </c:if>
                                                <c:if test="${item.cartQuantity eq 1}">
                                                <span class="fa fa-minus mr-2"></span>
                                            </c:if>
                                            ${item.cartQuantity}
                                            <c:if test="${item.cartQuantity < item.quantity}">
                                                <a href="AddToCartServlet?id=${item.productID}&url=cartPage.jsp"><span class="fa fa-plus ml-2"></span></a>
                                                </c:if>
                                                <c:if test="${item.cartQuantity >= item.quantity}">
                                                <span class="fa fa-plus ml-2"></span>
                                            </c:if>

                                        </div>

                                    </td>
                                    <td class="text-center text-lg text-medium">
                                        $${item.price * item.cartQuantity}
                                    </td>
                                    <td class="text-center">
                                        <a class="remove-from-cart" href="DeleteFromCartServlet?id=${item.productID}" data-toggle="tooltip" title="" data-original-title="Remove item">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <c:set var="total" value="${total + item.price * item.cartQuantity}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${not empty requestScope.ORDER_ERROR.quantityError}">
                        <div class="alert alert-danger" role="alert">
                            ${requestScope.ORDER_ERROR.quantityError}
                        </div>
                    </c:if>
                </div>

                <div class="text-right shopping-cart-footer">
                    <div class="column"><h3>Total: <span class="">$${total}</span></h3></div>
                </div>
                <div class="shopping-cart-footer">
                    <div class="column text-left mb-5">
                        <a class="btn btn-outline-secondary" href="DispatchServlet?btAction=Product"><i class="icon-arrow-left"></i>&nbsp;Back to Shopping</a>
                    </div>
                    <form action="DispatchServlet" method="post">
                        <div class="form-row">
                            <div class="form-group col-12">
                                <label for="email">Email: </label>
                                <input type="text" class="form-control" id="citizenID" name="txtEmail" aria-describedby="citizenIDHelp" value="${sessionScope.USER.email}" readonly="">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-12">
                                <label for="fullName">Fullname: </label>
                                <input type="text" class="form-control" id="citizenID" name="txtFullName" aria-describedby="citizenIDHelp" value="${sessionScope.USER.fullName}" required="true">
                                <c:if test="${not empty requestScope.ORDER_ERROR.customerNameError}">
                                    <div class="alert alert-danger" role="alert">
                                        ${requestScope.ORDER_ERROR.customerNameError}
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-12">
                                <label for="phoneNumber">Phone number</label>
                                <input type="text" class="form-control" id="phoneNumber" name="txtPhone" value="${sessionScope.USER.phone}" required="true">

                                <c:if test="${not empty requestScope.ORDER_ERROR.phoneError}">
                                    <div class="alert alert-danger" role="alert">
                                        ${requestScope.ORDER_ERROR.phoneError}
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-12">
                                <label for="address">Address</label>
                                <input type="text" class="form-control" id="address" name="txtAddress" value="${sessionScope.USER.address}" required="">
                                <c:if test="${not empty requestScope.ORDER_ERROR.addressError}">
                                    <div class="alert alert-danger" role="alert">
                                        ${requestScope.ORDER_ERROR.addressError}
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" name="totalPay" value="${total}">
                        <div class="text-center mb-5">
                            <input type="submit" class="btn btn-success col-4" value="Checkout" name="btAction" />
                        </div>
                    </form>
                    <c:if test="${not empty requestScope.SEND_ERROR}">
                        <div class="alert alert-danger" role="alert">
                            ${requestScope.SEND_ERROR}
                        </div>
                    </c:if>
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
