document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');
    form.addEventListener('submit', function (event) {
        let valid = true;

        // Validación del campo de nombre
        const firstName = document.getElementById('firstName');
        if (firstName.value.trim() === '') {
            valid = false;
            alert("El nombre es obligatorio.");
        }

        // Validación del campo de apellido
        const lastName = document.getElementById('lastName');
        if (lastName.value.trim() === '') {
            valid = false;
            alert("El apellido es obligatorio.");
        }

        // Validación del correo electrónico
        const email = document.getElementById('email');
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email.value)) {
            valid = false;
            alert("El correo electrónico no es válido.");
        }

        // Validación del usuario
        const nick = document.getElementById('nick');
        if (nick.value.trim().length < 3 || nick.value.trim().length > 15) {
            valid = false;
            alert("El nombre de usuario debe tener entre 3 y 15 caracteres.");
        }

        // Validación de la contraseña
        const password = document.getElementById('password');
        if (password.value.trim().length < 6) {
            valid = false;
            alert("La contraseña debe tener al menos 6 caracteres.");
        }

        if (!valid) {
            event.preventDefault(); // Evita que el formulario se envíe si las validaciones fallan
        }
    });
});