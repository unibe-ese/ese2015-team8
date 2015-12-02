function mailBoxColour(notifications) {
	var newMessages = notifications.length;
	if(newMessages==0){
		document.getElementById('mail-box').style.backgroundColor = 'transparent';
	}else{
		document.getElementById('mail-box').style.background = 'green';
	}
}