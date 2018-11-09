<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>結帳頁面</title>
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
 
<div class="container">
    <div>
        <h2>Shopping App | 結帳</h2>
    </div>

    <div class="row">
        <div class="col-sm-7">
            <h3>訂單商品</h3>
        </div>
        <div class="col-sm-1">
            <h4>單價</h4>
        </div>
        <div class="col-sm-1">
            <h4>數量</h4>
        </div>
        <div class="col-sm-3">
            <h4>總價</h4>
        </div>        
    </div>
	<c:forEach var="i" items="${beanCart}">
	    <div class="row">
	        <div class="col-sm-7"><img>${i.key.name}</div>
	        <div class="col-sm-1">${i.key.price}</div>
	        <div class="col-sm-1">${i.value}</div>
	        <div class="col-sm-3">
	            $250單位總價
	        </div>        
	    </div>
    </c:forEach>
    		    <h2>TOTAL: ${total}</h2>
    <div class="row">
    配送方式
        <div>
          <input type="radio" checked="checked" name="con"> 7-11取貨 
        </div>
        <div>
          <input type="radio" checked="checked" name="post"> 宅配 
        </div>
        <div class="address">
            <div class="form-group">
		            <input autofocus class="form-control" name="name" placeholder="收件者" type="text">
		    </div>
            <div class="form-group">
		            <input autofocus class="form-control" name="phone" placeholder="手機號碼" type="text">
		    </div>  
            <div class="form-group">
		            <input autofocus class="form-control" name="zipCode" placeholder="郵遞區號" type="text">
		    </div>          
            <div class="form-group">
		            <input autofocus class="form-control" name="city" placeholder="城市" type="text">
		    </div>
            <div class="form-group">
		            <input autofocus class="form-control" name="area" placeholder="區" type="text">
		    </div>
            <div class="form-group">
		            <input autofocus class="form-control" name="address" placeholder="樓層街路" type="text">
		    </div>
         
        </div>

    </div>
<form method="get" action="<c:out value='${ctx}'/>/checkOut">
<button type="submit" name="sendOrder" "btn btn-primary btn-default btn-lg" style="background-color: #f0ad4e; border-color: #eea236; position:absolute; right:27%; padding: 10px 70px;">結帳</button>
<button type="submit" name="checkOrder" "btn btn-primary btn-default btn-lg" style="background-color: #f0ad4e; border-color: #eea236; position:absolute; right:50%; padding: 10px 70px;">查看訂單不結帳</button>
</form>
</div>



<%@ include file="/common/footer.jsp"%>
</body>
</html>