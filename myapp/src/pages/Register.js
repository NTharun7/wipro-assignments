import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../api/authApi";

const Register = () => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    address: "",
    phone: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      await registerUser(form.name, form.email, form.password, form.address, form.phone);

      setSuccess("Registration successful! Please login.");
      setTimeout(() => navigate("/login"), 1500);
    } catch (err) {
      setError("Registration failed. Try again.");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "500px" }}>
      <h2 className="text-center mb-4">Register</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      {success && <div className="alert alert-success">{success}</div>}

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={form.name}
            onChange={handleChange}
            required
            placeholder="Enter full name"
          />
        </div>

        <div className="mb-3">
          <label>Email</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={form.email}
            onChange={handleChange}
            required
            placeholder="Enter email"
          />
        </div>

        <div className="mb-3">
          <label>Password</label>
          <input
            type="password"
            className="form-control"
            name="password"
            value={form.password}
            onChange={handleChange}
            required
            placeholder="Enter password"
          />
        </div>

        <div className="mb-3">
          <label>Address</label>
          <input
            type="text"
            className="form-control"
            name="address"
            value={form.address}
            onChange={handleChange}
            placeholder="Enter address"
          />
        </div>

        <div className="mb-3">
          <label>Phone</label>
          <input
            type="text"
            className="form-control"
            name="phone"
            value={form.phone}
            onChange={handleChange}
            placeholder="Enter phone number"
          />
        </div>

        <button type="submit" className="btn btn-dark w-100">
          Register
        </button>
      </form>
    </div>
  );
};

export default Register;
