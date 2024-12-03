// Функция для загрузки клиентов с сервера и отображения на странице
export async function loadClients(rowsPerPage, currentPage, loadingSpinner, tableBody, clientsData) {
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки
    fetch('/api/clients')
        .then(response => response.json())
        .then(clients => {
            clientsData = clients; // Сохраняем данные клиентов
            renderTablePage(currentPage, rowsPerPage, tableBody, clientsData); // Перезаполняем таблицу
            const totalPages = Math.ceil(clientsData.length / rowsPerPage);
            document.getElementById("pageNumber").innerText = `${currentPage} / ${totalPages}`;
        })
        .catch(error => console.error('Ошибка при загрузке клиентов:', error))
        .finally(() => loadingSpinner.style.display = "none"); // Скрываем индикатор
}

// Функция для отображения данных клиентов в таблице
function renderTablePage(currentPage, rowsPerPage, tableBody, clientsData) {
    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageClients = clientsData.slice(start, end);

    // Очищаем таблицу перед добавлением новых строк
    tableBody.innerHTML = '';

    // Заполняем таблицу данными клиентов
    pageClients.forEach(client => {
        const row = tableBody.insertRow();
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
        // Функция для перехода на следующую страницу
        function nextPage() {
            const totalPages = Math.ceil(clientsData.length / rowsPerPage); // Перерасчет общего числа страниц
            if (currentPage < totalPages) {
                currentPage++;
                renderTablePage(currentPage, rowsPerPage, tableBody, clientsData); // Отображаем следующую страницу
                document.getElementById("pageNumber").innerText = `${currentPage} / ${totalPages}`;
            }
        }
// Функция для перехода на предыдущую страницу
        function prevPage() {
            if (currentPage > 1) {
                currentPage--;
                renderTablePage(currentPage, rowsPerPage, tableBody, clientsData); // Отображаем предыдущую страницу
                document.getElementById("pageNumber").innerText = `${currentPage} / ${Math.ceil(clientsData.length / rowsPerPage)}`;
            }
        }
        window.nextPage = nextPage;
        window.prevPage = prevPage;
    });
}

// Функция для добавления клиента
export function addClient(event) {
    event.preventDefault();
    const newClient = {
        lastname: document.getElementById('lastname').value,
        firstname: document.getElementById('firstname').value,
        surname: document.getElementById('surname').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        passportSeries: document.getElementById('passportSeries').value,
        passportNumber: document.getElementById('passportNumber').value,
        dateOfIssue: document.getElementById('dateOfIssue').value,
        issueBy: document.getElementById('issueBy').value,
        photo: document.getElementById('photo').value
    };

    fetch('/api/clients/addClients', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newClient)
    })
        .then(response => {
            if (response.ok) {
                alert('Клиент добавлен!');
                closeModal('clientModal');
                loadClients(5, 1, document.getElementById("loadingSpinnerForClients"), document.querySelector("#clientsTable"), []);
            } else {
                throw new Error('Не удалось добавить клиента');
            }
        })
        .catch(error => console.error('Ошибка при добавлении клиента:', error));
}

// Функция для поиска клиента по ID
export function findClientById(event) {
    event.preventDefault();
    const clientId = document.getElementById("updateId").value;
    fetch(`/api/clients/${clientId}`)
        .then(response => {
            if (!response.ok) throw new Error('Клиент не найден');
            return response.json();
        })
        .then(client => {
            document.getElementById("lastname1").value = client.lastname;
            document.getElementById("firstname1").value = client.firstname;
            document.getElementById("surname1").value = client.surname;
            document.getElementById("dateOfBirth1").value = client.dateOfBirth;
            document.getElementById("passportSeries1").value = client.passportSeries;
            document.getElementById("passportNumber1").value = client.passportNumber;
            document.getElementById("dateOfIssue1").value = client.dateOfIssue;
            document.getElementById("issueBy1").value = client.issueBy;
            document.getElementById("photo1").value = client.photo;
            document.getElementById("updateClientForm").style.display = "block";
        })
        .catch(error => alert("Ошибка: " + error.message));
}

// Функция для обновления данных клиента
export function updateClient(event) {
    event.preventDefault();
    const clientId = document.getElementById("updateId").value;
    const updatedClient = {
        lastname: document.getElementById("lastname1").value,
        firstname: document.getElementById("firstname1").value,
        surname: document.getElementById("surname1").value,
        dateOfBirth: document.getElementById("dateOfBirth1").value,
        passportSeries: document.getElementById("passportSeries1").value,
        passportNumber: document.getElementById("passportNumber1").value,
        dateOfIssue: document.getElementById("dateOfIssue1").value,
        issueBy: document.getElementById("issueBy1").value,
        photo: document.getElementById("photo1").value
    };

    fetch(`/api/clients/${clientId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedClient)
    })
        .then(response => {
            if (!response.ok) throw new Error('Не удалось обновить клиента');
            alert("Данные клиента обновлены!");
            closeModal('clientUpdateModal');
            loadClients(5, 1, document.getElementById("loadingSpinnerForClients"), document.querySelector("#clientsTable"), []);
        })
        .catch(error => alert("Ошибка: " + error.message));
}

// Функция для удаления клиента
export function deleteClient(event) {
    event.preventDefault();
    const clientId = document.getElementById("deleteId").value;
    fetch(`/api/clients/${clientId}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) throw new Error('Ошибка при удалении клиента');
            closeModal('deleteModal');
            loadClients(5, 1, document.getElementById("loadingSpinnerForClients"), document.querySelector("#clientsTable"), []);
        })
        .catch(error => alert("Ошибка: " + error.message));
}
