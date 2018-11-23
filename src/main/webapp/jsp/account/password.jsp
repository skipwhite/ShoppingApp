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
	        <h2>更改密碼</h2>
	    </div>
	    <hr>			
<main class="container p-5">
<form action="<c:out value='${ctx}'/>/account" method="post">
        <div class="form-group">
        	<span class="text">舊密碼</span>
            <input autofocus class="form-control" name="passwordOld"  type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
        </div>
        <div class="form-group">
        	<span class="text">新密碼</span>
            <input autofocus class="form-control" name="passwordNew"  type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
        </div>
        <div class="form-group">
        	<span class="text">密碼確認</span>	
            <input autofocus class="form-control" name="passwordNew2"  type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
        </div>
		${errMessage}
	    <button class="btn btn-primary btn-lg" type="submit" name ="action" value="changePsw">更改密碼</button>
	</form>
</main>	
		</div>
	</div>
</div>




<%@ include file="/common/footer.jsp"%>
</body>
</html>