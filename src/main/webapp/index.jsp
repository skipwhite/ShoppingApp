<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/include.jsp" %>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>ShoppingApp 買堅果</title>

</head>
<body> 
<%@ include file="/common/navbar.jsp"%>
<div class="full-banner container">
    <div class="main-banner thumbnail">
<div id="wrapper">
  <div id="slide">
    <ul>
      <li><img src="photo/main1.jpg" width="800" height="500" alt="img1"></li>
      <li><img src="photo/main2.jpg" width="800" height="500" alt="img2"></li>
    </ul>
  	<a id="prevBtn" class="prev" >&#10094;</a>
<a id="nextBtn" class="next" >&#10095;</a>
  </div>
</div>
    </div>
    <div class="right-banner thumbnail">
    <img class="smBanner" src="photo/sm1.png">
    <img class="smBanner" src="photo/sm2.png">
    </div>
</div>

<div class="container">
	<div class="row">
		<c:forEach var="i" items="${maps}">
			<div class="col-lg-3 col-sm-6 smProduct zoom">
					<a href="<c:out value='${ctx}'/>/product?productId=${i.key.productId}" class="pic">		
						<div class="indexSmImg" style="background-image: url(data:image/jpg;base64,${i.value});">
						</div>
					</a>	
			      <div class="caption">
			        <h5 style="min-height: 30px;">${i.key.name}</h5>
			        <strong style="color:#ff5722;">$${i.key.price}</strong>
			      </div>					

			</div>
		</c:forEach>

	</div>
</div>
	<a href="<c:out value='${ctx}'/>/jsp/newProduct.jsp">New Product</a>
<script type="text/javascript" src="<c:out value='${ctx}'/>/scripts/index.js" charset="UTF-8"></script>
<%@ include file="/common/footer.jsp"%>
</body>
</html>