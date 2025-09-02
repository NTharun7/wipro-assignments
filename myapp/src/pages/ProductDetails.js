import React, { useEffect, useState, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";

const ProductDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useContext(AuthContext);

  const [product, setProduct] = useState(null);
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    API.get(`/products/${id}`)
      .then((res) => setProduct(res.data))
      .catch(() => setError("Product not found"));
  }, [id]);

  const handleAddToCart = async () => {
    if (!user) {
      navigate("/login");
      return;
    }

    try {
      await API.post("/cart", {
        userId: user.id,
        productId: id,
        quantity: 1,
      });
      setMessage("✅ Product added to cart!");
    } catch (err) {
      setError("❌ Failed to add product to cart");
    }
  };

  if (error) return <div className="container mt-4"><div className="alert alert-danger">{error}</div></div>;
  if (!product) return <div className="container mt-4"><p>Loading...</p></div>;

  return (
    <div className="container mt-5">
      <div className="row g-4">
        {/* Product Image */}
        <div className="col-md-6 d-flex align-items-center justify-content-center">
          <div className="card shadow-sm border-0 rounded-3 p-3" style={{ width: "100%", maxWidth: "400px" }}>
            {product.imageUrl ? (
              <img
                src={product.imageUrl}
                alt={product.name}
                className="img-fluid rounded"
                style={{ maxHeight: "350px", objectFit: "contain" }}
              />
            ) : (
              <div
                className="bg-light d-flex align-items-center justify-content-center rounded"
                style={{ height: "350px" }}
              >
                <span>No Image</span>
              </div>
            )}
          </div>
        </div>

        {/* Product Details */}
        <div className="col-md-6">
          <h2 className="fw-bold">{product.name}</h2>
          <h4 className="text-success fw-semibold mb-3">₹{product.price}</h4>
          <p className="text-muted">{product.description}</p>

          <p>
            <strong>Stock: </strong>
            {product.stock > 0 ? (
              <span className="badge bg-success">In Stock ({product.stock})</span>
            ) : (
              <span className="badge bg-danger">Out of Stock</span>
            )}
          </p>

          {message && <div className="alert alert-success mt-3">{message}</div>}
          {error && <div className="alert alert-danger mt-3">{error}</div>}

          <button
            className="btn btn-dark mt-4 w-100"
            onClick={handleAddToCart}
            disabled={product.stock === 0}
          >
            🛒 Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
