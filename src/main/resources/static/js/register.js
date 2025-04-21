window.onload = function() {
    // Desplaza la página al centro vertical
    window.scrollTo({
        top: document.body.scrollHeight / 2 - window.innerHeight / 2,
        behavior: 'auto' // 'smooth' para un efecto suave
    });
    
};

document.addEventListener("DOMContentLoaded", () => {
    const formulario = document.querySelector("form");
    const contraseña = formulario.querySelector('input[placeholder="Contraseña"]');
    const confirmarContraseña = formulario.querySelector('input[placeholder="Confirma contraseña"]');

     // enfocar automáticamente en formulario
    document.querySelector('input[type="text"]').focus();

    confirmarContraseña.addEventListener("input", () => {
        if (confirmarContraseña.value !== contraseña.value) {
            mostrarError(confirmarContraseña, "Las contraseñas no coinciden", "danger");
        } else {
            mostrarError(confirmarContraseña, "Las contraseñas coinciden", "success");
        }
    });

    formulario.addEventListener("submit", (evento) => {
        evento.preventDefault();
        
        const usuario = formulario.querySelector('input[placeholder="Nombre completo"]');
        const correo = formulario.querySelector('input[placeholder="Correo electrónico"]');
        
        let esValido = true;
        
      // Validación de campos vacíos
        [usuario, correo, contraseña, confirmarContraseña].forEach(input => {
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
    
      // Validación de contraseñas
        if (contraseña.value !== confirmarContraseña.value) {
            mostrarError(confirmarContraseña, "Las contraseñas no coinciden", "danger");
            esValido = false;
        }
        
        if (esValido) {
            const datosUsuario = {
                nombre: usuario.value.trim(),
                email: correo.value.trim(),
                contraseña: contraseña.value.trim()  // (No se recomienda almacenar contraseñas en texto plano.)

            };
            // Guardardando los datos en localStorage (o enviar a un servidor)

            console.log("Usuario registrado:", JSON.stringify(datosUsuario, null, 2));
            
            localStorage.setItem("usuario", JSON.stringify(datosUsuario));
            alert("Registro exitoso");
            formulario.reset();
        
        // Redirigir a login.html después de 1.5 segundos
        setTimeout(() => {
            window.location.href = "login.html";
        }, 1500);
        }
    });

    function mostrarError(input, mensaje, tipo) {
        limpiarError(input);
        const error = document.createElement("small");
      // Aplicar la clase correcta según el tipo
        error.classList.add(tipo === "success" ? "text-success" : "text-danger");
        error.textContent = mensaje;
      // Aplicar el borde correcto según el tipo
        input.classList.add(tipo === "success" ? "border-success" : "border-danger");
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