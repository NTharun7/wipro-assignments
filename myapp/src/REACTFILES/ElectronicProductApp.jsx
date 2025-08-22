import React, { useState, useEffect } from "react";

// ✅ Loading Component
const LoadingComponent = () => (
  <div className="alert alert-warning">⏳ Loading product details...</div>
);

// ✅ Error Component
const ErrorComponent = () => (
  <div className="alert alert-danger">❌ Error fetching product details</div>
);

// ✅ Product Component
const ProductComponent = ({ product }) => (
  <div className="card p-3 shadow">
    <h4>📦 Electronic Product Details</h4>
    <p>
      <b>Name:</b> {product.name}
    </p>
    <p>
      <b>Brand:</b> {product.brand}
    </p>
    <p>
      <b>Price:</b> ₹{product.price} <br />
      {product.price > 50000 ? (
        <span className="badge bg-success">Premium Product</span>
      ) : (
        <span className="badge bg-info">Budget Product</span>
      )}
    </p>
    <p>
      <b>Category:</b> {product.category}{" "}
      {product.category === "Laptop" && (
        <span className="badge bg-warning text-dark">
          🎒 Free Laptop Bag Offer
        </span>
      )}
    </p>
    <p>
      <b>Warranty:</b>{" "}
      {product.warranty > 0
        ? `${product.warranty} years`
        : "No Warranty"}
    </p>
    <p>
      <b>Availability:</b>{" "}
      {product.availability ? (
        <span className="text-success">✅ In Stock</span>
      ) : (
        <span className="text-danger">❌ Out of Stock</span>
      )}
    </p>
  </div>
);

// ✅ Parent App Component
function ElectronicProductApp() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [product, setProduct] = useState(null);

  useEffect(() => {
    // ⏳ Simulate API call
    setTimeout(() => {
      // Uncomment one at a time to test behavior:

      // ✅ Case 1: Success
      setProduct({
        name: "Sony Bravia TV",
        brand: "Sony",
        price: 60000,
        category: "TV",
        warranty: 2,
        availability: true,
      });
      setLoading(false);

      // ❌ Case 2: Error
      // setError(true);
      // setLoading(false);

    }, 2000);
  }, []);

  // ✅ Conditional Rendering with Ternary Operators
  return (
    <div className="container mt-4">
      <h2 className="mb-3">Lab Q17: Conditional Rendering</h2>
      {loading ? (
        <LoadingComponent />
      ) : error ? (
        <ErrorComponent />
      ) : (
        <ProductComponent product={product} />
      )}
    </div>
  );
}

export default ElectronicProductApp;
