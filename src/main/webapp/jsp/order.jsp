<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>訂單完成頁面</title>
</head>
<body> 
<span>poNo: ${ODb.poNo} </span>
<span>userId: ${ODb.userId} </span> 	
<span>total: ${ODb.total} </span> 	

<font><b>EL取List再取其中的物件</b></font><br>
	<c:forEach var="aVar" items="${ODb.odbList}">
		item：${aVar.item} <br>
		訂單號碼：${aVar.poNo} <br>
		產品：${aVar.productId} <br>
		數量：${aVar.qty} <br>
		------------------------------------------<br>
	</c:forEach>

<%@ include file="/common/footer.jsp"%>
</body>
</html>