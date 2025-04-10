
document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const inputs = document.querySelectorAll("input");//tutti input della pagina

    form.addEventListener("submit", function (event) {
        let valid = true;

        inputs.forEach(input => {//per tutti gli input
            const errorMessage = input.nextElementSibling;
            if (!errorMessage || !errorMessage.classList.contains("error-message")) {
                return;
            }

            errorMessage.textContent = "";
            input.style.border = "2px solid #ccc"; // Reset del bordo

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

        if (!valid) {
            event.preventDefault();
        }
    });

    function showError(input, message) {
        const errorMessage = input.nextElementSibling;
        errorMessage.textContent = message;
        input.style.border = "2px solid red";
    }
});
