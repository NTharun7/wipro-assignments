import React, { useMemo, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

// ========================================
// 🎯 LAB ASSIGNMENT: Chess Tournament Registration Form
// ========================================
// Objective: Create a React app to register players for chess tournament
// Requirements: Use Formik, Yup, Bootstrap, display data in table, log to console
// ========================================

/**
 * ✅ HELPER FUNCTION: Calculate age from date of birth
 * This function takes a date string and returns the calculated age
 * Used in Yup validation to ensure age is between 5 and 90 years
 */
const calcAge = (dob) => {
  if (!dob) return null;
  const d = new Date(dob);
  const now = new Date();
  let age = now.getFullYear() - d.getFullYear();
  const m = now.getMonth() - d.getMonth();
  // Adjust age if birthday hasn't occurred yet this year
  if (m < 0 || (m === 0 && now.getDate() < d.getDate())) age--;
  return age;
};

/**
 * 🏆 MAIN COMPONENT: ChessTournamentForm
 * This component demonstrates:
 * - Formik for form management
 * - Yup for validation
 * - Bootstrap for styling
 * - State management for multiple submissions
 * - Conditional rendering based on form values
 */
export default function ChessTournamentForm() {
  // ========================================
  // 📊 STATE MANAGEMENT
  // ========================================
  // State to store all submitted player registrations for table display
  const [rows, setRows] = useState([]);

  // ========================================
  // 🎯 INITIAL FORM VALUES (using useMemo for performance)
  // ========================================
  // useMemo ensures these values are only created once, not on every render
  const initialValues = useMemo(
    () => ({
      playerName: "",           // Player's full name
      dob: "",                  // Date of birth
      gender: "",               // Male/Female/Other
      fideId: "",              // FIDE ID (8 digits)
      rating: "",               // Chess rating (100-3000)
      email: "",                // Email address
      mobile: "",               // Mobile number (10 digits, starts with 6-9)
      country: "",              // Country of residence
      category: "",             // U-12, U-18, or Open
      parentContact: "",        // Parent contact (required for U-12)
      paymentConfirmed: false,  // Payment confirmation checkbox
      terms: false,             // Terms & conditions acceptance
    }),
    [] // Empty dependency array means this runs only once
  );

  // ========================================
  // ✅ YUP VALIDATION SCHEMA
  // ========================================
  // This schema defines all validation rules for the form
  const validationSchema = Yup.object({
    // Player Name: Minimum 3 characters, required
    playerName: Yup.string()
      .min(3, "Minimum 3 characters")
      .required("Player name is required"),
    
    // Date of Birth: Required, age must be between 5 and 90 years
    dob: Yup.string()
      .required("Date of birth is required")
      .test("age-range", "Age must be between 5 and 90 years", (value) => {
        const age = calcAge(value);
        return age !== null && age >= 5 && age <= 90;
      }),
    
    // Gender: Required selection
    gender: Yup.string()
      .required("Select a gender"),
    
    // FIDE ID: Must be exactly 8 digits, required
    fideId: Yup.string()
      .matches(/^\d{8}$/g, "FIDE ID must be exactly 8 digits")
      .required("FIDE ID is required"),
    
    // Rating: Must be a number between 100 and 3000, required
    rating: Yup.number()
      .typeError("Rating must be a number")
      .integer("Rating must be an integer")
      .min(100, "Rating must be at least 100")
      .max(3000, "Rating cannot exceed 3000")
      .required("Rating is required"),
    
    // Email: Must be valid email format, required
    email: Yup.string()
      .email("Invalid email")
      .required("Email is required"),
    
    // Mobile: Must be 10 digits starting with 6-9, required
    mobile: Yup.string()
      .matches(/^[6-9][0-9]{9}$/g, "Enter 10 digits starting with 6-9")
      .required("Mobile number is required"),
    
    // Country: Required selection
    country: Yup.string()
      .required("Country is required"),
    
    // Category: Must be one of the three options, required
    category: Yup.string()
      .oneOf(["U-12", "U-18", "Open"], "Pick a category")
      .required("Category is required"),
    
    // Parent Contact: Conditional validation - required only for U-12
    parentContact: Yup.string().when("category", {
      is: (cat) => cat === "U-12", // If category is U-12
      then: (schema) => schema
        .matches(/^[6-9][0-9]{9}$/g, "Enter valid 10-digit number starting with 6-9")
        .required("Parent contact is required for U-12"),
      otherwise: (schema) => schema.notRequired(), // Not required for other categories
    }),
    
    // Payment Confirmation: Must be checked (true), required
    paymentConfirmed: Yup.boolean()
      .oneOf([true], "Payment confirmation is required"),
    
    // Terms & Conditions: Must be checked (true), required
    terms: Yup.boolean()
      .oneOf([true], "You must accept Terms & Conditions"),
  });

  // ========================================
  // 🚀 FORM SUBMISSION HANDLER
  // ========================================
  // This function runs when the form is successfully submitted
  const onSubmit = (values, helpers) => {
    // 1. Log the registration data to console (requirement)
    console.log("Registration Data:", values);
    
    // 2. Add new player to the table (requirement: multiple entries)
    setRows((prev) => [...prev, { id: prev.length + 1, ...values }]);
    
    // 3. Reset the form after successful submission (requirement)
    helpers.resetForm();
  };

  // ========================================
  // 🎨 RENDER METHOD
  // ========================================
  return (
    <div className="container py-4">
      {/* ======================================== */}
      {/* 📋 FORM HEADER */}
      {/* ======================================== */}
      <h2 className="text-center mb-3">Chess Tournament Registration Form</h2>

      {/* ======================================== */}
      {/* 🎯 FORM CONTAINER WITH BOOTSTRAP CARD */}
      {/* ======================================== */}
      <div className="card shadow-sm">
        <div className="card-body">
          {/* ======================================== */}
          {/* 🔧 FORMIK FORM WRAPPER */}
          {/* ======================================== */}
          <Formik 
            initialValues={initialValues} 
            validationSchema={validationSchema} 
            onSubmit={onSubmit}
          >
            {/* Formik render prop gives us access to form state */}
            {({ values }) => (
              <Form noValidate>
                {/* ======================================== */}
                {/* 👤 PLAYER NAME FIELD */}
                {/* ======================================== */}
                <div className="mb-3">
                  <label className="form-label">Player Name</label>
                  <Field 
                    name="playerName" 
                    className="form-control" 
                    placeholder="e.g., Arjun Mehta" 
                  />
                  {/* Error message display using Formik's ErrorMessage component */}
                  <div className="text-danger">
                    <ErrorMessage name="playerName" />
                  </div>
                </div>

                {/* ======================================== */}
                {/* 📅 DATE OF BIRTH FIELD */}
                {/* ======================================== */}
                <div className="mb-3">
                  <label className="form-label">Date of Birth</label>
                  <Field 
                    name="dob" 
                    type="date" 
                    className="form-control" 
                  />
                  <div className="text-danger">
                    <ErrorMessage name="dob" />
                  </div>
                </div>

                {/* ======================================== */}
                {/* 🚻 GENDER SELECTION (Radio Buttons) */}
                {/* ======================================== */}
                <div className="mb-3">
                  <label className="form-label d-block">Gender</label>
                  <div className="form-check form-check-inline">
                    <Field 
                      type="radio" 
                      name="gender" 
                      value="Male" 
                      id="g-male" 
                      className="form-check-input" 
                    />
                    <label htmlFor="g-male" className="form-check-label">Male</label>
                  </div>
                  <div className="form-check form-check-inline">
                    <Field 
                      type="radio" 
                      name="gender" 
                      value="Female" 
                      id="g-female" 
                      className="form-check-input" 
                    />
                    <label htmlFor="g-female" className="form-check-label">Female</label>
                  </div>
                  <div className="form-check form-check-inline">
                    <Field 
                      type="radio" 
                      name="gender" 
                      value="Other" 
                      id="g-other" 
                      className="form-check-input" 
                    />
                    <label htmlFor="g-other" className="form-check-label">Other</label>
                  </div>
                  <div className="text-danger">
                    <ErrorMessage name="gender" />
                  </div>
                </div>

                {/* ======================================== */}
                {/* 🆔 FIDE ID & RATING (Side by Side) */}
                {/* ======================================== */}
                <div className="row g-3 mb-3">
                  <div className="col-md-6">
                    <label className="form-label">FIDE ID (8 digits)</label>
                    <Field 
                      name="fideId" 
                      className="form-control" 
                      placeholder="12345678" 
                    />
                    <div className="text-danger">
                      <ErrorMessage name="fideId" />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <label className="form-label">Rating (100-3000)</label>
                    <Field 
                      name="rating" 
                      className="form-control" 
                      placeholder="e.g., 1500" 
                    />
                    <div className="text-danger">
                      <ErrorMessage name="rating" />
                    </div>
                  </div>
                </div>

                {/* ======================================== */}
                {/* 📧 EMAIL & MOBILE (Side by Side) */}
                {/* ======================================== */}
                <div className="row g-3 mb-3">
                  <div className="col-md-6">
                    <label className="form-label">Email</label>
                    <Field 
                      name="email" 
                      type="email" 
                      className="form-control" 
                      placeholder="name@example.com" 
                    />
                    <div className="text-danger">
                      <ErrorMessage name="email" />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <label className="form-label">Mobile</label>
                    <Field 
                      name="mobile" 
                      className="form-control" 
                      placeholder="10-digit number" 
                    />
                    <div className="text-danger">
                      <ErrorMessage name="mobile" />
                    </div>
                  </div>
                </div>

                {/* ======================================== */}
                {/* 🌍 COUNTRY & CATEGORY (Side by Side) */}
                {/* ======================================== */}
                <div className="row g-3 mb-3">
                  <div className="col-md-6">
                    <label className="form-label">Country</label>
                    <Field as="select" name="country" className="form-select">
                      <option value="">-- Select Country --</option>
                      <option>India</option>
                      <option>USA</option>
                      <option>UK</option>
                      <option>Canada</option>
                      <option>Australia</option>
                      <option>Other</option>
                    </Field>
                    <div className="text-danger">
                      <ErrorMessage name="country" />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <label className="form-label">Category</label>
                    <Field as="select" name="category" className="form-select">
                      <option value="">-- Select Category --</option>
                      <option value="U-12">Under 12</option>
                      <option value="U-18">Under 18</option>
                      <option value="Open">Open</option>
                    </Field>
                    <div className="text-danger">
                      <ErrorMessage name="category" />
                    </div>
                  </div>
                </div>

                {/* ======================================== */}
                {/* 👨‍👩‍👧‍👦 PARENT CONTACT (Conditional Field) */}
                {/* ======================================== */}
                {/* This field only appears when category is U-12 */}
                {values.category === "U-12" && (
                  <div className="mb-3">
                    <label className="form-label">Parent Contact (required for U-12)</label>
                    <Field 
                      name="parentContact" 
                      className="form-control" 
                      placeholder="10-digit number" 
                    />
                    <div className="text-danger">
                      <ErrorMessage name="parentContact" />
                    </div>
                  </div>
                )}

                {/* ======================================== */}
                {/* ✅ CHECKBOXES FOR PAYMENT & TERMS */}
                {/* ======================================== */}
                <div className="form-check mb-2">
                  <Field 
                    type="checkbox" 
                    name="paymentConfirmed" 
                    id="paymentConfirmed" 
                    className="form-check-input" 
                  />
                  <label htmlFor="paymentConfirmed" className="form-check-label">
                    Payment Confirmation
                  </label>
                  <div className="text-danger">
                    <ErrorMessage name="paymentConfirmed" />
                  </div>
                </div>

                <div className="form-check mb-3">
                  <Field 
                    type="checkbox" 
                    name="terms" 
                    id="terms" 
                    className="form-check-input" 
                  />
                  <label htmlFor="terms" className="form-check-label">
                    I agree to Terms & Conditions
                  </label>
                  <div className="text-danger">
                    <ErrorMessage name="terms" />
                  </div>
                </div>

                {/* ======================================== */}
                {/* 🚀 SUBMIT BUTTON */}
                {/* ======================================== */}
                <div className="d-grid">
                  <button type="submit" className="btn btn-primary">
                    Submit Registration
                  </button>
                </div>
              </Form>
            )}
          </Formik>
        </div>
      </div>

      {/* ======================================== */}
      {/* 📊 SUBMITTED PLAYERS TABLE */}
      {/* ======================================== */}
      {/* This table displays all submitted registrations */}
      <div className="mt-4">
        <h5>Submitted Players</h5>
        <div className="table-responsive">
          <table className="table table-bordered table-striped align-middle">
            {/* Table Header */}
            <thead className="table-light">
              <tr>
                <th>#</th>
                <th>Player Name</th>
                <th>DOB</th>
                <th>Gender</th>
                <th>FIDE ID</th>
                <th>Rating</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Country</th>
                <th>Category</th>
                <th>Parent Contact</th>
                <th>Payment</th>
                <th>Terms</th>
              </tr>
            </thead>
            {/* Table Body */}
            <tbody>
              {/* Show message if no submissions yet */}
              {rows.length === 0 ? (
                <tr>
                  <td colSpan={13} className="text-center">
                    No submissions yet.
                  </td>
                </tr>
              ) : (
                /* Map through all submitted rows and display them */
                rows.map((r, idx) => (
                  <tr key={r.id}>
                    <td>{idx + 1}</td>
                    <td>{r.playerName}</td>
                    <td>{r.dob}</td>
                    <td>{r.gender}</td>
                    <td>{r.fideId}</td>
                    <td>{r.rating}</td>
                    <td>{r.email}</td>
                    <td>{r.mobile}</td>
                    <td>{r.country}</td>
                    <td>{r.category}</td>
                    <td>{r.parentContact || "-"}</td>
                    <td>{r.paymentConfirmed ? "Yes" : "No"}</td>
                    <td>{r.terms ? "Accepted" : "No"}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
