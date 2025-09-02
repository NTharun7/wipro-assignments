import React, { useEffect, useState, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) {
      navigate("/login");
      return;
    }

    API.get(`/orders/${user.id}`)
      .then((res) => setOrders(res.data))
      .catch(() => setError("Failed to load orders"));
  }, [user, navigate]);

  if (error)
    return (
      <div className="container mt-4">
        <div className="alert alert-danger">{error}</div>
      </div>
    );

  const getStatusBadge = (status) => {
    switch (status) {
      case "DELIVERED":
        return <span className="badge bg-success">{status}</span>;
      case "PENDING":
        return <span className="badge bg-warning text-dark">{status}</span>;
      case "CANCELLED":
        return <span className="badge bg-danger">{status}</span>;
      case "SHIPPED":
        return <span className="badge bg-info">{status}</span>;
      default:
        return <span className="badge bg-secondary">{status}</span>;
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">📦 My Orders</h2>

      {orders.length === 0 ? (
        <div className="text-center p-5 bg-light rounded shadow-sm">
          <h4>No orders found</h4>
          <Link to="/products" className="btn btn-dark mt-3">
            Shop Now
          </Link>
        </div>
      ) : (
        <div className="accordion" id="ordersAccordion">
          {orders.map((order, index) => (
            <div className="accordion-item mb-3 shadow-sm border-0" key={order.id}>
              <h2 className="accordion-header" id={`heading${index}`}>
                <button
                  className="accordion-button collapsed fw-semibold"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target={`#collapse${index}`}
                >
                  Order #{order.id} &nbsp; | &nbsp; {getStatusBadge(order.status)} &nbsp; | &nbsp; ₹{order.totalAmount}
                </button>
              </h2>
              <div
                id={`collapse${index}`}
                className="accordion-collapse collapse"
                data-bs-parent="#ordersAccordion"
              >
                <div className="accordion-body">
                  <p>
                    <strong>Date:</strong>{" "}
                    {new Date(order.orderDate).toLocaleString()}
                  </p>

                  <h5 className="mt-3">Items:</h5>
                  <ul className="list-group mb-3">
                    {order.items &&
                      order.items.map((item) => (
                        <li
                          key={item.id}
                          className="list-group-item d-flex justify-content-between align-items-center"
                        >
                          <span>
                            {item.product.name}{" "}
                            <span className="badge bg-secondary ms-2">
                              x{item.quantity}
                            </span>
                          </span>
                          <span>₹{item.price}</span>
                        </li>
                      ))}
                  </ul>

                  <div className="text-end">
                    <strong>Total Paid: ₹{order.totalAmount}</strong>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Orders;
