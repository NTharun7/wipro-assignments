import React, { useContext, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import "bootstrap-icons/font/bootstrap-icons.css"; // ✅ import bootstrap icons

const AdminDashboard = () => {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
    }
  }, [user, navigate]);

  return (
    <div className="container-fluid">
      <div className="row">
        {/* Sidebar */}
        <div className="col-md-3 col-lg-2 bg-dark text-light vh-100 p-3">
          <h4 className="mb-4">Admin Dashboard</h4>
          <ul className="nav flex-column">
            <li className="nav-item mb-2">
              <Link className="nav-link text-light d-flex align-items-center" to="/admin/products">
                <i className="bi bi-box-seam me-2"></i> Products
              </Link>
            </li>
            <li className="nav-item mb-2">
              <Link className="nav-link text-light d-flex align-items-center" to="/admin/categories">
                <i className="bi bi-tags me-2"></i> Categories
              </Link>
            </li>
            <li className="nav-item mb-2">
              <Link className="nav-link text-light d-flex align-items-center" to="/admin/orders">
                <i className="bi bi-cart-check me-2"></i> Orders
              </Link>
            </li>
            <li className="nav-item mb-2">
              <Link className="nav-link text-light d-flex align-items-center" to="/admin/users">
                <i className="bi bi-people me-2"></i> Users
              </Link>
            </li>
            <li className="nav-item mb-2">
              <Link className="nav-link text-light d-flex align-items-center" to="/admin/reports">
                <i className="bi bi-bar-chart me-2"></i> Reports
              </Link>
            </li>
          </ul>
        </div>

        {/* Main Content */}
        <div className="col-md-9 col-lg-10 p-4">
          <h2>Welcome, {user?.name || "Admin"} 👨‍💼</h2>
          <p className="text-muted">Manage products, categories, orders, users, and reports here.</p>

          {/* Quick Access Cards */}
          <div className="row mt-4">
            <div className="col-md-3">
              <div className="card text-center shadow-sm">
                <div className="card-body">
                  <i className="bi bi-box-seam fs-2 text-dark"></i>
                  <h5 className="card-title mt-2">Products</h5>
                  <p className="card-text">Manage store products</p>
                  <Link to="/admin/products" className="btn btn-dark btn-sm">
                    Go
                  </Link>
                </div>
              </div>
            </div>

            <div className="col-md-3">
              <div className="card text-center shadow-sm">
                <div className="card-body">
                  <i className="bi bi-cart-check fs-2 text-dark"></i>
                  <h5 className="card-title mt-2">Orders</h5>
                  <p className="card-text">View and update orders</p>
                  <Link to="/admin/orders" className="btn btn-dark btn-sm">
                    Go
                  </Link>
                </div>
              </div>
            </div>

            <div className="col-md-3">
              <div className="card text-center shadow-sm">
                <div className="card-body">
                  <i className="bi bi-people fs-2 text-dark"></i>
                  <h5 className="card-title mt-2">Users</h5>
                  <p className="card-text">View all customers</p>
                  <Link to="/admin/users" className="btn btn-dark btn-sm">
                    Go
                  </Link>
                </div>
              </div>
            </div>

            <div className="col-md-3">
              <div className="card text-center shadow-sm">
                <div className="card-body">
                  <i className="bi bi-bar-chart fs-2 text-dark"></i>
                  <h5 className="card-title mt-2">Reports</h5>
                  <p className="card-text">Sales and stock reports</p>
                  <Link to="/admin/reports" className="btn btn-dark btn-sm">
                    Go
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> 
    </div>
  );
};

export default AdminDashboard;
