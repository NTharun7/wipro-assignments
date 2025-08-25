// Import necessary React hooks and React Router components
import React, { useState } from "react";
import { NavLink, useLocation } from "react-router-dom";

// Bootstrap Navbar component for Income Tax Department
const IncomeTaxNavbar = () => {
  // State to manage mobile navbar collapse
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);
  
  // Get current location for active link highlighting
  const location = useLocation();

  // Handle navbar toggle for mobile devices
  const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);

  // Check if a link is active based on current location
  const isActive = (path) => {
    return location.pathname === path;
  };

  return (
    // Bootstrap 5 Navbar with responsive design
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container">
        {/* Brand/Logo */}
        <NavLink className="navbar-brand fw-bold" to="/">
          🏛️ Income Tax Department
        </NavLink>

        {/* Mobile toggle button */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded={!isNavCollapsed ? true : false}
          aria-label="Toggle navigation"
          onClick={handleNavCollapse}
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Collapsible navigation menu */}
        <div className={`${isNavCollapsed ? 'collapse' : ''} navbar-collapse`} id="navbarNav">
          {/* Navigation links - left side */}
          <ul className="navbar-nav me-auto">
            {/* Home page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/') ? 'active fw-bold' : ''}`}
                to="/"
                onClick={() => setIsNavCollapsed(true)}
              >
                🏠 Home
              </NavLink>
            </li>

            {/* Add Taxpayer page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/add-taxpayer') ? 'active fw-bold' : ''}`}
                to="/add-taxpayer"
                onClick={() => setIsNavCollapsed(true)}
              >
                ➕ Add Taxpayer
              </NavLink>
            </li>

            {/* Taxpayer List page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/taxpayer-list') ? 'active fw-bold' : ''}`}
                to="/taxpayer-list"
                onClick={() => setIsNavCollapsed(true)}
              >
                📋 Taxpayer List
              </NavLink>
            </li>

            {/* Calculate Tax page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/calculate-tax') ? 'active fw-bold' : ''}`}
                to="/calculate-tax"
                onClick={() => setIsNavCollapsed(true)}
              >
                🧮 Calculate Tax
              </NavLink>
            </li>

            {/* Tax Rates page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/tax-rates') ? 'active fw-bold' : ''}`}
                to="/tax-rates"
                onClick={() => setIsNavCollapsed(true)}
              >
                📊 Tax Rates
              </NavLink>
            </li>
          </ul>

          {/* Navigation links - right side */}
          <ul className="navbar-nav">
            {/* Contact page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/contact') ? 'active fw-bold' : ''}`}
                to="/contact"
                onClick={() => setIsNavCollapsed(true)}
              >
                📞 Contact
              </NavLink>
            </li>

            {/* About page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/about') ? 'active fw-bold' : ''}`}
                to="/about"
                onClick={() => setIsNavCollapsed(true)}
              >
                ℹ️ About
              </NavLink>
            </li>

            {/* FAQ page link */}
            <li className="nav-item">
              <NavLink 
                className={`nav-link ${isActive('/faq') ? 'active fw-bold' : ''}`}
                to="/faq"
                onClick={() => setIsNavCollapsed(true)}
              >
                ❓ FAQ
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default IncomeTaxNavbar;
