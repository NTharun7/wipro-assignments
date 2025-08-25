// Import React for component creation
import React from "react";

// Contact component - displays static content for contacting the department
const Contact = () => {
  return (
    // Main container with Bootstrap styling
    <div className="container mt-5">
      {/* Page header with Bootstrap classes */}
      <div className="row justify-content-center">
        <div className="col-lg-8 text-center">
          {/* Page title with Bootstrap display classes */}
          <h1 className="display-4 text-primary mb-4">
            📞 Contact
          </h1>
          
          {/* Main content card */}
          <div className="card shadow-lg border-0">
            <div className="card-body p-5">
              {/* Static content as required by lab assignment */}
              <h2 className="card-title text-success mb-4">
                Contact the Income Tax Department
              </h2>
              
              {/* Description text */}
              <p className="card-text lead text-muted mb-4">
                Get in touch with our department for any queries, support, or information.
                We are here to help you with all your income tax related concerns.
              </p>
              
              {/* Information section */}
              <div className="row mt-4">
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-primary mb-2">📞</div>
                    <h5>Phone Support</h5>
                    <small className="text-muted">Call our helpline</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-success mb-2">✉️</div>
                    <h5>Email Support</h5>
                    <small className="text-muted">Send us an email</small>
                  </div>
                </div>
                
                <div className="col-md-4 mb-3">
                  <div className="text-center">
                    <div className="h1 text-info mb-2">🏢</div>
                    <h5>Office Address</h5>
                    <small className="text-muted">Visit our office</small>
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

export default Contact;
