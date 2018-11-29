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
		        <h3 class="profileTitle">更改密碼</h3>
		    </div>
		    <hr>		
			<form action="<c:out value='${ctx}'/>/account" method="post">
	        <div class="inputWrapper">
		        <div class="inputNameProfile">舊密碼</div>
		        <div class="inputContentProfile">
            		<input autofocus class="form-control profile" name="passwordOld"  type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字"></div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">新密碼</div>
		        <div class="inputContentProfile">
            		<input autofocus class="form-control profile" name="passwordNew"  type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
		        </div>
	        </div>			
	        <div class="inputWrapper">
		        <div class="inputNameProfile">密碼確認</div>
		        <div class="inputContentProfile">
            		<input autofocus class="form-control profile" name="passwordNew2"  type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
		        </div>
	        </div>	
	        ${errMessage}
	   	 	<button id="passwordButton"class="btn btn-primary btn-lg" type="submit" name ="action" value="changePsw">更改密碼</button>
			</form>
		</div>
	</div>
</div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>