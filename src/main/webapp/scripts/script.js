var carts = document.getElementsByClassName("fas fa-shopping-cart");
var cartContent = document.getElementById("cartContent");

showCart();

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

function showCart(){
	// Loop through all cookie and show on cart
	var cartItem = document.getElementById('cartItem');
	var cookies = document.cookie.split(';');
	var list = [];
	var products =[];
	var values =[];
	for(var i = 0; i < cookies.length; i++) {
	    var productId = cookies[i].substring(0, cookies[i].indexOf('=')).trim();
	    var value = cookies[i].substring(cookies[i].indexOf('=') + 1);
	    products.push(productId);
	    values.push(value);
	}
	
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/ShoppingApp/showCart?productId='
			+ products.join(), true);
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var ret = JSON.parse(xhr.responseText);
			for(var i = 0; i < ret.length; i++){
				list.push('<div class="cartItem">' + ret[i].name + " - " + values[i] + ' 個</div>');
				cartItem.innerHTML = list.join('');
				cartItem.style.display = 'block';
			}
		}
	};
	xhr.send();
}

carts[0].addEventListener("mouseover",function(){
	cartContent.style.visibility = 'visible';
})
carts[0].addEventListener("mouseout",function(){
	cartContent.style.visibility = 'hidden';
})
cartContent.addEventListener("mouseover",function(){
cartContent.style.visibility = 'visible';
})
cartContent.addEventListener("mouseout",function(){
cartContent.style.visibility = 'hidden';
})

document.getElementById('keyword').oninput = function() {
		var keyword = this.value;
		// 典型的ajax编程模板
		// 1. 创建XMLHttpReqeust对象
		var xhr = new XMLHttpRequest();
		// 2. open操作初始化请求信息
		xhr.open('GET', '/ShoppingApp/search?keyword='
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
				lis.push('<li><a href="' + "/ShoppingApp/product?productId=" + ret[i].productId + '">' + ret[i].name + '</a></li>');
			}
			domHits.innerHTML = lis.join('');
			domHits.style.display = 'block';
		} else {
			domHits.innerHTML = '';
			domHits.style.display = 'none';
		}
	}
	// 单击提示结果放入输入框
	document.getElementById('hits').onclick = function(event) {
		document.getElementById('keyword').value = event.target.innerHTML;
		this.style.display = 'none';
	}


	
function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#blah')
                        .attr('src', e.target.result);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }



