<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Trang chủ" /></title>

</head>

<body class="no-skin">




	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>
							Manage <b>Member</b>
						</h2>
					</div>
					<div class="col-sm-6">
						<a href="${pageContext.request.contextPath}/admin-user/create" class="btn btn-success"
							data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add
								New Member</span></a>
						<div class="dropdown">
							<a class="btn btn-secondary dropdown-toggle" href="#"
								role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
								aria-expanded="false"> Role </a>

							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<c:forEach items="${userroles}" var="o">
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin-user/search?roleId=${o.roleId}">${o.roleName}</a></li>
								</c:forEach>
							</ul>
						</div>


					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>

						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Full name</th>
						<th>Password</th>
						<th>Image</th>
						<th>Phone</th>
						<th>Status</th>
						<th>Role</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="o">
						<tr>
							<td>${o.userId}</td>
							<td>${o.username}</td>
							<td>${o.email}</td>
							<td>${o.fullname}</td>
							<td>${o.password}</td>
							<td><img src="${o.images}"></td>
							<td>${o.phone}</td>
							<td>${o.status}</td>
							<td>${o.userRole.roleName}</td>
							<td><a
								href="${pageContext.request.contextPath}/admin-user/edit?userId=${o.userId}"
								class="edit" data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
								href="${pageContext.request.contextPath}/admin-user/delete?userId=${o.userId}"
								class="delete" data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clearfix" align="right">


				<ul class="pagination">
					<c:if test="${tag>1}">
						<li class="page-item disabled"><a
							href="${pageContext.request.contextPath}/admin-user?index=${tag-1}">Previous</a></li>
					</c:if>
					<c:forEach begin="1" end="${endP}" var="i">

						<li class="page-item ${tag==i?"active":""}" ><a
							href="${pageContext.request.contextPath}/admin-user?index=${i}"
							class="page-link">${i}</a></li>

					</c:forEach>
					<c:if test="${tag<endP}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/admin-user?index=${tag+1}"
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
					action="${pageContext.request.contextPath}/admin-user/create"
					method="post">
					<div class="modal-header">
						<h4 class="modal-title">Add Member</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>ID</label> <input name="userId" type="text"
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

	<!-- Delete Modal HTML -->

	<script>
		</body>
		</html>
	