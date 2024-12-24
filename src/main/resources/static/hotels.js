const hotelTableBody = document.querySelector('#hotelTable tbody');
const addHotelForm = document.querySelector('#addHotelForm');
const fetchHotelForm = document.getElementById('fetchHotelForm');
const fetchHotelId = document.getElementById('fetchHotelId');
const updateHotelForm = document.querySelector('#updateHotelForm');
const deleteHotelForm = document.querySelector('#deleteHotelForm');
const addHotelModal = document.getElementById('addHotelModal');
const updateHotelModal = document.getElementById('updateHotelModal');
const deleteHotelModal = document.getElementById('deleteHotelModal');

const updateHotelName = document.getElementById('updateHotelName');
const updateHotelClass = document.getElementById('updateHotelClass');

function displayHotel(hotel) {
    hotelTableBody.innerHTML = '';
    hotel.forEach(hotel => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${hotel.id}</td>
      <td>${hotel.hotelName}</td>
      <td>${hotel.hotelClass}</td>
    `;
        hotelTableBody.appendChild(row);
    });
}
async function fetchHotels() {
    try {
        const response = await axios.get('http://localhost:8080/api/hotel');
        displayHotel(response.data);
    } catch (error) {
        console.error('Error fetching hotels:', error);
    }
}

// Функция для добавления клиента
async function addHotel(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    // Получаем данные из формы
    const newHotel = {
        hotelName: document.getElementById('addHotelName').value,
        hotelClass: document.getElementById('addHotelClass').value,

    };
    try {
        await fetch('/api/hotel/addHotel', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newHotel),  // Преобразуем объект клиента в JSON
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newHotel)
        })
    } catch (error) {
        console.error('Ошибка во время добавления:', error);
    }
    await fetchHotels()
}

async function updateHotel(hotel, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/hotel/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(hotel),
        });
        if (!response.ok) {
            throw new Error('Ошибка при обновлении отеля');
        }
        const updatedData = await response.json();
        alert('отель успешно обновлён!');
    } catch (error) {
        alert(error.message);
    }
    await fetchHotels()
}

async function deleteHotel(id) {
    try {
        await fetch(`/api/hotel/${id}`, { method: 'DELETE' });
    } catch (error) {
        console.error('Error deleting hotel:', error);
    }
    await fetchHotels()
}

addHotelForm.addEventListener('submit', addClient);

updateHotelForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const hotelId = fetchHotelId.value; // ID клиента
    const updatedHotel = {
        hotelName: updateHotelName.value,
        hotelClass: updateHotelClass.value,
    };
    updateHotel(updatedHotel, hotelId);
    fetchHotels();
});


// Обработчик для загрузки данных клиента
fetchHotelForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const hotelId = fetchHotelId.value;

    try {
        const response = await fetch(`http://localhost:8080/api/hotel/${hotelId}`);
        if (!response.ok) {
            throw new Error('Отель не найден');
        }
        const hotelData = await response.json();
        // Заполнение полей формы данными клиента
        updateHotelName.value = hotelData.hotelName || '';
        updateHotelClass.value = hotelData.hotelClass || '';

        // Показать второй шаг формы
        document.getElementById('updateHotelStep').style.display = 'block';
    } catch (error) {
        alert(error.message);
    }
    await fetchHotels()
});

deleteHotelForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteHotelId').value;
    await deleteHotel(id);
    fetchHotels();
    openModal('deleteHotelModal', false);
});

// Fetch clients when the page loads
fetchHotels();

addHotelForm.addEventListener('submit', addHotel)


const searchHotelButton = document.getElementById('searchHotelButton');
const searchHotelInput = document.getElementById('searchHotelInput');

// Обработка поиска сотрудников
searchHotelButton.addEventListener("click", function () {
    searchHotelInput.style.display = searchHotelInput.style.display === "none" ? "block" : "none";
});

searchHotelInput.addEventListener("input", function () {
    const searchTerm = searchHotelInput.value.toLowerCase();
    const rows = hotelTableBody.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
        row.style.display = isVisible ? "" : "none";
    });
});