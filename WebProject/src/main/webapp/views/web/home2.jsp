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
	
	
	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">

			<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<div class="card  ">
					<div class="card-header bg-light text-black text-uppercase" >
						<i class="fa fa-list-alt"></i> Categories
					</div>
					<ul class="list-group ">
						<c:forEach items="${listCC}" var="o">
							<li  ><a class="list-group-item text-back ${tagCate == o.categoryId ? "active":""}"
								href="${pageContext.request.contextPath}/category/productfind?categoryId=${o.categoryId}">${o.categoryName}</a></li>
						</c:forEach>

					</ul>
				</div>
				
			</div>
			

			<div class="col-sm-9">
				<section class="product spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <ul class="filter__controls">
              <li class="active" data-filter="*">CATEGORIES</li>
             
            </ul>
          </div>
        </div>
        <div class="row product__filter">
        <c:forEach items="${listP}" var="o">
          	<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix">
            <div class="product__item">
            
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-1.jpg"
              ><a href="${pageContext.request.contextPath}/product/detail?pid=${o.productId}"><img class="card-img-top"
										src="${o.images }" width="600" height="280"
										alt="Card image cap"></a>
                <span class="label">Real</span>
                <ul class="product__hover">
                  <li>
                    <i class="fa fa-search"></i>
                  </li>
                  
                  <li>
                    <i class="fa fa-heart"></i>
                  </li>
                </ul>
                
              </div>
              <div class="product__item__text">
                <h6 href="${pageContext.request.contextPath}/product/detail?pid=${o.productId}">${o.productName}</h6>
               
                <a href="${pageContext.request.contextPath}/cart/add?productid=${o.productId}&price=${o.price}" class="add-cart">+ Add
													to cart</a>
                <div class="rating">
                  <i class="fa fa-star-o"></i>
                  <i class="fa fa-star-o"></i>
                  <i class="fa fa-star-o"></i>
                  <i class="fa fa-star-o"></i>
                  <i class="fa fa-star-o"></i>
                </div>
                <h5>$${o.price}</h5>
                <div class="product__color__select">
                  <label for="pc-1">
                    <input type="radio" id="pc-1" />
                  </label>
                  <label class="active black" for="pc-2">
                    <input type="radio" id="pc-2" />
                  </label>
                  <label class="grey" for="pc-3">
                    <input type="radio" id="pc-3" />
                  </label>
                </div>
              </div>
            </div>
          </div>
          </c:forEach>
        </div>
      </div>
    </section>
				<!-- 	Phan trang -->
	<div class="clearfix" align="right">


				<ul class="pagination">
					<c:if test="${tag>1}">
						<li class="page-item disabled"><a
							href="${pageContext.request.contextPath}/category/productfind?categoryId=${tagCate}&index=${tag-1}">Previous</a></li>
					</c:if>
					<c:forEach begin="1" end="${endP}" var="i">

						<li class="page-item ${tag==i?"active":""}" ><a
							href="${pageContext.request.contextPath}/category/productfind?categoryId=${tagCate}&index=${i}"
							class="page-link">${i}</a></li>

					</c:forEach>
					<c:if test="${tag<endP}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/category/productfind?categoryId=${tagCate}&index=${tag+1}"
							class="page-link">Next</a></li>
					</c:if>
				</ul>
			</div>
			</div>
			

		</div>

	</div>
	</div>
	

	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

</body>
</html>