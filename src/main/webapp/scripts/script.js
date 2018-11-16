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

var num = document.getElementById("num");
var minus = document.getElementById("minus");
var plus = document.getElementById("plus");
var cart = document.getElementById("cart");
var cartContent = document.getElementById("cartContent");

cart.addEventListener("mouseover",function(){
cartContent.style.visibility = 'visible';
})
cartContent.addEventListener("mouseover",function(){
cartContent.style.visibility = 'visible';
})

cart.addEventListener("mouseout",function(){
cartContent.style.visibility = 'hidden';
})
cartContent.addEventListener("mouseout",function(){
cartContent.style.visibility = 'hidden';
})

minus.addEventListener("click",function(){
	if (num.value > 0){
		num.value -= 1;
	}
})

plus.addEventListener("click",function(){
	num.value = parseInt(num.value) + 1;
})

