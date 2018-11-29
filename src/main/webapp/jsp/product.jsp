<%@ include file="/common/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>${pd.name} | 買堅果</title>
</head>
<body>
<%@ include file="/common/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-sm-5">
				<div class="thumbnail">
				<figure><img class="mainImg" src="data:image/jpg;base64,${imgs[0]}"></figure>
				</div>
			</div>
			<div class="col-sm-7">
				<div>
					<h2>${pd.name}</h2>
					<input type="hidden" name="productName" id="productName" value="${pd.name}">
				</div>
				<div>
					<div>
						<c:choose>
						    <c:when test="${avg == 0}">
								目前沒有評論！
						    </c:when>    
						    <c:otherwise>
								平均評分: 
								<c:forEach begin="1" end="${avg}" varStatus="loop">
									<span class="glyphicon glyphicon-star"></span>
								</c:forEach>
						    </c:otherwise>
						</c:choose>							
						<span>庫存: ${pd.inventory} </span> 
						<span>銷售量: ${pd.salesQty}</span>
					</div>
					<div>
						<h2 class="price">
							<span>$</span>${pd.price}
						</h2>
					</div>
					<div>
						<div>
							<div style="display:flex;margin: 20;height: 30;line-height: 30px;">
								<div class="text">
								數量: 
								</div>
								<div>
									<span id="minus" class="glyphicon glyphicon-minus"></span>
									<input id="num" type="text" name="qty" value="1" style="width:33px; text-align:center;"> 
									<span id="plus" class="glyphicon glyphicon-plus"></span>
									<input type="hidden" name="productId" id="productId" value="${pd.productId}">
								</div>
							</div>
						<div style="display:flex;">
							<div class="buttonDiv">
								<button id="addToCart" class="btn btn-primary btn-default" style="margin-right: 30;">
									<i class="fas fa-cart-plus"></i>加入購物車
								</button>
								
							</div>
							<div class="buttonDiv">
								<a href="/ShoppingApp/bill">
									<button id="bill" class="btn btn-primary btn-default" type="submit" name="action" value="bill">直接購買</button>
								</a>
							</div>
						</div>
					</div>
				</div>
				現在的商品ID是: ${pd.productId}
			</div>
		</div>
	</div>
	<div class="row">
		<c:forEach var="img" items="${imgs}">
			<div class="col-lg-1">
				<div class="thumbnail">
					<a class="smallImg" href="data:image/jpg;base64,${img}">
						<img class="imgSm" src="data:image/jpg;base64,${img}" alt="No image">
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div>
<div class="panel panel-warning">
	<div class="panel-heading">
		<h3 class="panel-title">商品詳情</h3>
	</div>
	<div class="panel-body"><h4>${pd.dscr}</h4></div>
</div>
</div>
</div>
<div id="space">
</div>
<div class="container">
<h3>商品評價</h3>
<hr>
	<div>
		<c:choose>
		    <c:when test="${comments == null}">
				目前沒有評論！
		    </c:when>    
		    <c:otherwise>
				<c:forEach var="comment" items="${comments}">
					<div class="well well-lg">
						<div>${comment.userId}</div>
						<div>
     						<fmt:parseNumber var="i" integerOnly = "true" type = "number"  value = "${comment.rate}" />
							<c:forEach begin="1" end="${i}" varStatus="loop">
								<span class="glyphicon glyphicon-star"></span>
							</c:forEach>
						</div>
						<div>${comment.comment}</div>
						<div>
							<fmt:formatDate value="${comment.timestamp}" pattern="yyyy-MM-dd HH:mm" />
						</div>
					</div>
					<hr>
				</c:forEach>
		    </c:otherwise>
		</c:choose>	
	</div>
</div>

		



<script type="text/javascript" src="<c:out value='${ctx}'/>/scripts/cart.js" charset="UTF-8"></script>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>