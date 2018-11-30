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
<div class="container">
	<h3><i class="fas fa-search"style="
    color: crimson;margin: 10;
"></i>'${query}' 的搜尋結果</h3>
<hr>
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
<%@ include file="/common/footer.jsp"%>
</body>
</html>