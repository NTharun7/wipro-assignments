import React, { useState, useEffect, useContext } from "react";
import api from "../api/axiosConfig";   // ✅ use configured axios instance
import { AuthContext } from "../context/AuthContext";

const Profile = () => {
  const { user } = useContext(AuthContext); // user object from AuthContext
  const [profile, setProfile] = useState({
    name: "",
    email: "",
    address: "",
    phone: ""
  });
  const [message, setMessage] = useState("");

  // Fetch profile details
  useEffect(() => {
    if (user) {
      api
        .get(`/users/${user.id}`, {
          headers: { Authorization: `Bearer ${user.token}` },
        })
        .then((res) => {
          const data = res.data;
          setProfile({
            name: data.name || "",
            email: data.email || "",
            address: data.address || "",
            phone: data.phone || "",
          });
        })
        .catch((err) => console.error("Error fetching profile:", err));
    }
  }, [user]);

  // Handle form input
  const handleChange = (e) => {
    setProfile({ ...profile, [e.target.name]: e.target.value });
  };

  // Update profile
  const handleSubmit = (e) => {
    e.preventDefault();
    api
      .put(`/users/${user.id}`, profile, {
        headers: { Authorization: `Bearer ${user.token}` },
      })
      .then(() => setMessage("Profile updated successfully ✅"))
      .catch(() => setMessage("Error updating profile ❌"));
  };

  return (
    <div className="container mt-4">
      <h2>My Profile</h2>
      {message && <div className="alert alert-info">{message}</div>}
      <form onSubmit={handleSubmit} className="col-md-6">
        <div className="mb-3">
          <label>Name</label>
          <input
            type="text"
            name="name"
            value={profile.name}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-3">
          <label>Email (read-only)</label>
          <input
            type="email"
            name="email"
            value={profile.email}
            readOnly
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label>Address</label>
          <input
            type="text"
            name="address"
            value={profile.address || ""}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label>Phone</label>
          <input
            type="text"
            name="phone"
            value={profile.phone || ""}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <button className="btn btn-primary" type="submit">
          Save Changes
        </button>
      </form>
    </div>
  );
};

export default Profile;
