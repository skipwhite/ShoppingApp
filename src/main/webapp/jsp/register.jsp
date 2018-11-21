<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>註冊頁面</title>
</head>
<body> 
<%@ include file="/common/navbar.jsp"%>
    <main class="container p-5">
		<form action="<c:out value='${ctx}'/>/register" method="post">
                <div>
		            <h2>註冊</h2>
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="userId" placeholder="使用者代號" type="text" value="${userId}" required>
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="name" placeholder="姓名" type="text" value="${name}" required>
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="email" placeholder="Email" type="text" value="${email}" required>
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="password" placeholder="密碼" type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="password2" placeholder="再次確認密碼" type="password" required pattern=".{8,20}" title="至少8-20個字元包含英文數字">
		        </div>

		        <ul>
					<c:forEach items="${errMessage}" var="content">
						<li><c:out value="${content}" /></li>
					</c:forEach>
				</ul>

		    <button class="btn btn-primary btn-lg" type="submit" name ="action" value="register">註冊</button>
		</form>
	</main>	

<%@ include file="/common/footer.jsp"%>
</body>
</html>