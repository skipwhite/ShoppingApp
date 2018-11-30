<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>

<title>新增商品</title>
</head>
<body> 

<main class="container p-5">
<form action="<c:out value='${ctx}'/>/newProduct" method="post" enctype="multipart/form-data">
       <div class="form-group">
           <input autofocus class="form-control" name="name" value="${name}" placeholder="商品名稱" type="text" >
       </div>
       <div class="form-group">
			<textarea name="dscr" rows="2" cols="30" placeholder="商品描述"></textarea>
       </div>
       <div class="form-group">
           <input autofocus class="form-control" name="category" value="${category}" placeholder="類別" type="text" >
       </div>
       <div class="form-group">
           <input autofocus class="form-control" name="price" value="${price}" placeholder="價格" type="number">
       </div>
       <div class="form-group">
           <input autofocus class="form-control" name="inventory" value="${inventory}" placeholder="庫存" type="number" >
       </div>
       <div class="form-group">
           <input autofocus class="form-control" name="tag" value="${tag}" placeholder="促銷標籤" type="hidden" >
       </div>
       <div class="form-group">
           <input autofocus class="form-control" name="discount" value="${discount}" placeholder="促銷折扣" type="hidden" >
       </div>
       <div class="form-group">
           <input autofocus class="form-control" name="launched" value="${launched}" placeholder="是否上架  1/0" type="text" >
       </div>
       <div class="form-group">
	    <input  class="form-control" type="file" name="file" multiple="true" onchange="readURL(this); "/>
	    <img id="blah" src="http://placehold.it/180" alt="your image" />
 	   <input type="submit" />
       </div>
</form>

</main>	

<%@ include file="/common/footer.jsp"%>
</body>
</html>