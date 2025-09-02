import React, { useEffect, useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";
import "bootstrap-icons/font/bootstrap-icons.css"; // for icons

const ProductManagement = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
      return;
    }
    fetchProducts();
  }, [user, navigate]);

  const fetchProducts = () => {
    API.get("/products")
      .then((res) => setProducts(res.data))
      .catch(() => setError("Failed to fetch products"));
  };

  return (
    <div className="container mt-5">
      <h2>📦 Manage Products</h2>

      {error && <div className="alert alert-danger mt-3">{error}</div>}

      <div className="mb-3 text-end">
        <Link to="/admin/products/new" className="btn btn-dark">
          <i className="bi bi-plus-lg me-1"></i> Add Product
        </Link>
      </div>

      <div className="card shadow-sm border-0">
        <div className="card-body">
          <div className="table-responsive">
            <table className="table table-hover table-striped align-middle">
              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Image</th>
                  <th>Name</th>
                  <th>Price (₹)</th>
                  <th>Stock</th>
                  <th>Category</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {products.length > 0 ? (
                  products.map((product) => (
                    <tr key={product.id}>
                      <td>{product.id}</td>
                      <td>
                        {product.imageUrl ? (
                          <img
                            src={product.imageUrl}
                            alt={product.name}
                            style={{ width: "50px", height: "50px", objectFit: "contain" }}
                            className="rounded"
                          />
                        ) : (
                          <span className="text-muted">No Image</span>
                        )}
                      </td>
                      <td>{product.name}</td>
                      <td>₹{product.price}</td>
                      <td>{product.stock}</td>
                      <td>{product.category ? product.category.name : "N/A"}</td>
                      <td>
                        <Link
                          to={`/admin/products/edit/${product.id}`}
                          className="btn btn-sm btn-warning d-flex align-items-center"
                        >
                          <i className="bi bi-pencil-square me-1"></i> Edit
                        </Link>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="7" className="text-center">
                      No products found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductManagement;
