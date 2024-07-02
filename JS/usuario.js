document.addEventListener('DOMContentLoaded', function() {
    const userFullNameElement = document.getElementById('userFullName');
    const userFullName = sessionStorage.getItem('userFullName');
    if (userFullName) {
        userFullNameElement.textContent = userFullName;
    } else {
        // Manejar caso donde no se encuentra el nombre y apellido en sessionStorage
        console.error('Nombre y apellido del usuario no encontrados en sessionStorage.');
    }
});