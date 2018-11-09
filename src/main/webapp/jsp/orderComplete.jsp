<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>訂單完成頁面</title>
</head>
<body> 
		poNo：${ob.poNo} <br>
		userId：${ob.userId} <br>
		total：${ob.total} <br>
		<c:forEach var="i" items="${odList}">
			item：${i.item} <br>
			訂單號碼：${i.poNo} <br>
			產品：${i.productId} <br>
			數量：${i.qty} <br>
			------------------------------------------<br>
		</c:forEach>


<%@ include file="/common/footer.jsp"%>
</body>
</html>