import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";

const Checkout = () => {
  const [address, setAddress] = useState("");
  const [orderSummary, setOrderSummary] = useState(null);
  const [paymentStatus, setPaymentStatus] = useState(null);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  const handlePlaceOrder = async (e) => {
    e.preventDefault();

    if (!user) {
      navigate("/login");
      return;
    }

    try {
      // 1. Place order
      const orderRes = await API.post("/orders", {
        userId: user.id,
        address: address,
      });

      const order = orderRes.data;
      setOrderSummary(order);

      // 2. Process dummy payment
      const paymentRes = await API.post("/payments", {
        orderId: order.id,
        paymentStatus: "SUCCESS", // simulate payment result
      });

      setPaymentStatus(paymentRes.data.paymentStatus);
    } catch (err) {
      setError("❌ Failed to place order. Try again.");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "700px" }}>
      <div className="card shadow-sm border-0 rounded-3">
        <div className="card-body p-4">
          <h2 className="mb-4 text-center fw-bold">Checkout</h2>

          {error && <div className="alert alert-danger">{error}</div>}

          {/* If order completed, show confirmation */}
          {orderSummary && paymentStatus ? (
            <div className="text-center">
              {paymentStatus === "SUCCESS" ? (
                <div className="alert alert-success">
                  🎉 Payment Successful! Your order is confirmed.
                </div>
              ) : (
                <div className="alert alert-danger">
                  ❌ Payment Failed! Please try again.
                </div>
              )}

              <h5 className="mt-3">Order Summary</h5>
              <p><strong>Order ID:</strong> {orderSummary.id}</p>
              <p><strong>Address:</strong> {orderSummary.address}</p>
              <p><strong>Total Amount:</strong> ₹{orderSummary.totalAmount}</p>
              <p><strong>Status:</strong> {orderSummary.status}</p>

              <button
                className="btn btn-dark mt-3"
                onClick={() => navigate("/orders")}
              >
                View My Orders
              </button>
            </div>
          ) : (
            // Otherwise show checkout form
            <form onSubmit={handlePlaceOrder}>
              <div className="mb-3">
                <label className="form-label fw-semibold">Shipping Address</label>
                <textarea
                  className="form-control"
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                  required
                  rows="3"
                  placeholder="Enter your delivery address"
                ></textarea>
              </div>

              <button type="submit" className="btn btn-dark w-100 py-2 fw-semibold">
                🛒 Place Order & Pay
              </button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
};

export default Checkout;
