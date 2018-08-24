
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

class Login{
    	
	/*async insertar(url, data) {
        try {    
        const respuesta = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data),
             
              credentials: 'same-origin' 
        });
        return respuesta;
    } catch (err) {
    throw new Error(err);
  }
   }*/
    static async insertar(url, data) {
        try {    
        const respuesta = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data),
             
              credentials: 'same-origin' 
        });
        return respuesta;
    } catch (err) {
    throw new Error(err);
  }
   }
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
   //let login = new Login();
   Login.insertar(URL_LOGIN_SERVER,loginUsuario)
           .then(response => response.json())
  .then( data => {
           if(data !== null && data !== 'error'){
            closeModal();   
           }
            
               
    }).catch (err => {
       console.log("error",err);
});

   

  
});