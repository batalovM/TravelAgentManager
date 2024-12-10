const clientTableBody = document.querySelector('#clientsTable tbody');
const addForm = document.querySelector('#addForm');
const updateForm = document.querySelector('#updateForm');
const deleteForm = document.querySelector('#deleteForm');
const addModal = document.getElementById('addModal');
const updateModal = document.getElementById('updateModal');
const deleteModal = document.getElementById('deleteModal');

// Helper function to display clients in the table
function displayClients(clients) {
    clientTableBody.innerHTML = '';
    clients.forEach(client => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${client.id}</td>
      <td>${client.lastName}</td>
      <td>${client.firstName}</td>
      <td>${client.surname}</td>
      <td>${client.dateOfBirth}</td>
      <td>${client.passportSeries}</td>
      <td>${client.passportNumber}</td>
      <td>${client.dateOfIssue}</td>
      <td>${client.issueBy}</td>
      <td>${client.photo}</td>
    `;
        clientTableBody.appendChild(row);
    });
}

// Fetch all clients
async function fetchClients() {
    try {
        const response = await axios.get('http://localhost:8080/api/clients');
        displayClients(response.data);
    } catch (error) {
        console.error('Error fetching clients:', error);
    }
}

// Add a new client
async function addClient(client) {
    try {
        const response = await axios.post('http://localhost:8080/api/clients/addClients', client);
        return response.data;
    } catch (error) {
        console.error('Error adding client:', error);
    }
}

// Update a client
async function updateClient(client, id) {
    try {
        const response = await axios.put(`http://localhost:8080/api/clients/${id}`, client);
        return response.data;
    } catch (error) {
        console.error('Error updating client:', error);
    }
}

// Delete a client
async function deleteClient(id) {
    try {
        await axios.delete(`http://localhost:8080/api/clients/${id}`);
    } catch (error) {
        console.error('Error deleting client:', error);
    }
}

// Event listeners
addForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(addForm);
    const client = {
        firstName: formData.get('addFirstname'),
        lastName: formData.get('addLastname'),
        surname: formData.get('addSurname'),
        dateOfBirth: formData.get('addDateOfBirth'),
        passportSeries: formData.get('addPassportSeries'),
        passportNumber: formData.get('addPassportNumber'),
        dateOfIssue: formData.get('addDateOfIssue'),
        issueBy: formData.get('addIssueBy'),
        photo: formData.get('addPhoto'),
    };
    await addClient(client);
    fetchClients();
    openModal('addModal', false);
});

updateForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(updateForm);
    const client = {
        firstName: formData.get('updateFirstname'),
        lastName: formData.get('updateLastname'),
        surname: formData.get('updateSurname'),
        dateOfBirth: formData.get('updateDateOfBirth'),
        passportSeries: formData.get('updatePassportSeries'),
        passportNumber: formData.get('updatePassportNumber'),
        dateOfIssue: formData.get('updateDateOfIssue'),
        issueBy: formData.get('updateIssueBy'),
        photo: formData.get('updatePhoto'),
    };
    const id = document.querySelector('#updateId').value;
    await updateClient(client, id);
    fetchClients();
    openModal('updateModal', false);
});

deleteForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.querySelector('#deleteId').value;
    await deleteClient(id);
    fetchClients();
    openModal('deleteModal', false);
});

// Fetch clients when the page loads
fetchClients();
