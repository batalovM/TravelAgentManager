import {loadRoutes} from "./route.js";


document.addEventListener("DOMContentLoaded", async function () {
    const rowsPerPage = 5; // Количество строк на странице
    let currentPage = 1; // Текущая страница
    const tableBody = document.querySelector("#routesTable tbody"); // Находим тело таблицы
    const loadingSpinner = document.getElementById("loadingSpinnerForRoutes"); // Индикатор загрузки
    let routesData = []; // Массив для хранения данных клиентов

    await loadRoutes(rowsPerPage, currentPage, loadingSpinner, tableBody, routesData);

    window.openModal = openModal;
    window.closeModal = closeModal;

    // Работа с формами

});