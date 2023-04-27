<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>




<div class="container" style="margin-top:40px">
	
	
	
	
    <div class="row">
        <div class="col" style="border-radius:10px">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" >
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
                </ol>
                 <div class="carousel-inner" style="border-radius:10px">
                    <div class="carousel-item active" >
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/5e52c1d3fd3cf778862ffc0069ff43b4_xhdpi" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/6c04072b53b30da61cb8955e215b5394_xhdpi" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/74fdd921956a1683530ae6b6531a219e_xxhdpi" alt="Third slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/ca186fd7e2f5ee3734b8adc0d3fed3bc_xxhdpi" alt="Fourth slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/dcbfa9aa2e035681911f7306c46be2cf_xxhdpi" alt="Fourth slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/1ca582b3728a578c745913917171e93d_xxhdpi" alt="Fourth slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        
			
				
					<!-- <div class="card-header bg-success text-white text-uppercase">
						<i class="fa fa-heart"></i> Top one
					</div>
					<img class="img-fluid border-0"
						src="${top1.images}" width="600" height="400"
						alt="Card image cap">
					<div class="card-body">
						<h4 class="card-title text-center">
							<a href="${pageContext.request.contextPath}/product/detail?pid=${top1.productId}" title="View Product">${top1.productName}</a>
            
							
							
						</h4>
						<div class="row">
							<div class="col">
								<p class="btn btn-danger btn-block">${top1.price} </p>
							</div>
							<div class="col">
								<a href="${pageContext.request.contextPath}/product/detail?pid=${top1.productId}" class="btn btn-success btn-block">View</a>
							</div>
						</div>
					</div>	
					 -->
					
					
          	<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix" style="margin-left:-18px">
            	<div class="carousel-item active" style="">
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/5b5de1582d99cbbe75a8f9a5c4dae1c2_xxhdpi" alt="First slide" style="height:122px;border-radius:5px">
                    </div>
                    <div class="carousel-item active" style="margin-top:15px;"> 
                        <img class="d-block w-100" src="https://cf.shopee.vn/file/138ad16e3961d670e43ef3f98a8819dc_xxhdpi" alt="First slide" style="height:122px;border-radius:5px">
                    </div>
          </div>
          		
            </div>
        </div> 
        
  


<div class="our-services">
    <div class="container">
        <div class="row" style="box-shadow: rgba(138, 43, 226, 1 ) 0px 0px 2px;">
			<div class="col-lg-12">
            <ul class="filter__controls">
              <li class="active" data-filter="*">Danh mục</li>
             	
            </ul>
          </div>
           <core:forEach items="${listCC}" var="o">
            <div class="col-sm-12 col-md-12 col-lg-2" style="margin-top:10px;padding-bottom: 40px">
            
                    <div class="media" style="text-align: center;padding:5px">
                   
                        <div class="" style=" padding:15px;border-radius:10px;width: 150px;height: 150px;box-shadow: rgba(138, 43, 226, 1 ) 0px 0px 10px;">

                         <a style="box-shadow: 10px 10px black" href="${pageContext.request.contextPath}/category/productfind?categoryId=${o.categoryId}">
                   
                            
                            <div class="media-body">
                            <img style="margin-bottom:15px;" alt="" src="${o.images}" width="60px" height="60px">
                
                                <h5 style="color: gray; " class="mt-0"><b> ${o.categoryName}</b></h5>
                                
                            </div>
                          
                            </a>
                      
                        
                        </div>
                          
                    </div>
           
            </div>
            </core:forEach>
            
        </div>
    </div>
</div>
<section class="product spad" style="margin-top:20px">
      <div class="container" style="box-shadow: rgba(138, 43, 226, 1 ) 0px 0px 2px;">
        <div class="row">
          <div class="col-lg-12">
            <ul class="filter__controls">
              <li class="active" data-filter="*">New Product</li>
             
            </ul>
          </div>
        </div>
        <div class="row product__filter">
        <core:forEach items="${top4last}" var="o">
          	<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix">
            <div class="product__item">
            
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-1.jpg"
              ><a href="${pageContext.request.contextPath}/product/detail?pid=${o.productId}"><img class="card-img-top"
										src="${o.images }" width="600" height="280"
										alt="Card image cap"></a>
                <span class="label">New</span>
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
          </core:forEach>
        </div>
      </div>
    </section>



<section class="product spad">
      <div class="container" style="box-shadow: rgba(138, 43, 226, 1 ) 0px 0px 2px;">
        <div class="row">
          <div class="col-lg-12">
            <ul class="filter__controls">
              <li class="active" data-filter="*">Best Product</li>
             
            </ul>
          </div>
        </div>
        <div class="row product__filter">
        <core:forEach items="${top4best}" var="o">
          	<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix">
            <div class="product__item">
            
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-1.jpg"
              ><a href="${pageContext.request.contextPath}/product/detail?pid=${o.productId}"><img class="card-img-top"
										src="${o.images }" width="600" height="280"
										alt="Card image cap"></a>
                <span class="label">Best</span>
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
          </core:forEach>
        </div>
      </div>
    </section>
    <!-- Product Section End -->
    
    
    
    
    


	<!-- Section-->
	
	<!-- Product -->


			<div class="container">
		<div class="row">
			

			<div class="col-sm-12">
				
				<section class="product spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <ul class="filter__controls">
              <li class="active" data-filter="*">ALL PRODUCTS</li>
             
            </ul>
          </div>
        </div>
        <div class="row product__filter">
        <core:forEach items="${listP}" var="o">
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
          </core:forEach>
        </div>
      </div>
    </section>
    
    
				<!-- 	Phan trang -->
	<div class="clearfix"  style="margin-bottom:20px;display:flex;align-items:center;justify-content: center;">


				<ul class="pagination">
					<core:if test="${tag>1}">
						<li class="page-item disabled" style="display:flex;align-items:center;justify-content: center;"><a
							href="${pageContext.request.contextPath}/category/list?index=${tag-1}"  style="margin-left:10px;margin-right:10px;margin-top:5px"><i class="fa fa-angle-left" aria-hidden="true"></i></a></li>
					</core:if>
					<core:forEach begin="1" end="${endP}" var="i">

						<li class="page-item ${tag==i?"active":""}" ><a style="margin-left:10px;margin-right:10px;border-radius:50%"
							href="${pageContext.request.contextPath}/category/list?index=${i}"
							class="page-link">${i}</a></li>

					</core:forEach>
					<core:if test="${tag<endP}">
						<li class="page-item" style="border:none"><a
							href="${pageContext.request.contextPath}/category/list?index=${tag+1}"
							class="page-link" style="margin-left:10px;margin-right:10px;border:none"><i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
					</core:if>
				</ul>
			</div>
			</div>
			

		</div>

	</div>

	

	

<!-- JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>
