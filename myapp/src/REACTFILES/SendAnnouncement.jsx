// Import necessary React hooks and the custom context hook
import React, { useState } from "react";
import { usePowerCut } from "./PowerCutContext";

// Component for sending new power cut announcements
const SendAnnouncement = () => {
  // Local state for form inputs
  const [street, setStreet] = useState("");
  const [message, setMessage] = useState("");

  // Get the addAnnouncement function from context
  const { addAnnouncement } = usePowerCut();

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent default form submission

    // Validate that both fields are filled
    if (!street.trim() || !message.trim()) {
      alert("Please fill in both Street Name and Message fields!");
      return;
    }

    // Add the announcement using context function
    addAnnouncement(street.trim(), message.trim());

    // Clear form fields after successful submission
    setStreet("");
    setMessage("");
  };

  return (
    <div className="container mt-4">
      {/* Form title */}
      <h3 className="text-center mb-3">Street Power Cut Announcements</h3>
      
      {/* Announcement form */}
      <form onSubmit={handleSubmit} className="row g-3">
        {/* Street Name input field */}
        <div className="col-12">
          <label htmlFor="street" className="form-label">
            Street Name:
          </label>
          <input
            type="text"
            className="form-control"
            id="street"
            value={street}
            onChange={(e) => setStreet(e.target.value)}
            placeholder="Enter street name"
            required
          />
        </div>

        {/* Announcement Message textarea */}
        <div className="col-12">
          <label htmlFor="message" className="form-label">
            Message:
          </label>
          <textarea
            className="form-control"
            id="message"
            rows="3"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            placeholder="Enter announcement message"
            required
          ></textarea>
        </div>

        {/* Submit button */}
        <div className="col-12 text-center">
          <button type="submit" className="btn btn-primary">
            Send Announcement
          </button>
        </div>
      </form>
    </div>
  );
};

export default SendAnnouncement;
