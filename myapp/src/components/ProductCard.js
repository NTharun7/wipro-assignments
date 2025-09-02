import React, { useContext } from "react";
import { Link } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";

const ProductCard = ({ product }) => {
  const { user } = useContext(AuthContext);

  const handleAddToCart = async () => {
    if (!user) {
      alert("Please login to add items to cart");
      return;
    }

    try {
      await API.post("/cart", {
        userId: user.id,
        productId: product.id,
        quantity: 1,
      });
      alert("Product added to cart!");
    } catch (err) {
      console.error("Failed to add to cart:", err);
      alert("Failed to add product to cart");
    }
  };

  return (
    <div className="col-md-3 mb-4">
      <div
        className="card h-100 shadow-sm border-0 rounded-3"
        style={{ transition: "transform 0.2s" }}
        onMouseEnter={(e) => (e.currentTarget.style.transform = "scale(1.03)")}
        onMouseLeave={(e) => (e.currentTarget.style.transform = "scale(1)")}
      >
        {/* Product Image */}
        {product.imageUrl ? (
          <img
            src={product.imageUrl}
            alt={product.name}
            className="card-img-top p-3 rounded-top"
            style={{
              height: "220px",
              objectFit: "contain",
              backgroundColor: "#f8f9fa",
            }}
          />
        ) : (
          <div
            className="d-flex align-items-center justify-content-center bg-light rounded-top"
            style={{ height: "220px" }}
          >
            <span>No Image</span>
          </div>
        )}

        {/* Card Body */}
        <div className="card-body text-center">
          <h5 className="card-title fw-bold">{product.name}</h5>
          <p className="text-success fw-semibold mb-3">₹{product.price}</p>

          <div className="d-flex justify-content-center gap-2">
            <Link to={`/products/${product.id}`} className="btn btn-outline-dark btn-sm">
              View Details
            </Link>
            <button
              className="btn btn-dark btn-sm"
              onClick={handleAddToCart}
            >
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
