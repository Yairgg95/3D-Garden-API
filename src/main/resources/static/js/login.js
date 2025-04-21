window.onload = function () {
  // Desplaza la página al centro vertical
  window.scrollTo({
    top: document.body.scrollHeight / 2 - window.innerHeight / 2,
    behavior: "auto", // 'smooth' para un efecto suave
  });
};

document.addEventListener("DOMContentLoaded", () => {
  const formulario = document.querySelector("form");
  const correo = formulario.querySelector(
    'input[placeholder="Ingresa tu correo electrónico"]'
  );
  const contraseña = formulario.querySelector(
    'input[placeholder="Ingresa tu contraseña"]'
  );

  // Focar automáticamente en el formulario
  document.querySelector('input[type="text"]').focus();

  formulario.addEventListener("submit", (evento) => {
    evento.preventDefault();

    let esValido = true;

    // Validación de campos vacíos
    [correo, contraseña].forEach((input) => {
      if (!input.value.trim()) {
        mostrarError(input, "Este campo es obligatorio", "danger");
        esValido = false;
      } else {
        limpiarError(input);
      }
    });

    // Validación de correo electrónico
    if (!validarCorreo(correo.value)) {
      mostrarError(correo, "Correo electrónico inválido", "danger");
      esValido = false;
    }

    if (esValido) {
      // Simulación de inicio de sesión exitoso
      fetch("https://dummyjson.com/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          /* username: 'emilys',
              password: 'emilyspass',
              */
          username: "emilys", // Se tiene que modificar para aceptar el correo
          password: contraseña.value,
        }),
      })
        .then((res) => res.json())
        .then((data) => {
          if (data.accessToken) {
            localStorage.setItem("token", JSON.stringify(data.accessToken));
            alert("Inicio de sesión exitoso");
            window.location.href = "../index.html";
          } else {
            mostrarError(correo, "Credenciales incorrectas", "danger");
            mostrarError(contraseña, "Credenciales incorrectas", "danger");
          }
        })
        .catch((err) => console.error("Error:", err));
    }
  });

  function mostrarError(input, mensaje, tipo) {
    limpiarError(input);
    const error = document.createElement("small");
    // Aplicar la clase correcta según el tipo
    error.classList.add(tipo === "success" ? "text-success" : "text-danger");
    error.textContent = mensaje;
    // Aplicar el borde correcto según el tipo
    input.classList.add(
      tipo === "success" ? "border-success" : "border-danger"
    );
    input.parentElement.appendChild(error);
  }

  function limpiarError(input) {
    input.classList.remove("border-danger", "border-success");
    const error = input.parentElement.querySelector("small");
    if (error) {
      error.remove();
    }
  }

  function validarCorreo(correo) {
    const expresion = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return expresion.test(correo);
  }
});
