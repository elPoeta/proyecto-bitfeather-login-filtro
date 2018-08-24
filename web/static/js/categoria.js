class Categoria{
   async get(url) {
	   console.log(url);
        const respuesta = await fetch(url, { 
            method: 'GET',
             headers: {
                'Content-type': 'application/json'
            }
           });
        let categorias = JSON.parse(await respuesta.text());
        return categorias;
    }
}  
