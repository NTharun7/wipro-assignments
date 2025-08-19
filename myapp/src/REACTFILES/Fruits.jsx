import React, { Component } from "react";

// Functional Component - displays list
const FruitList = ({ fruits }) => (
  <div>
    <h3>Fruit List</h3>
    <ul>
      {fruits.map((fruit, index) => (
        <li key={index}>{fruit}</li>
      ))}
    </ul>
  </div>
);

// Class Component - manages state
class Fruits extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fruits: ["Apple", "Banana", "Orange"],
      newFruit: ""
    };
  }

  handleChange = (event) => {
    this.setState({ newFruit: event.target.value });
  };

  addFruit = () => {
    const { newFruit, fruits } = this.state;
    if (newFruit.trim() !== "") {
      this.setState({
        fruits: [...fruits, newFruit],
        newFruit: ""
      });
    }
  };

  render() {
    return (
      <div>
        <h2>Fruits</h2>
        <input
          type="text"
          value={this.state.newFruit}
          onChange={this.handleChange}
          placeholder="Enter fruit"
        />
        <button onClick={this.addFruit}>Add</button>

        {/* Pass state fruits to child functional component */}
        <FruitList fruits={this.state.fruits} />
      </div>
    );
  }
}

export default Fruits;
