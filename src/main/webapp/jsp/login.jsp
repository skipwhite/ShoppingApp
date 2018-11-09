<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>登入頁面</title>
</head>
<body> 
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:out value='${ctx}'/>/index.jsp">ShoppingApp</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">商品分類<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">分類A</a></li>
            <li><a href="#">分類B</a></li>
            <li><a href="#">分類C</a></li>
          </ul>
        </li> 
        <li><a href="#">Link</a></li>
        
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" id="mainSearch" placeholder="搜尋商品">
        </div>
        <button type="submit" class="btn btn-default">搜尋</button>
      </form>
      
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="#" class="cart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>
        <%
            String name = (String) session.getAttribute("login");        
            String role = (String) session.getAttribute("role");
            if (name == null) {
        %>
        <li><a href="<c:out value='${ctx}'/>/jsp/login.jsp">登入</a></li>
        <li><a href="<c:out value='${ctx}'/>/jsp/register.jsp">註冊帳號</a></li>
        <% } else { %>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=name %><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">我的帳戶</a></li>
            <%
            	if (role != null && role.equals("vendor")) {
            %>
            <li><a href="#">維護商品</a></li>
            <% } %>
            <li><a href="#">我的訂單</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">登出</a></li>
          </ul>
        </li>         
        <% }%>  

      </ul>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>





    <main class="container p-5">
		<form action="<c:out value='${ctx}'/>/login" method="post">
                <div>
		            <h2>註冊</h2>
		        </div>
		        <div class="form-group">
		            <input autocomplete="off" autofocus class="form-control" name="userId" placeholder="使用者代號" type="text" value="${userId}">
		        </div>
		        <div class="form-group">
		            <input autocomplete="off" autofocus class="form-control" name="password" placeholder="密碼" type="text">
		        </div>
		        <ul>
					<c:forEach items="${errMessage}" var="content">
						<li><c:out value="${content}" /></li>
					</c:forEach>
				</ul>

		    <button class="btn btn-primary btn-lg" type="submit">註冊</button>
		</form>
	</main>	

<%@ include file="/common/footer.jsp"%>
</body>
</html>