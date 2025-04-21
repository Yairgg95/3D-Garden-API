// Función para mostrar un toast de Bootstrap
function showToast(mensaje) {
    const toastMessage = document.getElementById("toastMessage");
    const toast = new bootstrap.Toast(document.getElementById("toast"));
  
    toastMessage.textContent = mensaje; // Establece el mensaje
    toast.show(); // Muestra el toast
  }
  
  // Selecciona el formulario por su ID y añade un evento 'submit' para manejar el envío del formulario.
  document.getElementById("formularioMaceta").addEventListener("submit", function (event) {
    // Previene el comportamiento por defecto del formulario (evita que se recargue la página).
    event.preventDefault();
  
    // Obtiene los valores de los campos del formulario.
    const diseñoMaceta = document.getElementById("diseñoMaceta").value;
    const altura = document.getElementById("altura").value;
    const ancho = document.getElementById("ancho").value;
    const profundidad = document.getElementById("profundidad").value;
  
    // Verifica si los campos de forma y dimensiones están vacíos.
    if (!diseñoMaceta || !altura || !ancho || !profundidad) {
      // Muestra una alerta si algún campo obligatorio no está completado.
      showToast("Por favor, complete todos los campos obligatorios: forma y dimensiones.");
    } else {
      // Muestra una alerta indicando que el pedido se ha registrado correctamente.
      showToast(
        "Su pedido ha sido registrado exitosamente. Recibirá un correo para confirmar su diseño o realizar cambios."
      );
  
      // Limpia todos los campos del formulario.
      document.getElementById("formularioMaceta").reset();
      // Oculta el botón de borrar imagen si estaba visible.
      botonBorrarImagen.style.display = "none";
    }
  });
  
  // Selecciona el campo de carga de imagen y el botón de borrar.
  const imagenMaceta = document.getElementById("imagenMaceta");
  const botonBorrarImagen = document.getElementById("borrarImagen");
  
  // Añade un evento 'change' al campo de carga de imagen.
  imagenMaceta.addEventListener("change", function () {
    // Si se selecciona un archivo, muestra el botón de borrar.
    if (this.files.length > 0) {
      botonBorrarImagen.style.display = "block";
    } else {
      botonBorrarImagen.style.display = "none";
    }
  });
  
  // Añade un evento 'click' al botón de borrar.
  botonBorrarImagen.addEventListener("click", function () {
    // Limpia el campo de carga de imagen.
    imagenMaceta.value = "";
    // Oculta el botón de borrar.
    botonBorrarImagen.style.display = "none";
    // Muestra un mensaje de confirmación.
    showToast("La imagen ha sido eliminada correctamente.");
  });