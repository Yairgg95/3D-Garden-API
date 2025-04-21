const imgProducts = [
  {
    alt: "maceta geometrica",
    url: "https://www.colorplus3d.com/wp-content/uploads/2021/06/Plantygon_impresion3d_impresora3d_filamentos3d_basepla_filamentopremium_colorplus.jpg",
  },
  {
    alt: "maceta geometrica",
    url: "https://image.jimcdn.com/app/cms/image/transf/none/path/s46adb1a4c114f4c4/image/iccdc74de6fa96ded/version/1601948522/image.jpg",
  },
  {
    alt: "maceta pokeball",
    url: "https://cdn1.coppel.com/images/catalog/mkp/7370/3000/73701013-1.jpg",
  },
  {
    alt: "maceta",
    url: "https://http2.mlstatic.com/D_NQ_NP_674422-MLM76055620972_052024-O.webp",
  },
  {
    alt: "macetas blanca y rosa",
    url: "https://acdn-us.mitiendanube.com/stores/004/267/088/products/maceta-3d-rosa-771ab270fea944e6cc17127781470777-640-0.png",
  },
  {
    alt: "maceta geometrica",
    url: "https://http2.mlstatic.com/D_NQ_NP_687753-MLA77707658142_072024-O.webp",
  },
];
document.addEventListener("DOMContentLoaded", () => {
  const productsContainer = document.getElementById("products-container");

  imgProducts.forEach((img) => {
    const link = document.createElement("a");
    link.href = "../views/products.html";
    link.className =
      "product-link text-decoration-none col-12 col-md-6 col-lg-4 d-flex justify-content-center my-2 p-2";

    const imgContainer = document.createElement("div");
    imgContainer.className = "product-hover w-100";

    const product = document.createElement("img");
    product.src = img.url;
    product.alt = img.alt;
    product.className = "img-fluid rounded-4";
    product.style.width = "100%";
    product.style.maxWidth = "350px";
    product.style.height = "350px";
    product.style.objectFit = "cover";

    imgContainer.appendChild(product);
    link.appendChild(imgContainer);
    productsContainer.appendChild(link);
  });
});
