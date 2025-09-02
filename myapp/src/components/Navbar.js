import React, { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import { FaUserCircle } from "react-icons/fa";

const Navbar = () => {
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        {/* Brand */}
        <Link className="navbar-brand" to="/">
          🛒 Online Shopping
        </Link>

        {/* Toggle button for mobile */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Navbar Links */}
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            {/* Products visible only when logged in */}
            {user && (
              <li className="nav-item">
                <Link className="nav-link" to="/products">
                  Products
                </Link>
              </li>
            )}

            {/* Customer-only links */}
            {user && user.role === "CUSTOMER" && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/cart">
                    Cart
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/orders">
                    Orders
                  </Link>
                </li>
              </>
            )}

            {/* Admin-only link */}
            {user && user.role === "ADMIN" && (
              <li className="nav-item">
                <Link className="nav-link" to="/admin">
                  Admin Dashboard
                </Link>
              </li>
            )}
          </ul>

          {/* Right Side: Profile + Auth */}
          <ul className="navbar-nav ms-auto">
            {!user ? (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    Login
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/register">
                    Register
                  </Link>
                </li>
              </>
            ) : (
              <li className="nav-item dropdown">
                <a
                  href="#!"
                  className="nav-link dropdown-toggle d-flex align-items-center"
                  id="profileDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <FaUserCircle size={22} className="me-1" />
                  {user.name}
                </a>
                <ul
                  className="dropdown-menu dropdown-menu-end"
                  aria-labelledby="profileDropdown"
                >
                  {user.role === "CUSTOMER" && (
                    <>
                      <li>
                        <Link className="dropdown-item" to="/profile">
                          My Profile
                        </Link>
                      </li>
                      <li>
                        <Link className="dropdown-item" to="/orders">
                          My Orders
                        </Link>
                      </li>
                      <li>
                        <hr className="dropdown-divider" />
                      </li>
                    </>
                  )}
                  {/* Always show Logout */}
                  <li>
                    <button className="dropdown-item" onClick={handleLogout}>
                      Logout
                    </button>
                  </li>
                </ul>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
