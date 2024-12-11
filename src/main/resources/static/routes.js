const routeTableBody = document.querySelector('#routeTable tbody');
const addRouteForm = document.querySelector('#addRouteForm');
const fetchRouteForm = document.getElementById('fetchRouteForm');
const fetchRouteId = document.getElementById('fetchRouteId');
const updateRouteForm = document.querySelector('#updateRouteForm');
const deleteRouteForm = document.querySelector('#deleteRouteForm');
const addRouteModal = document.getElementById('addRouteModal');
const updateRouteModal = document.getElementById('updateRouteModal');
const deleteRouteModal = document.getElementById('deleteRouteModal');

const updateCountryId = document.getElementById('updateCountryId');
const updateRouteName = document.getElementById('updateRouteName');
const updateDuration = document.getElementById('updateDuration');



function displayRoutes(routes) {
    routeTableBody.innerHTML = '';
    routes.forEach(route => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${route.id}</td>
      <td>${route.countryId}</td>
      <td>${route.routeName}</td>
      <td>${route.duration}</td>
      
    `;
        routeTableBody.appendChild(row);
    });
}
async function fetchRoute() {
    try {
        const response = await axios.get('http://localhost:8080/api/routes');
        displayRoutes(response.data);
    } catch (error) {
        console.error('Error fetching routes:', error);
    }
}

// Функция для добавления клиента
async function addRoute(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newRoute = {
        countryId: document.getElementById('addCountryId').value,
        routeName: document.getElementById('addRouteName').value,
        duration: document.getElementById('addDuration').value,
    };
    try {
        await fetch('/api/routes/addRoute', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newRoute),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newRoute)
        })
        // Обновляем таблицу клиентов
        fetchRoute();
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
    }
}

async function updateRoute(route, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/routes/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(route),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении маршрута');
        }
        const updatedData = await response.json();
        alert('Маршрут успешно обновлён!');
    } catch (error) {
        alert(error.message);
    }
}

async function deleteRoute(id) {
    try {
        await fetch(`/api/routes/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting route:', error);
    }
}

addRouteForm.addEventListener('submit', addRoute);

updateRouteForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const routeId = fetchClientId.value; // ID клиента
    const updatedRoute = {
        countryId: updateCountryId.value,
        routeName: updateRouteName.value,
        duration: updateDuration.value,
    };
    updateRoute(updatedRoute, routeId);
    fetchRoute();
});


// Обработчик для загрузки данных клиента
fetchRouteForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const routeId = fetchRouteId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/routes/${routeId}`);
        if (!response.ok) {
            throw new Error('Маршрут не найден');
        }
        const routeData = await response.json();
        // Заполнение полей формы данными клиента
        updateCountryId.value = routeData.countryId || '';
        updateRouteName.value = routeData.routeName || '';
        updateDuration.value = routeData.duration || '';

        // Показать второй шаг формы
        document.getElementById('updateRouteStep').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
});

deleteRouteForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteRouteId').value;
    await deleteRoute(id);
    fetchRoute();
    openModal('deleteRouteModal', false);
});


fetchRoute();




const searchRouteButton = document.getElementById('searchRouteButton');
const searchRouteInput = document.getElementById('searchRouteInput');

// Обработка поиска сотрудников
searchRouteButton.addEventListener("click", function () {
    searchRouteInput.style.display = searchRouteInput.style.display === "none" ? "block" : "none";
});

searchRouteInput.addEventListener("input", function () {
    const searchTerm = searchRouteInput.value.toLowerCase();
    const rows = routeTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});