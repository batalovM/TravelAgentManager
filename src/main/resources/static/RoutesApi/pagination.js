
export function renderTablePage(page, rowsPerPage, tableBody, routesData) {
    tableBody.innerHTML = ""; // Очищаем таблицу
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    // Создаем строки таблицы на основе данных маршрутов
    routesData.slice(start, end).forEach(route => {
        const row = document.createElement("tr"); // Создаем новую строку для таблицы
        row.innerHTML = `
            <td>${route.id}</td>
            <td>${route.countryId}</td>
            <td>${route.routeName}</td>
            <td>${route.duration}</td>
        `;
        // Добавляем строку в тело таблицы
        tableBody.appendChild(row);
        // Функция для перехода на следующую страницу
        function nextPage() {
            const totalPages = Math.ceil(routesData.length / rowsPerPage); // Перерасчет общего числа страниц
            if (page < totalPages) {
                page++;
                renderTablePage(page, rowsPerPage, tableBody, routesData); // Отображаем следующую страницу
                document.getElementById("routePageNumber").innerText = `${page} / ${totalPages}`;
            }
        }
// Функция для перехода на предыдущую страницу
        function prevPage() {
            if (page > 1) {
                page--;
                renderTablePage(page, rowsPerPage, tableBody, routesData); // Отображаем предыдущую страницу
                document.getElementById("routePageNumber").innerText = `${page} / ${Math.ceil(routesData.length / rowsPerPage)}`;
            }
        }
        window.nextPage = nextPage;
        window.prevPage = prevPage;
    });
}

