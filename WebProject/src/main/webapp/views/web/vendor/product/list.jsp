<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Trang chủ" /></title>

<link href="<c:url value='/template/admin/css/bootstrap.min.css' />"
	rel="stylesheet" />
<link
	href="<c:url value='/template/admin/font-awesome/4.2.0/css/font-awesome.min.css' />"
	rel="stylesheet" />
<link
	href="<c:url value='/template/admin/fonts/fonts.googleapis.com.css' />"
	rel="stylesheet" />
<link href="<c:url value='/template/admin/css/ace.min.css' />"
	rel="stylesheet" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<style>
body {
	color: #566787;
	background: #f5f5f5;
	font-family: 'Varela Round', sans-serif;
	font-size: 13px;
	position: fixed;
}

.table-wrapper {
	background: #fff;
	padding: 20px 25px;
	margin: 30px 0;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.table-title {
	padding-bottom: 15px;
	background: #435d7d;
	color: #fff;
	padding: 16px 30px;
	margin: -20px -25px 10px;
	border-radius: 3px 3px 0 0;
}

.table-title h2 {
	margin: 5px 0 0;
	font-size: 24px;
}

.table-title .btn-group {
	float: right;
}

.table-title .btn {
	color: #fff;
	float: right;
	font-size: 13px;
	border: none;
	min-width: 50px;
	border-radius: 2px;
	border: none;
	outline: none !important;
	margin-left: 10px;
}

.table-title .btn i {
	float: left;
	font-size: 21px;
	margin-right: 5px;
}

.table-title .btn span {
	float: left;
	margin-top: 2px;
}

table.table tr th, table.table tr td {
	border-color: #e9e9e9;
	padding: 12px 15px;
	vertical-align: middle;
}

table.table tr th:first-child {
	width: 60px;
}

table.table tr th:last-child {
	width: 100px;
}

table.table-striped tbody tr:nth-of-type(odd) {
	background-color: #fcfcfc;
}

table.table-striped.table-hover tbody tr:hover {
	background: #f5f5f5;
}

table.table th i {
	font-size: 13px;
	margin: 0 5px;
	cursor: pointer;
}

table.table td:last-child i {
	opacity: 0.9;
	font-size: 22px;
	margin: 0 5px;
}

table.table td a {
	font-weight: bold;
	color: #566787;
	display: inline-block;
	text-decoration: none;
	outline: none !important;
}

table.table td a:hover {
	color: #2196F3;
}

table.table td a.edit {
	color: #FFC107;
}

table.table td a.delete {
	color: #F44336;
}

table.table td i {
	font-size: 19px;
}

table.table .avatar {
	border-radius: 50%;
	vertical-align: middle;
	margin-right: 10px;
}

.pagination {
	float: right;
	margin: 0 0 5px;
}

.pagination li a {
	border: none;
	font-size: 13px;
	min-width: 30px;
	min-height: 30px;
	color: #999;
	margin: 0 2px;
	line-height: 30px;
	border-radius: 2px !important;
	text-align: center;
	padding: 0 6px;
}

.pagination li a:hover {
	color: #666;
}

.pagination li.active a, .pagination li.active a.page-link {
	background: #03A9F4;
}

.pagination li.active a:hover {
	background: #0397d6;
}

.pagination li.disabled i {
	color: #ccc;
}

.pagination li i {
	font-size: 16px;
	padding-top: 6px
}

.hint-text {
	float: left;
	margin-top: 10px;
	font-size: 13px;
}
/* Custom checkbox */
.custom-checkbox {
	position: relative;
}

.custom-checkbox input[type="checkbox"] {
	opacity: 0;
	position: absolute;
	margin: 5px 0 0 3px;
	z-index: 9;
}

.custom-checkbox label:before {
	width: 18px;
	height: 18px;
}

.custom-checkbox label:before {
	content: '';
	margin-right: 10px;
	display: inline-block;
	vertical-align: text-top;
	background: white;
	border: 1px solid #bbb;
	border-radius: 2px;
	box-sizing: border-box;
	z-index: 2;
}

.custom-checkbox input[type="checkbox"]:checked+label:after {
	content: '';
	position: absolute;
	left: 6px;
	top: 3px;
	width: 6px;
	height: 11px;
	border: solid #000;
	border-width: 0 3px 3px 0;
	transform: inherit;
	z-index: 3;
	transform: rotateZ(45deg);
}

.custom-checkbox input[type="checkbox"]:checked+label:before {
	border-color: #03A9F4;
	background: #03A9F4;
}

.custom-checkbox input[type="checkbox"]:checked+label:after {
	border-color: #fff;
}

.custom-checkbox input[type="checkbox"]:disabled+label:before {
	color: #b8b8b8;
	cursor: auto;
	box-shadow: none;
	background: #ddd;
}
/* Modal styles */
.modal .modal-dialog {
	max-width: 400px;
}

.modal .modal-header, .modal .modal-body, .modal .modal-footer {
	padding: 20px 30px;
}

.modal .modal-content {
	border-radius: 3px;
}

.modal .modal-footer {
	background: #ecf0f1;
	border-radius: 0 0 3px 3px;
}

.modal .modal-title {
	display: inline-block;
}

.modal .form-control {
	border-radius: 2px;
	box-shadow: none;
	border-color: #dddddd;
}

.modal textarea.form-control {
	resize: vertical;
}

.modal .btn {
	border-radius: 2px;
	min-width: 100px;
}

.modal form label {
	font-weight: normal;
}

img {
	width: 200px;
	height: 120px;
}
</style>

</head>

<body class="no-skin">




	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-14"
						style="padding-bottom: 20px; padding-top: 20px">
						<h2>
							Manage <b>Product</b>
						</h2>
					</div>


				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>

				
						<th>Name</th>
						<th>Category</th>
						<th>Code</th>
						<th>Price</th>
						<th>Amount</th>
						<th>Image</th>
						<th>Status</th>
						<th>Create Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="o">
						<tr>
						
							<td>${o.productName}</td>
							<td>${o.category.categoryName}</td>
							<td>${o.productCode}</td>
							<td>${o.price}</td>
							<td>${o.amount}</td>
							<td><img style="width: 200px; height: 120px;"
								src="${o.images}"></td>
							<td>${o.status}</td>
							<td>${o.createDate}</td>
							<td><a
								href="${pageContext.request.contextPath}/store-product/edit?productId=${o.productId}"><i
									class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
								<a
								href="${pageContext.request.contextPath}/store-product/delete?productId=${o.productId}"><i
									class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clearfix" align="right">


				<ul class="pagination">
					<c:if test="${tag>1}">
						<li class="page-item disabled"><a
							href="${pageContext.request.contextPath}/store-product/list?index=${tag-1}">Previous</a></li>
					</c:if>
					<c:forEach begin="1" end="${endP}" var="i">

						<li class="page-item ${tag==i?"active":""}" ><a
							href="${pageContext.request.contextPath}/store-product/list?index=${i}"
							class="page-link">${i}</a></li>

					</c:forEach>
					<c:if test="${tag<endP}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/store-product/list?index=${tag+1}"
							class="page-link">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!-- Add Modal HTML -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form
					action="${pageContext.request.contextPath}/admin-product/create"
					method="post">
					<div class="modal-header">
						<h4 class="modal-title">Add Product</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>ID</label> <input name="productId" type="text"
								class="form-control" readonly required>
						</div>
						<div class="form-group">
							<label>Name</label> <input name="productName" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Category Id</label> <input name="categoryId" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Image</label> <input name="images" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Status</label> <input name="status" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Amount</label> <input name="amount" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Date</label> <input name="createDate" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Price</label> <input name="price" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Product Code</label> <input name="productCode" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Stock</label> <input name="stock" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Wishlist</label> <input name="wishlist" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Seller Id</label> <input name="sellerId" type="text"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Description</label> <input name="description" type="text"
								class="form-control">
						</div>

					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-success" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Edit Modal HTML -->
	<div
		style="display: flex; z-index: 10000; position: absolute; top: 98px; right: 200px">
		<div style="margin-right:15px">
		<form action="${pageContext.request.contextPath}/store-product/find" method="post"
							class="form-inline my-2 my-lg-0">
							<div class="input-group input-group-sm">
								<input oninput="searchByName(this)" value="${txtS}" name="txt"
									type="text" class="form-control" aria-label="Small"
									aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
								<div class="input-group-append" style="background-color: lightblue;">
									<button type="submit" class="btn btn-secondary btn-number">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
		</div>
		<a href="${pageContext.request.contextPath}/store-product/create"
			class="btn" style="background: #28a745; display: flex; color: white"><i
			class="material-icons">&#xE147;</i> <span style="font-size: 16px">Add
				New Product</span></a>

		<div class="dropdown" style="margin-left: 20px; height: 41px">
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-bs-toggle="dropdown"
				aria-expanded="false" style="height: 41px"> Category </a>

			<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<c:forEach items="${categorys}" var="o">
					<li><a class="dropdown-item"
						href="${pageContext.request.contextPath}/store-product/search?categoryId=${o.categoryId}">${o.categoryName}</a></li>
				</c:forEach>
			</ul>


		</div>


	</div>
	<!-- Delete Modal HTML -->


</body>

</html>
