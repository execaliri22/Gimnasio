// Modifica la función leerDatosElemento para incluir el ID del usuario
function leerDatosElemento(elemento) {
    const infoElemento = {
        imagen: elemento.querySelector('img').src,
        titulo: elemento.querySelector('h3').textContent,
        precio: elemento.querySelector('.precio').textContent.trim(),
        id: elemento.querySelector('a').getAttribute('data-id'),
        userId: 1  // Reemplaza con la ID del usuario actual obtenida del backend
    };
    insertarCarrito(infoElemento);
    enviarProductoAlServidor(infoElemento);
}

// Función para enviar producto al servidor
function enviarProductoAlServidor(producto) {
    fetch(`/person/${producto.userId}/products`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            nombre: producto.titulo,
            descripcion: producto.precio,
            imagen: producto.imagen,
            precio: limpiarPrecio(producto.precio)
        }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al agregar producto');
        }
        return response.json();
    })
    .then(data => {
        console.log('Producto agregado con éxito:', data);
    })
    .catch(error => {
        console.error('Error al agregar producto:', error);
    });
}
