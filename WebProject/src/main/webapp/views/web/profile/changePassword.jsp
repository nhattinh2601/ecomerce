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
	

			<h2 class="text-center">${ThongBao}</h2>
			<form action="${pageContext.request.contextPath}/changePassword"
				method="post">
				<div class="col-md-5 border-right">
					<div class="p-3 py-5">
						<div
							class="d-flex justify-content-between align-items-center mb-3">
							<a href="${pageContext.request.contextPath}/changePassword"><h4 class="text-right">Change Password</h4></a>
						</div>
						 <label>Current Password</label>
		    <div class="form-group pass_show"> 
                <input type="password"  name="password" class="form-control" placeholder="Current Password"> 
            </div> 
		       <label>New Password</label>
            <div class="form-group pass_show"> 
                <input type="password"  name="newpassword" class="form-control" placeholder="New Password"> 
            </div> 
		       <label>Confirm Password</label>
            <div class="form-group pass_show"> 
                <input type="password"  name="repassword" class="form-control" placeholder="Confirm Password"> 
            </div> 
						</div>
						<div class="mt-5 text-center">
							<input type="submit" class="btn btn-success" value="Edit">
						</div>
					</div>
			</form>
</body>
</html>