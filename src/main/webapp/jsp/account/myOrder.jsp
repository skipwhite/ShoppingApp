<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>我的帳戶</title>
</head>
<body> 
<%@ include file="/common/navbar.jsp"%>
<div class="container">
	<div class="row" style="height: 67%;">
		<div class="col-sm-2 left">
			<div class="userBrief">
				<i class="fas fa-user-circle" id="userIcon"></i> 哈囉 ${login.userId}
			</div>
			<div class="list-group">
			  <a href="<c:out value='${ctx}'/>/jsp/account/profile.jsp" class="list-group-item">
			  <i class="far fa-id-card miniIcon"></i><span>我的帳戶</span></a>
			  <a href="<c:out value='${ctx}'/>/account?action=myOrder" class="list-group-item">
			  <i class="fas fa-shopping-basket miniIcon"></i>我的訂單</a>
			  <a href="<c:out value='${ctx}'/>/jsp/account/password.jsp" class="list-group-item">
			  <i class="fas fa-key miniIcon"></i>更改密碼</a>
			</div>
		</div>
		<div class="col-sm-10">
	    <div>
	    	<h3 class="profileTitle">我的訂單</h3>
	    </div>
	    <hr>	
	    
<c:forEach var="OD" items="${ODList}">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">訂單號碼: ${OD.ob.poNo}</h3>
		</div>
		<div class="panel-body" id="myOrderPanel">
			<div class="row" style="color:#888;">
				<div class="col-sm-1">	
						項次
				</div>
				<div class="col-sm-5">	
				產品名稱		
				</div>
				<div class="col-sm-1">	
				數量		
				</div>
				<div class="col-sm-5">	
				評價進度		
				</div>
			</div>
			<hr id="orderHr" style="margin: 10 0;">
			<c:forEach var="odb" items="${OD.odbl}">
				<div class="row">
					<div class="col-sm-1">	
						${odb.item}
					</div>
					<div class="col-sm-5">	
					<input type="hidden" value="${odb.productId}" class="products">
						${odb.productId}	
						<span id="productName">應該要顯示產品名稱</span>
					</div>
					<div class="col-sm-1">	
						${odb.qty}	
					</div>
					<div class="col-sm-5">	
						<c:choose>
						    <c:when test="${odb.isCommented == false}">
							<form method="post" action="<c:out value='${ctx}'/>/comment?productId=${odb.productId}">
								<input type="hidden" name="poNo" value="${odb.poNo}">
								  <div>
								    <div class="star">&#9733</div>
								    <div class="star">&#9733</div>
								    <div class="star">&#9733</div>
								    <div class="star">&#9733</div>
								    <div class="star">&#9733</div>
								  </div>
								<input id="rate" type="hidden" name="rate" value="5">
								<div style="    display: flex;">
									<textarea name="comment" rows="2" cols="30" placeholder="分享你的評價"></textarea>
									<button class="btn btn-default" type="submit">去評價</button>
								</div>
							</form>				        
						    </c:when>    
						    <c:otherwise>
							已完成評價！<br>
						    </c:otherwise>
						</c:choose>			
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</c:forEach>

		</div>
	</div>
</div>



<script type="text/javascript" src="<c:out value='${ctx}'/>/scripts/myOrder.js" charset="UTF-8"></script>
<%@ include file="/common/footer.jsp"%>
</body>
</html>
<style>
  .star{
    display:inline-block;
    filter: grayscale(1);
    color: red;
  }
</style>