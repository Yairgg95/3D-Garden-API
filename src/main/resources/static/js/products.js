const route = "http://3.145.32.20";
const toastProducts = document.getElementById('liveToast');
const toast = new bootstrap.Toast(toastProducts);
let products = [];

function fetchProducts() {
  fetch(route + "/api/products")
    .then(response => {
      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      products = data;
      renderProducts(products);
    })
    .catch(error => {
      console.error("Error obteniendo los productos:", error);
    });
}

function renderProducts(Products) {
  const productCard = document.getElementById("productsCards");
  productCard.innerHTML = "";

  Products.forEach((product) => {
    const col = document.createElement("div");
    col.className = "col col-product pb-3";
    const card = document.createElement("div");
    card.className = "card d-flex flex-column h-100 align-items-center";
    const img = document.createElement("img");
    img.className = "card-img-top img-fluid rounded products-img";
    const cardBody = document.createElement("div");
    cardBody.className = "card-body text-center";
    const cardTitle = document.createElement("h5");
    cardTitle.className = "card-title card-title-products";
    const cardText = document.createElement("p");
    cardText.className = "card-text card-text-product";
    const addCart = document.createElement("a");
    addCart.className = "text-decoration-none text-primary-hover";

    img.src = product.image;
    cardTitle.innerHTML = product.name;
    cardText.innerHTML =
      "Precio: $" +
      product.price +
      ".00" +
      "<br>" +
      "Descripción: " +
      product.description;
    addCart.innerHTML = '<i class="bi bi-cart fs-1"></i>';
    addCart.addEventListener('click', function() {
      addToCart(product.id);
    });

    cardBody.append(cardTitle, cardText, addCart);
    card.append(img, cardBody);
    col.append(card);
    productCard.append(col);
  });
}

// Filter dropdowns
document.querySelectorAll(".filter-btn").forEach((btn) => {
  btn.addEventListener("click", () => {
    const category = btn.dataset.category;
    document.querySelectorAll(".item").forEach((item) => {
      item.style.display =
        item.dataset.category === category ? "block" : "none";
    });
  });
});


// Sort functions
function sortNameAZ() {
  checkProducts();
  const sortedProducts = [...products].sort((a, b) =>
    a.name.localeCompare(b.name)
  );
  renderProducts(sortedProducts);
}

function sortNameZA() {
  checkProducts();
  const sortedProducts = [...products].sort((a, b) =>
    b.name.localeCompare(a.name)
  );
  renderProducts(sortedProducts);
}

function sortPriceAsc() {
  checkProducts();
  const sortedProducts = [...products].sort((a, b) => a.price - b.price);
  renderProducts(sortedProducts);
}

function sortPriceDesc() {
  checkProducts();
  const sortedProducts = [...products].sort((a, b) => b.price - a.price);
  renderProducts(sortedProducts);
}

function sortSizeAsc() {
  checkProducts();
  const sortedProducts = [...products].sort((a, b) => a.size - b.size);
  renderProducts(sortedProducts);
}

function sortSizeDesc() {
  checkProducts();
  const sortedProducts = [...products].sort((a, b) => b.size - a.size);
  renderProducts(sortedProducts);
}

function resetProducts() {
  checkProducts();
  renderProducts(products);
}

function checkProducts(){
  if (!products) {
    console.error("No hay productos.");
    return;
  }
}

function addToCart(productId) {

  const product = Products.find((p) => p.id === productId);

  // Retrieve existing cart from local storage
  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  // Check if product already exists
  const idProduct = cart.findIndex((item) => item.id === productId);

  if (idProduct > -1) {
    cart[idProduct].quantity += 1;
    showToast(`${product.title} ya existe en tu carrito.`);
  } else {
    cart.push({
      ...product,
      quantity: 1,
    });

    showToast(`${product.name} agregado al carrito!`);
  }
  localStorage.setItem("cart", JSON.stringify(cart));
}

function showToast(message) {
  toastProducts.querySelector('.toast-body').textContent = message;
  toast.show();
}

document.addEventListener("DOMContentLoaded", () => {
  fetchProducts();
});
