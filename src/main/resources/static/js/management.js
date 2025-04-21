const Products = [
  {
    id: 1,
    title: "Opaco café",
    image:
      "https://images.pexels.com/photos/931186/pexels-photo-931186.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Maceta elegante en tono café, perfecta para plantas pequeñas y suculentas.",
    quantityStock: 1,
    price: 2500.0,
  },
  {
    id: 2,
    title: "Mini fuente",
    image:
      "https://images.pexels.com/photos/6231857/pexels-photo-6231857.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Encantador florero mini con diseño de fuente, ideal para centros de mesa.",
    quantityStock: 5,
    price: 3800.0,
  },
  {
    id: 3,
    title: "Acompañante de sueño",
    image:
      "https://images.pexels.com/photos/7663251/pexels-photo-7663251.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Maceta moderna para tu planta de dormitorio, promueve la relajación.",
    quantityStock: 10,
    price: 3500.0,
  },
  {
    id: 4,
    title: "Jardin de niños",
    image:
      "https://images.pexels.com/photos/11286045/pexels-photo-11286045.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Divertido juego de macetas infantiles para iniciar pequeños jardineros.",
    quantityStock: 2,
    price: 1800.0,
  },
  {
    id: 5,
    title: "Juego de bases",
    image:
      "https://images.pexels.com/photos/4045533/pexels-photo-4045533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Set de macetas modulares para crear composiciones florales únicas.",
    quantityStock: 8,
    price: 1500.0,
  },
  {
    id: 6,
    title: "Romano",
    image:
      "https://images.pexels.com/photos/4272613/pexels-photo-4272613.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Florero clásico de inspiración romana para arreglos sofisticados.",
    quantityStock: 8,
    price: 1500.0,
  },
  {
    id: 7,
    title: "Tomáte",
    image:
      "https://images.pexels.com/photos/8170226/pexels-photo-8170226.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Maceta especial para cultivar tomates cherry en interiores.",
    quantityStock: 1,
    price: 2500.0,
  },
  {
    id: 8,
    title: "Suculenta",
    image:
      "https://images.pexels.com/photos/773805/pexels-photo-773805.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Maceta diseñada especialmente para el crecimiento de suculentas.",
    quantityStock: 5,
    price: 3800.0,
  },
  {
    id: 9,
    title: "Mini flor",
    image:
      "https://images.pexels.com/photos/1191318/pexels-photo-1191318.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description: "Pequeño florero para ramitos de flores frescas o secas.",
    quantityStock: 10,
    price: 3500.0,
  },
  {
    id: 10,
    title: "Maceta grande",
    image:
      "https://images.pexels.com/photos/3771640/pexels-photo-3771640.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    description:
      "Amplia maceta para plantas de gran tamaño o pequeños árboles.",
    quantityStock: 2,
    price: 1800.0,
  },
];

function renderManagement(Products) {
  //? Creacion de elementos del tablero admin
  //
  const table = document.getElementById("mainTable");
  const thead = document.createElement("thead");
  const tbody = document.createElement("tbody");
  const tr = document.createElement("tr");

  table.innerHTML = "";

  // Create header row with each key
  Object.keys(Products[0]).forEach((key) => {
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
    const td_quantityStock = document.createElement("td");
    const td_price = document.createElement("td");
    const td_manage = document.createElement("td");

    td_manage.className = "d-grid gap-2 d-md-flex justify-content-md-end";

    th.className = "col align-middle";
    td.className = "align-middle";
    td_image.className = "align-middle";
    img.className = "management-img rounded";
    td_description.className = "align-middle";
    td_quantityStock.className = "align-middle";
    td_price.className = "align-middle";
    td_manage.classList = "align-middle";
    td_manage.innerHTML =
      '<div class="d-flex gap-2"> <button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#confirmDeleteProduct">Eliminar</button> <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editProduct">Editar</button></div>';

    th.innerHTML = product.id;
    td.innerHTML = product.title;
    td_description.innerHTML = product.description;
    img.src = product.image;
    td_quantityStock.innerHTML = product.quantityStock;
    td_price.innerHTML = product.price;

    td_image.append(img);
    tr.append(
      th,
      td,
      td_image,
      td_description,
      td_quantityStock,
      td_price,
      td_manage
    );
    tbody.append(tr);
  });

  table.append(thead, tbody);
}

function showDeleteProductAlert(){
  var deleteProductAlert = document.getElementById("deleteProduct");
  deleteProductAlert.classList.remove("d-none");
}

document.addEventListener("DOMContentLoaded", () => {
  renderManagement(Products);
});
