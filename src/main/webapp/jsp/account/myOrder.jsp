<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>我的帳戶</title>
</head>
<body> 
<div class="container">
	<div class="row">
		<div class="col-sm-2">
		<ul>
		<li>使用者名稱</li>
		<li>我的帳戶</li>
		<li><a href="<c:out value='${ctx}'/>/jsp/account/myOrder.jsp">我的訂單</a></li>
		<li><a href="<c:out value='${ctx}'/>/jsp/account/password.jsp">更改密碼</a></li>
		</ul>
		</div>
		<div class="col-sm-10">
		<c:forEach var="OD" items="${ODList}">
		User: ${OD.ob.userId}
		PO: ${OD.ob.poNo}
			<c:forEach var="odb" items="${OD.odbl}">
				item：${odb.item} <br>
				訂單號碼：${odb.poNo} <br>
				產品：${odb.productId} <br>
				數量：${odb.qty} <br>
				=====<br>
			</c:forEach>
			------------------------------------------<br>
		</c:forEach>

		</div>
	</div>
</div>




<%@ include file="/common/footer.jsp"%>
</body>
</html>