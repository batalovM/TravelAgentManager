import {renderTablePage} from "../RoutesApi/pagination.js";

export async function loadRoutes(rowsPerPage, currentPage, loadingSpinner, tableBody, routesData) {
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки
    // Запрашиваем данные клиентов через API
    fetch('/api/routes')
        .then(response => response.json())  // Преобразуем ответ в формат JSON
        .then(routes => {
            routesData = routes; // Сохраняем данные клиентов в переменную
            // Перезаполняем таблицу
            renderTablePage(currentPage, rowsPerPage, tableBody, routesData);

            // Перерасчет количества страниц
            const totalPages = Math.ceil(routesData.length / rowsPerPage);
            document.getElementById("pageNumber").innerText = `${currentPage} / ${totalPages}`;
        })
        .catch(error => {
            console.error('Ошибка при загрузке маршрутов:', error);
        })
        .finally(() => {
            loadingSpinner.style.display = "none"; // Скрываем индикатор загрузки после завершения
        });

}