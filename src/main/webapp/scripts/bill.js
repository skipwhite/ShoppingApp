var delItem = document.getElementById("delItem");
var totalPrice = document.getElementById("totalPrice");
var nums = document.getElementsByClassName("num");
var ship00 = document.getElementsByClassName("ship00");
var ship01 = document.getElementById("ship01");
var unitPrice = document.getElementById("unitPrice");
var unitTotal = document.getElementById("unitTotal");


document.getElementById("cartIcon").style.visibility = 'hidden';

$( ".num" ).change(function() {

});

function updateCookie(productId, num){
	document.cookie = pad(productId,5) + "=" + num;
	alert(document.cookie);
	reloadBill();
}

function reloadBill(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/ShoppingApp/billUpdate', true);
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var ret = JSON.parse(xhr.responseText);
			document.getElementById("totalPrice").innerHTML = ret; 
		}
	};
	xhr.send();
}


function pad(num, size) {
    var s = num + "";
    while (s.length < size) s = "0" + s;
    return s;
}

function minus(id){
	var productId = pad(id,5);
	var num = document.getElementById(productId);
	if (num.value > 1){
		num.value -= 1;
	}
	//update cookie
	document.cookie = productId + "=" + num.value;
	unitTotal.innerHTML = unitPrice.innerHTML * num.value;
	reloadBill();
}

function plus(id){
	var productId = pad(id,5);
	var num = document.getElementById(productId);
	num.value = parseInt(num.value) + 1;
	//update cookie
	document.cookie = productId + "=" + num.value;
	unitTotal.innerHTML = unitPrice.innerHTML * num.value;
	reloadBill();
}

function showPayment(shipId){
	if (shipId == 0){
		for (i = 0; i < ship00.length; i++){
			ship00[i].style.visibility = 'visible';
		}
	}
}


delItem.addEventListener("click",function(){
	document.cookie = delItem.value + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
})
