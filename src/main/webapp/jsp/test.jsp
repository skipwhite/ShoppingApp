<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>TEST</title>
</head>
<body> 

<form action="<c:out value='${ctx}'/>/test" method="post">

    <input autofocus class="form-control" name="name" placeholder="收件者" type="text">
    <input autofocus class="form-control" name="phone" placeholder="手機號碼" type="text">
    <input autofocus class="form-control" name="zipCode" placeholder="郵遞區號" type="text">
    <input autofocus class="form-control" name="address" placeholder="地址" type="text">
    <input type="submit" value="Submit"><br>
</form>

<form action="<c:out value='${ctx}'/>/test" method="post">
id <input type="text" name="id" value="${id}">${id}<br>
num <input type="text" name="num" value="${num}">${num}<br>
deci <input type="text" name="deci" value="${deci}">{deci}<br>
bin <input type="text" name="bin" value="${bin}">${bin}<br>
timestamp <input type="date" name="timestamp" value="${timestamp}">${timestamp}<br>
<input type="submit" value="Submit"><br>
<ul>
	<c:forEach items="${errMessage}" var="content">
		<li><c:out value="${content}" /></li>
	</c:forEach>
</ul>
${SucessMessage}
</form>




<%@ include file="/common/footer.jsp"%>
</body>
</html>