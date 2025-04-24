document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const emailInput = form.querySelector('input[placeholder="Ingresa tu correo electrónico"]');
  const passwordInput = form.querySelector('input[placeholder="Ingresa tu contraseña"]');

  emailInput.focus();

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    let isValid = true;

    [emailInput, passwordInput].forEach((input) => {
      if (!input.value.trim()) {
        showError(input, "Este campo es obligatorio", "danger");
        isValid = false;
      } else {
        clearError(input);
      }
    });

    if (!validateEmail(emailInput.value)) {
      showError(emailInput, "Correo electrónico inválido", "danger");
      isValid = false;
    }

    if (!isValid) return;

    try {
      const response = await fetch("http://3.145.32.20/api/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email: emailInput.value.trim(),
          password: passwordInput.value,
        }),
      });

      const user = await response.json();

      if (response.ok && user.registrationDate) {
              localStorage.setItem("user", JSON.stringify(user));

              if (user.role === "admin") {
                localStorage.setItem(
                  "adminToken",
                  JSON.stringify(user.registrationDate)
                );
                window.location.href = "./management.html";
              } else {
                localStorage.setItem("token", JSON.stringify(user.registrationDate));
                window.location.href = "./products.html";
              }
            } else {
        showError(emailInput, "Credenciales incorrectas", "danger");
        showError(passwordInput, "Credenciales incorrectas", "danger");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Ocurrió un error inesperado. Intenta nuevamente.");
    }
  });

  function showError(input, message, type) {
    clearError(input);
    const small = document.createElement("small");
    small.classList.add(type === "success" ? "text-success" : "text-danger");
    small.textContent = message;
    input.classList.add(type === "success" ? "border-success" : "border-danger");
    input.parentElement.appendChild(small);
  }

  function clearError(input) {
    input.classList.remove("border-danger", "border-success");
    const small = input.parentElement.querySelector("small");
    if (small) small.remove();
  }

  function validateEmail(email) {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return pattern.test(email);
  }
});
