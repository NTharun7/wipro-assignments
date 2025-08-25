// Import React for component creation
import React from "react";
import { Link } from "react-router-dom";

// NotFound component - displays 404 error for unknown routes
const NotFound = () => {
  return (
    // Main container with Bootstrap styling
    <div className="container mt-5">
      {/* Page header with Bootstrap classes */}
      <div className="row justify-content-center">
        <div className="col-lg-8 text-center">
          {/* 404 Error display with Bootstrap classes */}
          <div className="display-1 text-danger mb-4">
            404
          </div>
          
          {/* Main content card */}
          <div className="card shadow-lg border-0">
            <div className="card-body p-5">
              {/* Static content as required by lab assignment */}
              <h2 className="card-title text-danger mb-4">
                404 - Page Not Found
              </h2>
              
              {/* Description text */}
              <p className="card-text lead text-muted mb-4">
                Oops! The page you're looking for doesn't exist.
                It might have been moved, deleted, or you entered the wrong URL.
              </p>
              
              {/* Information section */}
              <div className="row mt-4">
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-primary mb-2">🔍</div>
                    <h5>Check URL</h5>
                    <small className="text-muted">Verify the address</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-success mb-2">🏠</div>
                    <h5>Go Home</h5>
                    <small className="text-muted">Return to homepage</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-info mb-2">📞</div>
                    <h5>Contact Support</h5>
                    <small className="text-muted">Get help</small>
                  </div>
                </div>
              </div>
              
              {/* Navigation buttons */}
              <div className="mt-4">
                <Link to="/" className="btn btn-primary me-3">
                  🏠 Go to Home
                </Link>
                <Link to="/contact" className="btn btn-outline-secondary">
                  📞 Contact Support
                </Link>
              </div>
              
              {/* Note about lab requirements */}
              <div className="alert alert-info mt-4" role="alert">
                <strong>Note:</strong> This is a static page component as required by the lab assignment. 
                No forms or calculations are implemented as per the specifications.
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NotFound;
