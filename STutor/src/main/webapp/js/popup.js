function openPopup(type,id) {
    document.getElementById('popUpDiv'+type+id).style.display = 'block';
    document.getElementById('fade').style.display = 'block';
}

function closePopup(type,id) {
    document.getElementById('popUpDiv'+type+id).style.display = 'none';
    document.getElementById('fade').style.display = 'none';
}