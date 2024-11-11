
import { closeModal } from '../modal.js';
import {renderTablePage} from './pagination.js';

export async function loadClients(rowsPerPage, currentPage, loadingSpinner, tableBody, clientsData) {
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки
    // Запрашиваем данные клиентов через API
    fetch('/api/clients')
        .then(response => response.json())  // Преобразуем ответ в формат JSON
        .then(clients => {
            clientsData = clients; // Сохраняем данные клиентов в переменную
            // Перезаполняем таблицу
            renderTablePage(currentPage, rowsPerPage, tableBody, clientsData);

            // Перерасчет количества страниц
            const totalPages = Math.ceil(clientsData.length / rowsPerPage);
            document.getElementById("pageNumber").innerText = `${currentPage} / ${totalPages}`;
        })
        .catch(error => {
            console.error('Ошибка при загрузке клиентов:', error);
        })
        .finally(() => {
            loadingSpinner.style.display = "none"; // Скрываем индикатор загрузки после завершения
        });

}

export function addClient(event) {
    event.preventDefault(); // Останавливаем стандартную отправку формы
    // Собираем данные формы
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
    // Отправляем данные на сервер с помощью fetch
    fetch('/api/clients/addClients', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newClient)  // Преобразуем объект клиента в JSON
    })
        .then(response => {
            if (response.ok) {
                // Закрываем модальное окно после успешной отправки данных
                closeModal('clientModal');
                const loadingSpinner = document.getElementById("loadingSpinnerForClients");
                const tableBody = document.querySelector("#clientsTable tbody");
                loadClients(5, 1, loadingSpinner, tableBody, []);  // Перезагружаем список клиентов
            } else {
                throw new Error('Не удалось добавить клиента');
            }
        })
        .catch(error => {
            console.error('Ошибка при добавлении клиента:', error);
        });
}

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

            document.getElementById("updateForm").style.display = "block";
        })
        .catch(error => alert("Ошибка: " + error.message));
}

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
            const loadingSpinner = document.getElementById("loadingSpinnerForClients");
            const tableBody = document.querySelector("#clientsTable tbody");
            loadClients(5, 1, loadingSpinner, tableBody, []);  // Перезагружаем список клиентов
        })
        .catch(error => alert("Ошибка: " + error.message));
}

export function deleteClient(event) {
    event.preventDefault();

    const clientId = document.getElementById("deleteId").value;

    fetch(`/api/clients/${clientId}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) throw new Error('Ошибка при удалении клиента');
            closeModal('deleteModal');
            const loadingSpinner = document.getElementById("loadingSpinnerForClients");
            const tableBody = document.querySelector("#clientsTable tbody");
            loadClients(5, 1, loadingSpinner, tableBody, []);  // Перезагружаем список клиентов
        })
        .catch(error => alert("Ошибка: " + error.message));
}
