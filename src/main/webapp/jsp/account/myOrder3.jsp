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
	<div class="row">
		<div class="col-sm-2">
		<ul>
			<li>使用者：${login.userId}</li>
			<li><a href="<c:out value='${ctx}'/>/jsp/account/profile.jsp">我的帳戶</a></li>
			<li><a href="<c:out value='${ctx}'/>/account?action=myOrder">我的訂單</a></li>
			<li><a href="<c:out value='${ctx}'/>/jsp/account/password.jsp">更改密碼</a></li>
		</ul>
		</div>
		<div class="col-sm-10">
	    <div>
	        <h2>我的訂單</h2>
	    </div>
	    <hr>		
		<c:forEach var="ODP" items="${ODPList}">
		<div>
			<div>
				<h3>訂單號碼: ${ODP.ob.poNo}</h3>
			</div>
			<div>
			<table>
				<tr>
					<th>項次</th>
					<th>產品名稱</th>
					<th>數量</th>
					<th>已評價</th>
				</tr>
				<c:forEach var="odb" items="${ODP.odbl}">
					<tr>
						<td>${odb.item}</td>
						<td>
						<c:forEach var="pbb" items="${ODP.pb}">
							<c:choose>
								<c:when test="${odb.productId == pbb.productId}">
									${pbb.name}
								</c:when>
							</c:choose>
						</c:forEach>
						</td>
						<td>${odb.qty}</td>
						<td>
				<c:choose>
				    <c:when test="${odb.isCommented == false}">
					<form method="post" action="<c:out value='${ctx}'/>/comment?productId=${odb.productId}">
						尚未收到你的評價喔！
						<input type="hidden" name="poNo" value="${odb.poNo}">
						<input type="number" name="rate" value="5">
						<textarea name="comment" rows="3" cols="50" placeholder="請輸入評價"></textarea>
						<button class="btn btn-default" type="submit">去評價</button>
					</form>				        
				    </c:when>    
				    <c:otherwise>
					已完成評價！<br>
				    </c:otherwise>
				</c:choose>						
						</td>
					</tr>
				</c:forEach>
				
			</table>
			
			</div>
		</div>
		<hr>
		</c:forEach>

		</div>
	</div>
</div>




<%@ include file="/common/footer.jsp"%>
</body>
</html>