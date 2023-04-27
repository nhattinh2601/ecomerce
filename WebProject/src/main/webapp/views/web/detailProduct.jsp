<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="/">Home</a></li>
						<li class="breadcrumb-item"><a href="category.html">Category</a></li>
						<li class="breadcrumb-item active" aria-current="page">Product</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<!-- Image -->
			<div class="col-12 col-lg-6">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<a href="" data-toggle="modal" data-target="#productModal"> <img
							class="img-fluid" src=${detail.images } />
							<p class="text-center">Zoom</p>
						</a>
					</div>
				</div>
			</div>

			<!-- Add to cart -->
			<div class="col-12 col-lg-6 add_to_cart_block">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<p style="color:black;margin-bottom:20px">${detail.productName } </p>
						<p style="margin-bottom:20px" class="price">$ ${detail.price}</p>
						<!-- <p class="price_discounted">0 đ </p> -->
						<form method="get" action="${pageContext.request.contextPath}/cart/add">
							
							<div class="form-group" style="display:flex;">
								<label style="margin-right:10px">Quantity :</label>
								<div class="input-group mb-3"style="width:60px;">
									
																	
									<input type="number" class="form-control"
										name="quantity2" min="1" max="100" value="1" >
										<input type="hidden" class="form-control"
										name="productid" value="${detail.productId }">
										<input type="hidden" class="form-control"
										name="price" value="${detail.price }">
										
										
									
								</div>
								
							</div>
							<%-- <a href="${pageContext.request.contextPath}/addcartitemondetail?productid=${detail.productId}&price=${detail.price}"
								class="btn btn-success btn-lg btn-block text-uppercase"> <i
								class="fa fa-shopping-cart"></i> Add To Cart
								
							</a> --%>
							<button class="btn btn-lg btn-block btn-success text-uppercase" style="background:white;color:#28a745;padding:10px 25px;border-radius:10px">Add to Cart</button>
					<!-- 	</form> -->
						
						
						
						<div style="margin:20px 0px;border:2px solid #eeeeee;border-radius:10px;display:flex;text-align:center;justify-content:center;width:150px;padding-right:40px"
							class="d-flex justify-content-between align-items-center mb-3">
							<a href="${pageContext.request.contextPath}/vendor/product?storeId=${detail.store.storeId}"><h4
									class="text-right">View Shop</h4></a>
						</div>
						<div class="product_rassurance">
							<ul class="list-inline">
								
								
								<li class="list-inline-item" style="display:flex"><a href="tel: 0869154692" style="color:black"><i class="fa fa-phone fa-2x" ></i></a>0869154692</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Description -->
			<div class="col-12">
				<div class="card border-light mb-3">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-align-justify"></i> Description
					</div>
					<div class="card-body">
						<p class="card-text">${detail.description}</p>
						<p class="card-text"></p>
					</div>
				</div>
			</div>

			<!-- Reviews -->
			
		</div>
		
		
		<!-- - -->
		
		
	</div>


	


	<!-- Modal image -->
	<div class="modal fade" id="productModal" tabindex="-1" role="dialog"
		aria-labelledby="productModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="productModalLabel">Product title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<img class="img-fluid"
						src="https://dummyimage.com/1200x1200/55595c/fff" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"></script>
	
	
	
</body>
</html>