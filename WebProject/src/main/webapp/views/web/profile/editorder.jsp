<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


  <!--Main layout-->
  <main class="mt-5 pt-4">
    <div class="container wow fadeIn">

      <!-- Heading -->
      <h2 class="my-5 h2 text-center">Thông tin đặt hàng </h2>

      <!--Grid row-->
      <div class="row">

        <!--Grid column-->
        <div class="col-md-8 mb-4">

          <!--Card-->
          <div class="card">

            <!--Card content-->
            <form class="card-body" action="${pageContext.request.contextPath}/BillControl/edit" method="post">

              <!--Grid row-->
              <div class="row">

                <!--Grid column-->
                <div class="col-md-6 mb-2">

                  <!--firstName-->
                  <div class="md-form mb-5">
                  <label for="fullname" class="">Họ và Tên</label>
                    <input type="text" name="fullname" value="${bill.fullname}" required class="form-control"placeholder="Example: Nguyễn Văn A">
                    
                  </div> 

                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-md-6 mb-2">

                  <!--SDT-->
                  <div class="md-form mb-5">
                  <label for="sdt" class="">Số điện thoại</label>
                    <input type="text" name="phone" value="${bill.phone}" required class="form-control">
                    
                  </div>

                </div>
                <!--Grid column-->

              </div>
              <!--Grid row-->

              
              <!--email-->
              <div class="md-form mb-5">
              <label for="email" class="">Email (optional)</label>
                <input type="text" name="email" value="${bill.email}" required class="form-control" placeholder="youremail@example.com">
                
              </div>

              <!--address-->
              <div class="md-form mb-5">
              <label for="address" class="">Địa Chỉ</label>
                <input type="text" name="address"  value="${bill.address}" required class="form-control" placeholder="1234 Main St">
                <input type="hidden" name="billId"  value="${bill.billId}" required class="form-control" placeholder="1234 Main St">
              </div>

              <!--address-2-->
              <!-- <div class="md-form mb-5">
                <input type="text" id="address-2" class="form-control" placeholder="Apartment or suite">
                <label for="address-2" class="">Address 2 (optional)</label>
              </div> -->

              <!--Grid row-->
              <div class="row">

                <!--Grid column-->
               <!--  <div class="col-lg-4 col-md-12 mb-4">

                  <label for="country">Country</label>
                  <select class="custom-select d-block w-100" id="country" required>
                    <option value="">Choose...</option>
                    <option>United States</option>
                  </select>
                  <div class="invalid-feedback">
                    Please select a valid country.
                  </div>

                </div>
                Grid column

                Grid column
                <div class="col-lg-4 col-md-6 mb-4">

                  <label for="state">State</label>
                  <select class="custom-select d-block w-100" id="state" required>
                    <option value="">Choose...</option>
                    <option>California</option>
                  </select>
                  <div class="invalid-feedback">
                    Please provide a valid state.
                  </div>

                </div> -->
                <!--Grid column-->

                <!--Grid column-->
                <!-- <div class="col-lg-4 col-md-6 mb-4">

                  <label for="zip">Zip</label>
                  <input type="text" class="form-control" id="zip" placeholder="" required>
                  <div class="invalid-feedback">
                    Zip code required.
                  </div>

                </div> -->
                <!--Grid column-->

              </div>
              <!--Grid row-->

              <hr>


               <label for="country">Hình Thức Thanh Toán
</label>
                  <select class="custom-select d-block w-100" name="paymentmethod" required>                    
                    <option>Thanh Toán Khi Nhận Hàng</option>
                    <option >Choose...</option>
                  </select>
                  <div class="invalid-feedback">
                    Vui Lòng Chọn Hình Thức Thanh Toán.
                  </div>
              <!-- <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="save-info">
                <label class="custom-control-label" for="save-info">Save this information for next time</label>
              </div> -->

              <hr>
              <div class="md-form mb-5">
              <label for="note" class="">Ghi Chú giao hàng</label>
                <input type="text" id="ghichu" name="note" value="${bill.note }" class="form-control" >              
              </div>
				
				
			
              <!-- <div class="d-block my-3">
                
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="cc-name">Name on card</label>
                  <input type="text" class="form-control" id="cc-name" placeholder="" required>
                  <small class="text-muted">Full name as displayed on card</small>
                  <div class="invalid-feedback">
                    Name on card is required
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="cc-number">Credit card number</label>
                  <input type="text" class="form-control" id="cc-number" placeholder="" required>
                  <div class="invalid-feedback">
                    Credit card number is required
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-3 mb-3">
                  <label for="cc-expiration">Expiration</label>
                  <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                  <div class="invalid-feedback">
                    Expiration date required
                  </div>
                </div>
                <div class="col-md-3 mb-3">
                  <label for="cc-expiration">CVV</label>
                  <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                  <div class="invalid-feedback">
                    Security code required
                  </div>
                </div>
              </div> -->
              <hr class="mb-6">
              
            </form>
            
    

          </div>
          <!--/.Card-->

        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-md-4 mb-4">

          <!-- Heading -->
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Giỏ Hàng</span>
            <span class="badge badge-secondary badge-pill"></span>
          </h4>

          <!-- Cart -->
          <ul class="list-group mb-3 z-depth-1">
            <!-- <li class="list-group-item d-flex justify-content-between lh-condensed">
              <div>
                <h6 class="my-0">Product name</h6>
                <small class="text-muted">Brief description</small>
              </div>
              <span class="text-muted">$12</span>
            </li>
            <li class="list-group-item d-flex justify-content-between lh-condensed">
              <div>
                <h6 class="my-0">Second product</h6>
                <small class="text-muted">Brief description</small>
              </div>
              <span class="text-muted">$8</span>
            </li> -->
            
            
            <core:forEach items="${listcart}" var="o">
            <li class="list-group-item d-flex justify-content-between lh-condensed">
              <div>
                <h6 class="my-0">${o.product.productName}</h6>
                <small class="text-muted">Số lượng: ${o.quantity}  </small>
              </div>
              <span class="text-muted">${o.unitPrice} $</span>
            </li>
                            
           </core:forEach>
            
            
            
            
            
            
            
            <!-- <li class="list-group-item d-flex justify-content-between lh-condensed">
              <div>
                <h6 class="my-0">Third item</h6>
                <small class="text-muted">Brief description</small>
              </div>
              <span class="text-muted">$5</span>
            </li> -->
            <li class="list-group-item d-flex justify-content-between bg-light">
              <div class="text-success">
                <h6 class="my-0">Mã Giảm Giá</h6>
                <small>EXAMPLECODE</small>
              </div>
              <span class="text-success">-0 $</span>
            </li>
            <li class="list-group-item d-flex justify-content-between">
              <span>Total (USD)</span>
              <strong>$ ${total}</strong>
            </li>
          </ul>
          <!-- Cart -->

          <!-- Promo code -->
          <form class="card p-2">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Promo code" aria-label="Recipient's username" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-secondary btn-md waves-effect m-0" type="button">Redeem</button>
              </div>
            </div>
          </form>
          <!-- Promo code -->

        </div>
        <!--Grid column-->

      </div>
      <!--Grid row-->

    </div>
  </main>
  <!--Main layout-->

  

</body>
</html>