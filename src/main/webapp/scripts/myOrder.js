  let stars = Array.from(document.getElementsByClassName("star"));
  var rate = document.getElementById("rate");
  
  stars.forEach(elm => elm.addEventListener('mouseover', function(e){
    // 找出目前元素的index
    let index = stars.findIndex(elm=> elm === e.target);
    rate.value = index + 1;
    
    // 將目前元素之前（包含目前元素）的設為彩色
    for (i=0;i<=index;i++){
        stars[i].style.filter = 'grayscale(0)';
    }
    
    // 將目前元素之後的設為黑白
    for (i=index+1;i<stars.length;i++){
        stars[i].style.filter = 'grayscale(1)';
    }
  }))