import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import API from "../api/axiosConfig";
import ProductCard from "../components/ProductCard";

const Home = () => {
  const [featured, setFeatured] = useState([]);

  useEffect(() => {
    API.get("/products")
      .then((res) => {
        setFeatured(res.data.slice(0, 4)); // show only first 4
      })
      .catch((err) => console.error("Error loading featured products:", err));
  }, []);

  return (
    <div>
      {/* Hero Section */}
      <div
        className="text-light text-center p-5 mb-5"
        style={{
          background: "linear-gradient(135deg, #212529, #343a40)",
        }}
      >
        <h1 className="display-4 fw-bold">Welcome to Online Shopping 🛒</h1>
        <p className="lead">Browse products, add to cart, and shop with ease!</p>
        <Link to="/products" className="btn btn-light btn-lg mt-3">
          <i className="bi bi-bag-fill me-2"></i> Shop Now
        </Link>
      </div>

      {/* Featured Products */}
      <div className="container">
        <h2 className="mb-4 text-center">✨ Featured Products</h2>
        <div className="row g-4">
          {featured.length > 0 ? (
            featured.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))
          ) : (
            <div className="text-center text-muted">
              <i className="bi bi-exclamation-circle me-2"></i>
              No featured products available
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Home;
