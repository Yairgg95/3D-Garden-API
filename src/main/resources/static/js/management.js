const route = "http://localhost:8080";
let products = [];
keys = ["Id", "Nombre", "Imagen", "Descripción", "Stock", "Precio"];

function fetchProducts() {
  fetch(route + "/api/products/")
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      products = data;
      renderManagement(products);
    })
    .catch((error) => {
      console.error("Error obteniendo los productos:", error);
    });
}

function renderManagement(Products) {
  //? Creacion de elementos del tablero admin

  const table = document.getElementById("mainTable");
  const thead = document.createElement("thead");
  const tbody = document.createElement("tbody");
  const tr = document.createElement("tr");

  table.innerHTML = "";

  keys.forEach((key) => {
    const th = document.createElement("th");
    th.className = "col";
    th.textContent = key;
    tr.append(th);
  });

  const thAdmin = document.createElement("th");
  thAdmin.className = "col";
  thAdmin.textContent = "Administrar";
  tr.append(thAdmin);

  thead.append(tr);

  // Insert each product element
  Products.forEach((product) => {
    const tr = document.createElement("tr");
    const th = document.createElement("th");
    const td = document.createElement("td");
    const td_description = document.createElement("td");
    const td_image = document.createElement("td");
    const img = document.createElement("img");
    const td_stock = document.createElement("td");
    const td_price = document.createElement("td");
    const td_manage = document.createElement("td");

    td_manage.className = "d-grid gap-2 d-md-flex justify-content-md-end";

    th.className = "col align-middle";
    td.className = "align-middle";
    td_image.className = "align-middle";
    img.className = "management-img rounded";
    td_description.className = "align-middle";
    td_stock.className = "align-middle";
    td_price.className = "align-middle";
    td_manage.classList = "align-middle";

    // Create delete button
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Eliminar";
    deleteBtn.className = "btn btn-danger me-2";
    deleteBtn.setAttribute("type", "button");
    deleteBtn.setAttribute("data-bs-toggle", "modal");
    deleteBtn.setAttribute("data-bs-target", "#confirmDeleteProduct");
    deleteBtn.setAttribute("data-product-id", product.id);

    // Create edit button
    const editBtn = document.createElement("button");
    editBtn.textContent = "Editar";
    editBtn.className = "btn btn-warning";
    editBtn.setAttribute("type", "button");
    editBtn.setAttribute("data-bs-toggle", "modal");
    editBtn.setAttribute("data-bs-target", "#editProduct");
    editBtn.setAttribute("data-product-id", product.id);
    editBtn.onclick = function () {
      editPrefillModal(product);
    };

    th.innerHTML = product.id;
    td.innerHTML = product.name;
    td_description.innerHTML = product.description;
    img.src = product.image;
    td_stock.innerHTML = product.stock;
    td_price.innerHTML = product.price;

    td_manage.append(deleteBtn, editBtn);
    td_image.append(img);
    tr.append(th, td, td_image, td_description, td_stock, td_price, td_manage);
    tbody.append(tr);
  });

  table.append(thead, tbody);
}

function addProduct(event) {
  event.preventDefault();

  const name = document.getElementById("name").value;
  const image = document.getElementById("image").value;
  const description = document.getElementById("description").value;
  const stock = parseInt(document.getElementById("stock").value);
  const price = parseFloat(document.getElementById("price").value);

  const productData = {
    name: name,
    description: description,
    price: price,
    stock: stock,
    image: image,
  };

  fetch(route + "/api/products/", {
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
      console.log("Producto agregado:", data);

      document.getElementById("name").value = "";
      document.getElementById("image").value = "";
      document.getElementById("description").value = "";
      document.getElementById("stock").value = "";
      document.getElementById("price").value = "";

      bootstrap.Modal.getInstance(document.getElementById("addProduct")).hide();
      fetchProducts();
    })
    .catch((error) => {
      console.error("Error agregando el producto:", error);
    });
}

function showDeleteProductAlert() {
  var deleteProductAlert = document.getElementById("deleteProduct");
  deleteProductAlert.classList.remove("d-none");
}

function deleteProduct() {
  const openButton = document.querySelector(
    '[data-bs-toggle="modal"][data-bs-target="#confirmDeleteProduct"][data-product-id]'
  );
  const productId = openButton.getAttribute("data-product-id");

  fetch(route + "/api/products/" + productId, {
    method: "DELETE",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Producto eliminado:", data);
      bootstrap.Modal.getInstance(document.getElementById("confirmDeleteProduct")).hide();
      fetchProducts();
    })
    .catch((error) => {
      console.error("Error eliminando el producto:", error);
    });
}

function editPrefillModal(product) {
  document
    .getElementById("editProduct")
    .setAttribute("data-product-id", product.id);
  document.getElementById("edit_name").value = product.name;
  document.getElementById("edit_image").value = product.image;
  document.getElementById("edit_description").value = product.description;
  document.getElementById("edit_stock").value = product.stock;
  document.getElementById("edit_price").value = product.price;
}

function editProduct(event) {
  event.preventDefault();

  const productId = document
    .getElementById("editProduct")
    .getAttribute("data-product-id");

  const name = document.getElementById("edit_name").value;
  const description = document.getElementById("edit_description").value;
  const stock = parseInt(document.getElementById("edit_stock").value);
  const price = parseFloat(document.getElementById("edit_price").value);

  const productData = {
    name,
    description,
    stock,
    price,
  };

  fetch(route + "/api/products/" + productId, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(productData),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Producto actualizado:", data);
      bootstrap.Modal.getInstance(document.getElementById("editProduct")).hide();
      fetchProducts();
    })
    .catch((error) => {
      console.error("Error actualizando el producto:", error);
    });
}

document.addEventListener("DOMContentLoaded", () => {
  fetchProducts(products);
});
