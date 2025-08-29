// server.js

const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");

const app = express();
const PORT = 3000;

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));

// Serve the HTML form
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "index.html"));
});

// Handle form submission
app.post("/submit", (req, res) => {
    const { trackingId, senderName, receiverName, pickupAddress, deliveryAddress, weight } = req.body;

    if (!trackingId || !senderName || !receiverName || !pickupAddress || !deliveryAddress || !weight) {
        return res.send("All fields are required!");
    }

    // Delivery cost calculation
    const cost = 50 + (parseInt(weight) * 20);

    // Response message
    res.send(`
        <h2>Courier Booking Confirmation</h2>
        <p>Courier Tracking ID: ${trackingId}</p>
        <p>Sender: ${senderName}</p>
        <p>Receiver: ${receiverName}</p>
        <p>Pickup: ${pickupAddress}</p>
        <p>Delivery: ${deliveryAddress}</p>
        <p>Weight: ${weight} kg</p>
        <p><b>Delivery Cost: ₹${cost}</b></p>
    `);
});

// Start server
app.listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}`);
});
