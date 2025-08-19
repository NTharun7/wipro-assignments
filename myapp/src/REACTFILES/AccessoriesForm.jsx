import React, { Component } from "react";

class AccessoriesForm extends Component {
  constructor(props) {
    super(props);

    // Initial state for all form fields
    this.state = {
      name: "",
      description: "",
      category: "Electronics",
      brand: "",
      inStock: false,
      warranty: "",
      submittedData: null, // store submitted data here
    };
  }

  // Generic function to handle changes in text, textarea, select, radio, number
  handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    // For checkbox, we use checked (true/false). For others, we use value.
    this.setState({
      [name]: type === "checkbox" ? checked : value,
    });
  };

  // When form is submitted
  handleSubmit = (e) => {
    e.preventDefault();

    // Save the current form data into submittedData state
    this.setState({
      submittedData: {
        name: this.state.name,
        description: this.state.description,
        category: this.state.category,
        brand: this.state.brand,
        inStock: this.state.inStock,
        warranty: this.state.warranty,
      },
    });
  };

  render() {
    return (
      <div className="container mt-4">
        <h2 className="mb-3">Accessories Form</h2>

        {/* ===== Form Section ===== */}
        <form onSubmit={this.handleSubmit} className="card p-4 shadow">
          {/* Text input */}
          <div className="mb-3">
            <label className="form-label">Accessory Name</label>
            <input
              type="text"
              name="name"
              className="form-control"
              value={this.state.name}
              onChange={this.handleChange}
              required
            />
          </div>

          {/* Textarea */}
          <div className="mb-3">
            <label className="form-label">Description</label>
            <textarea
              name="description"
              className="form-control"
              value={this.state.description}
              onChange={this.handleChange}
              required
            ></textarea>
          </div>

          {/* Select dropdown */}
          <div className="mb-3">
            <label className="form-label">Category</label>
            <select
              name="category"
              className="form-control"
              value={this.state.category}
              onChange={this.handleChange}
            >
              <option value="Electronics">Electronics</option>
              <option value="Clothing">Clothing</option>
              <option value="Home">Home</option>
            </select>
          </div>

          {/* Radio buttons */}
          <div className="mb-3">
            <label className="form-label d-block">Brand</label>
            <div>
              <input
                type="radio"
                name="brand"
                value="Sony"
                checked={this.state.brand === "Sony"}
                onChange={this.handleChange}
              />{" "}
              Sony
            </div>
            <div>
              <input
                type="radio"
                name="brand"
                value="Samsung"
                checked={this.state.brand === "Samsung"}
                onChange={this.handleChange}
              />{" "}
              Samsung
            </div>
            <div>
              <input
                type="radio"
                name="brand"
                value="LG"
                checked={this.state.brand === "LG"}
                onChange={this.handleChange}
              />{" "}
              LG
            </div>
          </div>

          {/* Checkbox */}
          <div className="mb-3 form-check">
            <input
              type="checkbox"
              name="inStock"
              className="form-check-input"
              checked={this.state.inStock}
              onChange={this.handleChange}
            />
            <label className="form-check-label">Available in stock</label>
          </div>

          {/* Number input */}
          <div className="mb-3">
            <label className="form-label">Warranty (in years)</label>
            <input
              type="number"
              name="warranty"
              className="form-control"
              value={this.state.warranty}
              onChange={this.handleChange}
              min="0"
            />
          </div>

          {/* Submit button */}
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </form>

        {/* ===== Table Section (only visible after submit) ===== */}
        {this.state.submittedData && (
          <div className="mt-4">
            <h3>Submitted Accessory Details</h3>
            <table className="table table-bordered table-striped">
              <tbody>
                <tr>
                  <th>Name</th>
                  <td>{this.state.submittedData.name}</td>
                </tr>
                <tr>
                  <th>Description</th>
                  <td>{this.state.submittedData.description}</td>
                </tr>
                <tr>
                  <th>Category</th>
                  <td>{this.state.submittedData.category}</td>
                </tr>
                <tr>
                  <th>Brand</th>
                  <td>{this.state.submittedData.brand}</td>
                </tr>
                <tr>
                  <th>In Stock</th>
                  <td>{this.state.submittedData.inStock ? "Yes" : "No"}</td>
                </tr>
                <tr>
                  <th>Warranty</th>
                  <td>{this.state.submittedData.warranty} years</td>
                </tr>
              </tbody>
            </table>
          </div>
        )}
      </div>
    );
  }
}

export default AccessoriesForm;
