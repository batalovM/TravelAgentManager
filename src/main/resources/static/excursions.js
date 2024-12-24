const excursionTableBody = document.querySelector('#excursionProgramTable tbody');
const addExcursionForm = document.querySelector('#addExcursionProgramForm');
const fetchExcursionForm = document.getElementById('fetchExcursionProgramForm');
const fetchExcursionId = document.getElementById('fetchExcursionProgramId');
const updateExcursionForm = document.querySelector('#updateExcursionProgramForm');
const deleteExcursionForm = document.querySelector('#deleteExcursionProgramForm');
const addExcursionProgramModal = document.getElementById('addExcursionProgramModal');
const updateExcursionProgramModal = document.getElementById('updateExcursionProgramModal');
const deleteExcursionProgramModal = document.getElementById('deleteExcursionProgramModal');

const updateExcursionProgramName = document.getElementById('updateExcursionProgramName');



function displayExcursion(excursion) {
    excursionTableBody.innerHTML = '';
    excursion.forEach(excursion => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${excursion.id}</td>
      <td>${excursion.excursionProgramName}</td>
    `;
        excursionTableBody.appendChild(row);
    });
}
async function fetchExcursion() {
    try {
        const response = await axios.get('http://localhost:8080/api/excursions');
        displayExcursion(response.data);
    } catch (error) {
        console.error('Error fetching excursion:', error);
    }
}

async function addExcursion(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newExcursion = {
        excursionProgramName: document.getElementById('addExcursionProgramName').value,
    };
    try {
        await fetch('/api/excursions/addExcursion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newExcursion),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newExcursion)
        })
        // Обновляем таблицу клиентов
        fetchExcursion();
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
    }
}

async function updateExcursion(excursion, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/excursions/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(excursion),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении экскурсии');
        }
        const updatedData = await response.json();
        alert('Экскурсия успешно обновлена!');
    } catch (error) {
        alert(error.message);
    }
    await fetchExcursion()
}

async function deleteExcursion(id) {
    try {
        await fetch(`/api/excursions/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting excursion:', error);
    }
}

addExcursionForm.addEventListener('submit', addExcursion);

updateExcursionForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const excursionId = fetchExcursionId.value; // ID клиента
    const updatedExcursion = {
        excursionProgramName: updateExcursionProgramName.value,
    };
    updateExcursion(updatedExcursion, excursionId);
   fetchExcursion();
});


fetchExcursionForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const excursionId = fetchExcursionId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/excursions/${excursionId}`);
        if (!response.ok) {
            throw new Error('Экскурсия не найдена');
        }
        const excursionData = await response.json();
        // Заполнение полей формы данными клиента
        updateExcursionProgramName.value = excursionData.excursionProgramName || '';
        // Показать второй шаг формы
        document.getElementById('updateExcursionProgramStep').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
});

deleteExcursionForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteExcursionProgramId').value;
    await deleteExcursion(id);
    fetchExcursion();
    openModal('deleteExcursionProgramModal', false);
});

fetchExcursion()




const searchExcursionProgramButton = document.getElementById('searchExcursionProgramButton');
const searchExcursionProgramInput = document.getElementById('searchExcursionProgramInput');

// Обработка поиска сотрудников
searchExcursionProgramButton.addEventListener("click", function () {
    searchExcursionProgramInput.style.display = searchExcursionProgramInput.style.display === "none" ? "block" : "none";
});

searchExcursionProgramInput.addEventListener("input", function () {
    const searchTerm = searchExcursionProgramInput.value.toLowerCase();
    const rows = excursionTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});