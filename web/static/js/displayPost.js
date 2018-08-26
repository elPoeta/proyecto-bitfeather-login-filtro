class DisplayPost{
	
	 mostrarTodos(posts){
	       
	        let template = posts.length !== 0 ?  
	        `<section class="main-post">
	        <h2 class="titulo-main">Posts</h2>
	        ${posts.map(post =>
	            `<article class="article-post">
	                  <h2 class="titulo-post" id="post-${post.id}"><a href="#">${post.titulo}</a></h2>
	                  <p>${post.subTitulo}</p>
	               <ul>
	                  <li><a href="#">Categoria: ${post.categoria.nombre}</a></li>
	                  <li><a href="#">Autor: ${post.user.nombre}</a></li>
	                  <li>Creado el ${post.fechaCreacion}</li>
	              </ul> 
	          </article>`).join('')}
	          </section>`
	        		 : `<section class="main-post">
	        		        <h2 class="titulo-main">Posts</h2>
	        		          <article class="article-post">
	        		                  <h2 class="titulo-post"><a href="#">Oops!! No hay posts disponibles</a></h2>
	        		                  <p>Se el primero en postear</p>
	        		          </article>
	        		          </section>`;		
	          
	        
	          document.querySelector('#panel-main').innerHTML = template;
	        
	          
	            let tituloPost = document.querySelectorAll('.titulo-post');
	            for (let i = 0; i < tituloPost.length; i++) {

	              tituloPost[i].addEventListener('click', ()=> {
	                let id = tituloPost[i].getAttribute('id');
	                id =id.replace(/\D/g,'');
	                this.mostrarBlogPost(id);
	              });
	          
	            }
	          
	        }

	    mostrarBlogPost(id){
                console.log('id>> ',id);
	    	
                loading(true);
	        Http.get(`api/postServer?&q=${id}`)
	    	.then(data => {
                        console.log(data);
	    		//let cuerpo = JSON.parse(data.cuerpo);
	    	    this.renderBlog(data);
                    loading(false);
	    	}).
                        catch(error =>{
                            console.log('error', error);
                    loading(false);
                });
	    }
	    
	    renderBlog(datos){
                  console.log(datos.titulo);
	        let options = {
	            readOnly: true,
	            scrollingContainer: '#scrolling-container'
	          };
	          let template = 
	          `<section class="blog-post">
	          <h2 class="titulo-main">${datos.titulo}</h2>
	          <p class="subtitulo-blogPost">${datos.subTitulo}</p>
	          <hr>
	          <div id="scrolling-container">
	          <div id="blog"></div>
	          </div>
	          </section>`;  
	          document.querySelector('#panel-main').innerHTML = template;
	          let blog = new Quill('#blog', options);
	          blog.setContents(JSON.parse(datos.cuerpo));
	    }
	    
}	    
	    
