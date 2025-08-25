// Import necessary React hooks and the custom context hook
import React from "react";
import { usePowerCut } from "./PowerCutContext";

// Component for displaying the list of power cut announcements
const AnnouncementList = () => {
  // Get announcements array from context
  const { announcements } = usePowerCut();

  return (
    <div className="container mt-4">
      {/* List title */}
      <h3 className="text-center mb-3">Power Cut Announcements</h3>
      
      {/* Scrollable container for announcements with max height of 300px */}
      <div 
        className="border rounded p-3" 
        style={{ 
          maxHeight: '300px', 
          overflowY: 'auto',
          backgroundColor: '#f8f9fa'
        }}
      >
        {/* Check if there are any announcements */}
        {announcements.length === 0 ? (
          // Show message when no announcements exist
          <div className="text-center text-muted">
            <p>No power cut announcements yet.</p>
            <p>Add an announcement using the form above.</p>
          </div>
        ) : (
          // Display all announcements
          announcements.map((announcement) => (
            <div 
              key={announcement.id}
              className="border p-2 rounded mb-2"
              style={{ backgroundColor: 'white' }}
            >
              {/* Street name - displayed prominently */}
              <div className="fw-bold text-primary">
                Street: {announcement.street}
              </div>
              
              {/* Announcement message */}
              <div className="mt-1">
                <strong>Message:</strong> {announcement.message}
              </div>
              
              {/* Timestamp - displayed in smaller text */}
              <div className="text-muted small mt-1">
                Time: {announcement.time}
              </div>
            </div>
          ))
        )}
      </div>
      
      {/* Display total count of announcements */}
      {announcements.length > 0 && (
        <div className="text-center mt-2">
          <small className="text-muted">
            Total Announcements: {announcements.length}
          </small>
        </div>
      )}
    </div>
  );
};

export default AnnouncementList;
