const carrito = document.getElementById('carrito');
const elementos1 = document.getElementById('lista-1');
const lista = document.querySelector('#lista-carrito tbody');
const vaciarCarritoBtn = document.getElementById('vaciar-carrito');

cargarEventListeners();

function cargarEventListeners() {
    if (elementos1) {
        elementos1.addEventListener('click', comprarElemento);
    }
    if (carrito) {
        carrito.addEventListener('click', eliminarElemento);
    }
    if (vaciarCarritoBtn) {
        vaciarCarritoBtn.addEventListener('click', vaciarCarrito);
    }
}

function comprarElemento(e) {
    e.preventDefault();

    if (e.target.classList.contains('agregar-carrito')) {
        const elemento = e.target.parentElement.parentElement;
        leerDatosElemento(elemento);
    }
}

function leerDatosElemento(elemento) {
    const infoElemento = {
        imagen: elemento.querySelector('img').src,
        titulo: elemento.querySelector('h3').textContent,
        precio: elemento.querySelector('.precio').textContent, 
        id: elemento.querySelector('a').getAttribute('data-id')
    }
    insertarCarrito(infoElemento);
}

function insertarCarrito(elemento) {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>
          <img src="${elemento.imagen}" width=100 >
      </td>
      <td>
           ${elemento.titulo}
      </td>
      <td>
           ${elemento.precio}    
      </td>
      <td>
          <a href="#" class="borrar" data-id="${elemento.id}">X </a>
      </td>
    `;
    lista.appendChild(row);
}

function eliminarElemento(e) {
    e.preventDefault();
    if (e.target.classList.contains('borrar')) {
        e.target.parentElement.parentElement.remove();
    }
}

function vaciarCarrito() {
    while (lista.firstChild) {
        lista.removeChild(lista.firstChild);
    }
    return false;
}


let productos = [];

const cargarRemeras = async () => {
    const url = 'https://fakestoreapi.com/products';
    try {
        const response = await fetch(url);
        const result = await response.json();
        productos = result; 
        insertarRemerasEnHTML(productos);
    } catch (error) {
        console.error(error);
    }
};

const insertarRemerasEnHTML = (remeras) => {
    const container = document.getElementById('lista-1');
    container.innerHTML = ''; 
    remeras.forEach(remera => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');

        const img = document.createElement('img');
        img.src = remera.image;
        img.alt = remera.title;
        productDiv.appendChild(img);

        const productTxtDiv = document.createElement('div');
        productTxtDiv.classList.add('product-txt');

        const title = document.createElement('h3');
        title.textContent = remera.title;
        productTxtDiv.appendChild(title);

        const description = document.createElement('p');
        description.textContent = remera.description;
        productTxtDiv.appendChild(description);

        const price = document.createElement('p');
        price.classList.add('precio');
        price.textContent = `$${remera.price}`;
        productTxtDiv.appendChild(price);

        const addButton = document.createElement('a');
        addButton.href = '#';
        addButton.classList.add('agregar-carrito', 'btn-2');
        addButton.dataset.id = remera.id;
        addButton.textContent = 'Agregar al carrito';
        productTxtDiv.appendChild(addButton);

        productDiv.appendChild(productTxtDiv);
        container.appendChild(productDiv);
    });
};

const filtrarProductos = (termino) => {
    const productosFiltrados = productos.filter(producto =>
        producto.title.toLowerCase().includes(termino.toLowerCase()) ||
        producto.description.toLowerCase().includes(termino.toLowerCase())
    );
    insertarRemerasEnHTML(productosFiltrados);
};

document.getElementById('search-bar').addEventListener('input', (event) => {
    const termino = event.target.value;
    filtrarProductos(termino);
});

cargarRemeras();



