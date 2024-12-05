document.addEventListener("DOMContentLoaded", () => {

    const translations = {
        "en-US": {
            returnMessage: "Return",
            noResults: "No results",
            loadingError: "Error loading data"
        },
        "pl-PL": {
            returnMessage: "Zwróć",
            noResults: "Brak wyników",
            loadingError: "Błąd ładowania danych"
        }
    };

    const language = navigator.language || navigator.userLanguage;

    const returnMessage = currentTranslations.returnMessage;
    const noResults = currentTranslations.noResults;
    const loadingError = currentTranslations.loadingError;

    const searchInput = document.getElementById('search');
    const resetFilterButton = document.getElementById('resetFilter');
    const rentsTableBody = document.querySelector("#rentsTable tbody");

    function loadRents(clientId = '') {
        const url = clientId ? `/rents/active/clientId/${clientId}` : '/rents/active';

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                rentsTableBody.innerHTML = '';
                if (data.length === 0) {
                    rentsTableBody.innerHTML = `<tr><td colspan='5'>${noResults}</td></tr>`;
                } else {
                    data.forEach(rent => {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td>${rent.id}</td>
                            <td>${rent.rentCost}</td>
                            <td>${rent.clientId}</td>
                            <td>${rent.itemId}</td>
                            <td>
                                <form action="/return/${rent.id}" method="POST">
                                    <button class="return" type="submit">${returnMessage}</button>
                                </form>
                            </td>
                        `;
                        rentsTableBody.appendChild(row);
                    });
                }
            })
            .catch(error => {
                console.error(loadingError, error);
            });
    }

    searchInput.addEventListener('input', function() {
        const clientId = searchInput.value.trim();
        if (clientId) {
            loadRents(clientId);
        } else {
            loadRents();
        }
    });

    resetFilterButton.addEventListener('click', () => {
        searchInput.value = '';
        loadRents();
    });

    loadRents();
});
