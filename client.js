// client.js

const net = require("net");
const readline = require("readline");

// Create socket connection
const client = net.createConnection({ port: 5000 }, () => {
    console.log("Connected to server.");
    console.log("Commands: LIST | ADD <name> <quantity> <price> | EXIT");
});

// Interface to read user input
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Send input to server
rl.on("line", (input) => {
    client.write(input);
});

// Receive data from server
client.on("data", (data) => {
    console.log("Server Response:\n" + data.toString());
});

// Handle disconnection
client.on("end", () => {
    console.log("Disconnected from server.");
    rl.close();
});
