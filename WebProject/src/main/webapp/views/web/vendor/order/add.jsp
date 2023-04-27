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
                            <h2>Add <b>Order</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            
            
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin-order/create" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Order</h4>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>User Id</label>
                                    <input name="userId" type="text" class="form-control" >
                                </div>
                                
                                <div class="modal-body">					
                                <div class="form-group">
                                <label>Order Status</label>
                                <select name="status" class="form-select" aria-label="Default select example">                                    
                                        <option value="1">Đang xử lý</option>                                    
                                        <option value="2">Đã hoàn tất</option>                                    
                                        <option value="3">Đang tạm giữ</option>                                    
                                        <option value="4">Đã hủy</option>                                    
                                </select>
                            </div>

                            </div>                                
                                                              
                                

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>



        </div>
        

</body>
</html>