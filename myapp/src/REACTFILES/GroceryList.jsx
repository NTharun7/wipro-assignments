import React from "react";

function GroceryList({ items }) {
  const handleClick = () => {
    alert("Groceries Added to Cart!");
  };

  return (
    <div>
      <h2>Groceries List:</h2>
      <ul>
        {items.map((item, index) => (
          <li key={index}>{item}</li>
        ))}
      </ul>
      <button onClick={handleClick}>Add to Cart</button>
    </div>
  );
}

export default GroceryList;
