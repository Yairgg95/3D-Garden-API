let mensaje = {
    nombre: "",
    mail: "",
    mensaje: ""
}

function crearMensaje(){
    mensaje.nombre = name;
    mensaje.mail = email;
    mensaje.mensaje = Message;
}

window.addEventListener(load, function(){
    let form = document.getElementById("contact-form");
    //console.log(form);
    form.addEventListener("submit", function(event) {
        
    event.preventDefault()
    })
})

