document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("registerForm");

    form.addEventListener("submit", (event) => {
        let isValid = true;

        const login = document.getElementById("login").value.trim();
        const password = document.getElementById("password").value.trim();

        if (login.length < 3 || login.length > 20) {
            isValid = false;
            document.getElementById("loginError").textContent = "Login musi mieć od 3 do 20 znaków.";
        } else {
            document.getElementById("loginError").textContent = "";
        }

        if (password.length < 8) {
            isValid = false;
            document.getElementById("passwordError").textContent = "Hasło musi mieć co najmniej 8 znaków.";
        } else {
            document.getElementById("passwordError").textContent = "";
        }

        if (!isValid) {
            event.preventDefault();
        }
    });
});

function confirmRegistration() {
    return window.confirm("Czy na pewno chcesz się zarejestrować?");
}
