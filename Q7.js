// app.js
const mysql = require("mysql2");
const readline = require("readline-sync");

// MySQL connection
const connection = mysql.createConnection({
    host: "localhost",
    user: "root",         // change if needed
    password: "Nut5823@", // your MySQL password
    database: "stock_market"
});

// Connect to DB
connection.connect((err) => {
    if (err) throw err;
    console.log("Connected to MySQL!");
    mainMenu();
});

// ----------------- MAIN MENU -----------------
function mainMenu() {
    console.log("\nStock Market Management System");
    console.log("1. Add Stock");
    console.log("2. View All Stocks");
    console.log("3. View Stock by ID");
    console.log("4. Update Stock");
    console.log("5. Delete Stock");
    console.log("6. Exit");

    let choice = readline.questionInt("Enter your choice: ");

    switch (choice) {
        case 1:
            addStock();
            break;
        case 2:
            viewAllStocks();
            break;
        case 3:
            viewStockById();
            break;
        case 4:
            updateStock();
            break;
        case 5:
            deleteStock();
            break;
        case 6:
            connection.end();
            console.log("MySQL connection closed.");
            process.exit();
        default:
            console.log("Invalid choice!");
            mainMenu();
    }
}

// ----------------- CRUD FUNCTIONS -----------------

// CREATE
function addStock() {
    let symbol = readline.question("Enter Symbol: ");
    let companyName = readline.question("Enter Company Name: ");
    let price = readline.questionFloat("Enter Price: ");
    let volume = readline.questionInt("Enter Volume: ");

    const sql = "INSERT INTO stocks (symbol, companyName, price, volume) VALUES (?, ?, ?, ?)";
    connection.query(sql, [symbol, companyName, price, volume], (err, result) => {
        if (err) throw err;
        console.log("Stock added with ID:", result.insertId);
        mainMenu();
    });
}

// READ (all)
function viewAllStocks() {
    connection.query("SELECT * FROM stocks", (err, results) => {
        if (err) throw err;
        console.table(results);
        mainMenu();
    });
}

// READ (by ID)
function viewStockById() {
    let id = readline.questionInt("Enter Stock ID: ");
    connection.query("SELECT * FROM stocks WHERE id = ?", [id], (err, results) => {
        if (err) throw err;
        if (results.length > 0) {
            console.log(results[0]);
        } else {
            console.log("Stock not found!");
        }
        mainMenu();
    });
}

// UPDATE
function updateStock() {
    let id = readline.questionInt("Enter Stock ID to update: ");
    let price = readline.questionFloat("Enter new Price: ");
    let volume = readline.questionInt("Enter new Volume: ");

    const sql = "UPDATE stocks SET price = ?, volume = ? WHERE id = ?";
    connection.query(sql, [price, volume, id], (err, result) => {
        if (err) throw err;
        if (result.affectedRows > 0) {
            console.log("Stock updated successfully!");
        } else {
            console.log("Stock not found!");
        }
        mainMenu();
    });
}

// DELETE
function deleteStock() {
    let id = readline.questionInt("Enter Stock ID to delete: ");
    const sql = "DELETE FROM stocks WHERE id = ?";
    connection.query(sql, [id], (err, result) => {
        if (err) throw err;
        if (result.affectedRows > 0) {
            console.log("Stock deleted successfully!");
        } else {
            console.log("Stock not found!");
        }
        mainMenu();
    });
}
