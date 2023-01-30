/**
 * 비주얼 이미지 컨트롤
 */

//var size=390;//이미지사이즈(360)+30
	
var marginLeft=390;
var basePos=marginLeft*1;
var speed=300;
var timmer=3000;
var myTimeout;
var flag=false;
var init=true;

	
$(function(){

	var lis=$("ol.visuals li");
		lis.first().before(lis.last());
		
	$(".visuals").css("margin-left", -basePos);
	start();
	
	//$(".visuals, .btn").hover(stop, start);
	
	$(".next").click(move);
	$(".prev").click(prev);
	$(".hold").click(toggle);
});


//브라우저의 화면이 보여지냐 숨겨지냐에 따라 실행되는 이벤트 
document.addEventListener("visibilitychange", function() {
	
	console.log( document.visibilityState );
	if(document.visibilityState=="hidden"){
		stop();
	}else if(document.visibilityState=="visible"){
		
		start();
	}
});

function start(){
	myTimeout = setTimeout(run, timmer);
}
function run(){
	console.log("타이머 시작");
	move();
	myTimeout = setTimeout(run, timmer);
}
function stop(){
	clearTimeout(myTimeout);
	console.log("타이머 멈춤");	
}

function move(){ 
	if(flag)return;//true(실행중) 이면 아래실행하지 않고 종료
	flag=true;
	
	
	var imgWrap=$(".visuals");
	//marginLeft += size;
	imgWrap.animate({marginLeft: -basePos-marginLeft},speed, function(){
		var lis=$("ol.visuals li");

		//첫번째 li태그가 마지막 li태그뒤로 이동
		lis.last().after(lis.first());
		imgWrap.css("margin-left", -basePos);
		flag=false;
	});
	count("plus");
}

function prev(){
	if(flag)return;//true(실행중) 이면 아래실행하지 않고 종료
	flag=true;
	var imgWrap=$(".visuals");
	imgWrap.animate({marginLeft: -basePos+marginLeft},speed,function(){
		//이미지 이동
		//첫번째 li태그가 마지막 li태그뒤로 이동
		var lis=$("ol.visuals li");
		lis.first().before(lis.last());
		imgWrap.css("margin-left", -basePos);
		flag=false;
	});
	count("minus")
}


function toggleImg(){
	let status_ = document.querySelector(".pause");
	if(status_ !=null) {
		run();
		status_.classList.remove("pause");
		return;
	}
	
	let play = document.querySelector(".play");
	play.classList.toggle("pause");
	stop();
	
}

function count(type)  {
	// 결과를 표시할 element
	const resultElement = document.getElementById('wrap_list');
	
	// 현재 화면에 표시된 값
	let number = resultElement.innerText;
	
	// 더하기/빼기
		if(type==='plus')
	{
	  number = parseInt(number) + 1;
		if(number==6) number=1;
	}else if(type==='minus'){
		number=parseInt(number)-1;
		if(number==0) number=5;
	}
	// 결과 출력
	resultElement.innerText = number;
  }
  
  
  // 시간 카운트 다운 함수
   setInterval(function time(){
      //시간 초기화
      var d = new Date();
      var hours = 24 - d.getHours();
      var min = 60 - d.getMinutes();
      var sec = 60 - d.getSeconds();
      //분이 있으면 시 반올림
      if(min =='00'){
          hours = 24 - d.getHours();
        }else{
          hours = 23 - d.getHours();
      }
      //초가 있으면 분 반올림        
      if(sec =='00'){
          min = 60 - d.getMinutes();
        }else{
          min = 59 - d.getMinutes();
      }
      //1자리수라면 0을 붙혀라
      if((hours + '').length == 1){
        hours = '0' + hours;
      }
      if((min + '').length == 1){
        min = '0' + min;
      }
      if((sec + '').length == 1){
        sec = '0' + sec;
      }
      //날짜를 표기하고 딜레이는 1초(1000)마다 바뀌겠금
      jQuery('#the-final-countdown p').html
          ('<span class="t_hour">'+hours+'</span>'+
           '<span class="t_colon"> : </span>'+
           '<span class="t_min">'+min+'</span>'+
           '<span class="t_colon"> : </span>'+
           '<span class="t_sec">'+sec+'</span>')
  
    }, 1000);
      
      
  function btn_color_change(type){
	let status_ = document.querySelector(".active");
	if(status_ !=null) {
		
		status_.classList.remove("active");
	}
	
	let btn = document.getElementsByClassName("Category_button");
	if(type===1){
	btn[0].classList.toggle("active");
	}
	if(type===2){
	btn[1].classList.toggle("active");
	}
	if(type===3){
	btn[2].classList.toggle("active");
	}
	if(type===4){
	btn[3].classList.toggle("active");
	}
	if(type===5){
	btn[4].classList.toggle("active");
	}
	if(type===6){
	btn[5].classList.toggle("active");
	}
	if(type===7){
	btn[6].classList.toggle("active");
	}
	if(type===8){
	btn[7].classList.toggle("active");
	}
	
}
  
  
