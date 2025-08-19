import React from "react";

const ServiceCard = ({ serviceName, price, fabricsAvailable }) => (
  <div className="card m-2 p-2" style={{ width: "18rem" }}>
    <div className="card-body">
      <h5 className="card-title">{serviceName}</h5>
      <p className="card-text">Price: ${price}</p>
      <p><b>Fabrics:</b></p>
      <ul>
        {fabricsAvailable.map((fabric, index) => (
          <li key={index}>{fabric}</li>
        ))}
      </ul>
    </div>
  </div>
);

const TailorShop = () => {
  const services = [
    { 
      serviceName: "Shirt", 
      price: 20, 
      fabricsAvailable: ["Cotton", "Linen", "Polyester"] 
    },
    { 
      serviceName: "Pants", 
      price: 30, 
      fabricsAvailable: ["Denim", "Wool", "Chino"] 
    },
    { 
      serviceName: "Lehenga", 
      price: 150, 
      fabricsAvailable: ["Silk", "Georgette", "Chiffon"] 
    },
    { 
      serviceName: "Blouse", 
      price: 40, 
      fabricsAvailable: ["Cotton", "Silk", "Satin"] 
    },
  ];

  return (
    <div className="container">
      <h2 className="tailor-heading" style={{ color: '#2c3e50', fontWeight: 'bold', fontSize: '2rem' }}>Tailoring Services</h2>
      <div className="row">
        {services.map((service, index) => (
          <div className="col" key={index}>
            <ServiceCard {...service} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default TailorShop;
