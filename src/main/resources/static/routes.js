// Функция для загрузки маршрутов с сервера и отображения на странице
export async function loadRoutes(rowsPerPage, currentPage, loadingSpinner, tableBody, routesData) {
    if (!loadingSpinner) {
        console.error("Элемент 'loadingSpinner' не найден.");
        return;
    }
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки

    try {
        const response = await fetch('/api/routes');
        routesData = await response.json();

        renderRouteTablePage(currentPage, rowsPerPage, tableBody, routesData);

        const totalPages = Math.ceil(routesData.length / rowsPerPage);
        document.getElementById("pageNumberRoute").innerText = `${currentPage} / ${totalPages}`;
    } catch (error) {
        console.error('Ошибка при загрузке маршрутов:', error);
    } finally {
        loadingSpinner.style.display = "none"; // Скрываем индикатор загрузки после завершения
    }
}

// Функция для отображения данных экскурсий в таблице
function renderRouteTablePage(currentPage, rowsPerPage, tableBody, routeDate) {
    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageRoutes = routeDate.slice(start, end);

    tableBody.innerHTML = ''; // Очищаем таблицу

    pageRoutes.forEach(route => {
        const row = tableBody.insertRow();
        row.innerHTML = `
            <td>${route.id}</td>
            <td>${route.countryId}</td>
            <td>${route.routeName}</td>
            <td>${route.duration}</td>
        `;
        // Функция для перехода на следующую страницу
        function nextPageRoute() {
            const totalPages = Math.ceil(routeDate.length / rowsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                renderRouteTablePage(currentPage, rowsPerPage, tableBody, routeDate);
                document.getElementById("pageNumberExcursion").innerText = `${currentPage} / ${totalPages}`;
            }
        }

// Функция для перехода на предыдущую страницу
        function prevPageRoute() {
            if (currentPage > 1) {
                currentPage--;
                renderRouteTablePage(currentPage, rowsPerPage, tableBody, routeDate);
                document.getElementById("pageNumberRoute").innerText = `${currentPage} / ${Math.ceil(routeDate.length / rowsPerPage)}`;
            }
        }
        window.nextPageRoute = nextPageRoute;
        window.prevPageRoute = prevPageRoute;
    });
}
