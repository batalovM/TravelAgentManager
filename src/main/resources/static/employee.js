// Функция для загрузки сотрудников с сервера и отображения на странице
export async function loadEmployees(rowsPerPage, currentPage, loadingSpinner, tableBody, employeesData) {
    if (!loadingSpinner) {
        console.error("Элемент 'loadingSpinner' не найден.");
        return;
    }
    loadingSpinner.style.display = "block"; // Показываем индикатор загрузки
    fetch('/api/employee')
        .then(response => response.json())
        .then(employees => {
            employeesData = employees; // Сохраняем данные сотрудников
            renderEmployeeTablePage(currentPage, rowsPerPage, tableBody, employeesData); // Перезаполняем таблицу
            const totalPages = Math.ceil(employeesData.length / rowsPerPage);
            document.getElementById("pageNumberEmployee").innerText = `${currentPage} / ${totalPages}`;
        })
        .catch(error => console.error('Ошибка при загрузке сотрудников:', error))
        .finally(() => loadingSpinner.style.display = "none"); // Скрываем индикатор
}

// Функция для отображения данных сотрудников в таблице
function renderEmployeeTablePage(currentPage, rowsPerPage, tableBody, employeesData) {
    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageEmployees = employeesData.slice(start, end);

    // Очищаем таблицу перед добавлением новых строк
    tableBody.innerHTML = '';

    // Заполняем таблицу данными сотрудников
    pageEmployees.forEach(employee => {
        const row = tableBody.insertRow();
        row.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.lastName}</td>
            <td>${employee.firstName}</td>
            <td>${employee.surName}</td>
        `;
        // Функция для перехода на следующую страницу
        function nextPageEmployee() {
            const totalPages = Math.ceil(employeesData.length / rowsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                renderEmployeeTablePage(currentPage, rowsPerPage, tableBody, employeesData);
                document.getElementById("pageNumberEmployee").innerText = `${currentPage} / ${totalPages}`;
            }
        }

// Функция для перехода на предыдущую страницу
        function prevPageEmployee() {
            if (currentPage > 1) {
                currentPage--;
                renderEmployeeTablePage(currentPage, rowsPerPage, tableBody, employeesData);
                document.getElementById("pageNumberEmployee").innerText = `${currentPage} / ${Math.ceil(employeesData.length / rowsPerPage)}`;
            }
        }
        window.nextPageEmployee = nextPageEmployee;
        window.prevPageEmployee = prevPageEmployee;
    });
}


// Функция для добавления сотрудника
export function addEmployee(event) {
    event.preventDefault();
    const newEmployee = {
        lastname: document.getElementById('employeeLastname').value,
        firstname: document.getElementById('employeeFirstname').value,
        surname: document.getElementById('employeeSurname').value
    };

    fetch('/api/employee/addEmployee', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newEmployee)
    })
        .then(response => {
            if (response.ok) {
                alert('Сотрудник добавлен!');
                closeModal('employeeModal');
                loadEmployees(5, 1, document.getElementById("loadingSpinnerForEmployee"), document.querySelector("#employeeTable"), []);
            } else {
                throw new Error('Не удалось добавить сотрудника');
            }
        })
        .catch(error => console.error('Ошибка при добавлении сотрудника:', error));
}

// Функция для поиска сотрудника по ID
export function findEmployeeById(event) {
    event.preventDefault();
    const employeeId = document.getElementById("updateEmployeeId").value;
    fetch(`/api/employee/${employeeId}`)
        .then(response => {
            if (!response.ok) throw new Error('Сотрудник не найден');
            return response.json();
        })
        .then(employee => {
            document.getElementById("employeeLastname1").value = employee.lastName;
            document.getElementById("employeeFirstname1").value = employee.firstName;
            document.getElementById("employeeSurname1").value = employee.surName;
            document.getElementById("updateEmployeeForm").style.display = "block";
        })
        .catch(error => alert("Ошибка: " + error.message));
}

// Функция для обновления данных сотрудника
export function updateEmployee(event) {
    event.preventDefault();
    const employeeId = document.getElementById("updateEmployeeId").value;
    const updatedEmployee = {
        lastName: document.getElementById("employeeLastname1").value,
        firstName: document.getElementById("employeeFirstname1").value,
        surName: document.getElementById("employeeSurname1").value
    };

    fetch(`/api/employee/${employeeId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedEmployee)
    })
        .then(response => {
            if (!response.ok) throw new Error('Не удалось обновить сотрудника');
            alert("Данные сотрудника обновлены!");
            closeModal('employeeUpdateModal');
            loadEmployees(5, 1, document.getElementById("loadingSpinnerForEmployee"), document.querySelector("#employeeTable"), []);
        })
        .catch(error => alert("Ошибка: " + error.message));
}

// Функция для удаления сотрудника
export function deleteEmployee(event) {
    event.preventDefault();
    const employeeId = document.getElementById("deleteEmployeeId").value;
    fetch(`/api/employee/${employeeId}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) throw new Error('Ошибка при удалении сотрудника');
            closeModal('employeeDeleteModal');
            loadEmployees(5, 1, document.getElementById("loadingSpinnerForEmployee"), document.querySelector("#employeeTable"), []);
        })
        .catch(error => alert("Ошибка: " + error.message));
}
