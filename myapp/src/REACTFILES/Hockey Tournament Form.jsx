import React, { useState } from "react";
import { Formik, Form, Field, FieldArray, ErrorMessage } from "formik";
import * as Yup from "yup";

// ========================================
// 🏒 LAB ASSIGNMENT: Hockey Tournament Registration Form
// ========================================
// Objective: Create a React app to register players for hockey tournament
// Requirements: Use Formik, Yup, Bootstrap, display data in table, log to console
// ========================================

/**
 * ✅ HELPER FUNCTION: Calculate age from date of birth
 * This function takes a date string and returns the calculated age
 * Used in Yup validation to ensure age is between 10 and 55 years
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
 * 🏆 MAIN COMPONENT: HockeyTournamentForm
 * This component demonstrates:
 * - Formik for form management
 * - Yup for validation with complex conditional rules
 * - Bootstrap for styling and responsive design
 * - Dynamic FieldArray for past teams
 * - Conditional rendering based on form values
 * - State management for multiple submissions
 */
const HockeyTournamentForm = () => {
  // ========================================
  // 📊 STATE MANAGEMENT
  // ========================================
  // State to store all submitted player registrations for table display
  const [records, setRecords] = useState([]);

  // ========================================
  // ✅ YUP VALIDATION SCHEMA
  // ========================================
  // This schema defines all validation rules for the form
  const validationSchema = Yup.object().shape({
    // ========================================
    // 👤 PLAYER DETAILS VALIDATION
    // ========================================
    
    // Player Name: 3-40 characters, alphabets and spaces only, required
    playerName: Yup.string()
      .matches(/^[A-Za-z ]+$/, "Alphabets and spaces only")
      .min(3, "Minimum 3 characters")
      .max(40, "Maximum 40 characters")
      .required("Player name is required"),
    
    // Jersey Number: 1-99, required
    jerseyNumber: Yup.number()
      .min(1, "Must be at least 1")
      .max(99, "Must be at most 99")
      .required("Jersey number is required"),
    
    // Position: Required selection from dropdown
    position: Yup.string()
      .required("Position is required"),
    
    // Stick Hand: Required selection from dropdown
    stickHand: Yup.string()
      .required("Stick hand is required"),
    
    // Date of Birth: Required, age must be between 10 and 55 years
    dateOfBirth: Yup.date()
      .required("Date of birth is required")
      .test("age", "Age must be between 10 and 55", (value) => {
        if (!value) return false;
        const age = calcAge(value);
        return age >= 10 && age <= 55;
      }),
    
    // Nationality: Required
    nationality: Yup.string()
      .required("Nationality is required"),
    
    // Email: Must be valid email format, required
    email: Yup.string()
      .email("Invalid email format")
      .required("Email is required"),
    
    // Phone: Must be valid Indian mobile number (10 digits, starts with 6-9), required
    phone: Yup.string()
      .matches(/^[6-9]\d{9}$/, "Must be a valid Indian mobile number")
      .required("Phone is required"),
    
    // Player ID: Must follow format HOCK-XXXX, required
    playerId: Yup.string()
      .matches(/^HOCK-\d{4}$/, "Format must be HOCK-XXXX")
      .required("Player ID is required"),
    
    // Guardian Name: Conditional validation - required only if age < 18
    guardianName: Yup.string().when("dateOfBirth", (dob, schema) => {
      if (!dob) return schema;
      const age = calcAge(dob);
      return age < 18 ? schema.required("Guardian name is required") : schema;
    }),

    // ========================================
    // 🏆 TEAM & EVENT INFORMATION VALIDATION
    // ========================================
    
    // Team Name: Required
    teamName: Yup.string()
      .required("Team name is required"),
    
    // League Level: Required selection from dropdown
    leagueLevel: Yup.string()
      .required("League level is required"),
    
    // Tournament Name: Required
    tournamentName: Yup.string()
      .required("Tournament name is required"),
    
    // Start Date: Required
    startDate: Yup.date()
      .required("Start date is required"),
    
    // End Date: Required, must be after start date
    endDate: Yup.date()
      .required("End date is required")
      .min(Yup.ref("startDate"), "End date must be after start date"),

    // ========================================
    // 👕 EQUIPMENT SIZE VALIDATION (Conditional)
    // ========================================
    
    // Jersey Size: Required if position is NOT "Goalie"
    jerseySize: Yup.string().when("position", {
      is: (pos) => pos !== "Goalie",
      then: (schema) => schema.required("Jersey size is required"),
      otherwise: (schema) => schema.notRequired(),
    }),
    
    // Pad Size: Required if position IS "Goalie"
    padSize: Yup.string().when("position", {
      is: "Goalie",
      then: (schema) => schema.required("Pad size is required"),
      otherwise: (schema) => schema.notRequired(),
    }),

    // ========================================
    // 🏥 MEDICAL & CONSENT VALIDATION
    // ========================================
    
    // Has Medical Condition: Boolean checkbox
    hasMedicalCondition: Yup.boolean(),
    
    // Medical Certificate Number: Required if hasMedicalCondition is true
    medicalCertNumber: Yup.string().when("hasMedicalCondition", {
      is: true,
      then: (schema) =>
        schema
          .matches(/^MED-\d{4}$/, "Format must be MED-XXXX")
          .required("Medical certificate number is required"),
    }),
    
    // Consent: Must be checked (true), required
    consent: Yup.boolean()
      .oneOf([true], "Consent must be checked"),

    // ========================================
    // 🏟️ PAST TEAMS VALIDATION (Dynamic FieldArray)
    // ========================================
    
    // Past Teams: Array of objects, maximum 3 entries
    pastTeams: Yup.array().of(
      Yup.object().shape({
        clubName: Yup.string()
          .min(2, "At least 2 characters")
          .max(30, "Max 30 characters")
          .required("Club name is required"),
        years: Yup.number()
          .min(1, "At least 1 year")
          .max(20, "At most 20 years")
          .required("Years are required"),
      })
    ).max(3, "You can add up to 3 past teams"),
  });

  // ========================================
  // 🎯 INITIAL FORM VALUES
  // ========================================
  const initialValues = {
    // Player Details
    playerName: "",
    jerseyNumber: "",
    position: "",
    stickHand: "",
    dateOfBirth: "",
    nationality: "",
    email: "",
    phone: "",
    playerId: "",
    guardianName: "",
    
    // Team & Event Information
    teamName: "",
    leagueLevel: "",
    tournamentName: "",
    startDate: "",
    endDate: "",
    
    // Equipment Size
    jerseySize: "",
    padSize: "",
    
    // Medical & Consent
    hasMedicalCondition: false,
    medicalCertNumber: "",
    consent: false,
    
    // Past Teams
    pastTeams: [],
  };

  // ========================================
  // 🚀 FORM SUBMISSION HANDLER
  // ========================================
  // This function runs when the form is successfully submitted
  const onSubmit = (values, { resetForm }) => {
    // 1. Log the registration data to console (requirement)
    console.log("Submitted Data:", values);
    
    // 2. Add new player to the table (requirement: multiple entries)
    setRecords([...records, values]);
    
    // 3. Reset the form after successful submission (requirement)
    resetForm();
  };

  // ========================================
  // 🎨 RENDER METHOD
  // ========================================
  return (
    <div className="container mt-4">
      {/* ======================================== */}
      {/* 📋 FORM HEADER */}
      {/* ======================================== */}
      <h2 className="text-center mb-4">Hockey Tournament Registration</h2>

      {/* ======================================== */}
      {/* 🔧 FORMIK FORM WRAPPER */}
      {/* ======================================== */}
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={onSubmit}
      >
        {/* Formik render prop gives us access to form state and validation */}
        {({ values, isValid }) => (
          <Form>
            {/* ======================================== */}
            {/* 👤 PLAYER DETAILS SECTION */}
            {/* ======================================== */}
            <h4>Player Details</h4>
            
            {/* Player Name & Jersey Number (Side by Side) */}
            <div className="row">
              <div className="col-md-6 mb-3">
                <label>Player Name</label>
                <Field name="playerName" className="form-control" />
                <ErrorMessage name="playerName" component="div" className="text-danger" />
              </div>
              <div className="col-md-6 mb-3">
                <label>Jersey Number</label>
                <Field name="jerseyNumber" type="number" className="form-control" />
                <ErrorMessage name="jerseyNumber" component="div" className="text-danger" />
              </div>
            </div>

            {/* Position & Stick Hand (Side by Side) */}
            <div className="row">
              <div className="col-md-6 mb-3">
                <label>Position</label>
                <Field as="select" name="position" className="form-control">
                  <option value="">Select</option>
                  <option value="Forward">Forward</option>
                  <option value="Defense">Defense</option>
                  <option value="Goalie">Goalie</option>
                </Field>
                <ErrorMessage name="position" component="div" className="text-danger" />
              </div>
              <div className="col-md-6 mb-3">
                <label>Stick Hand</label>
                <Field as="select" name="stickHand" className="form-control">
                  <option value="">Select</option>
                  <option value="Left">Left</option>
                  <option value="Right">Right</option>
                </Field>
                <ErrorMessage name="stickHand" component="div" className="text-danger" />
              </div>
            </div>

            {/* Date of Birth, Nationality, Email (Three Columns) */}
            <div className="row">
              <div className="col-md-4 mb-3">
                <label>Date of Birth</label>
                <Field name="dateOfBirth" type="date" className="form-control" />
                <ErrorMessage name="dateOfBirth" component="div" className="text-danger" />
              </div>
              <div className="col-md-4 mb-3">
                <label>Nationality</label>
                <Field name="nationality" className="form-control" />
                <ErrorMessage name="nationality" component="div" className="text-danger" />
              </div>
              <div className="col-md-4 mb-3">
                <label>Email</label>
                <Field name="email" type="email" className="form-control" />
                <ErrorMessage name="email" component="div" className="text-danger" />
              </div>
            </div>

            {/* Phone, Player ID, Guardian Name (Three Columns) */}
            <div className="row">
              <div className="col-md-4 mb-3">
                <label>Phone</label>
                <Field name="phone" className="form-control" />
                <ErrorMessage name="phone" component="div" className="text-danger" />
              </div>
              <div className="col-md-4 mb-3">
                <label>Player ID</label>
                <Field name="playerId" className="form-control" />
                <ErrorMessage name="playerId" component="div" className="text-danger" />
              </div>
              <div className="col-md-4 mb-3">
                <label>Guardian Name</label>
                <Field name="guardianName" className="form-control" />
                <ErrorMessage name="guardianName" component="div" className="text-danger" />
              </div>
            </div>

            {/* ======================================== */}
            {/* 🏆 TEAM & EVENT INFORMATION SECTION */}
            {/* ======================================== */}
            <h4>Team & Event Information</h4>
            
            {/* Team Name & League Level (Side by Side) */}
            <div className="row">
              <div className="col-md-6 mb-3">
                <label>Team Name</label>
                <Field name="teamName" className="form-control" />
                <ErrorMessage name="teamName" component="div" className="text-danger" />
              </div>
              <div className="col-md-6 mb-3">
                <label>League Level</label>
                <Field as="select" name="leagueLevel" className="form-control">
                  <option value="">Select</option>
                  <option value="Amateur">Amateur</option>
                  <option value="College">College</option>
                  <option value="Pro">Pro</option>
                </Field>
                <ErrorMessage name="leagueLevel" component="div" className="text-danger" />
              </div>
            </div>
            
            {/* Tournament Name, Start Date, End Date (Three Columns) */}
            <div className="row">
              <div className="col-md-6 mb-3">
                <label>Tournament Name</label>
                <Field name="tournamentName" className="form-control" />
                <ErrorMessage name="tournamentName" component="div" className="text-danger" />
              </div>
              <div className="col-md-3 mb-3">
                <label>Start Date</label>
                <Field name="startDate" type="date" className="form-control" />
                <ErrorMessage name="startDate" component="div" className="text-danger" />
              </div>
              <div className="col-md-3 mb-3">
                <label>End Date</label>
                <Field name="endDate" type="date" className="form-control" />
                <ErrorMessage name="endDate" component="div" className="text-danger" />
              </div>
            </div>

            {/* ======================================== */}
            {/* 👕 EQUIPMENT SIZE SECTION (Conditional) */}
            {/* ======================================== */}
            
            {/* Jersey Size: Only show if position is NOT "Goalie" */}
            {values.position !== "Goalie" ? (
              <div className="mb-3">
                <label>Jersey Size</label>
                <Field name="jerseySize" className="form-control" />
                <ErrorMessage name="jerseySize" component="div" className="text-danger" />
              </div>
            ) : (
              /* Pad Size: Only show if position IS "Goalie" */
              <div className="mb-3">
                <label>Pad Size</label>
                <Field name="padSize" className="form-control" />
                <ErrorMessage name="padSize" component="div" className="text-danger" />
              </div>
            )}

            {/* ======================================== */}
            {/* 🏥 MEDICAL & CONSENT SECTION */}
            {/* ======================================== */}
            <h4>Medical & Consent</h4>
            
            {/* Medical Condition Checkbox */}
            <div className="form-check mb-2">
              <Field 
                type="checkbox" 
                name="hasMedicalCondition" 
                className="form-check-input" 
              />
              <label className="form-check-label">Has Medical Condition</label>
            </div>
            
            {/* Medical Certificate Number: Only show if hasMedicalCondition is true */}
            {values.hasMedicalCondition && (
              <div className="mb-3">
                <label>Medical Certificate Number</label>
                <Field name="medicalCertNumber" className="form-control" />
                <ErrorMessage name="medicalCertNumber" component="div" className="text-danger" />
              </div>
            )}
            
            {/* Consent Checkbox */}
            <div className="form-check mb-3">
              <Field 
                type="checkbox" 
                name="consent" 
                className="form-check-input" 
              />
              <label className="form-check-label">Consent</label>
              <ErrorMessage name="consent" component="div" className="text-danger" />
            </div>

            {/* ======================================== */}
            {/* 🏟️ PAST TEAMS SECTION (Dynamic FieldArray) */}
            {/* ======================================== */}
            <h4>Past Teams</h4>
            
            {/* Formik FieldArray for dynamic past teams */}
            <FieldArray name="pastTeams">
              {({ push, remove }) => (
                <div>
                  {/* Map through existing past teams */}
                  {values.pastTeams.map((_, index) => (
                    <div key={index} className="row mb-2">
                      <div className="col-md-6">
                        <Field
                          name={`pastTeams.${index}.clubName`}
                          placeholder="Club Name"
                          className="form-control"
                        />
                        <ErrorMessage
                          name={`pastTeams.${index}.clubName`}
                          component="div"
                          className="text-danger"
                        />
                      </div>
                      <div className="col-md-4">
                        <Field
                          name={`pastTeams.${index}.years`}
                          type="number"
                          placeholder="Years"
                          className="form-control"
                        />
                        <ErrorMessage
                          name={`pastTeams.${index}.years`}
                          component="div"
                          className="text-danger"
                        />
                      </div>
                      <div className="col-md-2">
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={() => remove(index)}
                        >
                          Remove
                        </button>
                      </div>
                    </div>
                  ))}
                  
                  {/* Add Past Team Button: Only show if less than 3 teams */}
                  {values.pastTeams.length < 3 && (
                    <button
                      type="button"
                      className="btn btn-secondary"
                      onClick={() => push({ clubName: "", years: "" })}
                    >
                      Add Past Team
                    </button>
                  )}
                </div>
              )}
            </FieldArray>

            {/* ======================================== */}
            {/* 🚀 SUBMIT & RESET BUTTONS */}
            {/* ======================================== */}
            <div className="mt-3">
              {/* Submit Button: Disabled until form is valid (requirement) */}
              <button 
                type="submit" 
                className="btn btn-primary me-2" 
                disabled={!isValid}
              >
                Submit
              </button>
              
              {/* Reset Button */}
              <button type="reset" className="btn btn-warning">
                Reset
              </button>
            </div>
          </Form>
        )}
      </Formik>

      {/* ======================================== */}
      {/* 📊 SUBMITTED RECORDS TABLE */}
      {/* ======================================== */}
      {/* This table displays all submitted registrations */}
      {records.length > 0 && (
        <div className="mt-5">
          <h4>Submitted Records</h4>
          <div className="table-responsive">
            <table className="table table-bordered">
              <thead>
                <tr>
                  <th>Player Name</th>
                  <th>Jersey #</th>
                  <th>Team</th>
                  <th>Position</th>
                  <th>Stick Hand</th>
                  <th>DOB</th>
                  <th>Nationality</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                {/* Map through all submitted records and display them */}
                {records.map((r, i) => (
                  <tr key={i}>
                    <td>{r.playerName}</td>
                    <td>{r.jerseyNumber}</td>
                    <td>{r.teamName}</td>
                    <td>{r.position}</td>
                    <td>{r.stickHand}</td>
                    <td>{r.dateOfBirth}</td>
                    <td>{r.nationality}</td>
                    <td>{r.email}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default HockeyTournamentForm;
