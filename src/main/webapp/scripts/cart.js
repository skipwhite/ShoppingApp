var productId = document.getElementById("productId");
var addToCart = document.getElementById("addToCart");
var bill = document.getElementById("bill");
var num = document.getElementById("num");
var minus = document.getElementById("minus");
var plus = document.getElementById("plus");

$(function(){
	$("a.smallImg").mouseover(function(){
		$("figure img").attr("src",$(this).attr("href"));
		return false;
	});
});

$(function(){
	$(".imgSm").mouseover(function(){
		$(this).css("border", "2px solid #d3bc2b");
	});
	$(".imgSm").mouseout(function(){
		$(this).css("border", "2px solid white");
	});
});

bill.addEventListener("click",function(){
	var oriQty = getCookie(productId.value);
	
	if(oriQty != "") {
		document.cookie = productId.value + "=" + (Number(num.value) + Number(oriQty));
	} else {
		document.cookie = productId.value + "=" + num.value;
	}
})

addToCart.addEventListener("click",function(){
	var oriQty = getCookie(productId.value);

	if(oriQty != "") {
		document.cookie = productId.value + "=" + (Number(num.value) + Number(oriQty));
	} else {
		document.cookie = productId.value + "=" + num.value;
	}
	showCart();	
	cartContent.style.visibility = 'visible';
	setTimeout(function(){cartContent.style.visibility = 'hidden';},3000);
})

minus.addEventListener("click",function(){
	if (num.value > 1){
		num.value -= 1;
	}
})

plus.addEventListener("click",function(){
	num.value = parseInt(num.value) + 1;
})



