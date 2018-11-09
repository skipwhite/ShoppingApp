<%@ include file = "/common/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<script>
function readURL(input) {

	  if (input.files && input.files[0]) {
	    var reader = new FileReader();

	    reader.onload = function(e) {
	      $('#blah').attr('src', e.target.result);
	    }

	    reader.readAsDataURL(input.files[0]);
	  }
	}

	$("#imgInp").change(function() {
	  readURL(this);
	});
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<head>
<%@ include file="/common/header.jsp"%>
<title>TEST</title>
</head>
<body> 

<form action="<c:out value='${ctx}'/>/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" />
</form>
    <form id="form1" runat="server" action="<c:out value='${ctx}'/>/upload" method="post" enctype="multipart/form-data">
        <input type='file' id="imgInp" />
        <img id="blah" src="#" alt="your image" />
            <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" />
    </form>
<%@ include file="/common/footer.jsp"%>
</body>
</html>