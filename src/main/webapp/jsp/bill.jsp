<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>結帳頁面</title>
</head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
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
 
<div class="container">
    <div>
        <h2>Shopping App | 結帳 ${message}</h2>
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
    		    <h2>TOTAL: ${totalPrice}$</h2>
    <div class="row">
    配送方式
    <form method="post" action="<c:out value='${ctx}'/>/bill">
    
        <div>
          <input type="radio" checked="checked" name="shipId" value="0"> 宅配 
        </div>
        <div>
          <input type="radio" name="shipId" value="1"> 7-11取貨
        </div>
        <div>
          <input type="radio" checked="checked" name="payId" value="0"> 信用卡 
        </div>
        <div class="address">
            <div class="form-group">
		            <input autofocus class="form-control" name="name" placeholder="收件者" type="text" value="${bean.name}" required>
		    </div>
            <div class="form-group">
		            <input autofocus class="form-control" name="phone" placeholder="手機號碼" type="text" value="${bean.phone}" required>
		    </div>  
            <div class="form-group">
		            <input autofocus class="form-control" name="shipStore" placeholder="運送店家" value="${s_stName}" type="text">
		    </div>  
            <div class="form-group">
		            <input autofocus class="form-control" name="zipCode" placeholder="郵遞區號" value="${bean.zipCode}" type="text">
		    </div>          
            <div class="form-group">
		            <input autofocus class="form-control" name="address" placeholder="地址" value="${bean.address}" type="text">
		    </div>
         
        </div>
<button type="submit" name="action" value="sendOrder" "btn btn-primary btn-default btn-lg" style="background-color: #f0ad4e; border-color: #eea236; position:absolute; right:27%; padding: 10px 70px;">結帳</button>
        
</form>
  <!--使用 FORM SUBMIT 轉頁到電子地圖-->
  <form method="post" name="simulation_from" action="https://map.ezship.com.tw/ezship_map_web.jsp">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse">
      <tr>
       <td align="center">
          <input name="Submit2" type="submit" value="選擇門市"></center>
          <input type="hidden" name="rtURL"  value="http://localhost:8080/ShoppingApp/addToCart?action=bill"><!-- 回傳網址路徑及程式名稱。請輸入完整網站路徑網址。如  -->
        </td>
      </tr>
    </table>
  </form>
    </div>


</div>



<%@ include file="/common/footer.jsp"%>
</body>
</html>