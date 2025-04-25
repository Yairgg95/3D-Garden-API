document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("mail");
    const phoneInput = document.getElementById("phone");
    const messageInput = document.getElementById("message");
    const submitButton = form.querySelector("button[type='button']");
    const modal = new bootstrap.Modal(document.getElementById("thanksModal"));

    const createError = (input, message) => {
        let error = input.nextElementSibling;
        if (!error || !error.classList.contains("text-danger")) {
            error = document.createElement("div");
            error.className = "text-danger mt-1";
            input.insertAdjacentElement("afterend", error);
        }
        error.textContent = message;
        };
    const clearError = (input) => {
        const error = input.nextElementSibling;
        if (error && error.classList.contains("text-danger")) {
            error.textContent = "";
        }
        };
    const validateName = () => {
        const name = nameInput.value.trim();
        if (!name) {
            createError(nameInput, "El nombre es obligatorio.");
            return false;
        }
        if (name.length <= 4) {
            createError(nameInput, "El nombre debe tener más de 4 caracteres.");
            return false;
        }
        clearError(nameInput);
        return true;
        };
    const validateEmail = () => {
        const email = emailInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!email) {
            createError(emailInput, "El correo es obligatorio.");
            return false;
        }
        if (!emailRegex.test(email)) {
            createError(emailInput, "El correo no tiene un formato válido.");
            return false;
        }
        clearError(emailInput);
        return true;
        };
    const validatePhone = () => {
        const phone = phoneInput.value.trim();
        const phoneRegex = /^[0-9]+$/;
        if (!phone) {
            createError(phoneInput, "El teléfono es obligatorio.");
            return false;
        }
        if (!phoneRegex.test(phone)) {
            createError(phoneInput, "Solo se permiten números (sin espacios ni guiones).");
            return false;
        }
        if (phone.length !== 10) {
            createError(phoneInput, "El teléfono debe tener 10 dígitos.");
            return false;
        }
        clearError(phoneInput);
        return true;
        };
    const validateMessage = () => {
        if (!messageInput.value.trim()) {
            createError(messageInput, "El mensaje no puede estar vacío.");
            return false;
        }
        clearError(messageInput);
        return true;
        };

    const validateForm = () => {
        const nameValid = validateName();
        const emailValid = validateEmail();
        const phoneValid = validatePhone();
        const messageValid = validateMessage();
        return nameValid && emailValid && phoneValid && messageValid;
        };
    // Eventos en tiempo real
    nameInput.addEventListener("input", validateName);
    emailInput.addEventListener("input", validateEmail);
    phoneInput.addEventListener("input", validatePhone);
    messageInput.addEventListener("input", validateMessage);

    submitButton.addEventListener("click", (e) => {
        e.preventDefault();
        if (validateForm()) {
            modal.show();
            form.reset();
            clearError(nameInput);
            clearError(emailInput);
            clearError(phoneInput);
            clearError(messageInput);
        }
        });
    });



