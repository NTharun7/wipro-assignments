import React from "react";

const SweetsList = () => {
  const sweets = [
    { id: 1, name: "Laddu", price: 50 },
    { id: 2, name: "Jalebi", price: 40 },
    { id: 3, name: "Barfi", price: 60 },
  ];

  return (
    <div>
      <h2>Sweets List</h2>
      {sweets.map((sweet) => (
        <div key={sweet.id}>
          {sweet.name} - Price: ₹{sweet.price}
        </div>
      ))}
    </div>
  );
};

export default SweetsList;
