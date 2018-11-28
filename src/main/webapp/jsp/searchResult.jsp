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
	<h3>搜尋結果 關鍵字:${query}</h3>
	<div class="row">
		<c:forEach var="i" items="${maps}">
			<div class="col-lg-3 col-sm-6">
				<div class="thumbnail">
					<a href="<c:out value='${ctx}'/>/product?productId=${i.key.productId}" class="pic">		
						<div class="indexSmImg" style="background-image: url(data:image/jpg;base64,${i.value});">
						</div>
					</a>	
					${i.key.name}<br>
					<strong>${i.key.price}$</strong>
				</div>

			</div>
		</c:forEach>

	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>