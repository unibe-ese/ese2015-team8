function mailBoxColour(newMessages) {
	if(newMessages==0){
		document.getElementById('mail-box').style.backgroundColor = 'transparent';
	}else{
		document.getElementById('mail-box').style.background = 'green';
	}
}