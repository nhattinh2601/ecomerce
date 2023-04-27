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
							Edit <b>Product</b>
						</h2>
					</div>
					<div class="col-sm-6"></div>
				</div>
			</div>
		</div>
		<div id="editEmployeeModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="${pageContext.request.contextPath}/admin-user/create"
						method="post">
						<div class="modal-header">
							<h4 class="modal-title">Edit User</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Username</label> <input name="username" type="text"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Email</label> <input name="email" type="text"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Full name</label> <input name="fullname" type="text"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Password</label> <input name="password" type="text"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Images</label> <input name="images" type="text"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Phone</label> <input name="phone" type="text"
									class="form-control">
							</div>

							<input type="hidden" name="status" value="1" type="text"
								class="form-control">
							<div class="form-group">
								<label>Role</label> <select name="roleId" class="form-select"
									aria-label="Default select example">
									<c:forEach items="${userroles}" var="o">
										<option value="${o.roleId}">${o.roleName}</option>
									</c:forEach>
								</select>
							</div>


						
								class="form-control">



						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-success" value="Add">
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
	<script>
		
	</script>

</body>
</html>