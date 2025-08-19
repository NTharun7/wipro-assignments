import React, { Component, createRef } from "react";

// ---------------- Controlled Component ----------------
class FlightBookingControlled extends Component {
  constructor(props) {
    super(props);

    // Controlled form uses state
    this.state = {
      passengerName: "",
      email: "",
      gender: "",
      meal: "Veg",
      request: "",
      submitted: false
    };
  }

  // Handle input changes and update state
  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  // On form submit show table
  handleSubmit = (e) => {
    e.preventDefault();
    this.setState({ submitted: true });
  };

  render() {
    return (
      <div className="card p-3 shadow">
        <h4>Controlled Flight Booking Form</h4>
        <form onSubmit={this.handleSubmit}>
          <div className="mb-2">
            <label>Passenger Name</label>
            <input
              type="text"
              name="passengerName"
              className="form-control"
              value={this.state.passengerName}
              onChange={this.handleChange}
              required
            />
          </div>

          <div className="mb-2">
            <label>Email</label>
            <input
              type="email"
              name="email"
              className="form-control"
              value={this.state.email}
              onChange={this.handleChange}
              required
            />
          </div>

          <div className="mb-2">
            <label>Gender</label><br />
            <input
              type="radio"
              name="gender"
              value="Male"
              checked={this.state.gender === "Male"}
              onChange={this.handleChange}
            /> Male
            <input
              type="radio"
              name="gender"
              value="Female"
              checked={this.state.gender === "Female"}
              onChange={this.handleChange}
              className="ms-2"
            /> Female
          </div>

          <div className="mb-2">
            <label>Meal Preference</label>
            <select
              name="meal"
              className="form-control"
              value={this.state.meal}
              onChange={this.handleChange}
            >
              <option value="Veg">Veg</option>
              <option value="Non-Veg">Non-Veg</option>
            </select>
          </div>

          <div className="mb-2">
            <label>Special Request</label>
            <textarea
              name="request"
              className="form-control"
              value={this.state.request}
              onChange={this.handleChange}
            />
          </div>

          <button type="submit" className="btn btn-primary">Submit</button>
        </form>

        {/* Display Submitted Data in a Bootstrap Table */}
        {this.state.submitted && (
          <table className="table table-bordered mt-3">
            <thead>
              <tr>
                <th>Passenger Name</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Meal</th>
                <th>Request</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{this.state.passengerName}</td>
                <td>{this.state.email}</td>
                <td>{this.state.gender}</td>
                <td>{this.state.meal}</td>
                <td>{this.state.request}</td>
              </tr>
            </tbody>
          </table>
        )}
      </div>
    );
  }
}

// ---------------- Uncontrolled Component ----------------
class FlightBookingUncontrolled extends Component {
  constructor(props) {
    super(props);

    // Create refs for each input
    this.flightNoRef = createRef();
    this.sourceRef = createRef();
    this.destinationRef = createRef();
    this.dateRef = createRef();
    this.termsRef = createRef();

    this.state = { submitted: false };
  }

  handleSubmit = (e) => {
    e.preventDefault();

    // Access values using refs (not state)
    this.setState({
      submitted: true,
      flightNo: this.flightNoRef.current.value,
      source: this.sourceRef.current.value,
      destination: this.destinationRef.current.value,
      date: this.dateRef.current.value,
      terms: this.termsRef.current.checked
    });
  };

  render() {
    return (
      <div className="card p-3 shadow">
        <h4>Uncontrolled Flight Booking Form</h4>
        <form onSubmit={this.handleSubmit}>
          <div className="mb-2">
            <label>Flight Number</label>
            <input type="text" className="form-control" ref={this.flightNoRef} required />
          </div>

          <div className="mb-2">
            <label>Source</label>
            <input type="text" className="form-control" ref={this.sourceRef} required />
          </div>

          <div className="mb-2">
            <label>Destination</label>
            <input type="text" className="form-control" ref={this.destinationRef} required />
          </div>

          <div className="mb-2">
            <label>Travel Date</label>
            <input type="date" className="form-control" ref={this.dateRef} required />
          </div>

          <div className="mb-2">
            <input type="checkbox" ref={this.termsRef} /> Terms Accepted
          </div>

          <button type="submit" className="btn btn-success">Submit</button>
        </form>

        {/* Display submitted details */}
        {this.state.submitted && (
          <div className="card p-3 mt-3">
            <h5>Flight Details</h5>
            <p><b>Flight Number:</b> {this.state.flightNo}</p>
            <p><b>Source:</b> {this.state.source}</p>
            <p><b>Destination:</b> {this.state.destination}</p>
            <p><b>Date:</b> {this.state.date}</p>
            <p><b>Terms Accepted:</b> {this.state.terms ? "Yes" : "No"}</p>
          </div>
        )}
      </div>
    );
  }
}

// ---------------- Parent Component ----------------
class FlightBooking extends Component {
  render() {
    return (
      <div className="container mt-4">
        <div className="row">
          <div className="col-md-6">
            <FlightBookingControlled />
          </div>
          <div className="col-md-6">
            <FlightBookingUncontrolled />
          </div>
        </div>
      </div>
    );
  }
}

export default FlightBooking;
