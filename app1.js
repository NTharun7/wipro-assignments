// app.js
const connection = require("./mysqlConnection");

// Connect to MySQL
connection.connect((err) => {
    if (err) throw err;
    console.log("Connected to MySQL!");

    // Sequential execution of CRUD operations
    addStock("AAPL", "Apple Inc.", 175.50, 1000, (insertId) => {
        console.log("Inserted stock ID:", insertId);

        listStocks(() => {
            updateStockPriceAndVolume(insertId, 180.00, 1200, () => {
                listStocks(() => {
                    deleteStock(insertId, () => {
                        listStocks(() => {
                            connection.end();
                            console.log("MySQL connection closed.");
                        });
                    });
                });
            });
        });
    });
});

// ----------------- CRUD FUNCTIONS -----------------

// CREATE
function addStock(symbol, companyName, price, volume, callback) {
    const sql = "INSERT INTO stocks (symbol, companyName, price, volume) VALUES (?, ?, ?, ?)";
    connection.query(sql, [symbol, companyName, price, volume], (err, result) => {
        if (err) throw err;
        callback(result.insertId);
    });
}

// READ
function listStocks(callback) {
    connection.query("SELECT * FROM stocks", (err, results) => {
        if (err) throw err;
        console.log("Stock List:", results);
        callback();
    });
}

// UPDATE
function updateStockPriceAndVolume(id, price, volume, callback) {
    const sql = "UPDATE stocks SET price = ?, volume = ? WHERE id = ?";
    connection.query(sql, [price, volume, id], (err, result) => {
        if (err) throw err;
        console.log("Stock updated for ID:", id);
        callback();
    });
}

// DELETE
function deleteStock(id, callback) {
    const sql = "DELETE FROM stocks WHERE id = ?";
    connection.query(sql, [id], (err, result) => {
        if (err) throw err;
        console.log("Stock deleted with ID:", id);
        callback();
    });
}
