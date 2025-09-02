import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

const ProtectedRoute = ({ children, roleRequired }) => {
  const { user } = useContext(AuthContext);

  if (!user) {
    // If not logged in → redirect to login
    return <Navigate to="/login" replace />;
  }

  if (roleRequired && user.role !== roleRequired) {
    // If a role is required and user doesn't match → redirect home
    return <Navigate to="/" replace />;
  }

  // Authorized → render child page
  return children;
};

export default ProtectedRoute;
