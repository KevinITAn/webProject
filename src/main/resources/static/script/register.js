document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("registerForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        let valid = true;

        // Pulizia errori
        document.querySelectorAll(".error-message").forEach(el => el.textContent = "");
        document.querySelectorAll("input").forEach(input => input.style.border = "2px solid #ccc");

        const firstName = document.getElementById("firstName");
        const lastName = document.getElementById("lastName");
        const userName = document.getElementById("userName");
        const password = document.getElementById("password");
        const passwordConfirm = document.getElementById("passwordConfirm");

        // Validazioni
        if (firstName.value.trim() === "") {
            showError("firstName", "Il campo non può essere vuoto.");
            valid = false;
        }

        if (lastName.value.trim() === "") {
            showError("lastName", "Il campo non può essere vuoto.");
            valid = false;
        }

        if (userName.value.length < 5) {
            showError("userName", "Lo username deve essere almeno di 5 caratteri.");
            valid = false;
        }

        if (password.value.length < 8) {
            showError("password", "La password deve essere almeno di 8 caratteri.");
            valid = false;
        }

        if (passwordConfirm.value !== password.value) {
            showError("passwordConfirm", "Le password non coincidono.");
            valid = false;
        }

        if (valid) {
            form.submit(); // oppure usare fetch()
        }
    });

    function showError(inputId, message) {
        const input = document.getElementById(inputId);
        const errorDiv = document.getElementById(`${inputId}-error`);
        input.style.border = "2px solid red";
        if (errorDiv) {
            errorDiv.textContent = message;
        }
    }
});
