const Products = [
  {
    id: 1,
    title: "Opaco café",
    image: "https://images.pexels.com/photos/931186/pexels-photo-931186.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Maceta elegante en tono café, perfecta para plantas pequeñas y suculentas.",
    quantityStock: 1,
    price: 2500.0,
    brand: "3D-Garden",
  },
  {
    id: 2,
    title: "Mini fuente",
    image: "https://images.pexels.com/photos/6231857/pexels-photo-6231857.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Encantador florero mini con diseño de fuente, ideal para centros de mesa.",
    quantityStock: 5,
    price: 3800.0,
    brand: "3D-Garden",
  },
  {
    id: 3,
    title: "Acompañante de sueño",
    image: "https://images.pexels.com/photos/7663251/pexels-photo-7663251.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Maceta moderna para tu planta de dormitorio, promueve la relajación.",
    quantityStock: 10,
    price: 3500.0,
    brand: "3D-Garden",
  },
  {
    id: 4,
    title: "Jardin de niños",
    image: "https://images.pexels.com/photos/11286045/pexels-photo-11286045.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Divertido juego de macetas infantiles para iniciar pequeños jardineros.",
    quantityStock: 2,
    price: 1800.0,
    brand: "3D-Garden",
  },
  {
    id: 5,
    title: "Juego de bases",
    image: "https://images.pexels.com/photos/4045533/pexels-photo-4045533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Set de macetas modulares para crear composiciones florales únicas.",
    quantityStock: 8,
    price: 1500.0,
    brand: "3D-Garden",
  },
  {
    id: 6,
    title: "Romano",
    image: "https://images.pexels.com/photos/4272613/pexels-photo-4272613.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Florero clásico de inspiración romana para arreglos sofisticados.",
    quantityStock: 8,
    price: 1500.0,
    brand: "3D-Garden",
  },
  {
    id: 7,
    title: "Tomáte",
    image: "https://images.pexels.com/photos/8170226/pexels-photo-8170226.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Maceta especial para cultivar tomates cherry en interiores.",
    quantityStock: 1,
    price: 2500.0,
    brand: "3D-Garden",
  },
  {
    id: 8,
    title: "Suculenta",
    image: "https://images.pexels.com/photos/773805/pexels-photo-773805.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Maceta diseñada especialmente para el crecimiento de suculentas.",
    quantityStock: 5,
    price: 3800.0,
    brand: "3D-Garden",
  },
  {
    id: 9,
    title: "Mini flor",
    image: "https://images.pexels.com/photos/1191318/pexels-photo-1191318.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Pequeño florero para ramitos de flores frescas o secas.",
    quantityStock: 10,
    price: 3500.0,
    brand: "3D-Garden",
  },
  {
    id: 10,
    title: "Maceta grande",
    image: "https://images.pexels.com/photos/3771640/pexels-photo-3771640.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Amplia maceta para plantas de gran tamaño o pequeños árboles.",
    quantityStock: 2,
    price: 1800.0,
    brand: "3D-Garden",
  },
];

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

function sortNameAZ() {
  const sortedProducts = [...Products].sort((a, b) =>
    a.title.localeCompare(b.title)
  );
  renderProducts(sortedProducts);
}

function sortNameZA() {
  const sortedProducts = [...Products].sort((a, b) =>
    b.title.localeCompare(a.title)
  );
  renderProducts(sortedProducts);
}

function sortPriceAsc() {
  const sortedProducts = [...Products].sort((a, b) => a.price - b.price);
  renderProducts(sortedProducts);
}

function sortPriceDesc() {
  const sortedProducts = [...Products].sort((a, b) => b.price - a.price);
  renderProducts(sortedProducts);
}

function sortSizeAsc() {
  const sortedProducts = [...Products].sort((a, b) => a.size - b.size);
  renderProducts(sortedProducts);
}

function sortSizeDesc() {
  const sortedProducts = [...Products].sort((a, b) => b.size - a.size);
  renderProducts(sortedProducts);
}

function resetProducts() {
  renderProducts(Products);
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
    
    showToast(`${product.title} agregado al carrito!`);
  }
  localStorage.setItem("cart", JSON.stringify(cart));
}

const toastProducts = document.getElementById('liveToast');
const toast = new bootstrap.Toast(toastProducts);

function showToast(message) {
  toastProducts.querySelector('.toast-body').textContent = message;
  toast.show();
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
    cardTitle.innerHTML = product.title;
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

document.addEventListener("DOMContentLoaded", () => {
  renderProducts(Products);
});
