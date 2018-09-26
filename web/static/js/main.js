let displayPosts = new DisplayPost();
const crearPost = document.querySelector('#crear-post');
let verPost = document.querySelector('#nav-post');
const URL_LOGIN = 'LoginServer';
const URL_CATEGORIAS = 'api/categoriaServer';

let categorias = [];



crearPost.addEventListener('click', (e) =>{
    const URL_CREAR_POST_SERVER_PRIVADO = 'api/privado/CrearPostServer';

    Http.get(URL_CREAR_POST_SERVER_PRIVADO)
            .then(data => {
             console.log('data >>',data); 
             if(data != 'error'){
                let crearPost = new CrearPost();
                crearPost.mostrarCrearPost();
             }
              else{
                  window.location.replace("login.html");
              }
            
    })
      .catch(err =>{
               console.log(err);
              window.location.replace("login.html");
          }); ;
   
 });

verPost.addEventListener('click', ()=>{
	 buscarPost();
});

const iniciar = () =>{
	Http.get(URL_CATEGORIAS)
	.then(data => {
		 data.map(c => {
	         return   categorias.push(c);
	     });
	     })
              .catch(error =>{
                console.log(error);         
             });
        Usuario.consultar(URL_LOGIN);
	buscarPost();
};
function buscarPost(){
    loading(true); 
	 Http.get('api/postServer?&q=0')
     .then(data => {
    	 displayPosts.mostrarTodos(data);
      loading(false);
     })
       .catch((e)=>{
          console.log(e);
                loading(false);
     });  
     
} 

function loading(on) {
  let loadingsvg = document.querySelector("#loading");  
  if (on) {
      loadingsvg.style.display = "block";
  } else {
      loadingsvg.style.display = "none";
  }
}

window.addEventListener("load",iniciar,false); 


const login = document.querySelector('.login-user');

login.addEventListener('click', e => {
    if(document.querySelector('#login-header')){
        location.replace('login.html');
    }else
         if(document.querySelector('#logout-header'))
           {
             if(!document.querySelector('#logout')){
                 
                 Usuario.panelUser();
                 document.querySelector('.panel-usuario').classList.toggle('hide-panel-usuario');
            } 
            else{
               
               document.querySelector('.panel-usuario').classList.toggle('hide-panel-usuario');
              }
           
        }
  
});