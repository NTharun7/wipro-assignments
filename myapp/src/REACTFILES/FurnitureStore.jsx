import React, { useState } from "react";

// ----------------------- Chair Component -----------------------
// This component displays details about a Chair
const Chair = () => (
  <div className="card p-3 mt-3 shadow">
    <h4>🪑 Chair</h4>
    <p><b>Name:</b> Office Chair</p>
    <p><b>Price:</b> ₹2500</p>
    <p><b>Material:</b> Plastic</p>
    <p><b>Brand:</b> Nilkamal</p>
  </div>
);

// ----------------------- Table Component -----------------------
// This component displays details about a Table
const Table = () => (
  <div className="card p-3 mt-3 shadow">
    <h4>🛋️ Table</h4>
    <p><b>Name:</b> Dining Table</p>
    <p><b>Price:</b> ₹8000</p>
    <p><b>Material:</b> Wood</p>
    <p><b>Brand:</b> Godrej</p>
  </div>
);

// ----------------------- Sofa Component -----------------------
// This component displays details about a Sofa
const Sofa = () => (
  <div className="card p-3 mt-3 shadow">
    <h4>🛋️ Sofa</h4>
    <p><b>Name:</b> Recliner Sofa</p>
    <p><b>Price:</b> ₹20000</p>
    <p><b>Material:</b> Leather</p>
    <p><b>Brand:</b> Urban Ladder</p>
  </div>
);

// ----------------------- Bed Component -----------------------
// This component displays details about a Bed
const Bed = () => (
  <div className="card p-3 mt-3 shadow">
    <h4>🛏️ Bed</h4>
    <p><b>Name:</b> King Size Bed</p>
    <p><b>Price:</b> ₹30000</p>
    <p><b>Material:</b> Teak Wood</p>
    <p><b>Brand:</b> Durian</p>
  </div>
);

// ----------------------- Main Component -----------------------
function FurnitureStore() {
  // useState -> used to keep track of which furniture is selected
  // Default value is "Chair"
  const [selected, setSelected] = useState("Chair");

  // Function to decide which component to display
  // It uses switch-case (conditional rendering)
  const renderFurniture = () => {
    switch (selected) {
      case "Chair":
        return <Chair />;
      case "Table":
        return <Table />;
      case "Sofa":
        return <Sofa />;
      case "Bed":
        return <Bed />;
      default:
        return <p>Please select a furniture item.</p>;
    }
  };

  return (
    <div className="container mt-4">
      <h2>🛒 Furniture Store</h2>

      {/* Dropdown for user to select furniture type */}
      <select
        className="form-select w-50"
        value={selected}
        // when user selects a value, update the state
        onChange={(e) => setSelected(e.target.value)}
      >
        <option value="Chair">Chair</option>
        <option value="Table">Table</option>
        <option value="Sofa">Sofa</option>
        <option value="Bed">Bed</option>
      </select>

      {/* Show the selected furniture details */}
      {renderFurniture()}
    </div>
  );
}

export default FurnitureStore;
