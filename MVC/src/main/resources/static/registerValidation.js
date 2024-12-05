function confirmRegistration() {
    return window.confirm("Czy na pewno chcesz się zarejestrować?");
}

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("registerForm");

    const loginInput = document.getElementById("login");
    const passwordInput = document.getElementById("password");

    const loginError = document.getElementById("loginError");
    const passwordError = document.getElementById("passwordError");

    const validateLogin = () => {
        const login = loginInput.value.trim();
        const regex = /^[a-zA-Z0-9_]+$/;

        if (login.length < 3 || login.length > 20) {
            loginError.textContent = "Login musi mieć od 3 do 20 znaków.";
            return false;
        }
        else if (!regex.test(login)) {
            loginError.textContent = "Login może zawierać tylko litery, cyfry i podkreślenia.";
            return false;
        } else {
            loginError.textContent = "";
            return true;
        }
    }

    const validatePassword = () => {
        const password = passwordInput.value.trim();
        if (password.length < 8) {
            passwordError.textContent = "Hasło musi mieć co najmniej 8 znaków.";
            return false;
        } else {
            passwordError.textContent = "";
            return true;
        }
    };

    loginInput.addEventListener("input", validateLogin);
    passwordInput.addEventListener("input", validatePassword);

    form.addEventListener("submit", (event) => {
        const isLoginValid = validateLogin();
        const isPasswordValid = validatePassword();

        if (!isLoginValid || !isPasswordValid) {
            event.preventDefault();
        } else {
            if (!confirmRegistration()) {
                event.preventDefault();
            }
        }
    });
});