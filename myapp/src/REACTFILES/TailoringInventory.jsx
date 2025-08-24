import React, { useState, useEffect } from "react";
import axios from "axios";

const TailoringInventory = () => {
  const [tailoringItems, setTailoringItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTailoringItems = async () => {
      try {
        const response = await axios.get('http://localhost:5000/tailoringItems');
        setTailoringItems(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch tailoring items');
        setLoading(false);
        console.error('Error fetching data:', err);
      }
    };

    fetchTailoringItems();
  }, []);

  if (loading) {
    return (
      <div className="container mt-5">
        <div className="text-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
          <p className="mt-2">Loading tailoring items...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mt-5">
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Tailoring Inventory</h2>
      
      <div className="table-responsive">
        <table className="table table-striped table-hover">
          <thead className="table-dark">
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Size</th>
              <th scope="col">Price</th>
            </tr>
          </thead>
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
      
      {tailoringItems.length === 0 && (
        <div className="text-center mt-4">
          <p className="text-muted">No tailoring items found.</p>
        </div>
      )}
    </div>
  );
};

export default TailoringInventory;
