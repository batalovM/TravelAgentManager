const employeeTableBody = document.querySelector('#employeesTable tbody');
const addEmployeeForm = document.querySelector('#addEmployeeForm');
const fetchEmployeeForm = document.getElementById('fetchEmployeeForm');
const fetchEmployeeId = document.getElementById('fetchEmployeeId');
const updateEmployeeForm = document.querySelector('#updateEmployeeForm');
const deleteEmployeeForm = document.querySelector('#deleteEmployeeForm');
const addEmployeeModal = document.getElementById('addEmployeeModal');
const updateEmployeeModal = document.getElementById('updateEmployeeModal');
const deleteEmployeeModal = document.getElementById('deleteEmployeeModal');

const updateEmployeeLastname = document.getElementById('updateEmployeeLastName');
const updateEmployeeFirstname = document.getElementById('updateEmployeeFirstName');
const updateEmployeeSurname = document.getElementById('updateEmployeeSurname');


function displayEmployees(employees) {
    employeeTableBody.innerHTML = '';
    employees.forEach(employee => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${employee.id}</td>
      <td>${employee.lastName}</td>
      <td>${employee.firstName}</td>
      <td>${employee.surName}</td>
    `;
        employeeTableBody.appendChild(row);
    });
}
async function fetchEmployees() {
    try {
        const response = await axios.get('http://localhost:8080/api/employee');
        displayEmployees(response.data);
    } catch (error) {
        console.error('Error fetching employees:', error);
    }
}

// Функция для добавления клиента
async function addEmployee(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newEmployee = {
        lastName: document.getElementById('addEmployeeLastName').value,
        firstName: document.getElementById('addEmployeeFirstName').value,
        surName: document.getElementById('addEmployeeSurName').value,
    };
    try {
        await fetch('/api/employee/addEmployee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newEmployee),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newEmployee)
        })
        // Обновляем таблицу клиентов
        fetchEmployees();
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
    }
}

async function updateEmployee(employee, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/employee/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(employee),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении клиента');
        }
        const updatedData = await response.json();
        alert('Сотрудник успешно обновлён!');
    } catch (error) {
        alert(error.message);
    }
}

async function deleteEmployee(id) {
    try {
        await fetch(`/api/employee/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting employee:', error);
    }
}

addEmployeeForm.addEventListener('submit', addEmployee);

updateEmployeeForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const employeeId = fetchEmployeeId.value; // ID клиента
    const updatedEmployee = {
        lastName: updateEmployeeLastname.value,
        firstName: updateEmployeeFirstname.value,
        surName: updateEmployeeSurname.value,
    };
    updateEmployee(updatedEmployee, employeeId);
    fetchEmployees();
});


// Обработчик для загрузки данных клиента
fetchEmployeeForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const employeeId = fetchEmployeeId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/employee/${employeeId}`);
        if (!response.ok) {
            throw new Error('Сотрудник не найден');
        }
        const employeeData = await response.json();
        // Заполнение полей формы данными клиента
        updateEmployeeLastname.value = employeeData.lastName || '';
        updateEmployeeFirstname.value = employeeData.firstName || '';
        updateEmployeeSurname.value = employeeData.surName || '';

        // Показать второй шаг формы
        document.getElementById('updateEmployeeStep').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
});

deleteEmployeeForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteEmployeeId').value;
    await deleteEmployee(id);
    fetchEmployees();
    openModal('deleteEmployeeModal', false);
});

fetchEmployees()




const searchEmployeeButton = document.getElementById('searchEmployeeButton');
const searchEmployeeInput = document.getElementById('searchEmployeeInput');

// Обработка поиска сотрудников
searchEmployeeButton.addEventListener("click", function () {
    searchEmployeeInput.style.display = searchEmployeeInput.style.display === "none" ? "block" : "none";
});

searchEmployeeInput.addEventListener("input", function () {
    const searchTerm = searchEmployeeInput.value.toLowerCase();
    const rows = employeeTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});