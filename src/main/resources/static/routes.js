// Функция для загрузки маршрутов с сервера и отображения на странице
export async function loadRoutes(rowsPerPage, currentPage, loadingSpinner, tableBody, routesData) {
    if (!loadingSpinner) {
        console.error("Элемент 'loadingSpinner' не найден.");
        return;
    }
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки
    // Запрашиваем данные клиентов через API
    fetch('/api/routes')
        .then(response => response.json())  // Преобразуем ответ в формат JSON
        .then(routes => {
            routesData = routes; // Сохраняем данные клиентов в переменную
            // Перезаполняем таблицу
            renderRouteTablePage(currentPage, rowsPerPage, tableBody, routesData);
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


// Функция для отображения данных маршрутов в таблице
function renderRouteTablePage(currentPage, rowsPerPage, tableBody, routeData) {
    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageRoutes = routeData.slice(start, end);

    // Очищаем таблицу перед добавлением новых строк
    tableBody.innerHTML = '';

    // Заполняем таблицу данными сотрудников
    pageRoutes.forEach(route => {
        const row = tableBody.insertRow();
        row.innerHTML = `
            <td>${route.id}</td>
        `;
        // Функция для перехода на следующую страницу
        function nextPageRoute() {
            const totalPages = Math.ceil(routeData.length / rowsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                renderRouteTablePage(currentPage, rowsPerPage, tableBody, routeData);
                document.getElementById("pageNumberRoute").innerText = `${currentPage} / ${totalPages}`;
            }
        }

// Функция для перехода на предыдущую страницу
        function prevPageRoute() {
            if (currentPage > 1) {
                currentPage--;
                renderRouteTablePage(currentPage, rowsPerPage, tableBody, routeData);
                document.getElementById("pageNumberRoute").innerText = `${currentPage} / ${Math.ceil(routeData.length / rowsPerPage)}`;
            }
        }
        window.nextPageRoute = nextPageRoute();
        window.prevPageRoute = prevPageRoute();
    });
}
