var productId = document.getElementById("productId");
var addToCart = document.getElementById("addToCart");
var num = document.getElementById("num");
var minus = document.getElementById("minus");
var plus = document.getElementById("plus");
var cart = document.getElementById("cart");
var cartContent = document.getElementById("cartContent");

function getCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i = 0; i < ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
  }
  return "";
}
updateCart();

addToCart.addEventListener("click",function(){
	var oriQty = getCookie(productId.value);

	if(oriQty != "") {
		document.cookie = productId.value + "=" + (Number(num.value) + Number(oriQty));
	} else {
		document.cookie = productId.value + "=" + num.value;
	}
	updateCart();	
	cartContent.style.visibility = 'visible';
})

function updateCart(){
	// Loop through all cookie and show on cart
	var cartItem = document.getElementById('cartItem');
	var cookies = document.cookie.split(';');
	var list = [];
	for(var i = 0; i < cookies.length; i++) {
	    var name = cookies[i].substring(0, cookies[i].indexOf('='));
	    var value = cookies[i].substring(cookies[i].indexOf('=') + 1);
	    list.push('<li>商品 ' + name + " - " + value + ' 個</li>');
	}
	cartItem.innerHTML = list.join('');
	cartItem.style.display = 'block';
	// show name and qty
}

cart.addEventListener("mouseover",function(){
cartContent.style.visibility = 'visible';
})
cart.addEventListener("mouseout",function(){
cartContent.style.visibility = 'hidden';
})
cartContent.addEventListener("mouseover",function(){
cartContent.style.visibility = 'visible';
})
cartContent.addEventListener("mouseout",function(){
cartContent.style.visibility = 'hidden';
})

minus.addEventListener("click",function(){
	if (num.value > 1){
		num.value -= 1;
	}
})

plus.addEventListener("click",function(){
	num.value = parseInt(num.value) + 1;
})

document.getElementById('keyword').oninput = function() {
		var keyword = this.value;
		// 典型的ajax编程模板
		// 1. 创建XMLHttpReqeust对象
		var xhr = new XMLHttpRequest();
		// 2. open操作初始化请求信息
		xhr.open('GET', '/ShoppingApp/a2?keyword='
				+ encodeURIComponent(keyword), true);
		// 3. 监听事件处理响应结果
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				handleResult(xhr.responseText);
			}
		};
		// 4. send操作发出请求
		xhr.send();
	};
	// 处理响应结果

	function handleResult(ret) {
		ret = JSON.parse(ret);
		var domHits = document.getElementById('hits');
		if (ret.length) {
			var lis = [];
			for (var i = 0, len = ret.length; i < len; ++i) {
				ret[i].name = decodeURI(ret[i].name);
				
				lis.push('<li>' + ret[i].name + '</li>');
				console.log(ret[i].name);
//				alert(ret[i].name);
			}
			domHits.innerHTML = lis.join('');
			domHits.style.display = 'block';
		} else {
			domHits.innerHTML = '';
			domHits.style.display = 'none';
		}
	}
//	function handleResult(ret) {
//		ret = JSON.parse(ret);
//		var domHits = document.getElementById('hits');
//		if (ret.length) {
//			var lis = [];
//			for (var i = 0, len = ret.length; i < len; ++i) {
//				lis.push('<li>' + ret[i] + '</li>');
//			}
//			domHits.innerHTML = lis.join('');
//			domHits.style.display = 'block';
//		} else {
//			domHits.innerHTML = '';
//			domHits.style.display = 'none';
//		}
//	}
	// 单击提示结果放入输入框
	document.getElementById('hits').onclick = function(event) {
		document.getElementById('keyword').value = event.target.innerHTML;
		this.style.display = 'none';
	}


