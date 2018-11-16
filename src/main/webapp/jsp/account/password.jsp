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
		<li>我的訂單</li>
		<li>更改密碼</li>
		</ul>
		</div>
		<div class="col-sm-10">
<main class="container p-5">
<form action="<c:out value='${ctx}'/>/account" method="post">
        <div class="form-group">
            <input autofocus class="form-control" name="passwordOld" placeholder="舊密碼" type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
        </div>
        <div class="form-group">
            <input autofocus class="form-control" name="passwordNew" placeholder="新密碼" type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
        </div>
        <div class="form-group">
            <input autofocus class="form-control" name="passwordNew2" placeholder="密碼確認" type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
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