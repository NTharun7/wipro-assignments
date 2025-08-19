import React, { Component } from "react";
// Bootstrap is already included via CDN in public/index.html, so no local import here.

class MarriageForm extends Component {
  constructor(props) {
    super(props);

    // state -> to store all form fields + submitted data
    this.state = {
      brideName: "",
      groomName: "",
      marriageDate: "",
      venue: "",
      submitted: false // to check whether form submitted
    };
  }

  // handle input change -> update state
  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  // handle form submit
  handleSubmit = (event) => {
    event.preventDefault(); // stop page reload
    this.setState({ submitted: true }); // show submitted details
  };

  render() {
    return (
      <div className="container mt-4">
        <div className="card p-4 shadow">
          <h2 className="mb-3">Marriage Form</h2>

          {/* Form */}
          <form onSubmit={this.handleSubmit}>
            <div className="form-group mb-3">
              <label>Bride Name</label>
              <input
                type="text"
                name="brideName"
                className="form-control"
                value={this.state.brideName}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-group mb-3">
              <label>Groom Name</label>
              <input
                type="text"
                name="groomName"
                className="form-control"
                value={this.state.groomName}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-group mb-3">
              <label>Marriage Date</label>
              <input
                type="date"
                name="marriageDate"
                className="form-control"
                value={this.state.marriageDate}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-group mb-3">
              <label>Venue</label>
              <input
                type="text"
                name="venue"
                className="form-control"
                value={this.state.venue}
                onChange={this.handleChange}
                required
              />
            </div>

            <button type="submit" className="btn btn-primary w-100">
              Submit
            </button>
          </form>
        </div>

        {/* Show Submitted Details */}
        {this.state.submitted && (
          <div className="card p-4 mt-4 shadow">
            <h3>Marriage Details</h3>
            <p><b>Bride Name:</b> {this.state.brideName}</p>
            <p><b>Groom Name:</b> {this.state.groomName}</p>
            <p><b>Date:</b> {this.state.marriageDate}</p>
            <p><b>Venue:</b> {this.state.venue}</p>
          </div>
        )}
      </div>
    );
  }
}

export default MarriageForm;
