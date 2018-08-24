class Registro{

	static async get(url) {
        const respuesta = await fetch(url, { 
            method: 'GET',
             headers: {
                'Content-type': 'application/json'
            }
           });
        let posts = JSON.parse(await respuesta.text());
        return posts;
    }
	
	static async insertar(url, data) {
        const respuesta = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data),
                   /*credentials: 'include'  */
              credentials: 'same-origin' 
        });
        return respuesta;
    }

}

let signNombre = document.querySelector('#sign-nombre');
let signEmail = document.querySelector('#sign-email');
let signPass = document.querySelector('#sign-pass');
let signConfirm = document.querySelector('#sign-confirm');
let btnRegistrar = document.querySelector('#btn-registrar');

btnRegistrar.addEventListener('click', (e)=>{
   let registroUsuario ={};
   registroUsuario.nombre = signNombre.value;
   registroUsuario.email = signEmail.value;
   registroUsuario.password = signPass.value;
   registroUsuario.confirmPassword = signConfirm.value;
  console.log(registroUsuario);
   Registro.insertar('RegistroServer',registroUsuario)
           .then(response => response.json())
           .then(data =>{
             console.log('INSERT DATA');
             console.log(data);
            if(data !=='ERROR'){ 
            closeModal();
        }
   });
   
});

