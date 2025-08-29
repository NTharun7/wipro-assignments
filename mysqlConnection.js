// mysqlConnection.js
const mysql = require("mysql2");

const connection = mysql.createConnection({
    host: "localhost",    // change if needed
    user: "root",         // your MySQL username
    password: "Nut5823@", // your MySQL password
    database: "stock_market"
});

module.exports = connection;
