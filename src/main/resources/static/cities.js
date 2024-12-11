const cityTableBody = document.querySelector('#cityTable tbody');
const addCityForm = document.querySelector('#addCityForm');
const fetchCityForm = document.getElementById('fetchCityForm');
const fetchCityId = document.getElementById('fetchCityId');
const updateCityForm = document.querySelector('#updateCityForm');
const deleteCityForm = document.querySelector('#deleteCityForm');
const addCityModal = document.getElementById('addCityModal');
const updateCityModal = document.getElementById('updateCityModal');
const deleteCityModal = document.getElementById('deleteCityModal');

const updateCityHotelId = document.getElementById('updateCityHotelId');
const updateExcursionProgramId = document.getElementById('updateExcursionProgramId');
const updateCityName = document.getElementById('updateCityName');


function displayCity(city) {
    cityTableBody.innerHTML = '';
    city.forEach(city => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${city.id}</td>
      <td>${city.hotelId}</td>
      <td>${city.excursionProgramId}</td>
      <td>${city.cityName}</td>
    `;
        cityTableBody.appendChild(row);
    });
}
async function fetchCity() {
    try {
        const response = await axios.get('http://localhost:8080/api/city');
        displayCity(response.data);
    } catch (error) {
        console.error('Error fetching cities:', error);
    }
}

// Функция для добавления клиента
async function addCity(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newCity = {
        hotelId: document.getElementById('addCityHotel').value,
        excursionProgramId: document.getElementById('addExcursionProgramId').value,
        cityName: document.getElementById('addCityName').value,
    };
    try {
        await fetch('/api/city/addCity', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newCity),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newCity)
        })
        // Обновляем таблицу клиентов
        fetchCity();
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
    }
}

async function updateCity(city, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/city/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(city),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении города');
        }
        const updatedData = await response.json();
        alert('Город успешно обновлён!');
    } catch (error) {
        alert(error.message);
    }
}

async function deleteCity(id) {
    try {
        await fetch(`/api/city/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting city:', error);
    }
}

addCityForm.addEventListener('submit', addCity);

updateCityForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const cityId = fetchCityId.value; // ID клиента
    const updatedCity = {
        hotelId: updateCityHotelId.value,
        excursionProgramId: updateExcursionProgramId.value,
        cityName: updateCityName.value,

    };
    updateCity(updatedCity, cityId);
    fetchCity();
});


// Обработчик для загрузки данных клиента
fetchCityForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const cityId = fetchCityId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/city/${cityId}`);
        if (!response.ok) {
            throw new Error('Город не найден');
        }
        const cityData = await response.json();
        // Заполнение полей формы данными клиента
        updateCityHotelId.value = cityData.hotelId || '';
        updateExcursionProgramId.value = cityData.excursionProgramId || '';
        updateCityName.value = cityData.cityName;

        // Показать второй шаг формы
        document.getElementById('updateCityStep').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
});

deleteCityForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteCityId').value;
    await deleteCity(id);
    fetchCity();
    openModal('deleteCityModal', false);
});

// Fetch clients when the page loads
fetchCity();




const searchCityButton = document.getElementById('searchCityButton');
const searchCityInput = document.getElementById('searchCityInput');

// Обработка поиска сотрудников
searchCityButton.addEventListener("click", function () {
    searchCityInput.style.display = searchCityInput.style.display === "none" ? "block" : "none";
});

searchCityInput.addEventListener("input", function () {
    const searchTerm = searchCityInput.value.toLowerCase();
    const rows = cityTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});