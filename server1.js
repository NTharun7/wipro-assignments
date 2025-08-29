// server.js

const net = require("net");

// In-memory jewellery stock
let jewelleryItems = [
    { id: 1, name: "Gold Ring", quantity: 5, price: 15000 },
    { id: 2, name: "Silver Necklace", quantity: 2, price: 5000 }
];

// Create TCP server
const server = net.createServer((socket) => {
    console.log("Client connected.");

    // Handle client input
    socket.on("data", (data) => {
        const input = data.toString().trim();
        const parts = input.split(" ");
        const command = parts[0].toUpperCase();

        if (command === "LIST") {
            let response = jewelleryItems.map(
                item => `ID: ${item.id}, Name: ${item.name}, Quantity: ${item.quantity}, Price: ${item.price}`
            ).join("\n");
            socket.write(response + "\n");
        } 
        else if (command === "ADD") {
            if (parts.length < 4) {
                socket.write("Usage: ADD <name> <quantity> <price>\n");
                return;
            }
            const name = parts[1];
            const quantity = parseInt(parts[2]);
            const price = parseInt(parts[3]);

            const newItem = {
                id: jewelleryItems.length + 1,
                name: name,
                quantity: quantity,
                price: price
            };
            jewelleryItems.push(newItem);
            console.log("Item added:", newItem);
            socket.write("Jewellery item added successfully!\n");
        } 
        else if (command === "EXIT") {
            socket.write("Goodbye!\n");
            socket.end();
        } 
        else {
            socket.write("Unknown command. Use LIST, ADD, or EXIT.\n");
        }
    });

    socket.on("end", () => {
        console.log("Client disconnected.");
    });

    socket.on("error", (err) => {
        console.log("Error:", err.message);
    });
});

// Start server
server.listen(5000, () => {
    console.log("TCP Server running on port 5000...");
});
