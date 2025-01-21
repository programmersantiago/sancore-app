document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');

    form.addEventListener('submit', function (event) {
        let valid = true;

        // Remover mensajes de error previos
        document.querySelectorAll('.error-tooltip').forEach(function (tooltip) {
            tooltip.remove();
        });

        // Función para mostrar un mensaje de error dentro del campo
        function showError(input, message) {
            valid = false;

            // Crear el contenedor del mensaje
            const errorTooltip = document.createElement('div');
            errorTooltip.className = 'error-tooltip';
            errorTooltip.textContent = message;

            // Estilizar el mensaje de error
            errorTooltip.style.backgroundColor = '#f44336';
            errorTooltip.style.color = '#ffffff';
            errorTooltip.style.padding = '5px 10px';
            errorTooltip.style.borderRadius = '5px';
            errorTooltip.style.fontSize = '12px';
            errorTooltip.style.marginTop = '5px';
            errorTooltip.style.display = 'block';

            // Colocar el mensaje justo después del campo
            input.insertAdjacentElement('afterend', errorTooltip);
        }

        // Validación del campo de nombre
        const firstName = document.getElementById('firstName');
        if (firstName.value.trim() === '') {
            showError(firstName, "El nombre es obligatorio.");
        }

        // Validación del campo de apellido
        const lastName = document.getElementById('lastName');
        if (lastName.value.trim() === '') {
            showError(lastName, "El apellido es obligatorio.");
        }

        // Validación del correo electrónico
        const email = document.getElementById('email');
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email.value)) {
            showError(email, "El correo electrónico no es válido.");
        }

        // Validación de la confirmación de correo electrónico
        const confirmEmail = document.getElementById('confirmEmail');
        if (email.value !== confirmEmail.value) {
            showError(confirmEmail, "Los correos electrónicos no coinciden.");
        }

        // Validación del usuario
        const nick = document.getElementById('nick');
        if (nick.value.trim().length < 3 || nick.value.trim().length > 15) {
            showError(nick, "El nombre de usuario debe tener entre 3 y 15 caracteres.");
        }

        // Validación de la contraseña
        const password = document.getElementById('password');
        if (password.value.trim().length < 8) {
            showError(password, "La contraseña debe tener al menos 8 caracteres.");
        }

        // Validación de la confirmación de contraseña
        const confirmPassword = document.getElementById('confirmPassword');
        if (password.value !== confirmPassword.value) {
            showError(confirmPassword, "Las contraseñas no coinciden.");
        }

        // Evitar que se envíe el formulario si hay errores
        if (!valid) {
            event.preventDefault();
        }
    });
});
