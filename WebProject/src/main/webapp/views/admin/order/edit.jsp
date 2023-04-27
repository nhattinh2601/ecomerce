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
                            <h2>Edit <b>Order Status</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin-order/update" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Order Status</h4>
                            </div>
                            <div class="form-group">
                                    <input type="hidden" value="${cart.cartId}" name="cartId" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" value="${cart.user.userId}" name="userId" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <input type="hidden" value="${cart.buyDate}" name="buyDate" type="text" class="form-control" >
                                </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                <label>Status</label>
                                <select name="status" class="form-select" aria-label="Default select example">                                    
                                        <option value="1">Đang xử lý</option>                                    
                                        <option value="2">Đã hoàn tất</option>                                    
                                        <option value="3">Đang tạm giữ</option>                                    
                                        <option value="4">Đã hủy</option>                                    
                                </select>
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
        <script>
        
        </script>

</body>
</html>