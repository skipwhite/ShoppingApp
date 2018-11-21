<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>您已完成訂單</title>
</head>
<body> 
<%@ include file="/common/navbar.jsp"%>
<div class="container">
    <div>
        <h2>Shopping App | 您已完成訂購，訂單編號：${ob.poNo}</h2>
    </div>
		poNo：${ob.poNo} <br>
		userId：${ob.userId} <br>
		total：${ob.totalPrice} <br>
		<c:forEach var="i" items="${odList}">
			item：${i.item} <br>
			訂單號碼：${i.poNo} <br>
			產品：${i.productId} <br>
			數量：${i.qty} <br>
			------------------------------------------<br>
		</c:forEach>
</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>