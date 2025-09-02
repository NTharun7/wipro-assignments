import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

// Common Components
import Navbar from "./components/Navbar";

import ProtectedRoute from "./components/ProtectedRoute";

// Customer Pages
import Home from "./pages/Home";
import ProductList from "./pages/ProductList";
import ProductDetails from "./pages/ProductDetails";
import Cart from "./pages/Cart";
import Checkout from "./pages/Checkout";
import Orders from "./pages/Orders";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profile from "./pages/Profile";   // ✅ Added Profile page

// Admin Pages
import AdminDashboard from "./admin/AdminDashboard";
import ProductManagement from "./admin/ProductManagement";
import ProductForm from "./admin/ProductForm";
import CategoryManagement from "./admin/CategoryManagement";
import UserManagement from "./admin/UserManagement";
import OrderManagement from "./admin/OrderManagement";
import Reports from "./admin/Reports";

function App() {
  return (
    <div className="app-container">
      <Router>
        <Navbar />
        <div className="main-content container mt-4">
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<Home />} />
            <Route path="/products" element={<ProductList />} />
            <Route path="/products/:id" element={<ProductDetails />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* Customer-only Routes */}
            <Route
              path="/cart"
              element={
                <ProtectedRoute roleRequired="CUSTOMER">
                  <Cart />
                </ProtectedRoute>
              }
            />
            <Route
              path="/checkout"
              element={
                <ProtectedRoute roleRequired="CUSTOMER">
                  <Checkout />
                </ProtectedRoute>
              }
            />
            <Route
              path="/orders"
              element={
                <ProtectedRoute roleRequired="CUSTOMER">
                  <Orders />
                </ProtectedRoute>
              }
            />
            <Route
              path="/profile"   // ✅ New Route
              element={
                <ProtectedRoute roleRequired="CUSTOMER">
                  <Profile />
                </ProtectedRoute>
              }
            />

            {/* Admin-only Routes */}
            <Route
              path="/admin"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <AdminDashboard />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/products"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <ProductManagement />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/products/new"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <ProductForm />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/products/edit/:id"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <ProductForm />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/categories"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <CategoryManagement />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/users"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <UserManagement />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/orders"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <OrderManagement />
                </ProtectedRoute>
              }
            />
            <Route
              path="/admin/reports"
              element={
                <ProtectedRoute roleRequired="ADMIN">
                  <Reports />
                </ProtectedRoute>
              }
            />
          </Routes>
        </div>
       
      </Router>
    </div>
  );
}

export default App;
