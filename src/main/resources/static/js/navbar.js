document.addEventListener("DOMContentLoaded", async () => {
  try {
    const res = await fetch("../components/navbar.html");
    const data = await res.text();
    document.getElementById("navbar-container").innerHTML = data;
  } catch (error) {
    console.error(error);
  }

  const isLoggein = localStorage.getItem("token");
  const isAdminLoggein = localStorage.getItem("adminToken");

  const homeBtn = document.getElementById("home-btn");
  const managementBtn = document.getElementById("management-btn");
  const registerBtn = document.getElementById("register-btn");
  const loginBtn = document.getElementById("login-btn");
  const logoutBtn = document.getElementById("logout-btn");

    homeBtn.style.display = (isLoggein || isAdminLoggein) ? "none" : "block";
    managementBtn.style.display = isAdminLoggein ? "block" : "none";
    registerBtn.style.display = (isLoggein || isAdminLoggein) ? "none" : "block";
    loginBtn.style.display = (isLoggein || isAdminLoggein) ? "none" : "block";
    logoutBtn.style.display = (isLoggein || isAdminLoggein) ? "block" : "none";

});

function logout() {
  const isAdminLoggein = localStorage.getItem("adminToken")
  if (isAdminLoggein) {
    localStorage.removeItem("adminToken")
    window.open("../", "_self")
  } else {
    localStorage.removeItem("token");
    window.open("../", "_self")
  }
}

