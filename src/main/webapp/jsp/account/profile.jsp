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
		<li><a href="<c:out value='${ctx}'/>/account?action=myOrder">我的訂單</a></li>
		<li><a href="<c:out value='${ctx}'/>/jsp/account/password.jsp">更改密碼</a></li>
		</ul>
		</div>
		<div class="col-sm-10">
<main class="container p-5">
<form action="<c:out value='${ctx}'/>/account" method="post">
        <div class="form-group">
            <input autofocus class="form-control" name="name" placeholder="姓名" type="text" value="${bean.name}" required>
        </div>
        <div class="form-group">
            <input autofocus class="form-control" name="email" placeholder="Email" type="text" value="${bean.email}" required>
	        </div>
		<div class="form-group">
	            <input autofocus class="form-control" name="phone" placeholder="手機號碼" type="text" value="${bean.phone}">
	    </div>  
           <div class="form-group">
	            <input autofocus class="form-control" name="shipStore" placeholder="運送店家" type="text" value="${bean.shipStore}">
	    </div>  
           <div class="form-group">
	            <input autofocus class="form-control" name="zipCode" placeholder="郵遞區號" type="text" value="${bean.zipCode}">
	    </div>          
           <div class="form-group">
	            <input autofocus class="form-control" name="address" placeholder="地址" type="text" value="${bean.address}">
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