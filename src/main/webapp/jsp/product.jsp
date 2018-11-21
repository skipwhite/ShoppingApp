<%@ include file="/common/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>商品頁面</title>
</head>
<body>
<%@ include file="/common/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<c:forEach var="img" items="${imgs}">
					<div class="thumbnail">
						<img src="data:image/jpg;base64,${img}" alt="No image">
					</div>
				</c:forEach>
			</div>
			<div class="col-sm-6">
				<div>
					<h1>${pd.name}</h1>
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
						<h2>
							<span>$</span>${pd.price}
						</h2>
					</div>
					<div>
						<div>
							<div style="display:flex;">
								數量: <span id="minus" class="glyphicon glyphicon-minus"></span>
								<input id="num" type="text" name="qty" value="1" style="width:33px; text-align:center;"> 
								<span id="plus" class="glyphicon glyphicon-plus"></span>
								<input type="hidden" name="productId" id="productId" value="${pd.productId}">
							</div>
						<div>
							<input type="button" id="addToCart" class="btn btn-primary btn-default" value="加入購物車">
							<a href="/ShoppingApp/bill">
								<button class="btn btn-primary btn-default" type="submit" name="action" value="bill">結帳</button>
							</a>
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
					<img src="data:image/jpg;base64,${img}" alt="No image">
				</div>
			</div>
		</c:forEach>
	</div>
	<div>
		<h3>${pd.dscr}</h3>
	</div>
	<div>
		<h3>產品評論</h3>
		<c:choose>
		    <c:when test="${comments == null}">
				目前沒有評論！
		    </c:when>    
		    <c:otherwise>
				<c:forEach var="comment" items="${comments}">
					<ul>
					<li>Comment Id: ${comment.commentId}</li>
					<li>Product Id: ${comment.productId}</li>
					<li>評分: ${comment.rate}</li>
					<li>評論: ${comment.comment}</li>
					<li>時間: <fmt:formatDate value="${comment.timestamp}" pattern="yyyy-MM-dd HH:mm" /></li>
					</ul>
				</c:forEach>
		    </c:otherwise>
		</c:choose>	
	</div>
	</div>
<script type="text/javascript" src="<c:out value='${ctx}'/>/scripts/cart.js" charset="UTF-8"></script>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>