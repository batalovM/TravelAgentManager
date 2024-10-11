function previewPhoto(event) {
    const photoPreview = document.getElementById('photoPreview');
    const file = event.target.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            photoPreview.src = e.target.result;
            photoPreview.style.display = 'block'; // Показываем изображение
        }
        reader.readAsDataURL(file);
        // Дополняем: сохраняем имя файла в скрытое поле
        document.getElementById('photo').value = file.name;
    } else {
        photoPreview.src = '';
        photoPreview.style.display = 'none'; // Скрываем изображение
    }
}
