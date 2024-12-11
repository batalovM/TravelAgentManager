document.addEventListener("DOMContentLoaded", () => {
    const executeQueryButton = document.getElementById("executeQueryButton");
    const saveQueryButton = document.getElementById("saveQueryButton");

    // Выполнение SQL-запроса
    executeQueryButton.addEventListener("click", async () => {
        const query = document.getElementById("sqlQuery").value.trim();
        if (!query) {
            alert("Введите SQL-запрос.");
            return;
        }

        try {
            const response = await fetch('/api/execute-sql', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ sql: query }),
            });

            if (!response.ok) throw new Error('Ошибка при выполнении запроса');

            const data = await response.json();
            renderResultsTable(data);
        } catch (error) {
            console.error("Ошибка:", error);
            alert("Ошибка: " + error.message);
        }
    });

    saveQueryButton.addEventListener("click", () => {
        const query = document.getElementById("sqlQuery").value.trim();
        const table = document.getElementById("resultsTable");
        const rows = Array.from(table.querySelectorAll("tbody tr"));

        if (!query || rows.length === 0) {
            alert("Нет данных для сохранения.");
            return;
        }

        const results = rows.map(row => {
            const cells = Array.from(row.querySelectorAll("td"));
            return cells.map(cell => cell.textContent);
        });

        const jsonToSave = JSON.stringify({ query, results }, null, 2);

        const blob = new Blob([jsonToSave], { type: "application/json" });
        const url = URL.createObjectURL(blob);

        const a = document.createElement("a");
        a.href = url;
        a.download = "query_results.json";
        a.click();

        URL.revokeObjectURL(url);
    });

    const exampleButtons = [
        { id: "exampleQueryClients", query: "SELECT * FROM clients" },
        { id: "exampleQueryRoutes", query: "SELECT * FROM routes" },
        { id: "exampleQueryTrips", query: "SELECT * FROM trips" }
    ];

    exampleButtons.forEach(buttonConfig => {
        const button = document.getElementById(buttonConfig.id);
        if (!button) {
            console.error(`Кнопка с id ${buttonConfig.id} не найдена`);
            return;
        }

        button.addEventListener("click", () => {
            const sqlQueryTextarea = document.getElementById("sqlQuery");
            if (!sqlQueryTextarea) {
                console.error("Текстовое поле sqlQuery не найдено");
                return;
            }

            sqlQueryTextarea.value = buttonConfig.query;
        });
    });

    // Отображение результатов в таблице
    function renderResultsTable(data) {
        const table = document.getElementById("resultsTable");
        const tableHeaders = document.getElementById("tableHeaders");
        const tableBody = table.querySelector("tbody");

        // Очистка таблицы
        tableHeaders.innerHTML = '';
        tableBody.innerHTML = '';

        if (!data.length) {
            alert("Нет результатов.");
            table.classList.add("hidden");
            return;
        }

        // Заполнение заголовков
        const headers = Object.keys(data[0]);
        headers.forEach(header => {
            const th = document.createElement("th");
            th.textContent = header;
            tableHeaders.appendChild(th);
        });

        // Заполнение строк
        data.forEach(row => {
            const tr = document.createElement("tr");
            headers.forEach(header => {
                const td = document.createElement("td");
                td.textContent = row[header];
                tr.appendChild(td);
            });
            tableBody.appendChild(tr);
        });

        table.classList.remove("hidden");
    }
});