const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql2');

const app = express();
app.use(bodyParser.json());
app.use(cors());

// Configuración de la conexión a MySQL
const connection = mysql.createConnection({
    host: 'localhost', // nombre del host donde está corriendo MySQL
    user: 'root', // tu usuario de MySQL
    password: '12345', // tu contraseña de MySQL
    database: 'apirestdb', // tu base de datos de MySQL
    port: 3306, // puerto de MySQL, por defecto es 3306
    // otras opciones de configuración si las necesitas
});

connection.connect(err => {
    if (err) {
        console.error('Error conectando a MySQL:', err);
        return;
    }
    console.log('Conectado a MySQL');
});

// Ruta para registrar usuarios
app.post('/register', (req, res) => {
    const { firstName, lastName, email, password, documentNumber } = req.body;
    const sql = 'INSERT INTO person (email, firstName, lastName,  password, documentNumber) VALUES (?, ?, ?, ?, ?)';
    connection.query(sql, [email, firstName, lastName,  password, documentNumber], (err, result) => {
        if (err) {
            return res.status(500).send({ error: 'Error en el servidor' });
        }
        res.status(201).send({ id: result.insertId });
    });
});

// Ruta para iniciar sesión
app.post('/login', (req, res) => {
    const { email, password } = req.body;
    console.log('Datos recibidos para iniciar sesión:', email, password); // Depuración
    const sql = 'SELECT * FROM person WHERE email = ? AND password = ?';
    connection.query(sql, [email, password], (err, results) => {
        if (err) {
            console.error('Error en la consulta:', err); // Depuración
            return res.status(500).send({ error: 'Error en el servidor' });
        }
        if (results.length > 0) {
            res.status(200).send(results[0]);
        } else {
            res.status(401).send({ error: 'Credenciales incorrectas' });
        }
    });
});

// Servir archivos estáticos desde el directorio 'public'
const path = require('path');
app.use(express.static(path.join(__dirname, 'public')));

// Ruta para manejar todas las demás solicitudes y devolver el archivo HTML principal
app.get('*', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(8080, () => {
    console.log('Servidor corriendo en http://localhost:8080');
});
