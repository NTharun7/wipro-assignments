import React from "react";

const CanteenItem = ({ name, price, category, available }) => (
  <div>
    <p>
      {name} - ₹{price} | {category} | Available: {available}
    </p>
  </div>
);

const CanteenMenu = () => {
  return (
    <div>
      <h2>Canteen Menu</h2>
      <p><b>Canteen Name:</b> XYZ Canteen</p>
      <p><b>Location:</b> Main Street</p>
      <p><b>Open Hours:</b> 9 AM - 9 PM</p>

      <CanteenItem name="Idli" price={30} category="Breakfast" available="Yes" />
      <CanteenItem name="Meals" price={80} category="Lunch" available="Yes" />
      <CanteenItem name="Samosa" price={20} category="Snack" available="No" />
    </div>
  );
};

export default CanteenMenu;
