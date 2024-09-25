document.addEventListener('DOMContentLoaded', function() {
    var loginButton = document.getElementById('loginButton');
    var loginDropdown = document.getElementById('loginDropdown');
    var loginForm = document.getElementById('loginForm');
  
    loginButton.addEventListener('click', function(event) {
      event.preventDefault();
      event.stopPropagation(); // Prevent the event from bubbling up to the window
      loginDropdown.classList.toggle('hidden');
    });
  
    window.addEventListener('click', function(event) {
      if (!event.target.closest('#loginDropdown') && !event.target.closest('#loginButton')) {
        loginDropdown.classList.add('hidden');
      }
    });
  
    loginForm.addEventListener('submit', function(event) {
      event.preventDefault();
      var formData = new FormData(loginForm);
      var jsonData = {};
      formData.forEach((value, key) => {
        jsonData[key] = value;
      });
  
      fetch('/api/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
      })
      .then(response => response.json())
      .then(data => {
        console.log('Success:', data);
        alert('Datos guardados con éxito');
        loginDropdown.classList.add('hidden');
        loginForm.reset();
      })
      .catch((error) => {
        console.error('Error:', error);
      });
    });
  });


  function guardarDatos() {
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const documentNumber = document.getElementById('documentNumber').value;

    const data = {
        firstName: nombre,
        lastName: apellido,
        email: email,
        password: password,
        document: {
            documentNumber: documentNumber
        }
    };

    fetch('http://localhost:8080/person', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert('Datos guardados correctamente');
        } else {
            throw new Error('Error en la solicitud');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al guardar los datos');
    });
} 







  