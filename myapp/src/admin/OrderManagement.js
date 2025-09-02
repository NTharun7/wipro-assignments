import React, { useEffect, useState, useContext } from "react";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css";

const OrderManagement = () => {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [updatingId, setUpdatingId] = useState(null);
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
      return;
    }
    fetchOrders();
  }, [user, navigate]);

  const fetchOrders = () => {
    API.get("/orders")
      .then((res) => setOrders(res.data))
      .catch(() => setError("Failed to fetch orders"));
  };

  const handleStatusChange = async (orderId, newStatus) => {
    try {
      setUpdatingId(orderId); // disable dropdown
      await API.put(`/orders/${orderId}`, { status: newStatus });
      setSuccess(`✅ Order #${orderId} updated to ${newStatus}`);
      fetchOrders();
      setTimeout(() => setSuccess(""), 2000); // clear message
    } catch {
      setError("❌ Failed to update order status");
    } finally {
      setUpdatingId(null);
    }
  };

  const getStatusBadge = (status) => {
    switch (status) {
      case "DELIVERED":
        return <span className="badge bg-success">Delivered</span>;
      case "PENDING":
        return <span className="badge bg-warning text-dark">Pending</span>;
      case "CONFIRMED":
        return <span className="badge bg-primary">Confirmed</span>;
      case "SHIPPED":
        return <span className="badge bg-info text-dark">Shipped</span>;
      case "CANCELLED":
        return <span className="badge bg-danger">Cancelled</span>;
      default:
        return <span className="badge bg-secondary">{status}</span>;
    }
  };

  return (
    <div className="container mt-5">
      <h2>🛠️ Manage Orders</h2>

      {error && <div className="alert alert-danger mt-3">{error}</div>}
      {success && <div className="alert alert-success mt-3">{success}</div>}

      <div className="card shadow-sm border-0 mt-4">
        <div className="card-body">
          <div className="table-responsive">
            <table className="table table-hover table-striped align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>Order ID</th>
                  <th>User</th>
                  <th>Total (₹)</th>
                  <th>Status</th>
                  <th>Date</th>
                  <th>Items</th>
                  <th>Update Status</th>
                </tr>
              </thead>
              <tbody>
                {orders.length > 0 ? (
                  orders.map((order) => (
                    <tr key={order.id}>
                      <td>#{order.id}</td>
                      <td>{order.user ? order.user.name : "Unknown"}</td>
                      <td><strong>₹{order.totalAmount}</strong></td>
                      <td>{getStatusBadge(order.status)}</td>
                      <td>{new Date(order.orderDate).toLocaleString()}</td>
                      <td>
                        <ul className="list-unstyled mb-0">
                          {order.items && order.items.length > 0 ? (
                            order.items.map((item) => (
                              <li key={item.id}>
                                {item.product ? item.product.name : "Unknown"}{" "}
                                <span className="badge bg-secondary">x{item.quantity}</span>
                              </li>
                            ))
                          ) : (
                            <li className="text-muted">No items</li>
                          )}
                        </ul>
                      </td>
                      <td>
                        <select
                          className="form-select form-select-sm"
                          value={order.status}
                          disabled={updatingId === order.id}
                          onChange={(e) =>
                            handleStatusChange(order.id, e.target.value)
                          }
                        >
                          <option value="PENDING">Pending</option>
                          <option value="CONFIRMED">Confirmed</option>
                          <option value="SHIPPED">Shipped</option>
                          <option value="DELIVERED">Delivered</option>
                          <option value="CANCELLED">Cancelled</option>
                        </select>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="7" className="text-center">
                      <div className="p-3 text-muted">
                        <i className="bi bi-exclamation-circle me-2"></i>
                        No orders found
                      </div>
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

export default OrderManagement;
