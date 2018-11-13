<%@ include file = "/common/include.jsp" %>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>INDEX</title>
</head>
<body>
<h3>Cookie Example through Shopping Cart</h3>
<form method="get" action="<c:out value='${ctx}'/>/cart">
  
  Enter Item Name <input type="text" name="item"><br>
  Enter Item Quantity <input type="text" name="qty"><br>
  <input type="submit" value="Add Cookie" name="add">
  <input type="submit" value="List Cookies" name="list">
 
</form>
</body>
</html>