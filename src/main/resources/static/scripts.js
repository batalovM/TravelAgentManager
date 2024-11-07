function openModal(modalId) {
    document.getElementById(modalId).style.display = "block";
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

// Закрытие модального окна при клике вне его
window.onclick = function(event) {
    const modals = document.getElementsByClassName('modal');
    for (let i = 0; i < modals.length; i++) {
        if (event.target === modals[i]) {
            modals[i].style.display = "none";
        }
    }
}

// Функция для показа фотографии
function showPhoto(photoId) {
    const photoElement = document.getElementById(photoId);
    photoElement.style.display = "block";
}

// function previewPhoto(event) {
//     const photoPreview = document.getElementById('photoPreview');
//     const file = event.target.files[0];
//
//     if (file) {
//         const reader = new FileReader();
//         reader.onload = function(e) {
//             photoPreview.src = e.target.result;
//             photoPreview.style.display = 'block'; // Показываем изображение
//         }
//         reader.readAsDataURL(file);
//         // Дополняем: сохраняем имя файла в скрытое поле
//         document.getElementById('photo').value = file.name;
//     } else {
//         photoPreview.src = '';
//         photoPreview.style.display = 'none'; // Скрываем изображение
//     }
// }
