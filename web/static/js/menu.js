class Menu{
   static mostrarMenu(idPanel){
        let template = `<li><a href="#" id="cat-0">Todas</a></li> 
            ${categorias.map(categoria => 
                `<li><a href="#" id="cat-${categoria.id}">${categoria.nombre}</a></li>`).join('')}`;
                document.querySelector(idPanel).innerHTML = template;        
    }
}



const seleccionCategoriaNavbar = document.querySelector('#selecion-categoria-navbar');
const menuHorizontalCat = document.querySelector('#menu-horizontal-cat');
const btnNavPanel = document.querySelector('#btn-nav-panel');
const btnNavIcon = document.querySelector('#nav-icon-hamburger');
const navPanel = document.querySelector('.nav-panel');
const desplegarMenuCategoriasSidePanel = document.querySelector('#submenu-categorias');
const subMenuCate = document.querySelector('#panel-sideMenu');

seleccionCategoriaNavbar.addEventListener('click', (e)=>{
    e.preventDefault();
    if(menuHorizontalCat.classList.contains('hide-menu')){
        menuHorizontalCat.classList.remove('hide-menu');
        Menu.mostrarMenu("#panel-menuHorizontal");
           
        
    }
    else{
        menuHorizontalCat.classList.add('hide-menu');
    }
});

btnNavIcon.addEventListener('click', (e)=>{
    e.preventDefault();
   navPanel.style.width='250px';
   navPanel.style.border= '3px solid #022037';
   navPanel.style.display='block';
});

btnNavPanel.addEventListener('click', (e)=>{
    e.preventDefault();
    navPanel.style.width='0px';
    navPanel.style.border= '0px';
    subMenuCate.classList.add('hide-menu');
});

desplegarMenuCategoriasSidePanel.addEventListener('click', (e) =>{
    e.preventDefault();
    if(subMenuCate.classList.contains('hide-menu')){
        subMenuCate.classList.remove('hide-menu');
                 Menu.mostrarMenu("#panel-sideMenu");
    }
    else{
        subMenuCate.classList.add('hide-menu');
    }
});