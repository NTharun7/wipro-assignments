import React, { Component } from "react";

// Functional Component -> only for showing list of TVs
function TelevisionList(props) {
  return (
    <div>
      <h3>Television List</h3>
      <ul>
        {/* map() will loop through props.models array and show each TV model */}
        {props.models.map((tv, index) => (
          <li key={index}>{tv}</li>
        ))}
      </ul>
    </div>
  );
}

// Class Component -> manages state (TV models + input)
class TelevisionManager extends Component {
  constructor(props) {
    super(props);

    // state -> keeps list of tv models and the text typed in input
    this.state = {
      models: ["Sony Bravia", "Samsung QLED", "LG OLED"], // initial tvs
      newModel: "" // input box value
    };
  }

  // to handle text input change
  handleChange = (event) => {
    this.setState({ newModel: event.target.value });
  };

  // to add new tv into array
  addTelevision = () => {
    // check input is not empty
    if (this.state.newModel.trim() !== "") {
      this.setState({
        // spread operator (...) adds old models + new one
        models: [...this.state.models, this.state.newModel],
        newModel: "" // clear input box after adding
      });
    }
  };

  render() {
    return (
      <div>
        <h2>Television Manager</h2>
        {/* input box to type new model */}
        <input
          type="text"
          value={this.state.newModel}
          onChange={this.handleChange}
          placeholder="Enter TV Model"
        />
        {/* button to add tv */}
        <button onClick={this.addTelevision}>Add</button>

        {/* show list using functional component */}
        <TelevisionList models={this.state.models} />
      </div>
    );
  }
}

export default TelevisionManager;
