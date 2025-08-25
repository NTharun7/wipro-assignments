// Import React for component creation
import React from "react";

// Home component - displays welcome message for Income Tax Department Portal
const Home = () => {
  return (
    // Main container with Bootstrap styling
    <div className="container mt-5">
      {/* Page header with Bootstrap classes */}
      <div className="row justify-content-center">
        <div className="col-lg-8 text-center">
          {/* Main title with Bootstrap display classes */}
          <h1 className="display-4 text-primary mb-4">
            🏛️ Income Tax Department
          </h1>
          
          {/* Welcome message with Bootstrap typography */}
          <div className="card shadow-lg border-0">
            <div className="card-body p-5">
              <h2 className="card-title text-success mb-4">
                Welcome to the Income Tax Department Portal
              </h2>
              
              {/* Description text */}
              <p className="card-text lead text-muted mb-4">
                Your trusted platform for all income tax related services and information.
                Navigate through our comprehensive portal to access various tax services.
              </p>
                
              {/* Feature highlights */}
              <div className="row mt-4">
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-primary mb-2">📋</div>
                    <h5>Taxpayer Management</h5>
                    <small className="text-muted">Add and manage taxpayer information</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-success mb-2">🧮</div>
                    <h5>Tax Calculations</h5>
                    <small className="text-muted">Calculate taxes with our tools</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-info mb-2">📊</div>
                    <h5>Tax Rates</h5>
                    <small className="text-muted">View current tax rates and information</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    );
};

export default Home;