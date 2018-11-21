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
<div class="container">
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
	        <div class="col-sm-1">${i.key.price}</div>
	        <div class="col-sm-2" style="display:flex;">
	        	<div>
		        	<input type="hidden" name="productId" id="productId" value="${pd.productId}">
		        	<button id="minus${i.key.productId}" class="glyphicon glyphicon-minus"></button>
					<input class="num${i.key.productId}" type="text" name="qty" value="${i.value}" style="width:33px; text-align:center;"> 
					<span id="plus${i.key.productId}" class="glyphicon glyphicon-plus"></span>
	        	</div>
	        </div>
	        <div class="col-sm-2">${i.key.price*i.value}</div>        
	        <div class="col-sm-1">
	    	<button id="delItem" type="submit" value="${i.key.productId}">刪除</button>
	    	</div>
	    </div>
    </c:forEach>
    		    <h2>TOTAL: ${totalPrice}$</h2>
    <div class="row">
    配送方式
    <form method="post" action="<c:out value='${ctx}'/>/bill">
    
        <div>
          <input id="ship00" type="radio" name="shipId" value="0" onchange="showPayment(this.value)"> 宅配 
          <input id="ship01" type="radio" name="shipId" value="1" onchange="showPayment(this.value)"> 超商取貨
        </div>
        <div class="pay">
          <input class="ship00" type="radio" name="payId" value="0"> 信用卡 
          <input type="radio" name="payId" value="1"> 超商取貨付款 
        </div>
        <div class="address">
            <div class="form-group">
		            <input autofocus class="form-control ship00" name="name" placeholder="收件者" type="text" value="${login.name}" required>
		    </div>
            <div class="form-group">
		            <input autofocus class="form-control ship00" name="phone" placeholder="手機號碼" type="text" value="${login.phone}" required>
		    </div>  
            <div class="form-group">
		            <input autofocus class="form-control" name="shipStore" placeholder="運送店家" value="${s_stName}" type="text">
		    </div>  
            <div class="form-group">
		            <input autofocus class="form-control ship00" name="zipCode" placeholder="郵遞區號" value="${login.zipCode}" type="text">
		    </div>          
            <div class="form-group">
		            <input autofocus class="form-control ship00" name="address" placeholder="地址" value="${login.address}" type="text">
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


<script type="text/javascript" src="<c:out value='${ctx}'/>/scripts/bill.js" charset="UTF-8"></script>
<%@ include file="/common/footer.jsp"%>
</body>
</html>