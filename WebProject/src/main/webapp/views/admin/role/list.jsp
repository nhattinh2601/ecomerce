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
                            <h2>Manage <b>UserRole</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="${pageContext.request.contextPath}/admin-role/create"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Role</span></a>
                            
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            
                            <th>ID</th>
                            <th>Name</th>                            
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userroles}" var="o">
                            <tr>
                                <td>${o.roleId}</td>
                                <td>${o.roleName}</td>                                
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin-role/edit?roleId=${o.roleId}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="${pageContext.request.contextPath}/admin-role/delete?roleId=${o.roleId}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix" align="right">

                        
                        <ul class="pagination">
						<c:if test="${tag>1}">
							<li class="page-item disabled"><a
								href="${pageContext.request.contextPath}/admin-role?index=${tag-1}">Previous</a></li>
						</c:if>
						<c:forEach begin="1" end="${endP}" var="i">

							<li class="page-item ${tag==i?"active":""}" ><a
								href="${pageContext.request.contextPath}/admin-role?index=${i}" class="page-link">${i}</a></li>

						</c:forEach>
						<c:if test="${tag<endP}">
							<li class="page-item"><a href="${pageContext.request.contextPath}/admin-role?index=${tag+1}"
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
                    <form action="${pageContext.request.contextPath}/admin-role/create" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add role</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="roleName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="images" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <input name="status" type="text" class="form-control" required>
                            </div>
                            

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        
        <!-- Delete Modal HTML -->
        
        <script>
        $(document).ready(function () {
            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();

            // Select/Deselect checkboxes
            var checkbox = $('table tbody input[type="checkbox"]');
            $("#selectAll").click(function () {
                if (this.checked) {
                    checkbox.each(function () {
                        this.checked = true;
                    });
                } else {
                    checkbox.each(function () {
                        this.checked = false;
                    });
                }
            });
            checkbox.click(function () {
                if (!this.checked) {
                    $("#selectAll").prop("checked", false);
                }
            });
        });
        </script>

</body>
</html>