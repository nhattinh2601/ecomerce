<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<body>





<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Giỏ Hàng</h1>
     </div>
</section>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Sản Phẩm</th>
                            <th scope="col">Tình Trạng</th>
                            <th scope="col" class="text-center">Số lượng</th>
                            <th scope="col" class="text-right">Giá</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    
                    
                    <core:forEach items="${listcart}" var="o">
                            <tr>
                            <td><img src= "${o.product.images}" width="50" height="50"> </td>
                            <td>${o.product.productName}</td>
                      
									<core:if test="${o.product.amount >0}">
										<td>Còn Hàng</td>
									</core:if>
									<core:if test="${o.product.amount <=0}">
										<td>Hết Hàng</td>
									</core:if>
									<td><div class="input-group mb-3">
									<div class="input-group-prepend" style="margin-right:15px;">
										<a href="${pageContext.request.contextPath}/subtractionquantity?cartitemid=${o.cartItemId}&quantity=${o.quantity}&unitprice=${o.unitPrice}&pid=${o.product.productId}&cartid=${o.cart.cartId}">
										<button type="button"
											class="quantity-left-minus btn btn-danger btn-number"
											data-type="minus" data-field="" style="border-radius:50%;">
											<i class="fa fa-minus"></i>
										</button>
										</a>
									</div>
									<input type="text" class="form-control" id="quantity"
										name="quantity" min="1" max="100" value="${o.quantity }" disabled>
									<div class="input-group-append" style="margin-left:15px;">
									<a href="${pageContext.request.contextPath}/addquantity?cartitemid=${o.cartItemId}&quantity=${o.quantity}&unitprice=${o.unitPrice}&pid=${o.product.productId}&cartid=${o.cart.cartId}">
										<button type="button"
											class="quantity-right-plus btn btn-success btn-number"
											data-type="plus" data-field="" style="border-radius:50%;">
											<i class="fa fa-plus"></i>
										</button>
										</a>
									</div>
								    </div></td>
									
								

                            
                            <td class="text-right">${o.unitPrice} đ</td>
                            <%-- <td class="text-right"><a href="${pageContext.request.contextPath}/cartupdate?cartitemid=${o.cartItemId}&quantity=${o.quantity}&unitprice=${o.unitPrice}&pid=${o.product.productId}&cartid=${o.cart.cartId}"><button class="btn btn-primary"><i class="fa fa-refresh fa-spin fa fa-fw" aria-hidden="true"></i>
                          <span class="sr-only">Refreshing...</span> </button></a> </td> --%>
							<td class="text-right"><a href="${pageContext.request.contextPath}/deletecartitem?cartitemid=${o.cartItemId}"><button class="btn btn-danger"><i class="fa fa-trash"></i> </button></a> </td>
                        
                        
                        </tr>
                        </core:forEach>
							










							<!-- 
                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Dada</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">124,90 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Toto</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">33,90 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Titi</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">70,00 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr> -->
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Tổng tiền hàng</td>
                            <td class="text-right">${tienhang} đ</td>
                        </tr>
                       <%--  <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Phí Ship</td>
                            <td class="text-right">${tienship} đ</td>
                        </tr> --%>
                        <%-- <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Tổng</strong></td>
                            <td class="text-right"><strong>${tong} đ</strong></td>
                        </tr> --%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                     <a href="${pageContext.request.contextPath}/category/list"><button class="btn btn-block btn-light">Continue Shopping</button>
                      </a>
                </div>
                <div class="col-sm-12 col-md-6 text-right" >
                    <a href="${pageContext.request.contextPath}/checkout/xacnhan"><button class="btn btn-lg btn-block btn-success text-uppercase btn-light" style="height:35px">Checkout</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>