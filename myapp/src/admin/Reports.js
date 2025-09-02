import React, { useEffect, useState, useContext } from "react";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css";

const Reports = () => {
  const [ordersReport, setOrdersReport] = useState([]);
  const [paymentsReport, setPaymentsReport] = useState([]);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
      return;
    }
    fetchReports();
  }, [user, navigate]);

  const fetchReports = async () => {
    try {
      const ordersRes = await API.get("/reports/orders");
      const paymentsRes = await API.get("/reports/payments");
      setOrdersReport(ordersRes.data);
      setPaymentsReport(paymentsRes.data);
    } catch {
      setError("Failed to fetch reports. Please try again later.");
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
      case "SUCCESS":
        return <span className="badge bg-success">Success</span>;
      case "FAILED":
        return <span className="badge bg-danger">Failed</span>;
      default:
        return <span className="badge bg-secondary">{status}</span>;
    }
  };

  return (
    <div className="container mt-5">
      <h2>📊 Reports</h2>
      {error && <div className="alert alert-danger mt-3">{error}</div>}

      {/* Orders Report */}
      <div className="card shadow-sm border-0 mt-4">
        <div className="card-body">
          <h4 className="card-title mb-3">📦 Orders Report</h4>
          <div className="table-responsive">
            <table className="table table-hover table-striped align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>Order ID</th>
                  <th>User</th>
                  <th>Total Amount (₹)</th>
                  <th>Status</th>
                  <th>Date</th>
                </tr>
              </thead>
              <tbody>
                {ordersReport.length > 0 ? (
                  ordersReport.map((order) => (
                    <tr key={order.id}>
                      <td>#{order.id}</td>
                      <td>{order.user ? order.user.name : "Unknown"}</td>
                      <td><strong>₹{order.totalAmount}</strong></td>
                      <td>{getStatusBadge(order.status)}</td>
                      <td>{new Date(order.orderDate).toLocaleString()}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="5" className="text-center">
                      <div className="p-3 text-muted">
                        <i className="bi bi-exclamation-circle me-2"></i>
                        No orders data
                      </div>
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      {/* Payments Report */}
      <div className="card shadow-sm border-0 mt-5">
        <div className="card-body">
          <h4 className="card-title mb-3">💳 Payments Report</h4>
          <div className="table-responsive">
            <table className="table table-hover table-striped align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>Payment ID</th>
                  <th>Order ID</th>
                  <th>Status</th>
                  <th>Date</th>
                  <th>Transaction ID</th>
                </tr>
              </thead>
              <tbody>
                {paymentsReport.length > 0 ? (
                  paymentsReport.map((pay) => (
                    <tr key={pay.id}>
                      <td>#{pay.id}</td>
                      <td>{pay.order ? pay.order.id : "N/A"}</td>
                      <td>{getStatusBadge(pay.paymentStatus)}</td>
                      <td>{new Date(pay.paymentDate).toLocaleString()}</td>
                      <td>{pay.transactionId || "-"}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="5" className="text-center">
                      <div className="p-3 text-muted">
                        <i className="bi bi-exclamation-circle me-2"></i>
                        No payments data
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

export default Reports;
