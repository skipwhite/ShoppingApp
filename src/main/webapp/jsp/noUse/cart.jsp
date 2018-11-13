<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>商品頁面</title>
</head>
<body> 

<h1>Product Page</h1>
										<h3>購物車數量</h3>
					<c:forEach var="bean" items="${map}">
						<ol>
							<li>Map key: ${bean.key}</li>
							<li>Map商品ID: ${bean.key.productId}</li>
							<li>商品數量: ${bean.value}</li>
							<li>商品單價: ${bean.key.price}</li>
						</ol>
					</c:forEach>
					<c:forEach var="bean" items="${cart}">
						<ol>
							<li>Map商品ID: ${bean.key}</li>
							<li>商品數量: ${bean.value}</li>
							<li>商品價格:</li>
						</ol>
					</c:forEach>
<br><a href="<c:out value='${ctx}'/>/index.jsp">Home</a>
</body>
<%@ include file="/common/footer.jsp"%>
</html>

