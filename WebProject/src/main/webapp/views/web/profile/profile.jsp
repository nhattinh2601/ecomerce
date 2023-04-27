<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row" style="justify-content: center;">


			<form action="${pageContext.request.contextPath}/updateuser"
				method="get">
				<div class="border-right">
					<div
						style="border: 2px solid #eeeeee; padding: 10px 30px; border-radius: 10px; padding-top: 20px">
						<div class="sgh"
							style="display: flex; justify-content: center; align-items: center;">
							<div
								style="margin-right: 120px; border: 2px solid #eeeeee; padding: 10px 30px; border-radius: 10px; text-align: center; justify-content: center;"
								class="d-flex justify-content-between align-items-center mb-3">
								<a href="${pageContext.request.contextPath}/changePassword"><h4
										class="text-right">Change Password</h4></a>
							</div>
							<div
								style="margin-right: 120px; border: 2px solid #eeeeee; padding: 10px 30px; border-radius: 10px;"
								class="d-flex justify-content-between align-items-center mb-3">
								<a href="${pageContext.request.contextPath}/BillControl"><h4
										class="text-right">Order</h4></a>
							</div>

							<!-- test thử đoànj này  -->



							<c:if test="${not empty STOREMODEL}">
								<div
									class="dropdown d-flex justify-content-between align-items-center mb-3"
									style="margin-left: 20px; height: 41px; background-color: white; border: 2px solid #eeeeee;border-radius: 10px;">
									<a class="btn btn-secondary dropdown-toggle" href="#"
									
										role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
										aria-expanded="false" style="height: 41px;background-color: white; color: #007bff;border: 2px solid #eeeeee;border-radius: 10px;padding: 10px 30px;">
										<h4 class="text-right">Manager</h4>
									</a>

									<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">

										
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}//store-product/list">Product</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/store-order">Order</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/vendor-analytics/aday?date=2022-12-08">Chart in Day</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/vendor-analytics/aweek?date=2022-12-08">Chart in 7 Day</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/vendor-analytics/amonth?date=2022-12-08">Chart in Month</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/vendor-analytics/ayear?date=2022-12-08">Chart in Year</a></li>

									</ul>


								</div>


							</c:if>
							<c:if test="${empty STOREMODEL}">
								<div
									style="border: 2px solid #eeeeee; padding: 10px 30px; border-radius: 10px;"
									class="d-flex justify-content-between align-items-center mb-3">
									<a
										href="${pageContext.request.contextPath}/store-product/account"><h4
											class="text-right">Create Store</h4></a>
								</div>
							</c:if>
						</div>

						<div style="display: flex;">
							<div class="col-md-3 border-right">
								<div
									class="d-flex flex-column align-items-center text-center p-3 py-5">
									<img class="rounded-circle mt-5" width="150px"
										src="${sessionScope.USERMODEL.images }"></span>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-md-4">
									<label class="labels">Username</label><input type="text"
										class="form-control" placeholder="User name" name="username"
										value="${sessionScope.USERMODEL.username }">
								</div>



								<div class="col-md-8">
									<label class="labels">Email</label><input type="text"
										class="form-control" placeholder="enter email" name="email"
										value="${sessionScope.USERMODEL.email }">
								</div>
								<div class="col-md-12">
									<label class="labels">Full name</label><input type="text"
										class="form-control" placeholder="enter fullname"
										name=fullname value="${sessionScope.USERMODEL.fullname }">
								</div>
								<div class="col-md-12">
									<label class="labels">Phone</label><input type="text"
										class="form-control" placeholder="enter phone number"
										name="phone" value="${sessionScope.USERMODEL.phone }">
								</div>

							</div>
						</div>
						<div class="mt-5 text-center">
							<input type="submit" class="btn btn-success" value="Edit"
								style="background: white; color: #28a745; padding: 10px 25px; border-radius: 10px">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>