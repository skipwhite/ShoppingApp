<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/include.jsp" %>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>ShoppingApp 買堅果</title>

</head>
<body> 
<%@ include file="/common/navbar.jsp"%>
<div class="full-banner container">
    <div class="main-banner thumbnail">
        <div style="background-image: url(&quot;https://assets.bonappetit.com/photos/58000512a9151a0762f43fdb/master/w_1200,c_limit/nuts-almonds-cashews-pistachios-pecans.jpg;); background-size: cover; background-repeat: no-repeat;"></div>
        <img src="https://assets.bonappetit.com/photos/58000512a9151a0762f43fdb/master/w_1200,c_limit/nuts-almonds-cashews-pistachios-pecans.jpg">
    </div>
    <div class="right-banner thumbnail">
    <img  thumbnail src="https://www.naturaloilsforhair.net/wp-content/uploads/2013/11/macadamia-nuts-oil-for-hair-growth-640x360.jpg">
            <img  thumbnail src="https://www.naturaloilsforhair.net/wp-content/uploads/2013/11/macadamia-nuts-oil-for-hair-growth-640x360.jpg">

    </div>
</div>


<a href="<c:out value='${ctx}'/>/bill">結帳bill</a>  
<a href="<c:out value='${ctx}'/>/jsp/ajax.jsp">ajax</a>  
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