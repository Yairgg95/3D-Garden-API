const route = "http://3.145.32.20";

// Función para mostrar un toast de Bootstrap
function showToast(mensaje) {
  const toastMessage = document.getElementById("toastMessage");
  const toast = new bootstrap.Toast(document.getElementById("toast"));

  toastMessage.textContent = mensaje; // Establece el mensaje
  toast.show(); // Muestra el toast
}

// Selecciona el formulario por su ID y añade un evento 'submit' para manejar el envío del formulario.
document
  .getElementById("formularioMaceta")
  .addEventListener("submit", addPersonalizedProduct);
function addPersonalizedProduct(event) {
  event.preventDefault();

  const name = document.getElementById("correo").value;
  const forma = document.getElementById("diseñoMaceta").value;
  const altura = document.getElementById("altura").value;
  const ancho = document.getElementById("ancho").value;
  const profundidad = document.getElementById("profundidad").value;
  const caracteristicas = document.getElementById("message").value;
  const image = document.getElementById("imagenMaceta").value;
  const botonBorrarImagen = document.getElementById("borrarImagen");

  // Validación de campos obligatorios
  if (!forma || !altura || !ancho || !profundidad) {
    showToast(
      "Por favor, complete todos los campos obligatorios: forma y dimensiones."
    );
    return;
  }

  const description = `Forma: ${forma}, Dimensiones: ${altura}x${ancho}x${profundidad} cm, Características: ${caracteristicas}`;

  const productData = {
    name: name,
    description: description,
    price: 0,
    stock: 0,
    image: image,
  };

  fetch(route + "/api/products", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(productData),
  })
    .then((response) => {
      if (!response.ok)
        throw new Error(`Error HTTP! status: ${response.status}`);
      return response.json();
    })
    .then((data) => {
      console.log("Producto personalizado agregado:", data);
      showToast(
        "Su pedido ha sido registrado exitosamente. Recibirá un correo para confirmar su diseño o realizar cambios."
      );
      document.getElementById("formularioMaceta").reset();
    })
    .catch((error) => {
      console.error("Error agregando el producto personalizado:", error);
    });
}
