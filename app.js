// app.js

const calculateVAT = require('./vatCalculator');

// Jewellery items
let jewelleryItems = [
    { id: 1, name: "Gold Necklace", price: 50000 },
    { id: 2, name: "Silver Ring", price: 1000 },
    { id: 3, name: "Diamond Earrings", price: 75000 }
];

// Loop through items and display result
jewelleryItems.forEach(item => {
    let result = calculateVAT(item.price);
    console.log(`Jewellery ID: ${item.id}`);
    console.log(`Name: ${item.name}`);
    console.log(`Price: ${item.price}`);
    console.log(`VAT: ${result.vat}`);
    console.log(`Total Price: ${result.totalPrice}`);
    console.log("--------------------------");
});
