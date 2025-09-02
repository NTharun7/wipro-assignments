import React, { useEffect, useState, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [total, setTotal] = useState(0);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) {
      navigate("/login");
      return;
    }

    API.get(`/cart/${user.id}`)
      .then((res) => {
        setCartItems(res.data);
        calculateTotal(res.data);
      })
      .catch(() => setError("Failed to load cart items"));
  }, [user, navigate]);

  const calculateTotal = (items) => {
    const totalAmount = items.reduce(
      (acc, item) => acc + item.product.price * item.quantity,
      0
    );
    setTotal(totalAmount);
  };

  const handleUpdateQuantity = async (cartItemId, quantity) => {
    try {
      await API.post("/cart", {
        userId: user.id,
        productId: cartItems.find((i) => i.id === cartItemId).product.id,
        quantity,
      });

      const updatedItems = cartItems.map((item) =>
        item.id === cartItemId ? { ...item, quantity } : item
      );
      setCartItems(updatedItems);
      calculateTotal(updatedItems);
    } catch {
      setError("Failed to update quantity");
    }
  };

  const handleRemove = async (cartItemId) => {
    try {
      await API.delete(`/cart/${cartItemId}`);
      const updatedItems = cartItems.filter((item) => item.id !== cartItemId);
      setCartItems(updatedItems);
      calculateTotal(updatedItems);
    } catch {
      setError("Failed to remove item from cart");
    }
  };

  if (error) return <div className="container mt-4"><div className="alert alert-danger">{error}</div></div>;

  return (
    <div className="container mt-5">
      <h2 className="mb-4">🛒 My Cart</h2>

      {cartItems.length === 0 ? (
        <div className="text-center p-5 bg-light rounded shadow-sm">
          <h4>Your cart is empty</h4>
          <Link to="/products" className="btn btn-dark mt-3">
            Shop Now
          </Link>
        </div>
      ) : (
        <div className="row">
          {/* Cart Table */}
          <div className="col-lg-8">
            <div className="card shadow-sm border-0">
              <div className="card-body">
                <div className="table-responsive">
                  <table className="table align-middle">
                    <thead className="table-dark">
                      <tr>
                        <th>Product</th>
                        <th>Price (₹)</th>
                        <th style={{ width: "120px" }}>Quantity</th>
                        <th>Subtotal (₹)</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      {cartItems.map((item) => (
                        <tr key={item.id}>
                          <td>
                            <div className="d-flex align-items-center">
                              {item.product.imageUrl && (
                                <img
                                  src={item.product.imageUrl}
                                  alt={item.product.name}
                                  style={{ width: "50px", height: "50px", objectFit: "contain" }}
                                  className="me-2 rounded"
                                />
                              )}
                              {item.product.name}
                            </div>
                          </td>
                          <td>₹{item.product.price}</td>
                          <td>
                            <input
                              type="number"
                              min="1"
                              value={item.quantity}
                              className="form-control form-control-sm text-center"
                              onChange={(e) =>
                                handleUpdateQuantity(item.id, parseInt(e.target.value))
                              }
                            />
                          </td>
                          <td>₹{item.product.price * item.quantity}</td>
                          <td>
                            <button
                              className="btn btn-sm btn-danger"
                              onClick={() => handleRemove(item.id)}
                            >
                              Remove
                            </button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>

          {/* Summary Card */}
          <div className="col-lg-4 mt-4 mt-lg-0">
            <div className="card shadow-sm border-0">
              <div className="card-body">
                <h4 className="mb-3">Order Summary</h4>
                <p className="d-flex justify-content-between">
                  <span>Total Items</span> <strong>{cartItems.length}</strong>
                </p>
                <p className="d-flex justify-content-between">
                  <span>Grand Total</span> <strong>₹{total}</strong>
                </p>
                <button
                  className="btn btn-dark w-100 mt-3"
                  onClick={() => navigate("/checkout")}
                >
                  Proceed to Checkout →
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Cart;
