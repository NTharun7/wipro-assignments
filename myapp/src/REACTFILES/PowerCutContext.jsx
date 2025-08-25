// Import necessary React hooks for creating context
import React, { createContext, useContext, useState } from "react";

// Create a context for power cut announcements
const PowerCutContext = createContext();

// Custom hook to use the power cut context
export const usePowerCut = () => {
  const context = useContext(PowerCutContext);
  if (!context) {
    throw new Error('usePowerCut must be used within a PowerCutProvider');
  }
  return context;
};

// Provider component that wraps the app and provides context values
export const PowerCutProvider = ({ children }) => {
  // State to store all power cut announcements
  const [announcements, setAnnouncements] = useState([]);

  // Function to add a new announcement to the top of the list
  const addAnnouncement = (street, message) => {
    // Create a new announcement object with current timestamp
    const newAnnouncement = {
      id: Date.now(), // Use timestamp as unique ID
      street: street,
      message: message,
      time: new Date().toLocaleTimeString() // Current time in readable format
    };

    // Add new announcement to the beginning of the array (most recent first)
    setAnnouncements(prevAnnouncements => [newAnnouncement, ...prevAnnouncements]);
  };

  // Context value object containing state and functions
  const value = {
    announcements,
    addAnnouncement
  };

  // Provide the context value to all child components
  return (
    <PowerCutContext.Provider value={value}>
      {children}
    </PowerCutContext.Provider>
  );
};

export default PowerCutContext;
