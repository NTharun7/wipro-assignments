// Import React for component creation
import React from "react";

// AddTaxpayer component - displays static content for adding taxpayers
const AddTaxpayer = () => {
  return (
    // Main container with Bootstrap styling
    <div className="container mt-5">
      {/* Page header with Bootstrap classes */}
      <div className="row justify-content-center">
        <div className="col-lg-8 text-center">
          {/* Page title with Bootstrap display classes */}
          <h1 className="display-4 text-primary mb-4">
            ➕ Add Taxpayer
          </h1>
          
          {/* Main content card */}
          <div className="card shadow-lg border-0">
            <div className="card-body p-5">
              {/* Static content as required by lab assignment */}
              <h2 className="card-title text-success mb-4">
                Add Taxpayer Page
              </h2>
              
              {/* Description text */}
              <p className="card-text lead text-muted mb-4">
                This page is dedicated to taxpayer registration and management services.
                Here you can add new taxpayers to the system and manage their information.
              </p>
              
              {/* Information section */}
              <div className="row mt-4">
                <div className="col-md-6 mb-3">
                  <div className="text-center">
                    <div className="h1 text-primary mb-2">👤</div>
                    <h5>Taxpayer Registration</h5>
                    <small className="text-muted">Register new taxpayers</small>
                  </div>
                </div>
                
                <div className="col-md-6 mb-3">
                  <div className="text-center">
                    <div className="h1 text-success mb-2">📝</div>
                    <h5>Information Management</h5>
                    <small className="text-muted">Manage taxpayer details</small>
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

export default AddTaxpayer;
