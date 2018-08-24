let displayPosts = new DisplayPost();
const crearPost = document.querySelector('#crear-post');
let verPost = document.querySelector('#nav-post');
const traerPosts = new Post();
const URL_CATEGORIAS = 'api/categoriaServer';

const traerCategorias = new Categoria();
let categorias = [];



crearPost.addEventListener('click', (e) =>{
    const URL_CREAR_POST_SERVER_PRIVADO = 'api/privado/CrearPostServer';
    let post = new Post();
    post.get(URL_CREAR_POST_SERVER_PRIVADO)
            .then(data => {
             console.log('data >>',data); 
             if(data !=='error'){
              let crearPost = new CrearPost();
                crearPost.mostrarCrearPost();
             }else{
                    openModal();
             }
    });
   
 });

verPost.addEventListener('click', ()=>{
	 buscarPost();
});

const iniciar = () =>{
	traerCategorias.get(URL_CATEGORIAS)
	.then(data => {
		 data.map(c => {
	         return   categorias.push(c);
	     });
	     });
	buscarPost();
};
function buscarPost(){
    loading(true); 
	 traerPosts.get('api/postServer?&q=0')
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
