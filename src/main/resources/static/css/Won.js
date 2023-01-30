const toggleBtn =document.querySelector('.dropDownTrigger');
const menu =document.querySelector('.dropDownList');


toggleBtn.addEventListener('click', () => {
	menu.classList.toggle('active')
})

const button = document.querySelector('.moreViewWrap');
const lst =document.querySelector('.categoryMenu');


button.addEventListener('click', () => {
	lst.classList.toggle('active')	
})


