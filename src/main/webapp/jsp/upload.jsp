<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/include.jsp" %>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<style>
    img{
  max-width:180px;
}</style>
<title>TEST</title>
</head>
<body> 

<form action="<c:out value='${ctx}'/>/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" multiple="true" />
    <input type="submit" />
</form>

<input type='file' onchange="readURL(this);" />
<img id="blah" src="http://placehold.it/180" alt="your image" />
<img src="data:image/jpg;base64,${base64}" alt="No image">
		<c:forEach var="i" items="${imgList}">
			<img src="data:image/jpg;base64,${i}" alt="No image">
			------------------------------------------<br>
		</c:forEach>


		<c:forEach var="i" items="${beanList}">
			<img src="data:image/jpg;base64,${i.img}" alt="No image">
			------------------------------------------<br>
		</c:forEach>
		<c:forEach var="i" items="${bls}">
			<img src="data:image/jpg;base64,${i}" alt="No image">
			------------------------------------------<br>
		</c:forEach>

<%@ include file="/common/footer.jsp"%>
</body>
</html>