// Import necessary React components
import React from "react";
import { PowerCutProvider } from "./PowerCutContext";
import SendAnnouncement from "./SendAnnouncement";
import AnnouncementList from "./AnnouncementList";

// Main application component for Power Cut Announcement System
const PowerCutApp = () => {
  return (
    // Wrap the entire app with PowerCutProvider to provide context
    <PowerCutProvider>
      <div className="container-fluid py-4">
        {/* Main page title */}
        <div className="text-center mb-4">
          <h1 className="display-4 text-primary">
            ⚡ Electricity Power Cut Announcement System
          </h1>
          <p className="lead text-muted">
            Manage and display power cut announcements for all streets
          </p>
        </div>

        {/* Send Announcement Form - positioned at the top */}
        <SendAnnouncement />
        
        {/* Announcement List - displayed below the form */}
        <AnnouncementList />
        
        {/* Footer information */}
        <div className="text-center mt-5">
          <small className="text-muted">
            Built with React Context API and Bootstrap
          </small>
        </div>
      </div>
    </PowerCutProvider>
  );
};

export default PowerCutApp;
