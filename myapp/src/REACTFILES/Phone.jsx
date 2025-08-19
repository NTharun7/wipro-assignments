import React, { useState } from "react";

const Phone = () => {
  const [phone, setPhone] = useState({
    brand: "Apple",
    model: "iPhone 14",
    price: 80000,
  });

  const updatePrice = () => {
    setPhone({ ...phone, price: phone.price + 5000 });
  };

  return (
    <div>
      <h2>Phone Details:</h2>
      <p><b>Brand:</b> {phone.brand}</p>
      <p><b>Model:</b> {phone.model}</p>
      <p><b>Price:</b> ₹{phone.price}</p>
      <button onClick={updatePrice}>Increase Price</button>
    </div>
  );
};

export default Phone;
