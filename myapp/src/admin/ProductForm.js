import React, { useEffect, useState, useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";

const ProductForm = () => {
  const { id } = useParams(); // product id if editing
  const navigate = useNavigate();
  const { user } = useContext(AuthContext);

  const [product, setProduct] = useState({
    name: "",
    description: "",
    price: "",
    stock: "",
    categoryId: "",
    imageUrl: "",
  });

  const [categories, setCategories] = useState([]);
  const [error, setError] = useState("");

  // Fetch categories + product (if editing)
  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
      return;
    }

    API.get("/categories")
      .then((res) => setCategories(res.data))
      .catch(() => setError("Failed to load categories"));

    if (id) {
      API.get(`/products/${id}`)
        .then((res) => {
          setProduct({
            name: res.data.name,
            description: res.data.description,
            price: res.data.price,
            stock: res.data.stock,
            categoryId: res.data.category ? res.data.category.id : "",
            imageUrl: res.data.imageUrl,
          });
        })
        .catch(() => setError("Failed to load product"));
    }
  }, [id, user, navigate]);

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      name: product.name,
      description: product.description,
      price: product.price,
      stock: product.stock,
      imageUrl: product.imageUrl,
      category: { id: product.categoryId },
    };

    try {
      if (id) {
        // Update product
        await API.put(`/products/${id}`, payload);
      } else {
        // Add product
        await API.post("/products", payload);
      }
      navigate("/admin/products");
    } catch (err) {
      setError("Failed to save product. Please check inputs.");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "600px" }}>
      <h2 className="mb-4">{id ? "Edit Product" : "Add Product"}</h2>
      {error && <div className="alert alert-danger">{error}</div>}

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={product.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Description</label>
          <textarea
            className="form-control"
            name="description"
            value={product.description}
            onChange={handleChange}
            rows="3"
          ></textarea>
        </div>

        <div className="mb-3">
          <label>Price (₹)</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={product.price}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Stock</label>
          <input
            type="number"
            className="form-control"
            name="stock"
            value={product.stock}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Category</label>
          <select
            className="form-select"
            name="categoryId"
            value={product.categoryId}
            onChange={handleChange}
            required
          >
            <option value="">-- Select Category --</option>
            {categories.map((cat) => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label>Image URL</label>
          <input
            type="text"
            className="form-control"
            name="imageUrl"
            value={product.imageUrl}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="btn btn-dark w-100">
          {id ? "Update Product" : "Add Product"}
        </button>
      </form>
    </div>
  );
};

export default ProductForm;
