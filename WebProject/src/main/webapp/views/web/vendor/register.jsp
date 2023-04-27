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
		<div class="row">

		
			<form
				action="${pageContext.request.contextPath}/store-product/account"
				method="post">
				
				<h2>Create Store</h2>
				<div class="row mt-2">
                    <div class="col-md-6"><label class="labels">Store Name</label><input type="text" class="form-control" placeholder="Store Name" name="storeName" "></div>
                    
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">Images </label><input type="text" class="form-control" placeholder="Images" name="images" "></div>
                </div>
                <input type="hidden" name="status" value="1">
                <div class="mt-5 text-center"><input type="submit" class="btn btn-success" value="Add"></div>
				
				</form>
		</div>
	</div>

</body>
</html>