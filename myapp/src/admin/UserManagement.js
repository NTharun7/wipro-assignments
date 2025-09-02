import React, { useEffect, useState, useContext } from "react";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css";

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
      return;
    }
    fetchUsers();
  }, [user, navigate]);

  const fetchUsers = () => {
    API.get("/users")
      .then((res) => setUsers(res.data))
      .catch(() => setError("Failed to fetch users"));
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this user?")) return;
    try {
      await API.delete(`/users/${id}`);
      setUsers(users.filter((u) => u.id !== id));
    } catch {
      setError("Failed to delete user");
    }
  };

  const getRoleBadge = (role) => {
    if (role === "ADMIN") return <span className="badge bg-danger">Admin</span>;
    return <span className="badge bg-primary">Customer</span>;
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">
        <i className="bi bi-people-fill me-2"></i> Manage Users
      </h2>

      {error && <div className="alert alert-danger mt-3">{error}</div>}

      <div className="card shadow-sm border-0">
        <div className="card-body">
          <div className="table-responsive">
            <table className="table table-hover table-striped align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Role</th>
                  <th>Address</th>
                  <th>Phone</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {users.length > 0 ? (
                  users.map((u) => (
                    <tr key={u.id}>
                      <td>{u.id}</td>
                      <td>{u.name}</td>
                      <td>{u.email}</td>
                      <td>{getRoleBadge(u.role)}</td>
                      <td>{u.address}</td>
                      <td>{u.phone}</td>
                      <td>
                        {u.role !== "ADMIN" && (
                          <button
                            className="btn btn-sm btn-danger d-flex align-items-center"
                            onClick={() => handleDelete(u.id)}
                          >
                            <i className="bi bi-trash me-1"></i> Delete
                          </button>
                        )}
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="7" className="text-center">
                      <div className="p-3 text-muted">
                        <i className="bi bi-exclamation-circle me-2"></i>
                        No users found
                      </div>
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserManagement;
