// Import necessary React components and React Router
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

// Import all page components
import IncomeTaxNavbar from "./IncomeTaxNavbar";
import Home from "./Home";
import AddTaxpayer from "./AddTaxpayer";
import TaxpayerList from "./TaxpayerList";
import CalculateTax from "./CalculateTax";
import TaxRates from "./TaxRates";
import Contact from "./Contact";
import About from "./About";
import FAQ from "./FAQ";
import NotFound from "./NotFound";

// Main Income Tax Department application with routing
const IncomeTaxApp = () => {
  return (
    // Router wrapper for navigation
    <Router>
      {/* Bootstrap Navbar component */}
      <IncomeTaxNavbar />
      
      {/* Main content area with routing */}
      <main className="min-vh-100 bg-light">
        {/* Define all routes as specified in lab assignment */}
        <Routes>
          {/* Home page route - default route */}
          <Route path="/" element={<Home />} />
          
          {/* Add Taxpayer page route */}
          <Route path="/add-taxpayer" element={<AddTaxpayer />} />
          
          {/* Taxpayer List page route */}
          <Route path="/taxpayer-list" element={<TaxpayerList />} />
          
          {/* Calculate Tax page route */}
          <Route path="/calculate-tax" element={<CalculateTax />} />
          
          {/* Tax Rates page route */}
          <Route path="/tax-rates" element={<TaxRates />} />
          
          {/* Contact page route */}
          <Route path="/contact" element={<Contact />} />
          
          {/* About page route */}
          <Route path="/about" element={<About />} />
          
          {/* FAQ page route */}
          <Route path="/faq" element={<FAQ />} />
          
          {/* Catch-all route for unknown paths - 404 page */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
      
      {/* Footer with Bootstrap styling */}
      <footer className="bg-dark text-light py-4 mt-5">
        <div className="container text-center">
          <p className="mb-0">
            © 2024 Income Tax Department. All rights reserved.
          </p>
          <small className="text-muted">
            Built with React Router v6+ and Bootstrap 5
          </small>
        </div>
      </footer>
    </Router>
  );
};

export default IncomeTaxApp;
