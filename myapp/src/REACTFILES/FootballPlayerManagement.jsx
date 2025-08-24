import React, { useState, useEffect } from "react";
import axios from "axios";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

// Football Player Management System Component
// This component implements full CRUD operations (Create, Read, Update, Delete)
// for managing football players with form validation using Formik and Yup

const FootballPlayerManagement = () => {
  // State management for players data and UI interactions
  const [players, setPlayers] = useState([]); // Stores all players from API
  const [loading, setLoading] = useState(true); // Loading state for API calls
  const [editingPlayer, setEditingPlayer] = useState(null); // Currently editing player
  const [message, setMessage] = useState({ text: "", type: "" }); // Success/error messages

  // Yup validation schema for form fields
  // Each field has specific validation rules as per lab requirements
  const validationSchema = Yup.object({
    name: Yup.string()
      .required("Name is required") // Required field validation
      .min(3, "Name must be at least 3 characters"), // Minimum length validation
    
    age: Yup.number()
      .required("Age is required") // Required field validation
      .min(16, "Age must be at least 16") // Minimum age validation
      .max(40, "Age must be at most 40"), // Maximum age validation
    
    position: Yup.string()
      .required("Position is required"), // Required field validation
    
    club: Yup.string()
      .required("Club is required"), // Required field validation
    
    nationality: Yup.string()
      .required("Nationality is required"), // Required field validation
    
    goals: Yup.number()
      .required("Goals are required") // Required field validation
      .min(0, "Goals cannot be negative"), // Minimum value validation
    
    matchesPlayed: Yup.number()
      .required("Matches played is required") // Required field validation
      .min(0, "Matches played cannot be negative"), // Minimum value validation
    
    jerseyNumber: Yup.number()
      .required("Jersey number is required") // Required field validation
      .min(1, "Jersey number must be at least 1") // Minimum value validation
      .max(99, "Jersey number must be at most 99"), // Maximum value validation
    
    email: Yup.string()
      .required("Email is required") // Required field validation
      .email("Invalid email format"), // Email format validation
    
    contactNumber: Yup.string()
      .required("Contact number is required") // Required field validation
      .matches(/^\d{10}$/, "Contact number must be exactly 10 digits"), // 10-digit validation
  });

  // Initial form values for new player
  const initialValues = {
    name: "",
    age: "",
    position: "",
    club: "",
    nationality: "",
    goals: "",
    matchesPlayed: "",
    jerseyNumber: "",
    email: "",
    contactNumber: "",
  };

  // useEffect hook to fetch players when component mounts
  useEffect(() => {
    fetchPlayers();
  }, []);

  // Function to fetch all players from JSON Server
  const fetchPlayers = async () => {
    try {
      setLoading(true);
      const response = await axios.get("http://localhost:5000/players");
      setPlayers(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching players:", error);
      setMessage({ text: "Failed to fetch players", type: "danger" });
      setLoading(false);
    }
  };

  // Function to handle form submission (Create/Update)
  const handleSubmit = async (values, { resetForm, setSubmitting }) => {
    try {
      if (editingPlayer) {
        // UPDATE operation: Update existing player
        await axios.put(`http://localhost:5000/players/${editingPlayer.id}`, values);
        setMessage({ text: "Player updated successfully!", type: "success" });
        setEditingPlayer(null); // Clear editing state
      } else {
        // CREATE operation: Add new player
        await axios.post("http://localhost:5000/players", values);
        setMessage({ text: "Player added successfully!", type: "success" });
        resetForm(); // Reset form to initial values
      }
      
      fetchPlayers(); // Refresh the players list
      setSubmitting(false);
      
      // Clear success message after 3 seconds
      setTimeout(() => setMessage({ text: "", type: "" }), 3000);
    } catch (error) {
      console.error("Error saving player:", error);
      setMessage({ text: "Failed to save player", type: "danger" });
      setSubmitting(false);
    }
  };

  // Function to handle edit button click
  const handleEdit = (player) => {
    setEditingPlayer(player); // Set the player to edit mode
  };

  // Function to handle delete button click
  const handleDelete = async (playerId) => {
    if (window.confirm("Are you sure you want to delete this player?")) {
      try {
        // DELETE operation: Remove player from database
        await axios.delete(`http://localhost:5000/players/${playerId}`);
        setMessage({ text: "Player deleted successfully!", type: "success" });
        fetchPlayers(); // Refresh the players list
        
        // Clear success message after 3 seconds
        setTimeout(() => setMessage({ text: "", type: "" }), 3000);
      } catch (error) {
        console.error("Error deleting player:", error);
        setMessage({ text: "Failed to delete player", type: "danger" });
      }
    }
  };

  // Function to cancel editing and reset form
  const handleCancel = () => {
    setEditingPlayer(null);
  };

  // Loading state display
  if (loading) {
    return (
      <div className="container mt-5">
        <div className="text-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
          <p className="mt-2">Loading players...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      {/* Page Title */}
      <h1 className="text-center mb-4">Football Player Management System</h1>

      {/* Success/Error Message Display */}
      {message.text && (
        <div className={`alert alert-${message.type} alert-dismissible fade show`} role="alert">
          {message.text}
          <button
            type="button"
            className="btn-close"
            onClick={() => setMessage({ text: "", type: "" })}
          ></button>
        </div>
      )}

      <div className="row">
        {/* Player Form Section */}
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h4>Player Form</h4>
            </div>
            <div className="card-body">
              {/* Formik form with validation */}
              <Formik
                initialValues={editingPlayer || initialValues}
                validationSchema={validationSchema}
                onSubmit={handleSubmit}
                enableReinitialize={true} // Reinitialize form when editingPlayer changes
              >
                {({ isSubmitting, values }) => (
                  <Form>
                    {/* Name Field */}
                    <div className="mb-3">
                      <label htmlFor="name" className="form-label">Name *</label>
                      <Field
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        placeholder="Enter player name"
                      />
                      <ErrorMessage name="name" component="div" className="text-danger small" />
                    </div>

                    {/* Age Field */}
                    <div className="mb-3">
                      <label htmlFor="age" className="form-label">Age *</label>
                      <Field
                        type="number"
                        className="form-control"
                        id="age"
                        name="age"
                        placeholder="Enter age (16-40)"
                      />
                      <ErrorMessage name="age" component="div" className="text-danger small" />
                    </div>

                    {/* Position Field */}
                    <div className="mb-3">
                      <label htmlFor="position" className="form-label">Position *</label>
                      <Field as="select" className="form-select" id="position" name="position">
                        <option value="">Select position</option>
                        <option value="Forward">Forward</option>
                        <option value="Midfielder">Midfielder</option>
                        <option value="Defender">Defender</option>
                        <option value="Goalkeeper">Goalkeeper</option>
                      </Field>
                      <ErrorMessage name="position" component="div" className="text-danger small" />
                    </div>

                    {/* Club Field */}
                    <div className="mb-3">
                      <label htmlFor="club" className="form-label">Club *</label>
                      <Field
                        type="text"
                        className="form-control"
                        id="club"
                        name="club"
                        placeholder="Enter club name"
                      />
                      <ErrorMessage name="club" component="div" className="text-danger small" />
                    </div>

                    {/* Nationality Field */}
                    <div className="mb-3">
                      <label htmlFor="nationality" className="form-label">Nationality *</label>
                      <Field
                        type="text"
                        className="form-control"
                        id="nationality"
                        name="nationality"
                        placeholder="Enter nationality"
                      />
                      <ErrorMessage name="nationality" component="div" className="text-danger small" />
                    </div>

                    {/* Goals Field */}
                    <div className="mb-3">
                      <label htmlFor="goals" className="form-label">Goals *</label>
                      <Field
                        type="number"
                        className="form-control"
                        id="goals"
                        name="goals"
                        placeholder="Enter goals scored"
                      />
                      <ErrorMessage name="goals" component="div" className="text-danger small" />
                    </div>

                    {/* Matches Played Field */}
                    <div className="mb-3">
                      <label htmlFor="matchesPlayed" className="form-label">Matches Played *</label>
                      <Field
                        type="number"
                        className="form-control"
                        id="matchesPlayed"
                        name="matchesPlayed"
                        placeholder="Enter matches played"
                      />
                      <ErrorMessage name="matchesPlayed" component="div" className="text-danger small" />
                    </div>

                    {/* Jersey Number Field */}
                    <div className="mb-3">
                      <label htmlFor="jerseyNumber" className="form-label">Jersey Number *</label>
                      <Field
                        type="number"
                        className="form-control"
                        id="jerseyNumber"
                        name="jerseyNumber"
                        placeholder="Enter jersey number (1-99)"
                      />
                      <ErrorMessage name="jerseyNumber" component="div" className="text-danger small" />
                    </div>

                    {/* Email Field */}
                    <div className="mb-3">
                      <label htmlFor="email" className="form-label">Email *</label>
                      <Field
                        type="email"
                        className="form-control"
                        id="email"
                        name="email"
                        placeholder="Enter email address"
                      />
                      <ErrorMessage name="email" component="div" className="text-danger small" />
                    </div>

                    {/* Contact Number Field */}
                    <div className="mb-3">
                      <label htmlFor="contactNumber" className="form-label">Contact Number *</label>
                      <Field
                        type="text"
                        className="form-control"
                        id="contactNumber"
                        name="contactNumber"
                        placeholder="Enter 10-digit contact number"
                      />
                      <ErrorMessage name="contactNumber" component="div" className="text-danger small" />
                    </div>

                    {/* Form Action Buttons */}
                    <div className="d-flex gap-2">
                      {editingPlayer ? (
                        <>
                          <button
                            type="submit"
                            className="btn btn-primary"
                            disabled={isSubmitting}
                          >
                            {isSubmitting ? "Updating..." : "Update Player"}
                          </button>
                          <button
                            type="button"
                            className="btn btn-secondary"
                            onClick={handleCancel}
                          >
                            Cancel
                          </button>
                        </>
                      ) : (
                        <button
                          type="submit"
                          className="btn btn-success"
                          disabled={isSubmitting}
                        >
                          {isSubmitting ? "Adding..." : "Add Player"}
                        </button>
                      )}
                    </div>
                  </Form>
                )}
              </Formik>
            </div>
          </div>
        </div>

        {/* Player List Section */}
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h4>Player List</h4>
            </div>
            <div className="card-body">
              {/* Bootstrap table displaying players */}
              <div className="table-responsive">
                <table className="table table-striped table-hover">
                  <thead className="table-dark">
                    <tr>
                      <th>Name</th>
                      <th>Age</th>
                      <th>Position</th>
                      <th>Club</th>
                      <th>Goals</th>
                      <th>Matches</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    {players.map((player) => (
                      <tr key={player.id}>
                        <td>{player.name}</td>
                        <td>{player.age}</td>
                        <td>{player.position}</td>
                        <td>{player.club}</td>
                        <td>{player.goals}</td>
                        <td>{player.matchesPlayed}</td>
                        <td>
                          {/* Action buttons for each player */}
                          <div className="btn-group" role="group">
                            <button
                              type="button"
                              className="btn btn-warning btn-sm"
                              onClick={() => handleEdit(player)}
                            >
                              Edit
                            </button>
                            <button
                              type="button"
                              className="btn btn-danger btn-sm"
                              onClick={() => handleDelete(player.id)}
                            >
                              Delete
                            </button>
                          </div>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>

              {/* Display message when no players exist */}
              {players.length === 0 && (
                <p className="text-center text-muted">No players found.</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FootballPlayerManagement;
