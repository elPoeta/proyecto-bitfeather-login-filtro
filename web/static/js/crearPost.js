class CrearPost{
   constructor(){
    this.quill;
   }


   mostrarCrearPost(){
	    const  template = `
	        <section class="main-crear-post">
	        <h2 class="titulo-main">Crear Post</h2>
	        <form id="form-crearPost">
	        
	        <div class="contenedorFieldsetCrearPost">
	        <fieldset class="fieldsetCrearPost">
	        <legend>Encabezado</legend>
	        <label for="titulo"><h3>Titulo:</h3></label>
	        <input type="text" id="crear-titulo" name="crear-titulo" placeholder="Titulo" required>
	        <label for="subtitulo"><h3>Subtitulo:</h3></label>
	        <input type="text" id="crear-subtitulo" name="crear-subtitulo" placeholder="Subtitulo" required>
	        </fieldset>  

	        <fieldset class="fieldsetCrearPost">
	        <legend>Categoria</legend>
	        <label for="categoria"><h3>Categoria:</h3></label>
	        <select id="seleccionarCat-crearPost">
	            <option selected disabled>Selecionar Categoria</option>
	            ${categorias.map(categoria =>
	              `<option value="${categoria.id}">${categoria.nombre}</option>`
	            ).join('')}
	          </select>
	          <label for="tag"><h3>Tags (Opcional):</h3></label>
	       <input type="text" id="crear-tag" name="crear-tag" placeholder="Separar con comas">
	       </fieldset>   
	       </div>
	       <fieldset class="fieldsetCrearPost fieldsetCuerpo">
	       <legend>Cuerpo</legend>
	      <input name="cuerpo" id="crear-cuerpo" type="hidden" required>
	      <div id="editor"></div>
	      </fieldset>
	        <button id="btn-publicar-post">Publicar</button>
	        </form>
	        </section>`;
      
	    document.querySelector('#panel-main').innerHTML = template;
	   
      let selectCategoria = document.querySelector('#seleccionarCat-crearPost');
      selectCategoria.addEventListener('change', (e)=>{
      	this.catSelect = e.target.value;
      });
      
      this.renderEditor();
    
  }

    renderEditor(){
        const toolbarOptions = [
            ['bold', 'italic', 'underline'],        
            ['blockquote', 'code-block','link', 'image', 'video'],
          
            [{ 'header': 1 }, { 'header': 2 }],               
            [{ 'list': 'ordered'}, { 'list': 'bullet' }],
            [{ 'script': 'sub'}, { 'script': 'super' }],      
            [{ 'indent': '-1'}, { 'indent': '+1' }],        
            [{ 'direction': 'rtl' }],                    
          
            [{ 'size': ['small', false, 'large', 'huge'] }],  
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
          
            [{ 'color': [] }, { 'background': [] }],        
            [{ 'font': [] }],
            [{ 'align': [] }]
          
            //['clean']                                       
          ];

         this.quill = new Quill('#editor', {
            modules: {  
              toolbar: toolbarOptions,
              imageResize: {}
            },
            placeholder: 'Construye aquí tu epopeya...',
            theme: 'snow'
          });
          const botonPublicarPost = document.querySelector('#btn-publicar-post');
          botonPublicarPost.addEventListener('click', (e)=>{
            this.publicarPost();
      });
    }

    publicarPost(){
    	console.log('Publicar')
    	let posteo = {};
    	let titulo = document.querySelector('#crear-titulo');
        let subTitulo = document.querySelector('#crear-subtitulo');
        let cuerpo = this.quill.getContents();
        let cat = {
        		"id" : this.catSelect
        };
        //let autor = {
        	//	"id": 1
       // };
        posteo.titulo=titulo.value;
        posteo.subTitulo=subTitulo.value;
        posteo.cuerpo = JSON.stringify(cuerpo);
        posteo.categoria = cat;
        //posteo.autor = autor;
        const URL_PUBLICAR_POST = 'api/privado/CrearPostServer';
        let p = new Post();
        p.insertar(URL_PUBLICAR_POST, posteo);
    }
 
}

