
const modal = document.querySelector('#simpleModal');

const modalBtnLogin = document.querySelector('#login-header');

const closeBtn = document.querySelector('#closeBtn');


modalBtnLogin.addEventListener('click', openModal);

closeBtn.addEventListener('click', closeModal);



function openModal(){
  modal.style.display = 'block';
}

function closeModal(){
  modal.style.display = 'none';

}


let logEmail = document.querySelector('#log-email');
let logPass = document.querySelector('#log-pass');
let btnLogin = document.querySelector('#btn-login');
const URL_LOGIN_SERVER = 'LoginServer';

btnLogin.addEventListener('click', (e)=>{
   let loginUsuario ={};
   loginUsuario.email = logEmail.value;
   loginUsuario.password = logPass.value;
   console.log(loginUsuario);

   Http.post(URL_LOGIN_SERVER,loginUsuario)
           .then(response => response.json())
  .then( data => {
           if(data !== null && data !== 'error'){
            closeModal();   
           }
            
               
    }).catch (err => {
       console.log("error",err);
});
  
});