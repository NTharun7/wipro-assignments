
const buffer = Buffer.alloc(256); // alloc = pre-fills with zeros

// Student record in JSON format (string)
const student = {
  id: 1,
  name: "Thananya",
  age: 20,
  grade: "A"
};

// Convert object to string
const studentStr = JSON.stringify(student);

// ✅ Step 2: Write data to buffer
// buffer.write() returns number of bytes written
const bytesWritten = buffer.write(studentStr, "utf8");
console.log("Bytes written to buffer:", bytesWritten);

// Show buffer content as string
console.log("Buffer content as string:", buffer.toString("utf8", 0, bytesWritten));

// ✅ Step 3: Read Data back from Buffer
const readData = buffer.toString("utf8", 0, bytesWritten); // extract only written part
const studentObj = JSON.parse(readData); // convert back to JS object

console.log("\nParsed Student Data:");
console.log("Id:", studentObj.id);
console.log("Name:", studentObj.name);
console.log("Age:", studentObj.age);
console.log("Grade:", studentObj.grade);

// ✅ Step 4: Buffer Operations
// 4a. Append another student (if enough space)
const student2 = { id: 2, name: "Arjun", age: 22, grade: "B" };
const student2Str = JSON.stringify(student2);

// Write after the first student's bytes
const bytesWritten2 = buffer.write(student2Str, bytesWritten, "utf8");
console.log("\nAppended new student. Total bytes:", bytesWritten + bytesWritten2);

// Slice buffer to get ONLY the first student
const firstStudentBuffer = buffer.slice(0, bytesWritten);
console.log("First Student Slice:", firstStudentBuffer.toString("utf8"));

// Copy buffer into another buffer
const copyBuffer = Buffer.alloc(256);
firstStudentBuffer.copy(copyBuffer);
console.log("Copied Buffer Data:", copyBuffer.toString("utf8", 0, bytesWritten));

// ✅ Step 5: Encoding & Decoding
// Show data in different encodings
console.log("\nBuffer content in base64:");
console.log(buffer.toString("base64", 0, bytesWritten));
console.log("\nBuffer content in ascii:");
console.log(buffer.toString("ascii", 0, bytesWritten));
console.log("\nBuffer content in hex:");
console.log(buffer.toString("hex", 0, bytesWritten));
