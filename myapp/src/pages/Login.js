import React, { useState, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import { loginUser } from "../api/authApi";
import { AuthContext } from "../context/AuthContext";
import "bootstrap-icons/font/bootstrap-icons.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const data = await loginUser(email, password);
      login(data);

      if (data.role === "ADMIN") {
        navigate("/admin");
      } else {
        navigate("/");
      }
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="container my-5" style={{ maxWidth: "400px" }}>
      <div className="card shadow-sm border-0">
        <div className="card-body p-4">
          <h2 className="text-center mb-4">
            <i className="bi bi-lock-fill me-2"></i> Login
          </h2>

          {error && <div className="alert alert-danger">{error}</div>}

          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label fw-semibold">Email</label>
              <input
                type="email"
                className="form-control"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                placeholder="Enter your email"
              />
            </div>

            <div className="mb-3">
              <label className="form-label fw-semibold">Password</label>
              <input
                type="password"
                className="form-control"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                placeholder="Enter your password"
              />
            </div>

            <button type="submit" className="btn btn-dark w-100 fw-semibold">
              <i className="bi bi-box-arrow-in-right me-1"></i> Login
            </button>
          </form>

          <div className="text-center mt-3">
            <small>
              Don’t have an account?{" "}
              <Link to="/register" className="text-decoration-none">
                Register
              </Link>
            </small>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
