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
                            <h2>Edit <b>Store</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin-store/update" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit store</h4>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${store.storeId}" name="storeId" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${store.storeName}" name="storeName" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input value="${store.images}" name="images" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Status</label>
                                    <input value="${store.status}" name="status" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>User ID</label>
                                    <input value="${store.user.userId}" name="userId" type="text" class="form-control" >
                                </div>
                                

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
        

</body>
</html>