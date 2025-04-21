document.addEventListener("DOMContentLoaded", async () => {
    try {
      const res = await fetch("../components/footer.html");
      const data = await res.text();
      document.getElementById("footer-container").innerHTML = data;
    } catch (error) {
      console.error(error);
    }
  });