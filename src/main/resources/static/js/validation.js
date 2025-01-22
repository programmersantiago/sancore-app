document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const globalMessage = document.querySelector(".alert");

    // Mostrar mensaje global (y ocultarlo después de 5 segundos si existe)
    if (globalMessage) {
        setTimeout(() => {
            globalMessage.style.opacity = "0";
            setTimeout(() => globalMessage.remove(), 500);
        }, 7000);
    }

    form.addEventListener("submit", function (event) {
        let valid = true;

        // Remover mensajes de error previos
        document.querySelectorAll(".error-tooltip").forEach(function (tooltip) {
            tooltip.remove();
        });

        // Validaciones específicas
        function showError(input, message) {
            valid = false;

            const errorTooltip = document.createElement("div");
            errorTooltip.className = "error-tooltip";
            errorTooltip.textContent = message;

            input.insertAdjacentElement("afterend", errorTooltip);
        }

        const firstName = document.getElementById("firstName");
        if (firstName.value.trim() === "") {
            showError(firstName, "El nombre es obligatorio.");
        }

        const lastName = document.getElementById("lastName");
        if (lastName.value.trim() === "") {
            showError(lastName, "El apellido es obligatorio.");
        }

        const nick = document.getElementById("nick");
        if (nick.value.trim().length < 3 || nick.value.trim().length > 15) {
            showError(nick, "El nombre de usuario debe tener entre 3 y 15 caracteres.");
        }

        const email = document.getElementById("email");
        const confirmEmail = document.querySelector("[name='confirmEmail']");
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email.value)) {
            showError(email, "El correo electrónico no es válido.");
        } else if (email.value !== confirmEmail.value) {
            showError(confirmEmail, "Los correos electrónicos no coinciden.");
        }

        const password = document.getElementById("password");
        const confirmPassword = document.querySelector("[name='confirmPassword']");
        if (password.value.trim().length < 6) {
            showError(password, "La contraseña debe tener al menos 6 caracteres.");
        } else if (password.value !== confirmPassword.value) {
            showError(confirmPassword, "Las contraseñas no coinciden.");
        }

        // Prevenir envío si hay errores
        if (!valid) {
            event.preventDefault();
        }
    });
});
