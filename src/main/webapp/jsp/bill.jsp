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
<%@ include file="/common/navbar.jsp"%>
<div class="container bill">
    <div>
        <h2>Shopping App | 結帳 ${message}</h2>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <h3>訂單商品</h3>
        </div>
        <div class="col-sm-1">
            <h4>單價</h4>
        </div>
        <div class="col-sm-2">
            <h4>數量</h4>
        </div>
        <div class="col-sm-2">
            <h4>總價</h4>
        </div>  
        <div class="col-sm-1">
            <h4>操作</h4>
        </div>       
    </div>
	<c:forEach var="i" items="${beanCart}">
	    <div class="row">
	        <div class="col-sm-6"><img>${i.key.name}</div>
	        <div class="col-sm-1"><span id="unitPrice">${i.key.price}</span></div>
	        <div class="col-sm-2" style="display:flex;">
	        	<div>
		        	<input type="hidden" name="productId" id="productId" value="${i.key.productId}">
		        	<span onclick="minus(${i.key.productId})" class="glyphicon glyphicon-minus"></span>
					<input id="${i.key.productId}" class="num" onchange="updateCookie(${i.key.productId},this.value)" type="text" name="qty" value="${i.value}" style="width:33px; text-align:center;"> 
					<span onclick="plus(${i.key.productId})" class="glyphicon glyphicon-plus"></span>	        	
	        	</div>
	        </div>
	        <div class="col-sm-2">
	        <span id="unitTotal">${i.key.price*i.value}</span></div>        
	        <div class="col-sm-1">
			<a href="/ShoppingApp/bill">
		    	<button id="delItem" type="submit" value="${i.key.productId}">刪除</button>
			</a>	        
	    	</div>
	    </div>
    </c:forEach>
    		    <h2>TOTAL: 
    		    <span id="totalPrice">${totalPrice}$</span>
    		    </h2>
    <div class="row">
    <div class="title">
  		<h3 class="curr">配送方式</h3>  
    </div>
    <form method="post" action="<c:out value='${ctx}'/>/bill">
    
        <div>
        	<ul>
	        	<li class="billList">
			          <input id="ship00" type="radio" name="shipId" value="0" onchange="showPayment(this.value)" checked> 宅配 
	        	</li>
	        	<li class="billList">
			          <input id="ship01" type="radio" name="shipId" value="1" onchange="showPayment(this.value)"> 超商取貨
	        	</li>
        	</ul>
        </div>
    <div class="title">
  		<h3 class="curr">付款方式</h3>  
    </div>        
     
        <div class="pay">
        <ul>
        	<li class="billList">        
         			<input class="ship00" type="radio" name="payId" value="0" checked> 信用卡 
        	</li>
        	<li class="billList">          
         			<input type="radio" name="payId" value="1"> 超商取貨付款 
       		</li>
       	</ul>
        </div>
    <div class="title">
  		<h3 class="curr">訂購人資訊</h3>  
    </div>           
        <div class="address">
	        <div class="inputWrapper">
		        <div class="inputName">姓名</div>
		        <div class="inputContent">
		            <input autofocus class="form-control ship00" name="name" placeholder="收件者" type="text" value="${login.name}" required>
		        </div>
	        </div>
	        <div class="inputWrapper">
		        <div class="inputName">手機號碼</div>
		        <div class="inputContent">
		            <input autofocus class="form-control ship00" name="phone" placeholder="手機號碼" type="text" value="${login.phone}" required>
		        </div>
	        </div>
	        <div class="inputWrapper">
		        <div class="inputName">運送店家</div>
		        <div class="inputContent">
		            <input autofocus class="form-control" name="shipStore" placeholder="運送店家" value="${s_stName}" type="text">
		        </div>
	        </div>
	        <div class="inputWrapper">
		        <div class="inputName">郵遞區號</div>
		        <div class="inputContent">
		            <input autofocus class="form-control ship00" name="zipCode" placeholder="郵遞區號" value="${login.zipCode}" type="text">
		        </div>
	        </div>
	        <div class="inputWrapper">
		        <div class="inputName">地址</div>
		        <div class="inputContent">
		            <input autofocus class="form-control ship00" name="address" placeholder="地址" value="${login.address}" type="text">
		        </div>
	        </div>
		<button id="billSubmit" type="submit" name="action" value="sendOrder" class="btn btn-default btn-primary">結帳</button>
	</div>
</form>
  <!--使用 FORM SUBMIT 轉頁到電子地圖-->
  <form method="post" name="simulation_from" action="https://map.ezship.com.tw/ezship_map_web.jsp" name="storeSubmit">
	  <!--  <input name="storeSubmit" type="submit" value="選擇門市"> -->
	  <input type="hidden" name="rtURL"  value="http://localhost:8080/ShoppingApp/bill">
	  <button id="storeCheck" class="btn btn-default" type="submit">選擇門市</button><!-- 回傳網址路徑及程式名稱。請輸入完整網站路徑網址。如  -->
  </form>
    </div>


</div>

<script type="text/javascript" src="<c:out value='${ctx}'/>/scripts/bill.js" charset="UTF-8"></script>
<%@ include file="/common/footer.jsp"%>
</body>
</html>