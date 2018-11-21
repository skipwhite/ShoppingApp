var productId = document.getElementById("productId");
var delItem = document.getElementById("delItem");
var num = document.getElementById("num");
var minus = document.getElementById("minus");
var plus = document.getElementById("plus");

delItem.addEventListener("click",function(){
	document.cookie = delItem.value +"=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
})

minus.addEventListener("click",function(){
	if (num.value > 1){
		num.value -= 1;
	}
})

plus.addEventListener("click",function(){
	num.value = parseInt(num.value) + 1;
})