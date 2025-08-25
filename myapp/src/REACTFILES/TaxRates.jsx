// Import React for component creation
import React from "react";

// TaxRates component - displays static content for tax rates information
const TaxRates = () => {
  return (
    // Main container with Bootstrap styling
    <div className="container mt-5">
      {/* Page header with Bootstrap classes */}
      <div className="row justify-content-center">
        <div className="col-lg-8 text-center">
          {/* Page title with Bootstrap display classes */}
          <h1 className="display-4 text-primary mb-4">
            📊 Tax Rates
          </h1>
          
          {/* Main content card */}
          <div className="card shadow-lg border-0">
            <div className="card-body p-5">
              {/* Static content as required by lab assignment */}
              <h2 className="card-title text-success mb-4">
                Tax Rates Information
              </h2>
              
              {/* Description text */}
              <p className="card-text lead text-muted mb-4">
                This page provides comprehensive information about current tax rates,
                tax slabs, and other important tax-related information for taxpayers.
              </p>
              
              {/* Information section */}
              <div className="row mt-4">
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-primary mb-2">📊</div>
                    <h5>Current Rates</h5>
                    <small className="text-muted">Latest tax rates</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-success mb-2">📋</div>
                    <h5>Tax Slabs</h5>
                    <small className="text-muted">Income tax slabs</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-info mb-2">📅</div>
                    <h5>Updates</h5>
                    <small className="text-muted">Recent changes</small>
                  </div>
                </div>
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

export default TaxRates;
