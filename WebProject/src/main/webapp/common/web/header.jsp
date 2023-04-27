<!-- Header -->


<header class="header-v4" >
	<!-- Header desktop -->


	<!-- Topbar -->
	<div class="container-menu-desktop"
		style="position: fixed; z-index: 20000;">
		<div class="ss" style="position: relative">

			<div class="ss" style="justify-content: space-between;">

				<div class="wrap-menu-desktop how-shadow1" 
					style="padding-top: 30px; padding-bottom: 30px;background-color: rgb(26, 148, 255)">
					<nav class="limiter-menu-desktop container" style="top: 0px;">

						<!-- Logo desktop -->
						<div style="height: 350px;">
							<a href="${pageContext.request.contextPath}/homemain" class="logo">
							
							 <img src="<c:url value='/template/logo.png' />"
								style="width: 100%; height: 100%; padding-top: 120px;padding-bottom: 2px "
								alt="IMG-LOGO">
							</a>
						</div>
						<!-- Menu desktop -->
						<div class="menu-desktop"
							style="position: absolute; right: 600px;">
							<ul class="main-menu">
								<li><a href="${pageContext.request.contextPath}/homemain"
									style="margin-right: 30px;color: white">Home</a>

									<li class="label1" data-label1="hot"><a
									href="${pageContext.request.contextPath}/vendor/home"
									style="padding-right:30px;color: white">Shop</a></li>
						
						<li><a href="${pageContext.request.contextPath}/contact" style="color: white">Contact</a></li>
					</ul>
				</div>

				<!-- Icon header -->
			  	<div class="wrap-icon-header flex-w flex-r-m" style="">
										<div class="input-group input-group-sm"
											style="width: 50%;position: absolute; right:-350px;">
						<form action="${pageContext.request.contextPath}/search" method="post" style="position: relative;">
                  <input oninput="searchByName(this)" value="${txtS}" name="txt"
														type="text"  aria-label="Small"
														aria-describedby="inputGroup-sizing-sm"
														placeholder="Search..." style=" width: 100%;border-radius:20px;
  font-size: 15px;
  color: #b7b7b7;
  padding-left: 20px;
  border: 1px solid #e5e5e5;
  height: 42px;"/>
                  <button type="submit" style="color: #b7b7b7;
  font-size: 15px;
  border: none;
  background: transparent;
  position: absolute;
  right: 0;
  padding: 0 15px;
  top: 0;
  height: 100%;">
                    <i class="fa fa-search"></i>
                  </button>
                </form>
					</div>
					
					<!-- Icon header -->

					<div class="icon1" style="position: absolute; right:60px">
					<c:if test="${empty USERMODEL}">
						<div class="icon-header-item ">
						<a href="${pageContext.request.contextPath}/login"> <i
														class="zmdi zmdi-shopping-cart "></i></a>
																		<style>
.zmdi{color: white;}
</style>
						
					</div>
	
					</c:if>
					<c:if test="${not empty USERMODEL}">

<div class="icon-header-item ">
							<a href="${pageContext.request.contextPath}/cart"> <i
														class="zmdi zmdi-shopping-cart"></i></a>
														<style>
.zmdi{color: white;}</style>

						</div>

					</c:if>
					</div>
					<div class="right-top-bar flex-w h-full"
											style="margin-left:20px;position: absolute; right:100px">	
						 
					
					<c:if test="${empty USERMODEL}">
						<a href='<c:url value="/login?action=login"/>'
													class="flex-c-m trans-04 p-lr-25" style="font-size:20px;"> <i class="fa fa-user-o" aria-hidden="true"></i>
 </a>
					</c:if>
					</div>
					<c:if test="${not empty USERMODEL}">
						
							
							
					
            
            		 <ul id="nav"
												style="margin-left:20px;position: absolute; right:100px">
                
                <li>
                    
                    <a href="#" class="flex-c-m trans-04 p-lr-25"
													style="font-size:20px"> <i class="fa fa-user hihi"></i> </a>
													<style>
.hihi{
color: white;
}
</style>
                    <ul class="subnav">
                        <li><a
															href="${pageContext.request.contextPath}/profile"
															class="flex-c-m trans-04 p-lr-25">My Account</a></li>
                        <li><a href="#"
															class="flex-c-m trans-04 p-lr-25"> Welcome ,
							${sessionScope.USERMODEL.fullname } </a></li>
                        <li><a
															href='<c:url value="/logout?action=logout"/>'
															class="flex-c-m trans-04 p-lr-25 ;font-size:20px"> Logout </a></li>
                    </ul>
                </li>
            </ul>  
            		
					</c:if>
				
				
					
				</div>
				
								</nav>
		</div>
		</div>
		</div>
	</div>


</header>

