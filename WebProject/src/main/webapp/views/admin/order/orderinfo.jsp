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
						<h2>Receiver's information</h2>
					</div>
					<div class="col-sm-6"></div>
				</div>
			</div>
		</div>


		<div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin-order/fix" method="post">
                            <div class="modal-body">					
                                <div class="form-group">
                                <input type="hidden" value="${bill.bill_Id}" name="bill_Id" type="text" class="form-control" >
                                <input type="hidden" value="${bill.date}" name="date" type="text" class="form-control" >
                                    <label>Full name</label>
                                    <input value="${bill.fullname}" name="fullname" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input value="${bill.email}" name="email" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input value="${bill.phone}" name="phone" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input value="${bill.address}" name="address" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Note</label>
                                    <input value="${bill.note}" name="note" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Payment method</label>
                                    <input value="${bill.paymentmethod}" name="paymentmethod" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Total</label>
                                    <input value="${bill.total}" name="total" type="text" class="form-control" >
                                </div>                                
                            </div>
                            <!-- <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div> -->
                        </form>
                    </div>
                </div>
            </div>



	</div>


</body>
</html>