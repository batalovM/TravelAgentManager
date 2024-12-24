const clientTableBody = document.querySelector('#clientsTable tbody');
const addForm = document.querySelector('#addForm');
const fetchClientForm = document.getElementById('fetchClientForm');
const fetchClientId = document.getElementById('fetchClientId');
const updateForm = document.querySelector('#updateForm');
const deleteForm = document.querySelector('#deleteForm');
const addModal = document.getElementById('addModal');
const updateModal = document.getElementById('updateModal');
const deleteModal = document.getElementById('deleteModal');

const updateLastname = document.getElementById('updateLastname');
const updateFirstname = document.getElementById('updateFirstname');
const updateSurname = document.getElementById('updateSurname');
const updateDateOfBirth = document.getElementById('updateDateOfBirth');
const updatePassportSeries = document.getElementById('updatePassportSeries');
const updatePassportNumber = document.getElementById('updatePassportNumber');
const updateDateOfIssue = document.getElementById('updateDateOfIssue');
const updateIssueBy = document.getElementById('updateIssueBy');
const updatePhoto = document.getElementById('updatePhoto');

function displayClients(clients) {
    clientTableBody.innerHTML = '';  // Очищаем тело таблицы перед добавлением новых строк

    clients.forEach(client => {
        const row = document.createElement('tr');

        // Проверяем, существует ли путь к фото
        let photoCell;
        if (client.photo) {
            // Проверяем, существует ли изображение
            const img = new Image();
            img.src = `./${client.photo}`;
            img.onload = () => {
                photoCell = `<img src="./${client.photo}" width="200" height="200"/>`;
                row.innerHTML += photoCell; // Добавляем фото в строку
                clientTableBody.appendChild(row); // Добавляем строку в таблицу только после загрузки изображения
            };
            img.onerror = () => {
                photoCell = client.photo; // Если изображение не найдено, показываем название файла
                row.innerHTML += `<td>${photoCell}</td>`; // Добавляем название файла в строку
                clientTableBody.appendChild(row); // Добавляем строку в таблицу
            };
        } else {
            photoCell = 'Нет фото'; // Если фото нет, показываем текст
            row.innerHTML += `<td>${photoCell}</td>`; // Добавляем текст в строку
            clientTableBody.appendChild(row); // Добавляем строку в таблицу
        }

        // Добавляем остальные данные клиента
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
        ` + row.innerHTML; // Объединяем строки с данными клиента и фото

        // Добавляем строку в таблицу
        clientTableBody.appendChild(row);
    });
}


async function fetchClients() {
    try {
        const response = await axios.get('http://localhost:8080/api/clients');
        displayClients(response.data);
    } catch (error) {
        console.error('Error fetching clients:', error);
    }
}

// Функция для добавления клиента
async function addClient(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newClient = {
        lastname: document.getElementById('addLastname').value,
        firstname: document.getElementById('addFirstname').value,
        surname: document.getElementById('addSurname').value,
        dateOfBirth: document.getElementById('addDateOfBirth').value,
        passportSeries: document.getElementById('addPassportSeries').value,
        passportNumber: document.getElementById('addPassportNumber').value,
        dateOfIssue: document.getElementById('addDateOfIssue').value,
        issueBy: document.getElementById('addIssueBy').value,
        photo: document.getElementById('addPhoto').value
    };
    try {
        await fetch('/api/clients/addClients', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newClient),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newClient)
        })
        // Обновляем таблицу клиентов
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
        alert("ошибка")
    }
    await fetchClients();
}

async function updateClient(client, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/clients/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(client),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении клиента');
        }
        const updatedData = await response.json();
        alert('Клиент успешно обновлён!');
    } catch (error) {
        alert(error.message);
    }
    await fetchClients();
}

async function deleteClient(id) {
    try {
        await fetch(`/api/clients/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting client:', error);
    }
}

addForm.addEventListener('submit', addClient);

updateForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const clientId = fetchClientId.value; // ID клиента
    const updatedClient = {
        lastname: updateLastname.value,
        firstname: updateFirstname.value,
        surname: updateSurname.value,
        dateOfBirth: updateDateOfBirth.value,
        passportSeries: updatePassportSeries.value,
        passportNumber: updatePassportNumber.value,
        dateOfIssue: updateDateOfIssue.value,
        issueBy: updateIssueBy.value,
        photo: updatePhoto.value
    };
    updateClient(updatedClient, clientId);
    fetchClients();
});


// Обработчик для загрузки данных клиента
fetchClientForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const clientId = fetchClientId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/clients/${clientId}`);
        if (!response.ok) {
            throw new Error('Клиент не найден');
        }
        const clientData = await response.json();
        // Заполнение полей формы данными клиента
        updateLastname.value = clientData.lastname || '';
        updateFirstname.value = clientData.firstname || '';
        updateSurname.value = clientData.surname || '';
        updateDateOfBirth.value = clientData.dateOfBirth || '';
        updatePassportSeries.value = clientData.passportSeries || '';
        updatePassportNumber.value = clientData.passportNumber || '';
        updateDateOfIssue.value = clientData.dateOfIssue || '';
        updateIssueBy.value = clientData.issueBy || '';
        updatePhoto.value = clientData.photo || '';

        // Показать второй шаг формы
        document.getElementById('step2').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
});

deleteForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteId').value;
    await deleteClient(id);
    fetchClients();
    openModal('deleteModal', false);
});

// Fetch clients when the page loads
fetchClients();




const searchButton = document.getElementById('searchButton');
const searchInput = document.getElementById('searchInput');

// Обработка поиска сотрудников
searchButton.addEventListener("click", function () {
    searchInput.style.display = searchInput.style.display === "none" ? "block" : "none";
});

searchInput.addEventListener("input", function () {
    const searchTerm = searchInput.value.toLowerCase();
    const rows = clientTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});