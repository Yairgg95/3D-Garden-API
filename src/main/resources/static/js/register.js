document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const firstNameInput = form.querySelector('input[name="firtsName"]');
  const lastNameInput = form.querySelector('input[name="lastName"]');
  const emailInput = form.querySelector('input[type="email"]');
  const passwordInput = form.querySelector('input[placeholder="Contraseña"]');
  const confirmPasswordInput = form.querySelector(
    'input[placeholder="Confirma contraseña"]'
  );

  firstNameInput.focus();

  confirmPasswordInput.addEventListener("input", () => {
    if (confirmPasswordInput.value !== passwordInput.value) {
      showFeedback(
        confirmPasswordInput,
        "Las contraseñas no coinciden",
        "danger"
      );
    } else {
      showFeedback(
        confirmPasswordInput,
        "Las contraseñas coinciden",
        "success"
      );
    }
  });

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const isValid = validateForm(
      [
        firstNameInput,
        lastNameInput,
        emailInput,
        passwordInput,
        confirmPasswordInput,
      ],
      emailInput,
      passwordInput,
      confirmPasswordInput
    );

    if (!isValid) return;

    const userData = {
      name: firstNameInput.value.trim(),
      lastName: lastNameInput.value.trim(),
      email: emailInput.value.trim(),
      password: passwordInput.value.trim(),
    };

    try {
      const response = await fetch("http://3.145.32.20/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        form.reset();
        setTimeout(() => (window.location.href = "login.html"), 1500);
      } else {
        const errorData = await response.json();
        alert(`Error: ${errorData.message || "Registro fallido"}`);
      }
    } catch (error) {
      console.error("Fetch error:", error);
      alert("Ocurrió un error inesperado. Intenta más tarde.");
    }
  });
});

function showFeedback(input, message, type) {
  clearFeedback(input);
  const small = document.createElement("small");
  small.classList.add(type === "success" ? "text-success" : "text-danger");
  small.textContent = message;
  input.classList.add(type === "success" ? "border-success" : "border-danger");
  input.parentElement.appendChild(small);
}

function clearFeedback(input) {
  input.classList.remove("border-danger", "border-success");
  const error = input.parentElement.querySelector("small");
  if (error) error.remove();
}

function isValidEmail(email) {
  const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return pattern.test(email);
}

function validateForm(inputs, emailInput, passwordInput, confirmPasswordInput) {
  let valid = true;

  inputs.forEach((input) => {
    if (!input.value.trim()) {
      showFeedback(input, "Este campo es obligatorio", "danger");
      valid = false;
    } else {
      clearFeedback(input);
    }
  });

  if (!isValidEmail(emailInput.value)) {
    showFeedback(emailInput, "Formato de correo inválido", "danger");
    valid = false;
  }

  if (passwordInput.value !== confirmPasswordInput.value) {
    showFeedback(
      confirmPasswordInput,
      "Las contraseñas no coinciden",
      "danger"
    );
    valid = false;
  }

  return valid;
}
