# Football Player Management System

## 🏆 Lab Assignment: Football Player Management System

This React application implements a complete CRUD (Create, Read, Update, Delete) system for managing football players using modern web technologies.

## 🚀 Features

- **Full CRUD Operations**: Create, Read, Update, and Delete football players
- **Form Validation**: Comprehensive validation using Formik and Yup
- **Responsive Design**: Bootstrap-based UI with mobile-friendly layout
- **Real-time Updates**: Instant UI updates after database operations
- **Error Handling**: User-friendly error messages and loading states

## 🛠️ Technologies Used

- **Frontend**: React 18 with Hooks
- **Form Management**: Formik for form state handling
- **Validation**: Yup for schema validation
- **HTTP Client**: Axios for API communication
- **UI Framework**: Bootstrap 5 for responsive design
- **Backend**: JSON Server for mock REST API

## 📋 Prerequisites

Before running this application, ensure you have:

- Node.js (version 14 or higher)
- npm (Node Package Manager)
- Git (for version control)

## ⚙️ Installation & Setup

### Step 1: Install Dependencies

Navigate to the project directory and install required packages:

```bash
cd myapp
npm install axios formik yup bootstrap react-bootstrap
```

### Step 2: Start JSON Server

Start the JSON Server to serve the football players data:

```bash
# In a new terminal window
cd myapp
json-server --watch football-db.json --port 5000
```

**Expected Output:**
```
  \{^_^}/ hi!

  Loading football-db.json
  Done

  Resources
  http://localhost:5000/players

  Home
  http://localhost:5000

  Type s + enter at any time to create a snapshot of the database
  Watching...
```

### Step 3: Start React Application

In another terminal window, start the React development server:

```bash
# In a new terminal window
cd myapp
npm start
```

The application will open automatically in your browser at `http://localhost:3000`

## 🎯 Lab Requirements Implementation

### ✅ Backend Setup
- **db.json**: Contains 5 default football players with complete data
- **JSON Server**: Running on port 5000 with RESTful API endpoints

### ✅ Frontend Setup
- **React App**: Built with create-react-app
- **Dependencies**: All required packages installed and configured
- **Bootstrap**: Imported in index.js for global styling

### ✅ Player Form
- **Form Fields**: All 10 required fields with proper labels
- **Validation Rules**: Implemented exactly as per lab specifications:
  - Name: Required, minimum 3 characters
  - Age: Required, range 16-40
  - Position: Required dropdown (Forward, Midfielder, Defender, Goalkeeper)
  - Club: Required
  - Nationality: Required
  - Goals: Required, ≥ 0
  - Matches Played: Required, ≥ 0
  - Jersey Number: Required, range 1-99
  - Email: Required, valid email format
  - Contact Number: Required, exactly 10 digits

### ✅ CRUD Operations (Axios)
- **CREATE**: `POST /players` - Add new player
- **READ**: `GET /players` - List all players
- **UPDATE**: `PUT /players/:id` - Edit existing player
- **DELETE**: `DELETE /players/:id` - Remove player

### ✅ UI Requirements
- **Bootstrap Table**: Displays players with columns: Name, Age, Position, Club, Goals, Matches Played, Actions
- **Action Buttons**: Edit and Delete buttons for each player row
- **Validation Errors**: Displayed below each form input field
- **Success Messages**: Shown on create/update/delete operations

## 🗄️ Database Structure

The `football-db.json` file contains the following default players:

1. **Lionel Messi** - Forward, Inter Miami, Argentina
2. **Cristiano Ronaldo** - Forward, Al Nassr, Portugal
3. **Kevin De Bruyne** - Midfielder, Manchester City, Belgium
4. **Virgil van Dijk** - Defender, Liverpool, Netherlands
5. **Manuel Neuer** - Goalkeeper, Bayern Munich, Germany

## 🔧 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/players` | Get all players |
| GET | `/players/:id` | Get specific player |
| POST | `/players` | Create new player |
| PUT | `/players/:id` | Update existing player |
| DELETE | `/players/:id` | Delete player |

## 🎨 User Interface

### Form Section (Left Side)
- Input fields for all player details
- Real-time validation with error messages
- Dynamic buttons (Add Player / Update Player)
- Form resets after successful submission

### Player List Section (Right Side)
- Responsive Bootstrap table
- Player information display
- Action buttons for each player
- Loading states and empty state handling

## 🚨 Troubleshooting

### Common Issues

1. **Port 5000 Already in Use**
   ```bash
   # Use a different port
   json-server --watch football-db.json --port 5001
   ```
   Then update the API calls in `FootballPlayerManagement.jsx`

2. **Dependencies Not Found**
   ```bash
   # Reinstall dependencies
   npm install
   npm install axios formik yup bootstrap react-bootstrap
   ```

3. **JSON Server Not Starting**
   ```bash
   # Install json-server globally
   npm install -g json-server
   ```

4. **Bootstrap Styles Not Loading**
   - Ensure Bootstrap is imported in `index.js`
   - Check if the import path is correct

### Error Messages

- **"Failed to fetch players"**: JSON Server not running or wrong port
- **"Failed to save player"**: Validation errors or server issues
- **"Failed to delete player"**: Player ID not found or server error

## 📱 Responsive Design

The application is fully responsive and works on:
- Desktop computers
- Tablets
- Mobile phones
- All modern web browsers

## 🔒 Data Validation

### Client-Side Validation (Formik + Yup)
- Real-time field validation
- Custom error messages
- Form submission prevention on errors

### Server-Side Validation
- JSON Server handles data persistence
- Automatic ID generation for new players
- Data integrity maintenance

## 🎯 Lab Deliverables Checklist

- ✅ **Functional React app implementing all CRUD operations**
- ✅ **Bootstrap form with Formik + Yup validation**
- ✅ **JSON Server running with default player data**
- ✅ **Clean, user-friendly UI**
- ✅ **Form validation for all required fields**
- ✅ **Success/error message display**
- ✅ **Responsive Bootstrap table**
- ✅ **Edit/Delete functionality**

## 🚀 Future Enhancements

Potential improvements for the system:
- Search and filter functionality
- Pagination for large player lists
- Image upload for player photos
- Advanced statistics and analytics
- User authentication and authorization
- Export data to CSV/PDF
- Real-time notifications

## 📞 Support

For any issues or questions:
1. Check the troubleshooting section above
2. Verify all dependencies are installed
3. Ensure both servers are running
4. Check browser console for error messages

---

**Lab Assignment Completed Successfully! 🎉**
