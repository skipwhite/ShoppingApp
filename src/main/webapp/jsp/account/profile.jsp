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
		        <h3 class="profileTitle">我的帳戶</h3>
		    </div>
		    <hr>		
			<form action="<c:out value='${ctx}'/>/account" method="post">
	        <div class="inputWrapper">
		        <div class="inputNameProfile">姓名</div>
		        <div class="inputContentProfile">
		            <input autofocus class="form-control profile" name="name" placeholder="姓名" type="text" value="${login.name}" required>
		        </div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">Email</div>
		        <div class="inputContentProfile">
		            <input autofocus class="form-control profile" name="email" placeholder="Email" type="text" value="${login.email}" required>
		        </div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">手機號碼</div>
		        <div class="inputContentProfile">
		            <input autofocus class="form-control profile" name="phone" placeholder="手機號碼" type="text" value="${login.phone}">
		        </div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">常用店家</div>
		        <div class="inputContentProfile">
		            <input autofocus class="form-control profile" name="shipStore" placeholder="運送店家" type="text" value="${login.shipStore}">
		        </div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">郵遞區號</div>
		        <div class="inputContentProfile">
		            <input autofocus class="form-control profile" name="zipCode" placeholder="郵遞區號" type="text" value="${login.zipCode}">
		        </div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">地址</div>
		        <div class="inputContentProfile">
		            <input autofocus class="form-control profile" name="address" placeholder="地址" type="text" value="${login.address}">
		        </div>
	        </div>			
				    <button id="profileButton"class="btn btn-primary btn-lg" type="submit" name ="action" value="save">儲存</button>
				</form>
		</div>
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>