<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <form class="navbar-form navbar-left" role="search" action="<c:out value='${ctx}'/>/search" method="get">
        <div class="form-group" id="searchbox">
          <input type="text" name="query" autocomplete="off" class="form-control" id="keyword" placeholder="搜尋商品">
          <ul id="hits" style="display: none;">
          </ul>
        </div>
        <button class="btn btn-default">搜尋</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
      	<li><a><span id="cart" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>
	 	<c:choose>
		    <c:when test="${login == null}">
		        <li><a href="<c:out value='${ctx}'/>/jsp/login.jsp">登入</a></li>
		        <li><a href="<c:out value='${ctx}'/>/jsp/register.jsp">註冊帳號</a></li>
		    </c:when>    
		    <c:otherwise>
		        <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${login.userId}<span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="<c:out value='${ctx}'/>/jsp/account/profile.jsp">我的帳戶</a></li>
		            <li><a href="<c:out value='${ctx}'/>/account?action=myOrder">我的訂單</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="<c:out value='${ctx}'/>/login?action=logout">登出</a></li>
		          </ul>
		        </li>         
		    </c:otherwise>
		</c:choose>	
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div id="cartContent">
          <li><a href="#">
           		購物車<br>
					<ul id="cartItem" style="display: none;">
					</ul>
				<c:forEach var="bean" items="${map}">
					<ul>
						<li>商品: ${bean.key.productId} - ${bean.value} 個</li>
					</ul>
				</c:forEach>
             </a></li>
             <form method="post" action="<c:out value='${ctx}'/>/bill">
          	 	<button type="submit">查看購物車</button>
          	 </form>
</div>