// streamsLab.js
// Lab: Working with Node.js Streams
// Objective: Use streams to read, write, and manipulate data efficiently

const fs = require("fs"); // fs = File System module

// Fruit data in JSON format
const fruits = [
  { id: 1, name: "Apple", color: "Red", price: 120 },
  { id: 2, name: "Banana", color: "Yellow", price: 40 },
  { id: 3, name: "Mango", color: "Orange", price: 150 }
];

// ✅ Step 1: Create a Writable Stream
const writeStream = fs.createWriteStream("fruits.txt");

// Listen for 'finish' event when writing is done
writeStream.on("finish", () => {
  console.log("Data written to fruits.txt successfully!\n");

  // After writing, read the file using readable stream
  readFruitsFile();
});

// Write fruit records into fruits.txt (line by line as JSON)
fruits.forEach(fruit => {
  writeStream.write(JSON.stringify(fruit) + "\n");
});

// End the writable stream
writeStream.end();


// ✅ Step 2: Create a Readable Stream
function readFruitsFile() {
  const readStream = fs.createReadStream("fruits.txt", { encoding: "utf8" });

  let dataBuffer = "";

  // Collect data chunks
  readStream.on("data", chunk => {
    dataBuffer += chunk;
  });

  // When file reading is finished
  readStream.on("end", () => {
    console.log("Reading fruits.txt using stream:\n");

    // Split into lines, filter empty ones
    const lines = dataBuffer.split("\n").filter(line => line.trim() !== "");
    lines.forEach(line => {
      const fruit = JSON.parse(line);
      console.log(`Fruit ID: ${fruit.id}`);
      console.log(`Name: ${fruit.name}`);
      console.log(`Color: ${fruit.color}`);
      console.log(`Price: ${fruit.price}\n`);
    });

    // ✅ Step 3: Pipe Streams (copy file)
    copyFileUsingPipe();
  });

  // ✅ Step 4: Stream Events (handle errors)
  readStream.on("error", err => {
    console.error("Error while reading file:", err.message);
  });
}

// ✅ Step 3: Pipe Stream - copy fruits.txt → fruits_copy.txt
function copyFileUsingPipe() {
  const readStream = fs.createReadStream("fruits.txt");
  const writeStream = fs.createWriteStream("fruits_copy.txt");

  // Pipe connects readable → writable
  readStream.pipe(writeStream);

  writeStream.on("finish", () => {
    console.log("Content copied to fruits_copy.txt using pipe!");
  });

  // Error handling
  readStream.on("error", err => console.error("Read error:", err.message));
  writeStream.on("error", err => console.error("Write error:", err.message));
}
