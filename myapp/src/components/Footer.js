import React from "react";

const Footer = () => {
  return (
    <footer className="bg-dark text-light text-center py-3">
      <div className="container">
        <small>
          © {new Date().getFullYear()} Online Shopping | All Rights Reserved
        </small>
      </div>
    </footer>
  );
};

export default Footer;
