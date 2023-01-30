//Tooltip JavaScript
//btn_tooltip = button id , img_tooltip = image id
function showDetail() {
	document.querySelector('#img_tooltip').style.display = "block";
	document.querySelector('#btn_tooltip').style.display = "none";
}

function hideDetail() {
	document.querySelector('#img_tooltip').style.display = "none";
	document.querySelector('#btn_tooltip').style.display = "block";
}

//serch check JavaScript
function cusChk(event) {
	if(document.keywordForm.keyword.value == ""){
		alert("내용을 입력해주세요");
		event.preventDefault();
	}
}





function quantityChanged(){
	var tot=$("#quantity").val() * $("#price2").val();
	$("#total-price").text(addComma(tot));
}
//숫자 3자리마다 ,표현
function addComma(num){
	var reg=/\B(?=(\d{3})+(?!\d))/g;
	return num.toString().replace(reg,',');
}




function btnCartClicked(){
	
	//로그인 하지 않았을떄
	if(!loginCheck()){
		var result=confirm("카트저장은 로그인 이후 가능합니다.\n로그인페이지로 이동할까요?");
		if(result){
			location.href="/member/login";
		}
		return;
	}
	
	//장바구니 버튼을 눌렀을때
	var ea=$("#quantity").val();
	var no=$("#no").val();
	
	$.ajax({
		url: "/user/cart-item",
		//이게 직접적으로 dto에 들어가는건가?
		//cartCount == 선생님 quantity, 
		//pNum == 선생님 itemNo
		data:{quantity:ea, itemNo:no},
		type: "post",
		success:function(){
			if(!confirm("장바구니에 상품을 담았습니다.\n장바구니 페이지로 이동할까요?"))return;
			location.href="/user/cart-items";
			
		},
		error:function(){
		}
	});
	
}

//로그인 체크기능
function loginCheck(){
	var isLogin=false;
	$.ajax({
		url:"/member/login-check",
		async: false,
		success:function(result){
			isLogin=result;
		}
	})
	return isLogin;
}		
