document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');
    const emailInput = document.querySelector('input[name="email"]');
    const errorMessageDiv = document.querySelector('.error');

    // Validación de correo electrónico
    form.addEventListener("submit", function (event) {
        // Validar si el correo electrónico tiene el formato adecuado
        if (!emailInput.value.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/)) {
            event.preventDefault(); // Evitar el envío del formulario
            showError("Por favor ingresa un correo electrónico válido.");
        }
    });

    // Función para mostrar los errores
    function showError(message) {
        errorMessageDiv.textContent = message;
        errorMessageDiv.style.display = 'block';
    }
});