import React, { useState, useEffect } from "react";

function RestaurantForm() {
  // ---------------- State for form fields ----------------
  const [formData, setFormData] = useState({
    restaurantName: "",
    ownerName: "",
    email: "",
    contact: "",
    address: "",
    cuisine: "",
    hours: "",
  });

  // state to store submitted data
  const [submittedData, setSubmittedData] = useState(null);

  // ---------------- Handle Input Change ----------------
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value }); // update state dynamically
  };

  // ---------------- Handle Submit ----------------
  const handleSubmit = (e) => {
    e.preventDefault(); // prevent page refresh
    setSubmittedData(formData); // save formData to submittedData
  };

  // ---------------- useEffect Examples ----------------

  // 1️⃣ Runs on every render
  useEffect(() => {
    console.log("Form component rendered!");
  });

  // 2️⃣ Runs only once (on mount)
  useEffect(() => {
    console.log("Form mounted!");
  }, []);

  // 3️⃣ Runs whenever formData changes
  useEffect(() => {
    console.log("Form data changed:", formData);
  }, [formData]);

  // 4️⃣ Auto-save every 5 seconds (cleanup with clearInterval)
  useEffect(() => {
    const interval = setInterval(() => {
      console.log("Auto-saving form data:", formData);
    }, 5000);

    // cleanup function → stops the interval when component unmounts
    return () => clearInterval(interval);
  }, [formData]);

  return (
    <div className="container mt-4">
      <div className="card p-4 shadow">
        <h2>🍴 Restaurant Registration Form</h2>
        <p>Welcome to Restaurant Registration 📝</p>

        {/* ---------------- Form ---------------- */}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label>Restaurant Name</label>
            <input
              type="text"
              name="restaurantName"
              className="form-control"
              value={formData.restaurantName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Owner Name</label>
            <input
              type="text"
              name="ownerName"
              className="form-control"
              value={formData.ownerName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Email</label>
            <input
              type="email"
              name="email"
              className="form-control"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Contact Number</label>
            <input
              type="text"
              name="contact"
              className="form-control"
              value={formData.contact}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Address</label>
            <input
              type="text"
              name="address"
              className="form-control"
              value={formData.address}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Cuisine Type</label>
            <input
              type="text"
              name="cuisine"
              className="form-control"
              value={formData.cuisine}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Opening Hours</label>
            <input
              type="text"
              name="hours"
              className="form-control"
              value={formData.hours}
              onChange={handleChange}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary w-100">
            Submit
          </button>
        </form>
      </div>

      {/* ---------------- Submitted Data as JSON ---------------- */}
      {submittedData && (
        <div className="card p-4 mt-4 shadow">
          <h4>📄 Submitted Data (JSON)</h4>
          <pre>{JSON.stringify(submittedData, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}

export default RestaurantForm;
