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