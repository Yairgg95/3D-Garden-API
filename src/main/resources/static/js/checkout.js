document.addEventListener("DOMContentLoaded", () => {
  const cardNumberInput = document.getElementById("card-number");
  const expiryDateInput = document.getElementById("expiry-date");
  const cardBrand = document.getElementById("card-brand");
  const shippingForm = document.getElementById("shipping-form");
  const paymentForm = document.getElementById("payment-form");
  const addressChoiceContainer = document.getElementById(
    "address-choice-container"
  );
  const savedAddressText = document.getElementById("saved-address-text");

  function updateAddressView() {
    const address = JSON.parse(localStorage.getItem("address")); // this need to refactor when the endpoint is ready

    if (address) {
      const userAddres = `${address.street} ${address.extNum} ${
        address.intNum ? address.intNum : ""
      } ${address.city} ${address.state}`;
      savedAddressText.textContent = `Dirección guardada: ${userAddres}`;
      addressChoiceContainer.classList.remove("d-none");
      shippingForm.classList.add("d-none");
    } else {
      addressChoiceContainer.classList.add("d-none");
      shippingForm.classList.remove("d-none");
    }
  }

  updateAddressView();

  function showToast(message, bgClass) {
    const toastElement = document.getElementById("main-toast");
    const toastBody = document.getElementById("toast-body");

    toastBody.textContent = message;

    toastElement.className = `toast align.items-center texte-white ${bgClass} border-0`;

    const bsToast = new bootstrap.Toast(toastElement);
    bsToast.show();
  }

  showToast(
    "Este formulario de pago es únicamente con fines de demostración. Por favor, no proporciones información real.",
    "bg-warning"
  );

  document.getElementById("use-saved").addEventListener("change", () => {
    if (document.getElementById("use-saved").checked) {
      shippingForm.classList.add("d-none");
    }
  });

  document.getElementById("update-address").addEventListener("change", () => {
    if (document.getElementById("update-address").checked) {
      shippingForm.classList.remove("d-none");
    }
  });

  expiryDateInput.addEventListener("input", (e) => {
    let value = e.target.value.replace(/\D/g, "");
    if (value.length > 2) {
      value = `${value.slice(0, 2)}/${value.slice(2, 4)}`;
    }
    e.target.value = value;
  });

  expiryDateInput.addEventListener("blur", (e) => {
    const [month, year] = e.target.value.split("/").map(Number);
    const currentYear = new Date().getFullYear() % 100;
    if (month < 1 || month > 12 || year < currentYear) {
      showToast("Fecha de expiración inválida", "bg-danger");
      e.target.value = "";
    }
  });

  const cardTypes = {
    visa: /^4/,
    mastercard: /^5[1-5]/,
    amex: /^3[47]/,
  };

  function detectCardType(number) {
    number = number.replace(/\s+/g, "");
    if (cardTypes.visa.test(number)) {
      return "VISA";
    } else if (cardTypes.mastercard.test(number)) {
      return "MasterCard";
    } else if (cardTypes.amex.test(number)) {
      return "AMEX";
    } else {
      return "Invalid";
    }
  }

  cardNumberInput.addEventListener("input", (e) => {
    let value = e.target.value.replace(/\D/g, "");
    value = value.replace(/(\d{4})/g, "$1 ").trim();
    e.target.value = value;

    const cardType = detectCardType(value);
    let cardImageUrl = "";

    if (cardType === "VISA") {
      cardImageUrl =
        "https://w7.pngwing.com/pngs/308/426/png-transparent-visa-logo-credit-card-visa-logo-payment-visa-blue-text-trademark-thumbnail.png";
    } else if (cardType === "MasterCard") {
      cardImageUrl =
        "https://w7.pngwing.com/pngs/397/885/png-transparent-logo-mastercard-product-font-mastercard-text-orange-logo.png";
    } else if (cardType === "AMEX") {
      cardImageUrl =
        "https://e7.pngegg.com/pngimages/868/55/png-clipart-logo-american-express-cards-bank-insurance-bank-blue-text.png";
    } else {
      cardImageUrl = "https://cdn-icons-png.flaticon.com/512/7596/7596460.png";
    }

    cardBrand.innerHTML = "";

    if (cardImageUrl) {
      const imgElement = document.createElement("img");
      imgElement.src = cardImageUrl;
      imgElement.alt = cardType;
      imgElement.width = 40;

      cardBrand.appendChild(imgElement);
    }
  });

  shippingForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const data = {
      street: document.getElementById("street").value,
      extNum: document.getElementById("ext-number").value,
      intNum: document.getElementById("int-number").value,
      zip: document.getElementById("zip").value,
      city: document.getElementById("city").value,
      state: document.getElementById("state").value,
    };

    localStorage.setItem("address", JSON.stringify(data));

    showToast("Dirección guardada", "bg-success");
    const userAddress = `${data.street} ${data.extNum} ${
      data.intNum ? data.intNum : ""
    } ${data.city} ${data.state}`;
    savedAddressText.textContent = `Dirección guardada: ${userAddress}`;
    addressChoiceContainer.classList.remove("d-none");
    shippingForm.classList.add("d-none");

    document.getElementById("use-saved").checked = true;

    fetch("endpoint para actualizar la dirección del usuario", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then((res) => res.json())
      .catch((err) => console.error("Error:", err));
  });

  paymentForm.addEventListener("submit", (e) => {
    const address = JSON.parse(localStorage.getItem("address"));

    if (!address) {
      showToast(
        "Debe ingresar una dirección antes de continuar con el pago.",
        "bg-danger"
      );
      e.preventDefault();
      return;
    }

    e.preventDefault();
    // Only for exaple purposes
    const data = {
      cardNumber: document
        .getElementById("card-number")
        .value.replace(/\s/g, ""),
      expiryDate: document.getElementById("expiry-date").value,
      cvv: document.getElementById("cvv").value,
    };
    window.location.href = "../views/thank-you.html";
  });
});
