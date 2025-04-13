document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("registerForm");
    const inputs = form.querySelectorAll("input");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevenire l'invio del modulo

        let valid = true;

        inputs.forEach(input => {
            const errorMessage = input.nextElementSibling;
            if (!errorMessage || !errorMessage.classList.contains("error-message")) {
                return;
            }

            errorMessage.textContent = ""; // Resetta il messaggio di errore
            input.style.border = "2px solid #ccc"; // Reset del bordo

            // Validazioni per il nome, cognome, username
            if (input.id === "firstName" || input.id === "lastName") {
                if (input.value.trim() === "") {
                    valid = false;
                    showError(input, "Il campo non può essere vuoto.");
                }
            }

            if (input.id === "username" && input.value.length < 5) {
                valid = false;
                showError(input, "Lo username deve essere almeno di 5 caratteri.");
            }

            if (input.id === "password" && input.value.length < 8) {
                valid = false;
                showError(input, "La password deve essere almeno di 8 caratteri.");
            }

            if (input.id === "passwordConfirm" && input.value !== document.getElementById("password").value) {
                valid = false;
                showError(input, "Le password non coincidono.");
            }
        });

        // Se il modulo è valido, invia i dati
        if (valid) {
            form.submit();
        }
    });

    function showError(input, message) {
        const errorMessage = input.nextElementSibling;
        errorMessage.textContent = message;
        input.style.border = "2px solid red";
    }
});
