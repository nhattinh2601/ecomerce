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
                            <h2>Add <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin-product/create" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                            </div>
                            <div class="modal-body">					                              
                                <div class="form-group">
                                    <label>Name</label>
                                    <input  name="productName" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                <label>Category</label>
                                <select name="categoryId" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${categorys}" var="o">
                                        <option  value="${o.categoryId}">${o.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input  name="images" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <input type="hidden" value="1" name="status" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Amount</label>
                                    <input  name="amount" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Date</label>
                                    <input name="createDate" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input  name="price" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Product Code</label>
                                    <input  name="productCode" type="text" class="form-control" >
                                </div>

                                    <input type="hidden" value="1" name="stock" type="text" class="form-control" >


                                    <input type="hidden" value="1" name="wishlist" type="text" class="form-control" >

                                    <input type="hidden" value="1" name="sellerId" type="text" class="form-control" >

                                <div class="form-group">
                                    <label>Description</label>
                                    <input  name="description" type="text" class="form-control" >
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
        <script>
        
        </script>

</body>
</html>