import React, { useState } from "react";

const Electronics = () => {
  const [name] = useState("Laptop");
  const [brand, setBrand] = useState("Dell");
  const [price, setPrice] = useState(50000);

  return (
    <div>
      <h2>Electronics Item</h2>
      <p><b>Name:</b> {name}</p>
      <p><b>Brand:</b> {brand}</p>
      <p><b>Price:</b> ₹{price}</p>

      <button onClick={() => setBrand("HP")}>Update Brand</button>
      <button onClick={() => setPrice(price + 5000)}>Increase Price</button>
    </div>
  );
};

export default Electronics;
