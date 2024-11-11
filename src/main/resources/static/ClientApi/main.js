
import { loadClients, addClient, deleteClient, updateClient, findClientById } from './client.js';
import { openModal, closeModal } from '../modal.js';

document.addEventListener("DOMContentLoaded", async function () {
    const rowsPerPage = 5; // Количество строк на странице
    let currentPage = 1; // Текущая страница

    const tableBody = document.querySelector("#clientsTable tbody"); // Находим тело таблицы
    const loadingSpinner = document.getElementById("loadingSpinnerForClients"); // Индикатор загрузки
    let clientsData = []; // Массив для хранения данных клиентов

    await loadClients(rowsPerPage, currentPage, loadingSpinner, tableBody, clientsData);

    // Привязка функций к глобальному контексту для управления модальными окнами и пагинацией
    window.openModal = openModal;
    window.closeModal = closeModal;


    // Работа с формами
    document.getElementById('addClientForm').addEventListener('submit', addClient);
    document.getElementById('updateClientForm').addEventListener('submit', findClientById);
    document.getElementById('updateForm').addEventListener('submit', updateClient);
    document.getElementById('deleteClientForm').addEventListener('submit', deleteClient);
});
