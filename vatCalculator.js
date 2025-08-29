// vatCalculator.js

// Exported function to calculate VAT
function calculateVAT(price, vatRate = 0.05) {   // default 5%
    let vat = price * vatRate;
    let totalPrice = price + vat;
    return {
        vat: vat,
        totalPrice: totalPrice
    };
}

module.exports = calculateVAT;
