const excursionApiUrl = "http://localhost:8080/api/excursions";

const excursionOverlay = document.querySelector("#excursionOverlay");
const updateExcursionModal = document.querySelector("#updateExcursionModal");
const excursionStep1 = document.querySelector("#excursionStep1");
const excursionStep2 = document.querySelector("#excursionStep2");


function openExcursionModal(modal) {
    modal.classList.add("active");
    excursionOverlay.classList.add("active");
}
function closeExcursionModal() {
    document.querySelectorAll("#updateExcursionModal, #addExcursionModal, #deleteExcursionModal").forEach(modal => modal.classList.remove("active"));
    excursionOverlay.classList.remove("active");
    excursionStep1.style.display = "block";
    excursionStep2.style.display = "none";
}

excursionOverlay.addEventListener("click", closeExcursionModal);

// Функционал для кнопки добавления экскурсионной программы'
document.querySelector("#addExcursionButton").addEventListener("click", () => openExcursionModal(document.querySelector("#addExcursionModal")));
// Загрузка данных экскурсионной программы для обновления
document.querySelector("#fetchExcursionForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.querySelector("#fetchExcursionId").value;
    const response = await fetch(`${excursionApiUrl}/${id}`);
    if (response.ok) {
        const excursion = await response.json();
        // Заполняем поля данными сотрудника
        document.querySelector("#updateExcursionName").value = excursion.excursionProgramName || "";
        // Переход ко второму шагу
        excursionStep1.style.display = "none";
        excursionStep2.style.display = "block";
    } else {
        alert("Программа с таким ID не найдена!");
    }
});



// Функционал для кнопки обновления сотрудника
document.querySelector("#updateExcursionButton").addEventListener("click", () => openExcursionModal(updateExcursionModal));
// Функционал для кнопки удаления экскурсионной программы
document.querySelector("#deleteExcursionButton").addEventListener("click", () => openExcursionModal(document.querySelector("#deleteExcursionModal")));
// Отправка обновлённых данных экскурсионной программы
document.querySelector("#updateExcursionForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.querySelector("#fetchExcursionId").value;
    const excursion = {
        excursionProgramName: document.querySelector("#updateExcursionName").value,
    };
    await fetch(`${excursionApiUrl}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(excursion),
    });
    closeExcursionModal();
    loadExcursions();
});
// Функционал для кнопки поиска
document.querySelector("#searchExcursionButton").addEventListener("click", () => {
    const searchExcursionInput = document.querySelector("#searchExcursionInput");
    searchExcursionInput.style.display = searchExcursionInput.style.display === "none" || searchExcursionInput.style.display === "" ? "block" : "none";
});
document.querySelector("#searchExcursionInput").addEventListener("input", async (e) => {
    const query = e.target.value.toLowerCase();
    const response = await fetch(excursionApiUrl);
    const excursions = await response.json();
    const filteredExcursions = excursions.filter(excursion =>
        excursion.excursionProgramName.toLowerCase().includes(query)
    );
    renderExcursions(filteredExcursions);
});
// Функционал для удаления экскурсионной программы
document.querySelector("#deleteExcursionForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.querySelector("#deleteExcursionId").value;
    await fetch(`${excursionApiUrl}/${id}`, {
        method: "DELETE",
    });
    closeExcursionModal();
    loadExcursions();
});

// Загрузка всех программ экскурсий
async function loadExcursions() {
    const response = await fetch(excursionApiUrl);
    const excursions = await response.json();
    renderExcursions(excursions);
}
function renderExcursions(excursions) {
    const tableBody = document.querySelector("#excursionsTable tbody");
    tableBody.innerHTML = "";
    excursions.forEach(excursion => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${excursion.id}</td>
            <td>${excursion.excursionProgramName}</td>
        `;
        tableBody.appendChild(row);
    });
}
loadExcursions();