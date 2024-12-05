function confirmRent() {
    return window.confirm("Czy na pewno chcesz wypożyczyć przedmiot?");
}

document.addEventListener("DOMContentLoaded", () => {
    const rentCost = document.getElementById("rentCost");
    const clientId = document.getElementById("clientId");
    const itemId = document.getElementById("itemId");

    const rentCostError = document.getElementById("rentCostError");
    const clientIdError = document.getElementById("clientIdError");
    const itemIdError = document.getElementById("itemIdError");

    const validateRentCost = () => {
        const value = parseFloat(rentCost.value.trim());
        if (isNaN(value) || value <= 0) {
            rentCostError.textContent = "Wartość wypożyczenia musi być większa od 0.";
            return false;
        } else {
            rentCostError.textContent = "";
            return true;
        }
    };

    const validateClientId = () => {
        if (clientId.value.trim().length !== 24) {
            clientIdError.textContent = "Id klienta musi mieć dokładnie 24 znaki.";
            return false;
        } else {
            clientIdError.textContent = "";
            return true;
        }
    };

    const validateItemId = () => {
        if (itemId.value.trim().length !== 24) {
            itemIdError.textContent = "Id przedmiotu musi mieć dokładnie 24 znaki.";
            return false;
        } else {
            itemIdError.textContent = "";
            return true;
        }
    };

    rentCost.addEventListener("input", validateRentCost);
    clientId.addEventListener("input", validateClientId);
    itemId.addEventListener("input", validateItemId);

    const form = document.getElementById("rentForm");
    form.addEventListener("submit", (event) => {
        const isRentCostValid = validateRentCost();
        const isClientIdValid = validateClientId();
        const isItemIdValid = validateItemId();

        if (!isRentCostValid || !isClientIdValid || !isItemIdValid) {
            event.preventDefault();
        } else {
            if (!confirmRent()) {
                event.preventDefault();
            }
        }
    });
});