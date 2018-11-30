$(function(){
	// 水平捲動方向：-1表示向左；1表示向右
	var dir = -1; 
	
	// 水平捲動的時間間隔(每隔幾秒捲動一次。設定為3000毫秒)
	var interval = 5000;
	
	// 水平捲動速度(設定為700毫秒)
	var duration = 700;
	
	// 計時器用的變數
	var timer;

	// 修改圖片順序(將圖片3改為第1個圖)
	$("#slide ul").prepend($("#slide li:last-child"));
	
	// 修改圖片位置(偏移1張圖片的寬度)
	$("#slide ul").css("left", -1000);

	// 每隔3000毫秒(變數interval的值)執行一次slideTimer()函式
	timer = setInterval(slideTimer, interval);

	function slideTimer(){
		if(dir == -1){
			$("#slide ul").animate({"left" : "-=1000px" }, duration, function(){
				$(this).append($("#slide li:first-child"));
				$(this).css("left", -1000);
			});
		}else{
			$("#slide ul").animate({"left" : "+=1000px" }, duration, function(){
				$(this).prepend($("#slide li:last-child"));
				$(this).css("left", -1000);
				dir = -1;
			});
		}
	}

	// 按鈕：播放上一張圖片
	$("#prevBtn").click(function(){
		// 切換水平捲動的方向(向右)
		dir = 1;
		
		// 將計時器停止後重啟
		clearInterval(timer);
		timer = setInterval(slideTimer, interval);
		
		// 計時開始時顯示的圖片
		slideTimer();
	});
	
	// 按鈕：播放下一張圖片
	$("#nextBtn").click(function(){
		// 切換水平捲動的方向(向左)
		dir = -1;
		clearInterval(timer);
		timer = setInterval(slideTimer, interval);
		
		slideTimer();
	});
});