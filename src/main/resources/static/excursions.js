// Функция для загрузки экскурсий с сервера и отображения на странице
export async function loadExcursions(rowsPerPage, currentPage, loadingSpinner, tableBody, excursionsData) {
    if (!loadingSpinner) {
        console.error("Элемент 'loadingSpinner' не найден.");
        return;
    }
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки

    try {
        const response = await fetch('/api/excursions');
        excursionsData = await response.json();

        renderExcursionTablePage(currentPage, rowsPerPage, tableBody, excursionsData);

        const totalPages = Math.ceil(excursionsData.length / rowsPerPage);
        document.getElementById("pageNumberExcursion").innerText = `${currentPage} / ${totalPages}`;
    } catch (error) {
        console.error('Ошибка при загрузке экскурсий:', error);
    } finally {
        loadingSpinner.style.display = "none"; // Скрываем индикатор загрузки после завершения
    }
}

// Функция для отображения данных экскурсий в таблице
function renderExcursionTablePage(currentPage, rowsPerPage, tableBody, excursionsData) {
    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageExcursions = excursionsData.slice(start, end);

    tableBody.innerHTML = ''; // Очищаем таблицу

    pageExcursions.forEach(excursion => {
        const row = tableBody.insertRow();
        row.innerHTML = `
            <td>${excursion.id}</td>
            <td>${excursion.excursionProgramName}</td>
        `;
        // Функция для перехода на следующую страницу
        function nextPageExcursion() {
            const totalPages = Math.ceil(excursionsData.length / rowsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                renderExcursionTablePage(currentPage, rowsPerPage, tableBody, excursionsData);
                document.getElementById("pageNumberExcursion").innerText = `${currentPage} / ${totalPages}`;
            }
        }

// Функция для перехода на предыдущую страницу
        function prevPageExcursion() {
            if (currentPage > 1) {
                currentPage--;
                renderExcursionTablePage(currentPage, rowsPerPage, tableBody, excursionsData);
                document.getElementById("pageNumberExcursion").innerText = `${currentPage} / ${Math.ceil(excursionsData.length / rowsPerPage)}`;
            }
        }
        window.nextPageExcursion = nextPageExcursion;
        window.prevPageExcursion = prevPageExcursion;
    });
}
