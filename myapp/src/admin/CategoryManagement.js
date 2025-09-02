// import React, { useEffect, useState, useContext } from "react";
// import API from "../api/axiosConfig";
// import { AuthContext } from "../context/AuthContext";
// import { useNavigate } from "react-router-dom";
// import "bootstrap-icons/font/bootstrap-icons.css"; // icons

// const CategoryManagement = () => {
//   const [categories, setCategories] = useState([]);
//   const [form, setForm] = useState({ name: "", description: "" });
//   const [editingId, setEditingId] = useState(null);
//   const [error, setError] = useState("");
//   const { user } = useContext(AuthContext);
//   const navigate = useNavigate();

//   useEffect(() => {
//     if (!user || user.role !== "ADMIN") {
//       navigate("/");
//       return;
//     }
//     fetchCategories();
//   }, [user, navigate]);

//   const fetchCategories = () => {
//     API.get("/categories")
//       .then((res) => setCategories(res.data))
//       .catch(() => setError("Failed to fetch categories"));
//   };

//   const handleChange = (e) => {
//     setForm({ ...form, [e.target.name]: e.target.value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       if (editingId) {
//         await API.put(`/categories/${editingId}`, form);
//       } else {
//         await API.post("/categories", form);
//       }
//       setForm({ name: "", description: "" });
//       setEditingId(null);
//       fetchCategories();
//     } catch {
//       setError("Failed to save category");
//     }
//   };

//   const handleEdit = (category) => {
//     setForm({ name: category.name, description: category.description });
//     setEditingId(category.id);
//   };

//   return (
//     <div className="container mt-5">
//       <h2>📂 Manage Categories</h2>
//       {error && <div className="alert alert-danger mt-3">{error}</div>}

//       {/* Category Form */}
//       <div className="card shadow-sm border-0 mt-4">
//         <div className="card-body">
//           <h5 className="card-title mb-3">{editingId ? "Update Category" : "Add New Category"}</h5>
//           <form onSubmit={handleSubmit}>
//             <div className="row g-3">
//               <div className="col-md-4">
//                 <label className="form-label fw-semibold">Name</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   name="name"
//                   value={form.name}
//                   onChange={handleChange}
//                   placeholder="Enter category name"
//                   required
//                 />
//               </div>
//               <div className="col-md-5">
//                 <label className="form-label fw-semibold">Description</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   name="description"
//                   value={form.description}
//                   onChange={handleChange}
//                   placeholder="Enter description"
//                 />
//               </div>
//               <div className="col-md-3 d-flex align-items-end">
//                 <button type="submit" className="btn btn-dark w-100">
//                   {editingId ? "Update" : "Add"}
//                 </button>
//               </div>
//             </div>
//           </form>
//         </div>
//       </div>

//       {/* Category List */}
//       <div className="card shadow-sm border-0 mt-4">
//         <div className="card-body">
//           <h5 className="card-title mb-3">Category List</h5>
//           <div className="table-responsive">
//             <table className="table table-hover table-striped align-middle">
//               <thead className="table-dark">
//                 <tr>
//                   <th>ID</th>
//                   <th>Name</th>
//                   <th>Description</th>
//                   <th>Action</th>
//                 </tr>
//               </thead>
//               <tbody>
//                 {categories.length > 0 ? (
//                   categories.map((cat) => (
//                     <tr key={cat.id}>
//                       <td>{cat.id}</td>
//                       <td>{cat.name}</td>
//                       <td>{cat.description}</td>
//                       <td>
//                         <button
//                           className="btn btn-sm btn-warning d-flex align-items-center"
//                           onClick={() => handleEdit(cat)}
//                         >
//                           <i className="bi bi-pencil me-1"></i> Edit
//                         </button>
//                       </td>
//                     </tr>
//                   ))
//                 ) : (
//                   <tr>
//                     <td colSpan="4" className="text-center">
//                       No categories found
//                     </td>
//                   </tr>
//                 )}
//               </tbody>
//             </table>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default CategoryManagement;
import React, { useEffect, useState, useContext } from "react";
import API from "../api/axiosConfig";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css"; // icons

const CategoryManagement = () => {
  const [categories, setCategories] = useState([]);
  const [form, setForm] = useState({ name: "", description: "" });
  const [editingId, setEditingId] = useState(null);
  const [error, setError] = useState("");
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== "ADMIN") {
      navigate("/");
      return;
    }
    fetchCategories();
  }, [user, navigate]);

  const fetchCategories = () => {
    API.get("/categories")
      .then((res) => setCategories(res.data))
      .catch(() => setError("Failed to fetch categories"));
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await API.put(`/categories/${editingId}`, form);
      } else {
        await API.post("/categories", form);
      }
      setForm({ name: "", description: "" });
      setEditingId(null);
      fetchCategories();
    } catch {
      setError("Failed to save category");
    }
  };

  const handleEdit = (category) => {
    setForm({ name: category.name, description: category.description });
    setEditingId(category.id);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this category?")) return;
    try {
      await API.delete(`/categories/${id}`);
      fetchCategories();
    } catch {
      setError("Failed to delete category");
    }
  };

  return (
    <div className="container mt-5">
      <h2>📂 Manage Categories</h2>
      {error && <div className="alert alert-danger mt-3">{error}</div>}

      {/* Category Form */}
      <div className="card shadow-sm border-0 mt-4">
        <div className="card-body">
          <h5 className="card-title mb-3">{editingId ? "Update Category" : "Add New Category"}</h5>
          <form onSubmit={handleSubmit}>
            <div className="row g-3">
              <div className="col-md-4">
                <label className="form-label fw-semibold">Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="name"
                  value={form.name}
                  onChange={handleChange}
                  placeholder="Enter category name"
                  required
                />
              </div>
              <div className="col-md-5">
                <label className="form-label fw-semibold">Description</label>
                <input
                  type="text"
                  className="form-control"
                  name="description"
                  value={form.description}
                  onChange={handleChange}
                  placeholder="Enter description"
                />
              </div>
              <div className="col-md-3 d-flex align-items-end">
                <button type="submit" className="btn btn-dark w-100">
                  {editingId ? "Update" : "Add"}
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>

      {/* Category List */}
      <div className="card shadow-sm border-0 mt-4">
        <div className="card-body">
          <h5 className="card-title mb-3">Category List</h5>
          <div className="table-responsive">
            <table className="table table-hover table-striped align-middle">
              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Description</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {categories.length > 0 ? (
                  categories.map((cat) => (
                    <tr key={cat.id}>
                      <td>{cat.id}</td>
                      <td>{cat.name}</td>
                      <td>{cat.description}</td>
                      <td>
                        <div className="btn-group">
                          <button
                            className="btn btn-sm btn-warning"
                            onClick={() => handleEdit(cat)}
                          >
                            <i className="bi bi-pencil me-1"></i> Edit
                          </button>
                          <button
                            className="btn btn-sm btn-danger"
                            onClick={() => handleDelete(cat.id)}
                          >
                            <i className="bi bi-trash me-1"></i> Delete
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4" className="text-center">
                      No categories found
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

export default CategoryManagement;

