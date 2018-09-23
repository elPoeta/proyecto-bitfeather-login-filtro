
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
   Http.post('RegistroServer',registroUsuario)
           .then(response => response.json())
           .then(data =>{
             console.log('INSERT DATA');
             console.log(data);
            if(data !=='ERROR'){ 
            closeModal();
        }
   });
   
});

