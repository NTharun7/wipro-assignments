import React, { useState } from "react";

// ---------------- Parent Component ----------------
export default function ParentChildSibling() {
  // Parent state: fruits list (shared with Child A & updated by Child B)
  const [fruits, setFruits] = useState(["Apple", "Mango"]);

  // Parent state: currently selected fruit (shared between Child A → Child C)
  const [selectedFruit, setSelectedFruit] = useState("Mango");

  // Function: Child → Parent communication
  // Child B calls this function, parent updates its fruit list
  const addFruitFromChild = (name) =>
    setFruits((prev) => (prev.includes(name) ? prev : [...prev, name]));

  return (
    <div style={{ fontFamily: "Arial, sans-serif", padding: 20 }}>
      <h2 style={{ textAlign: "center", marginBottom: 20 }}>
        React – Parent / Child / Sibling Communication
      </h2>

      {/* Layout: 2-column grid for children */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "1fr 1fr",
          gap: 16,
          maxWidth: 640,
          margin: "0 auto",
        }}
      >
        {/* Child A: shows fruit list from Parent (Parent → Child) 
            Also, when user clicks a fruit, calls Parent function to update selectedFruit */}
        <ChildA list={fruits} onSelect={setSelectedFruit} />

        {/* Child C: shows the selected fruit (Sibling ←→ Sibling via Parent) */}
        <ChildC selected={selectedFruit} />

        {/* Child B: has a button that calls Parent’s addFruitFromChild (Child → Parent) */}
        <ChildB onSend={(name) => addFruitFromChild(name)} />
      </div>
    </div>
  );
}

// ---------------- Reusable Card Wrapper ----------------
function Card({ title, children }) {
  return (
    <div
      style={{
        border: "1px solid #ddd",
        borderRadius: 12,
        padding: 16,
        boxShadow: "0 1px 4px rgba(0,0,0,0.06)",
        background: "#fff",
      }}
    >
      <h4 style={{ marginTop: 0, marginBottom: 12 }}>{title}</h4>
      {children}
    </div>
  );
}

// // ---------------- Child A ----------------
// // - Displays fruit list (Parent → Child data flow)
// // - On click, sends selected fruit back to Parent (to share with Child C)
// function ChildA({ list, onSelect }) {
//   return (
//     <Card title="Fruit List">
//       <ul style={{ listStyle: "disc", paddingLeft: 18, margin: 0 }}>
//         {list.map((f) => (
//           <li key={f} style={{ marginBottom: 6 }}>
//             {/* On click, notify parent about the selected fruit */}
//             <button
//               type="button"
//               onClick={() => onSelect(f)}
//               style={{
//                 border: "1px solid #ccc",
//                 padding: "6px 10px",
//                 borderRadius: 8,
//                 cursor: "pointer",
//                 background: "#f9f9f9",
//               }}
//             >
//               {f}
//             </button>
//           </li>
//         ))}
//       </ul>
//     </Card>
//   );
// }
function ChildA({ list, onSelect }) {
    return (
      <Card title="Fruit List">
        <ul style={{ listStyle: "disc", paddingLeft: 18, margin: 0 }}>
          {list.map((f) => (
            <li key={f} style={{ marginBottom: 6 }}>
              <div
                onClick={() => onSelect(f)}
                style={{
                  display: "inline-block",
                  padding: "6px 12px",
                  borderRadius: 8,
                  cursor: "pointer",
                  background: "#f1f1f1",
                  border: "1px solid #ccc",
                  userSelect: "none",
                  fontWeight: "bold",
                  color: "#333",
                }}
              >
                {f}
              </div>
            </li>
          ))}
        </ul>
      </Card>
    );
  }
  
// ---------------- Child B ----------------
// - Has a button "Send Fruit"
// - When clicked, it calls Parent function to add "Orange" into list
function ChildB({ onSend }) {
  const FRUIT_TO_SEND = "Orange"; // fruit Child B always sends
  return (
    <Card title="Sender">
      <button
        onClick={() => onSend(FRUIT_TO_SEND)} // Child → Parent communication
        style={{
          border: "none",
          padding: "10px 14px",
          borderRadius: 10,
          cursor: "pointer",
        }}
      >
        Send Fruit
      </button>
      <div style={{ fontSize: 12, color: "#666", marginTop: 8 }}>
        (Adds {FRUIT_TO_SEND} to the parent list)
      </div>
    </Card>
  );
}

// ---------------- Child C ----------------
// - Displays the currently selected fruit
// - Gets data from Parent (which was updated by Child A)
function ChildC({ selected }) {
  return (
    <Card title="Selected Fruit">
      <div
        style={{
          border: "1px solid #eee",
          borderRadius: 10,
          padding: "8px 10px",
          background: "#fafafa",
        }}
      >
        {selected}
      </div>
    </Card>
  );
}
