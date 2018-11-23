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
		        <h2>我的帳戶</h2>
		    </div>
		    <hr>		
			<main class="container p-5">
			<form action="<c:out value='${ctx}'/>/account" method="post">
			        <div class="form-group">
			            <span class="text">姓名</span>
			            <input autofocus class="form-control" name="name" placeholder="姓名" type="text" value="${login.name}" required>
			        </div>
			        <div class="form-group">
			        	<span class="text">Email</span>
			            <input autofocus class="form-control" name="email" placeholder="Email" type="text" value="${login.email}" required>
				    </div>
					<div class="form-group">
						<span class="text">手機號碼</span> 
						<input autofocus class="form-control" name="phone" placeholder="手機號碼" type="text" value="${login.phone}">
				    </div>  
			        <div class="form-group">
			        	<span class="text">常用店家</span>
			        	<input autofocus class="form-control" name="shipStore" placeholder="運送店家" type="text" value="${login.shipStore}">
				    </div>  
			        <div class="form-group">
			        	<span class="text">郵遞區號</span>
				        <input autofocus class="form-control" name="zipCode" placeholder="郵遞區號" type="text" value="${login.zipCode}">
				    </div>          
			        <div class="form-group">
			        	<span class="text">地址</span>
				        <input autofocus class="form-control" name="address" placeholder="地址" type="text" value="${login.address}">
				    </div>
				    <button class="btn btn-primary btn-lg" type="submit" name ="action" value="save">儲存</button>
				</form>
			</main>	
		</div>
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>