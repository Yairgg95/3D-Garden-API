// Datos de productos
const products = [
    { id: 1, name: "Maceta Pokemon Chikorita 3D ", price: 100, img: '../assets/macetas/1.jpg' },
    { id: 2, name: "Maceta Pokemon Charmander 3D", price: 250, img: '../assets/macetas/3.png' },
    { id: 3, name: "Maceta Pokemon Bulbasaur 3D", price: 200, img: '../assets/macetas/2.jpg'  },
    { id: 4, name: "Maceta Pokemon Gengar 3D", price: 200, img: '../assets/macetas/4.jpg' },
    { id: 5, name: "Maceta Pokemon Pikachu 3D", price: 300, img: '../assets/macetas/5.jpg' },
    { id: 6, name: "Maceta Pokemon Chikorita 3D", price: 110, img: '../assets/macetas/1.jpg' }
];

function displayProducts() {
    productListElement.innerHTML = '';
    products.forEach(product => {
        const productElement = document.createElement("div");
        productElement.classList.add("col-12 col-sm-6 col-md-4");

        productElement.innerHTML = `
            <div class="card h-100 shadow-lg">
                <img src="${product.img}" class="card-img-top" alt="${product.name}" style="height: 180px; object-fit: cover;">
                <div class="card-body text-center">
                    <h5 class="card-title roboto-flex">${product.name}</h5>
                    <p class="card-text roboto-flex">Precio: $${product.price}</p>
                    <button class="btn btn-primary w-100" onclick="addToCart(${product.id})">Añadir al carrito</button>
                </div>
            </div>
        `;
        productListElement.appendChild(productElement);
    });
}


// Variables
let cart = []; // Array para el carrito
const cartItemsElement = document.getElementById("cart-items");
const totalPriceElement = document.getElementById("total-price");
const productListElement = document.getElementById("product-list");

// Función para mostrar productos
function displayProducts() {
    productListElement.innerHTML = '';
    products.forEach(product => {
        const productElement = document.createElement("div");
        productElement.classList.add("col-12", "col-sm-6", "col-md-6", "mb-4");
        // Crea columnas de 4 unidades en pantallas medianas y añade margen inferior
        productElement.innerHTML = `
            <div class="card">
                <img src="${product.img}" class="card-img-top" alt="${product.name}">
                <div class="card-body">
                    <h5 class="card-title roboto-flex">${product.name}</h5>
                    <p class="card-text roboto-flex">Precio: $${product.price}</p>
                    <button class="btn btn-primary" onclick="addToCart(${product.id})">Añadir al carrito</button>
                </div>
            </div>
             <br>
        `;
        productListElement.appendChild(productElement);
    });
}

// Función para agregar al carrito
function addToCart(productId) {
    const product = products.find(p => p.id === productId);
    if (product) {
        const existingProduct = cart.find(item => item.id === productId);
        if (existingProduct) {
            existingProduct.quantity += 1;
        } else {
            cart.push({ ...product, quantity: 1 });
        }
        updateCart();
    }
}



// Función para actualizar el carrito
function updateCart() {
    const SHIPPING_COST = 80;
    cartItemsElement.innerHTML = ''; // Limpiar carrito actual
    let total = 0;

    cart.forEach((item, index) => {
        const cartItemElement = document.createElement("div");
        cartItemElement.classList.add("card", "mb-3");
        cartItemElement.innerHTML = `
        <div class="row g-0 align-items-center">
        <div class="col-3">
            <img src="${item.img}" class="img-fluid rounded-start" alt="${item.name}">
         </div>

        <div class="col-6">
         <div class="card-body py-2 px-3">
        <h6 class="card-title mb-1">${item.name}</h6>
        <p class="card-text mb-1">Precio: $${item.price}</p>
        <p class="card-text">Cantidad: ${item.quantity}</p>
         </div>
         </div>
    
         <div class="col-3 text-end pe-3">
             <button class="btn btn-outline-danger btn-sm" onclick="removeFromCart(${index})">
            <i class="bi bi-trash"></i>
            </button>
        </div>
        </div>
        `;

    
        cartItemsElement.appendChild(cartItemElement);
        total += item.price * item.quantity;
    });

    totalPriceElement.textContent = total;

    localStorage.setItem('cart', JSON.stringify(cart));
}


function loadCart() {
    const storedCart = localStorage.getItem('cart');
    if (storedCart) {
        cart = JSON.parse(storedCart);
    }
    updateCart(); // <-- Agregado para que se muestre en pantalla
}


function removeFromCart(index) {
    if (cart[index].quantity > 1) {
        cart[index].quantity -= 1;
    } else {
        cart.splice(index, 1);
    }
    updateCart();
}


function clearCart() {
    cart = []; // Vaciar el array del carrito
    localStorage.removeItem('cart'); // Eliminar el carrito de localStorage
    updateCart(); // Actualizar la visualización del carrito
}
const clearCartButton = document.getElementById("clear-cart-button");
clearCartButton.addEventListener("click", clearCart);

const checkoutButton = document.getElementById("checkout-button");
checkoutButton.addEventListener("click", proceedToCheckout);


function showBootstrapAlert(message, type = 'success') {
    const alertContainer = document.getElementById("alert-container");
    alertContainer.innerHTML = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
        </div>
    `;
}
function proceedToCheckout() {
    if (cart.length === 0) {
        showBootstrapAlert("Tu carrito está vacío.", "warning");
        return;
    }

    showBootstrapAlert("¡Gracias por tu compra! Te llevaremos al pago...", "success");

    setTimeout(() => {
        window.location.href = "checkout.html";
    }, 3000); 
}

// Llamar a loadCart al inicio
loadCart();

updateCart();
// Mostrar productos cuando cargue la página
displayProducts();
