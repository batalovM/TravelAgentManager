
export function renderTablePage(page, rowsPerPage, tableBody, clientsData) {
    tableBody.innerHTML = ""; // Очищаем таблицу
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    // Создаем строки таблицы на основе данных клиентов
    clientsData.slice(start, end).forEach(client => {
    const row = document.createElement("tr"); // Создаем новую строку для таблицы
    // Добавляем ячейки с данными клиента <td><img src="${client.photo}" alt="Фото Клиента" style="width: 100px; height: auto; margin-top: 10px;"></td>
    row.innerHTML = `
            <td>${client.id}</td>
            <td>${client.lastname}</td>
            <td>${client.firstname}</td>
            <td>${client.surname}</td>
            <td>${client.dateOfBirth}</td>
            <td>${client.passportSeries}</td>
            <td>${client.passportNumber}</td>
            <td>${client.dateOfIssue}</td>
            <td>${client.issueBy}</td>
            <td>${client.photo}</td>
        `;
    // Добавляем строку в тело таблицы
    tableBody.appendChild(row);
    // Функция для перехода на следующую страницу
    function nextPage() {
        const totalPages = Math.ceil(clientsData.length / rowsPerPage); // Перерасчет общего числа страниц
        if (page < totalPages) {
            page++;
            renderTablePage(page, rowsPerPage, tableBody, clientsData); // Отображаем следующую страницу
            document.getElementById("pageNumber").innerText = `${page} / ${totalPages}`;
        }
    }
// Функция для перехода на предыдущую страницу
    function prevPage() {
        if (page > 1) {
            page--;
            renderTablePage(page, rowsPerPage, tableBody, clientsData); // Отображаем предыдущую страницу
            document.getElementById("pageNumber").innerText = `${page} / ${Math.ceil(clientsData.length / rowsPerPage)}`;
        }
    }
        window.nextPage = nextPage;
        window.prevPage = prevPage;
    });
}

