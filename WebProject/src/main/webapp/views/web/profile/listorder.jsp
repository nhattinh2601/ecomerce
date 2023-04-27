<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bootstrap Simple Data Table</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">

			<div class="container-xl">
				<div class="table-responsive">
					<div class="table-wrapper">
						<div class="table-title">
							<div class="row">
								<div class="col-sm-8">
									<h2>
										Order <b>List</b>
									</h2>
								</div>
								<div class="col-sm-4"></div>
							</div>
						</div>
						<table class="table table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th>Time</th>
									<th>Order Status</th>
									<th>Price</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<core:forEach items="${bills}" var="o">
									<tr>
										<td>${o.date}</td>
										<core:if test="${o.cart.status ==1}">
											<td>Đang xử lý</td>
										</core:if>
										<core:if test="${o.cart.status ==2}">
											<td>Đã hoàn tất</td>
										</core:if>
										<core:if test="${o.cart.status ==3}">
											<td>Đang tạm giữ</td>
										</core:if>
										<core:if test="${o.cart.status ==4}">
											<td>Đã hủy bỏ</td>
										</core:if>
										<td>${o.total}</td>
										<td><a href="${pageContext.request.contextPath}/BillControl/detail?billId=${o.billId}&cartId=${o.cart.cartId}"><button class="btn btn-primary"><i class="fa fa-edit" aria-hidden="true"></i> 
										</td>
									</tr>
								</core:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>