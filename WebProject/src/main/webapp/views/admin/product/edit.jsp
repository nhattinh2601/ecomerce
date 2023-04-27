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
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin-product/update" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Product</h4>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${product.productId}" name="productId" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${product.productName}" name="productName" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Category Id</label>
                                    <input value="${product.category.categoryId}" name="categoryId" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input value="${product.images}" name="images" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Status</label>
                                    <input value="${product.status}" name="status" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Amount</label>
                                    <input value="${product.amount}" name="amount" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Date</label>
                                    <input value="${product.createDate}" name="createDate" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="${product.price}" name="price" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Product Code</label>
                                    <input value="${product.productCode}" name="productCode" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Stock</label>
                                    <input value="${product.stock}" name="stock" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Wishlist</label>
                                    <input value="${product.wishlist}" name="wishlist" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Seller Id</label>
                                    <input value="${product.store.storeId}" name="storeId" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input value="${product.description}" name="description" type="text" class="form-control" >
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