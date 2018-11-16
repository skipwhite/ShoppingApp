<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/include.jsp" %>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>ShoppingApp 買堅果</title>
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
      <a class="navbar-brand" href="<c:out value='${ctx}'/>">ShoppingApp</a>
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

      	<li><a href="#" class="cart"><span id="cart" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>
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
            <li><a href="<c:out value='${ctx}'/>/login?action=logout">登出</a></li>
          </ul>
        </li>         
        <% }%>  

      </ul>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div id="cartContent">
          <li><a href="#">
           		購物車<br>
				<c:forEach var="bean" items="${map}">
					<ul>
						<li>商品: ${bean.key.productId} - ${bean.value} 個</li>
					</ul>
				</c:forEach>
             </a></li>
          <button>查看購物車</button>
</div>



<a href="<c:out value='${ctx}'/>/addToCart?action=bill">結帳</a>  
<a href="<c:out value='${ctx}'/>/bill">結帳bill</a>  
	<a href="<c:out value='${ctx}'/>/jsp/test.jsp">Test</a>
	<a href="<c:out value='${ctx}'/>/jsp/upload.jsp">Upload</a>
	<a href="<c:out value='${ctx}'/>/jsp/newProduct.jsp">New Product</a>
	
<div class="container">
	<div class="row">
		<c:forEach var="i" items="${maps}">
			<div class="col-lg-4 col-sm-6">
				<div class="thumbnail">
					<a href="<c:out value='${ctx}'/>/product?productId=${i.key.productId}" class="pic">
						<img src="data:image/jpg;base64,${i.value}" alt="No image">
					</a>
					${i.key.name}<br>
					<strong>${i.key.price}$</strong>
				</div>
			</div>
		</c:forEach>

	</div>
</div>


<%@ include file="/common/footer.jsp"%>
</body>
</html>