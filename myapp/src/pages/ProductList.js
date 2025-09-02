import React, { useEffect, useState } from "react";
import API from "../api/axiosConfig";
import ProductCard from "../components/ProductCard";

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [loading, setLoading] = useState(false);

  // Fetch categories
  useEffect(() => {
    API.get("/categories")
      .then((res) => setCategories(res.data))
      .catch((err) => console.error("Error fetching categories:", err));
  }, []);

  // Fetch products
  const fetchProducts = () => {
    setLoading(true);

    let query = [];
    if (search.trim() !== "") query.push(`name=${search}`);
    if (category !== "") query.push(`category=${category}`); // 🔥 if backend expects categoryId, use `categoryId=${category}`
    if (minPrice !== "") query.push(`minPrice=${minPrice}`);
    if (maxPrice !== "") query.push(`maxPrice=${maxPrice}`);

    const queryString = query.length > 0 ? `?${query.join("&")}` : "";

    API.get(`/products${queryString}`)
      .then((res) => setProducts(res.data))
      .catch((err) => console.error("Error fetching products:", err))
      .finally(() => setLoading(false));
  };

  // Load all products initially
  useEffect(() => {
    fetchProducts();
  }, []);

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-center">All Products</h2>

      {/* Filters Section */}
      <div className="row mb-4 align-items-end">
        {/* Search */}
        <div className="col-md-3 mb-2">
          <input
            type="text"
            placeholder="🔍 Search products..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="form-control"
          />
        </div>

        {/* Category */}
        <div className="col-md-3 mb-2">
          <select
            className="form-select"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            <option value="">All Categories</option>
            {categories.map((cat) => (
              <option key={cat.id} value={cat.id}>  {/* ✅ using id instead of name */}
                {cat.name}
              </option>
            ))}
          </select>
        </div>

        {/* Price */}
        <div className="col-md-2 mb-2">
          <input
            type="number"
            placeholder="Min Price"
            value={minPrice}
            onChange={(e) => setMinPrice(e.target.value)}
            className="form-control"
          />
        </div>
        <div className="col-md-2 mb-2">
          <input
            type="number"
            placeholder="Max Price"
            value={maxPrice}
            onChange={(e) => setMaxPrice(e.target.value)}
            className="form-control"
          />
        </div>

        {/* Apply Filters Button */}
        <div className="col-md-2 mb-2">
          <button className="btn btn-dark w-100" onClick={fetchProducts}>
            Apply Filters
          </button>
        </div>
      </div>

      {/* Product List */}
      <div className="row">
        {loading ? (
          <p className="text-center">Loading products...</p>
        ) : products.length > 0 ? (
          products.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))
        ) : (
          <p className="text-center">No products available</p>
        )}
      </div>
    </div>
  );
};

export default ProductList;
