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
        <h2 style="color: chocolate;">Shopping App | 購物車</h2>
    </div>
    <div class="alert alert-success" role="alert">
    	<h3>您已完成訂購</h3>
    	<a href="<c:out value='${ctx}'/>/account?action=myOrder">
        	<button class="btn btn-primary">瀏覽訂單</button>
        </a>
    </div>
    
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">訂單編號：${ob.poNo}</h3>
		</div>
		<div class="panel-body" id="myOrderPanel">
			<div class="row" style="color:#888;">
				<div class="col-sm-2">	
						項次
				</div>
				<div class="col-sm-7">	
				產品ID		
				</div>
				<div class="col-sm-3">	
				數量		
				</div>
			</div>
			<hr id="orderHr" style="margin: 10 0;">
			<c:forEach var="i" items="${odList}">
				<div class="row">
					<div class="col-sm-2">	
						${i.item}
					</div>
					<div class="col-sm-7">	
						${i.productId}	
					</div>
					<div class="col-sm-3">	
						${i.qty}	
					</div>
				</div>
			</c:forEach>  
		</div>
	</div>  
</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>