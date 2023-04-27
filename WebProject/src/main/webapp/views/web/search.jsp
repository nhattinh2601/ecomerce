<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>


<div class="col">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a>Các kết quả tìm kiếm của
							sản phẩm "${txtname}"</a></li>

				</ol>
			</nav>
		</div>
	</div>
	<div class="row">



		<core:forEach items="${list}" var="o">
			<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix">
				<div class="product__item">

					<div class="product__item__pic set-bg"
						data-setbg="img/product/product-1.jpg">
						<a
							href="${pageContext.request.contextPath}/product/detail?pid=${o.productId}"><img
							class="card-img-top" src="${o.images }" width="600" height="280"
							alt="Card image cap"></a> <span class="label">Real</span>
						<ul class="product__hover">
							<li><i class="fa fa-search"></i></li>

							<li><i class="fa fa-heart"></i></li>
						</ul>

					</div>
					<div class="product__item__text">
						<h6>${o.productName}</h6>

						<a
							href="${pageContext.request.contextPath}/cart/add?productid=${o.productId}&price=${o.price}"
							class="add-cart">+ Add to cart</a>
						<div class="rating">
							<i class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
								class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
								class="fa fa-star-o"></i>
						</div>
						<h5>$${o.price}</h5>
						<div class="product__color__select">
							<label for="pc-1"> <input type="radio" id="pc-1" />
							</label> <label class="active black" for="pc-2"> <input
								type="radio" id="pc-2" />
							</label> <label class="grey" for="pc-3"> <input type="radio"
								id="pc-3" />
							</label>
						</div>
					</div>
				</div>
			</div>
		</core:forEach>


	</div>


	<!-- Hien thi danh sach cac cua hang ten giong nhu vay  -->
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a>Các kết quả tìm kiếm của
							Shop "${txtname}"</a></li>

				</ol>
			</nav>
		</div>
	</div>
	<div class="row">
		<core:forEach items="${stores}" var="o">
			<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix">
				<div class="product__item">

					<div class="product__item__pic set-bg"
						data-setbg="img/product/product-1.jpg">
						<a
							href="${pageContext.request.contextPath}/vendor/product?storeId=${o.storeId}"><img
							class="card-img-top" src="${o.images }" width="600" height="280"
							alt="Card image cap"></a> <span class="label">Real</span>
						<ul class="product__hover">
							<li><i class="fa fa-search"></i></li>

							<li><i class="fa fa-heart"></i></li>
						</ul>

					</div>
					<div class="product__item__text">
						<h6>${o.storeName}</h6>
						<div class="rating">
							<i class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
								class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
								class="fa fa-star-o"></i>
						</div>

					</div>
				</div>
			</div>
		</core:forEach>

	</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<core:forEach begin="1" end="${numpage}" var="i">
				<li class="page-item"><a class="page-link"
					href="category?index=${i}" ${index==i? "style=\"color: red;\"":""}>${i}</a></li>
			</core:forEach>

		</ul>
	</nav>
</div>







<!-- JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	type="text/javascript"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	type="text/javascript">
	
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" >
</script>


<script>
	$(window).scroll(
			function() {
				if ($(window).scrollTop() + $(window).height() >= $(document)
						.height()) {
					loadMore();
				}
			});
	function loadMore() {
		var amount = document.getElementsByClassName("product").length;
		$.ajax({
			url : "/LTWeb/productLoadAjaxController",
			type : "get", //send it through get method
			data : {
				exits : amount
			},
			success : function(data) {
				var row = document.getElementById("content");
				row.innerHTML += data;
			},
			error : function(xhr) {
				//Do Something to handle error
			}
		});
	}
	function searchByName(param) {
		var txtSearch = param.value;
		$.ajax({
			url : "/WebBanGiay2/searchAjax",
			type : "get", //send it through get method
			data : {
				txt : txtSearch
			},
			success : function(data) {
				var row = document.getElementById("content");
				row.innerHTML = data;
			},
			error : function(xhr) {
				//Do Something to handle error
			}
		});
	}
</script>


</body>
</html>