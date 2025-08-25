// Import necessary React hooks and axios for HTTP requests
import React, { useState, useEffect } from "react";
import axios from "axios";

// Main component for displaying tailoring inventory items
const TailoringInventory = () => {
  // State to store the list of tailoring items fetched from API
  const [tailoringItems, setTailoringItems] = useState([]);
  // State to track loading status while fetching data
  const [loading, setLoading] = useState(true);
  // State to store any error messages if API call fails
  const [error, setError] = useState(null);

  // useEffect hook runs when component mounts to fetch data
  useEffect(() => {
    // Async function to fetch tailoring items from the backend API
    const fetchTailoringItems = async () => {
      try {
        // Make GET request to local backend server
        const response = await axios.get('http://localhost:5000/tailoringItems');
        // Update state with fetched data
        setTailoringItems(response.data);
        // Set loading to false since data is loaded
        setLoading(false);
      } catch (err) {
        // Handle any errors during API call
        setError('Failed to fetch tailoring items');
        setLoading(false);
        console.error('Error fetching data:', err);
      }
    };

    // Call the fetch function when component mounts
    fetchTailoringItems();
  }, []); // Empty dependency array means this effect runs only once on mount

  // Show loading spinner while data is being fetched
  if (loading) {
    return (
      <div className="container mt-5">
        <div className="text-center">
          {/* Bootstrap spinner for loading indication */}
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
          <p className="mt-2">Loading tailoring items...</p>
        </div>
      </div>
    );
  }

  // Show error message if API call failed
  if (error) {
    return (
      <div className="container mt-5">
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      </div>
    );
  }

  // Main component render - display the inventory table
  return (
    <div className="container mt-5">
      {/* Page title */}
      <h2 className="text-center mb-4">Tailoring Inventory</h2>
      
      {/* Responsive table container */}
      <div className="table-responsive">
        <table className="table table-striped table-hover">
          {/* Table header with dark theme */}
          <thead className="table-dark">
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Size</th>
              <th scope="col">Price</th>
            </tr>
          </thead>
          {/* Table body - map through tailoring items */}
          <tbody>
            {tailoringItems.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{item.size}</td>
                <td>₹{item.price}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      
      {/* Show message when no items are found */}
      {tailoringItems.length === 0 && (
        <div className="text-center mt-4">
          <p className="text-muted">No tailoring items found.</p>
        </div>
      )}
    </div>
  );
};

// Export the component for use in other parts of the application
export default TailoringInventory;
