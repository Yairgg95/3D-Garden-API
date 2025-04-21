document.addEventListener("DOMContentLoaded", async function() {
  const container = document.getElementById("us-team-cards"); //atrapo el contenedor
  const colors = ["#C6434E", "#BBEE4D", "#37C7BD"]
  try {
    const response = await fetch("../assets/data/developers.json"); // Cargar el JSON
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }
    const data = await response.json();
    //console.log("JSON:", data);
    container.innerHTML = ""; 

    data.developers.forEach((dev, i ) =>{
      //Crear contenedor de cada card
      const devCard = document.createElement("div");
      devCard.className = "card col-12 col-md-6 col-lg-4 text-center mb-5";
      //Crear elemento **imagen  
      const devImg = document.createElement("img");
      devImg.src = dev.img;
      devImg.alt = dev.name;
      devImg.className = "card-img-top rounded-circle mx-auto mt-3 mb-2";
      devImg.setAttribute = ("id","imgStory");
      devImg.style = "d-block w-100" 
      //Crear elemento **div con sus respectivos textos y botones
      const devText = document.createElement("div");
      devText.className = "card-body rounded-bottom-5";
      const title = document.createElement("h3");
      title.innerText = dev.name;
      title.style.fontWeight = "700";
      const rol = document.createElement("p");
      rol.innerText = dev.rol;
      rol.style.fontStyle = "italic";
      rol.className = "pt-4";
      const bio = document.createElement("p");
      bio.innerText = dev.bio;
      bio.className = "card-text pe-5 ps-5 pb-3";
      // Contenedor para los enlaces sociales
      const socialLinks = document.createElement("div");
      socialLinks.className = "social-links";
      //LinkedIn con ícono
      const linkedinLink = document.createElement("a");
      linkedinLink.href = dev.linkedin;
      linkedinLink.target = "_blank";
      linkedinLink.innerHTML = '<i class="fab fa-linkedin fa-2x"></i>'; // Ícono de LinkedIn
      //GitHub con ícono
      const githubLink = document.createElement("a");
      githubLink.href = dev.github;
      githubLink.target = "_blank";
      githubLink.innerHTML = '<i class="fab fa-github fa-2x"></i>'; // Ícono de GitHub
      // Agregar los enlaces al contenedor
      socialLinks.appendChild(linkedinLink);
      socialLinks.appendChild(githubLink);
      //Agregar elementos al DOM
      devText.append(rol, title, bio, socialLinks);
      devCard.append(devImg, devText);
      container.appendChild(devCard);
    });
      // Aplicar colores según la pantalla actual
      applyColors();
      // Agregar un solo event listener para actualizar colores cuando cambia el tamaño de pantalla
      window.addEventListener("resize", applyColors);
  } catch (error) {
    console.error("Error al cargar los desarrolladores:", error);
  }

  // Función para obtener el tamaño del grupo según la pantalla
  function getGroupSize() {
    if (window.innerWidth >= 992) return 3; // Desktop: cada 3 tarjetas
    if (window.innerWidth >= 768) return 2; // Tablet: cada 2 tarjetas
    return 1; // Mobile: cada 1 tarjeta
  }
  // Función para aplicar colores dinámicamente
  function applyColors() {
    const devTexts = document.querySelectorAll(".card-body");
    const groupSize = getGroupSize();
    devTexts.forEach((devText, i) => {
      devText.style.backgroundColor = colors[Math.floor(i / groupSize) % colors.length];
    });
  }


  // Fetch para la línea del tiempo
  fetch("../assets/data/story.json")
  .then(response => response.json())
  .then(data => {
    
    const storyDiv = document.getElementById("us-story-timeline");

    data.story.forEach((item, i) => {
      const timelineItem = document.createElement("div");
      timelineItem.classList.add("timeline-item");
      //creación de elementos de línea del tiempo
      const divDate = document.createElement("div");
      const divImg = document.createElement("div");
      const divText = document.createElement("div");
      const date = document.createElement("h2");
      const img = document.createElement("img");

      divDate.className = "date-bar rounded-3";
      divDate.style.backgroundColor = colors[i % colors.length];
      date.innerHTML = item.date;
      divDate.appendChild(date);

      divImg.className = "image-container";
      img.src = item.img;
      img.alt = "evento";
      divImg.appendChild(img);

      divText.className = "text-container lead bg-green-opacity rounded-3";
      divText.innerText = item.description;
      
      timelineItem.append(divDate, divImg, divText);

      storyDiv.appendChild(timelineItem);
    }); // cierra containers
  }) //cierra fetch de historia
  .catch(e => console.log("Error de carga de JSON", e));
});