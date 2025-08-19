import React, { useState } from "react";

function BakingItemsForm() {
  // State for input fields
  const [formData, setFormData] = useState({
    itemName: "",
    quantity: "",
    ingredients: "",
    bakingTime: "",
    category: "Cake",
  });

  // State for storing all submitted items
  const [items, setItems] = useState([]);

  // Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value, // update field dynamically
    });
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    // Add new item into items array using spread operator
    setItems([...items, formData]);
    // Reset form after submission
    setFormData({
      itemName: "",
      quantity: "",
      ingredients: "",
      bakingTime: "",
      category: "Cake",
    });
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-3">🍰 Baking Items Form</h2>
      <form onSubmit={handleSubmit} className="card p-4 shadow">
        <div className="mb-3">
          <label className="form-label">Item Name</label>
          <input
            type="text"
            name="itemName"
            className="form-control"
            value={formData.itemName}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Quantity</label>
          <input
            type="number"
            name="quantity"
            className="form-control"
            value={formData.quantity}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Ingredients</label>
          <input
            type="text"
            name="ingredients"
            className="form-control"
            value={formData.ingredients}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Baking Time (minutes)</label>
          <input
            type="text"
            name="bakingTime"
            className="form-control"
            value={formData.bakingTime}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Category</label>
          <select
            name="category"
            className="form-control"
            value={formData.category}
            onChange={handleChange}
          >
            <option value="Cake">Cake</option>
            <option value="Bread">Bread</option>
            <option value="Pastry">Pastry</option>
          </select>
        </div>

        <button type="submit" className="btn btn-success">
          Add Baking Item
        </button>
      </form>

      {/* Bootstrap Table for displaying submitted items */}
      {items.length > 0 && (
        <div className="mt-4">
          <h3>Baking Items List</h3>
          <table className="table table-bordered table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Ingredients</th>
                <th>Baking Time</th>
                <th>Category</th>
              </tr>
            </thead>
            <tbody>
              {items.map((item, index) => (
                <tr key={index}>
                  <td>{index + 1}</td>
                  <td>{item.itemName}</td>
                  <td>{item.quantity}</td>
                  <td>{item.ingredients}</td>
                  <td>{item.bakingTime}</td>
                  <td>{item.category}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default BakingItemsForm;
