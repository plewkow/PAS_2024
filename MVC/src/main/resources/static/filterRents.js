document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById('search');
    const resetFilterButton = document.getElementById('resetFilter');
    const rentsTableBody = document.querySelector("#rentsTable tbody");

    const noResults = /*[[${noResults}]]*/ "Brak wyników";
    const loadingError = /*[[${loadingError}]]*/ "Błąd ładowania danych";
    const returnMessage = /*[[${returnMessage}]]*/ "Zwróć";

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
                    rentsTableBody.innerHTML = `<tr><td colspan="5">${noResults}</td></tr>`;
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
