<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>AJAX DEMO</title>
<style>
* {
	font-family: "Microsoft Yahei", serif;
	font-size: 14px;
}

h1 {
	font-size: 16px;
	font-weight: normal;
}

#container {
	text-align: center;
	margin-top: 100px;
}

#searchbox {
	display: inline-block;
	width: 400px;
	position: relative;
}

#hits {
	position: absolute;
	list-style: none;
	margin: 0;
	padding: 0;
	text-align: left;
	border: 1px solid gray;
	border-top: none;
	width: 398px;
}

#hits>li {
	padding: 5px 15px;
}

#hits>li:hover {
	background: lightblue;
	cursor: default;
}

#keyword {
	border: 1px solid gray;
	outline: none;
	padding: 10px 15px;
	margin: 0;
	width: 368px;
}

#searchbtn {
	padding: 8px 15px;
}
</style>
</head>

<body>
	<a href="index.jsp">Return Index</a>
	<br>
	<div id="container">
		<h1>Input the name of lesson</h1>

		<div>
			<div id="searchbox">
				<input type="text" autocomplete="off" id="keyword" />
				<ul id="hits" style="display: none;">
				</ul>
			</div>
			<button id="searchbtn">search</button>
		</div>
	</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>