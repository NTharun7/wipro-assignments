import React, { Component } from "react";

class MenuItem extends Component {
  render() {
    const { name, price, category, available } = this.props;
    return (
      <p>
        {name} - ₹{price} | {category} | Available: {available}
      </p>
    );
  }
}

class Restaurant extends Component {
  render() {
    return (
      <div>
        <h2>Restaurant Details</h2>
        <p><b>Name:</b> ABC Restaurant</p>
        <p><b>Location:</b> Hyderabad</p>
        <p><b>Open Hours:</b> 10 AM - 10 PM</p>

        <MenuItem name="Pizza" price={150} category="Main Course" available="Yes" />
        <MenuItem name="Burger" price={100} category="Snack" available="Yes" />
        <MenuItem name="Ice Cream" price={50} category="Dessert" available="No" />
      </div>
    );
  }
}

export default Restaurant;
