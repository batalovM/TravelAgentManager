const tripTableBody = document.querySelector('#tripTable tbody');
const addTripForm = document.querySelector('#addTripForm');
const fetchTripForm = document.getElementById('fetchTripForm');
const fetchTripId = document.getElementById('fetchTripId');
const updateTripForm = document.querySelector('#updateTripForm');
const deleteTripForm = document.querySelector('#deleteTripForm');
const addTripModal = document.getElementById('addTripModal');
const updateTripModal = document.getElementById('updateTripModal');
const deleteTripModal = document.getElementById('deleteTripModal');

const updateTripCost = document.getElementById('updateTripCost');
const updateEmployeeId = document.getElementById('updateEmployeeId');
const updateRoutesId = document.getElementById('updateRoutesId');
const updateDepartureTime = document.getElementById('updateDepartureTime');
const updateArrivalTime = document.getElementById('updateArrivalTime');
const updateTouristCount = document.getElementById('updateTouristCount');
const updatePenaltySize = document.getElementById('updatePenaltySize');



function displayTrip(trip) {
    tripTableBody.innerHTML = '';
    trip.forEach(trip => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${trip.id}</td>
      <td>${trip.tripCost}</td>
      <td>${trip.employeeFullName}</td>
      <td>${trip.routeName}</td>
      <td>${trip.departureTime}</td>
      <td>${trip.arrivalTime}</td>
      <td>${trip.touristCount}</td>
      <td>${trip.penaltySize}</td>
    `;
        tripTableBody.appendChild(row);
    });
}
async function fetchTrips() {
    try {
        const response = await axios.get('http://localhost:8080/api/trip');
        displayTrip(response.data);
    } catch (error) {
        console.error('Error fetching trip:', error);
    }
}

// Функция для добавления клиента
async function addTrips(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newTrip = {
        tripCost: document.getElementById('addTripCost').value,
        employeeId: document.getElementById('addEmployeeId').value,
        routesId: document.getElementById('addRoutesId').value,
        departureTime: document.getElementById('addDepartureTime').value,
        arrivalTime: document.getElementById('addArrivalTime').value,
        touristCount: document.getElementById('addTouristCount').value,
        penaltySize: document.getElementById('addPenaltySize').value,
    };
    try {
        await fetch('/api/trip/addTrips', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newTrip),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newTrip)
        })
        // Обновляем таблицу клиентов
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
    }
    await fetchTrips();
}

async function updateTrip(trip, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/trip/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(trip),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении поездки');
        }
        const updatedData = await response.json();
        alert('Поездка успешно обновлён!');
    } catch (error) {
        alert(error.message);
    }
    await fetchTrips();
}

async function deleteTrip(id) {
    try {
        await fetch(`/api/trip/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting trip:', error);
    }
    await fetchTrips();
}

addTripForm.addEventListener('submit', addTrips);

updateTripForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const tripId = fetchTripId.value; // ID клиента
    const updatedTrip = {
        tripCost: updateTripCost.value,
        employeeId: updateEmployeeId.value,
        routesId: updateRoutesId.value,
        departureTime: updateDepartureTime.value,
        arrivalTime: updateArrivalTime.value,
        touristCount: updateTouristCount.value,
        penaltySize: updatePenaltySize.value,
    };
    updateTrip(updatedTrip, tripId);
    fetchTrips();
});

fetchTripForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const tripId = fetchTripId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/trip/${tripId}`);
        if (!response.ok) {
            throw new Error('Поездка не найдена');
        }
        const tripData = await response.json();
        // Заполнение полей формы данными клиента
        updateTripCost.value = tripData.tripCost || '';
        updateEmployeeId.value = tripData.employeeId || '';
        updateRoutesId.value = tripData.routesId || '';
        updateDepartureTime.value = tripData.departureTime || '';
        updateArrivalTime.value = tripData.arrivalTime || '';
        updateTouristCount.value = tripData.touristCount || '';
        updatePenaltySize.value = tripData.penaltySize || '';

        // Показать второй шаг формы
        document.getElementById('updateTripStep').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
});

deleteTripForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteTripId').value;
    await deleteTrip(id);
    fetchTrips();
    openModal('deleteTripModal', false);
});



const searchTripButton = document.getElementById('searchTripButton');
const searchTripInput = document.getElementById('searchTripInput');

// Обработка поиска сотрудников
searchTripButton.addEventListener("click", function () {
    searchTripInput.style.display = searchTripInput.style.display === "none" ? "block" : "none";
});

searchTripInput.addEventListener("input", function () {
    const searchTerm = searchTripInput.value.toLowerCase();
    const rows = tripTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});


fetchTrips()