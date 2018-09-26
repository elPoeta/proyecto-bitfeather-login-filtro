
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
        .then( data => {
           if(data !== null && data !== 'error'){
            //window.location.replace("index.html");
                    msgLogueo("Registro completado Inicia Sesion",'msg-color-ok')
                    sw2();
           }else{
                      msgLogueo("Error al crear cuenta",'msg-color-error');

           }
                    
    }).catch (err => {
       console.log("error",err);
       msgLogueo("Error al crear cuenta "+err, 'msg-color-error');
});
   
});

