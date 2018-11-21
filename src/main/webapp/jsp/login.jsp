<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>登入頁面</title>
</head>
<body> 
<%@ include file="/common/navbar.jsp"%>
    <main class="container p-5">
		<form action="<c:out value='${ctx}'/>/login" method="post">
                <div>
		            <h2>登入</h2>
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="userId" placeholder="使用者代號" type="text" value="${userId}" required>
		        </div>
		        <div class="form-group">
		            <input autofocus class="form-control" name="password" placeholder="密碼" type="password" required>
		        </div>
				<c:forEach items="${errMessage}" var="content">
					<c:out value="${content}" /><br>
				</c:forEach>
		    <button class="btn btn-primary btn-lg" type="submit" name ="action" value="login">登入</button>
		</form>
	</main>	

<%@ include file="/common/footer.jsp"%>
</body>
</html>