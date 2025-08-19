import React from "react";

const Juice = ({ id, name, price }) => (
  <tr>
    <td>{id}</td>
    <td>{name}</td>
    <td>₹{price}</td>
  </tr>
);

const JuiceList = () => {
  const juices = [
    { id: 1, name: "Mango", price: 50 },
    { id: 2, name: "Orange", price: 40 },
    { id: 3, name: "Apple", price: 60 },
  ];

  return (
    <div>
      <h2>Juice List</h2>
      <table border="1" align="center">
        <thead>
          <tr>
            <th>ID</th>
            <th>Juice</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {juices.map((juice) => (
            <Juice key={juice.id} {...juice} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default JuiceList;
