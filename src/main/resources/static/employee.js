document.addEventListener("DOMContentLoaded", function () {
    const employeesTableBody = document.querySelector("#employeesTable tbody");
    const addEmployeeButton = document.getElementById("addEmployeeButton");
    const updateEmployeeButton = document.getElementById("updateEmployeeButton");
    const deleteEmployeeButton = document.getElementById("deleteEmployeeButton");
    const searchEmployeeButton = document.getElementById("searchEmployeeButton");
    const searchEmployeeInput = document.getElementById("searchEmployeeInput");

    // Загрузка всех сотрудников при загрузке страницы
    loadEmployees();

    // Обработка добавления сотрудника
    addEmployeeButton.addEventListener("click", function () {
        const addEmployeeModal = document.getElementById("addEmployeeModal");
        addEmployeeModal.style.display = "block";
    });

    document.getElementById("addEmployeeForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const newEmployee = {
            lastName: document.getElementById("addEmployeeLastname").value,
            firstName: document.getElementById("addEmployeeFirstname").value,
            surName: document.getElementById("addEmployeeSurname").value,
        };

        fetch("/api/employee/addEmployee", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newEmployee),
        })
            .then(response => {
                if (response.ok) {
                    loadEmployees();
                    closeEmployeeModal(addEmployeeModal);
                } else {
                    alert("Ошибка при добавлении сотрудника");
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });

    // Обработка обновления сотрудника
    updateEmployeeButton.addEventListener("click", function () {
        const updateEmployeeModal = document.getElementById("updateEmployeeModal");
        updateEmployeeModal.style.display = "block";
        document.getElementById("employeeStep1").style.display = "block";
        document.getElementById("employeeStep2").style.display = "none";
    });

    document.getElementById("fetchEmployeeForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const employeeId = document.getElementById("fetchEmployeeId").value;

        fetch(`/api/employee/${employeeId}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    alert("Сотрудник не найден");
                }
            })
            .then(data => {
                if (data) {
                    document.getElementById("updateEmployeeLastname").value = data.lastName;
                    document.getElementById("updateEmployeeFirstname").value = data.firstName;
                    document.getElementById("updateEmployeeSurname").value = data.surName;

                    document.getElementById("employeeStep1").style.display = "none";
                    document.getElementById("employeeStep2").style.display = "block";
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });

    document.getElementById("updateEmployeeForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const employeeId = document.getElementById("fetchEmployeeId").value;
        const updatedEmployee = {
            lastName: document.getElementById("updateEmployeeLastname").value,
            firstName: document.getElementById("updateEmployeeFirstname").value,
            surName: document.getElementById("updateEmployeeSurname").value,
        };

        fetch(`/api/employee/${employeeId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedEmployee),
        })
            .then(response => {
                if (response.ok) {
                    loadEmployees();
                    closeEmployeeModal(document.getElementById("updateEmployeeModal"));
                } else {
                    alert("Ошибка при обновлении сотрудника");
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });

    // Обработка удаления сотрудника
    deleteEmployeeButton.addEventListener("click", function () {
        const deleteEmployeeModal = document.getElementById("deleteEmployeeModal");
        deleteEmployeeModal.style.display = "block";
    });

    document.getElementById("deleteEmployeeForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const employeeId = document.getElementById("deleteEmployeeId").value;

        fetch(`/api/employee/${employeeId}`, {
            method: "DELETE",
        })
            .then(response => {
                if (response.ok) {
                    loadEmployees();
                    closeEmployeeModal(document.getElementById("deleteEmployeeModal"));
                } else {
                    alert("Ошибка при удалении сотрудника");
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });

    // Обработка поиска сотрудников
    searchEmployeeButton.addEventListener("click", function () {
        searchEmployeeInput.style.display = searchEmployeeInput.style.display === "none" ? "block" : "none";
    });

    searchEmployeeInput.addEventListener("input", function () {
        const searchTerm = searchEmployeeInput.value.toLowerCase();
        const rows = employeesTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });

    // Функция для загрузки всех сотрудников
    function loadEmployees() {
        fetch("/api/employee")
            .then(response => response.json())
            .then(employees => {
                employeesTableBody.innerHTML = "";
                employees.forEach(employee => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${employee.id}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.surName}</td>
                    `;
                    employeesTableBody.appendChild(row);
                });
            })
            .catch(error => console.error("Ошибка:", error));
    }

    // Функция для закрытия модального окна
    function closeEmployeeModal(modal) {
        modal.style.display = "none";
    }


    // Закрытие модальных окон при нажатии на кнопку "Закрыть"
    document.querySelectorAll(".close").forEach(button => {
        button.addEventListener("click", function () {
            closeEmployeeModal(button.closest(".modal"));
        });
    });
});
