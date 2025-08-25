# Electricity Power Cut Announcement System

## Overview
This React application demonstrates the implementation of a power cut announcement system using **React Context API** and **Bootstrap** styling. The system allows administrators to send announcements about power cuts for different streets and displays them to all residents in real-time.

## Features
- ✅ **Real-time Announcements**: Add new power cut announcements instantly
- ✅ **Street-specific Messages**: Target announcements to specific streets
- ✅ **Timestamp Tracking**: Automatic time recording for each announcement
- ✅ **Responsive Design**: Bootstrap-powered UI that works on all devices
- ✅ **Context API**: Global state management using React Context
- ✅ **Form Validation**: Ensures both fields are filled before submission

## Technical Implementation

### 1. PowerCutContext.jsx
**Purpose**: Manages global state for all power cut announcements
- **State Management**: Uses `useState` to store announcements array
- **Context Creation**: Implements React Context API with `createContext`
- **Custom Hook**: Provides `usePowerCut()` hook for easy context access
- **Functions**: `addAnnouncement(street, message)` adds new announcements

**Key Features**:
- Announcements stored in descending order (most recent first)
- Unique ID generation using timestamps
- Automatic time formatting for each announcement

### 2. SendAnnouncement.jsx
**Purpose**: Form component for adding new announcements
- **Form Fields**: Street Name (text input) and Message (textarea)
- **Validation**: Ensures both fields are filled before submission
- **State Management**: Local state for form inputs with controlled components
- **Bootstrap Styling**: Uses `form-control`, `btn btn-primary`, and spacing classes

**Key Features**:
- Form validation with user feedback
- Automatic form clearing after successful submission
- Responsive Bootstrap grid layout

### 3. AnnouncementList.jsx
**Purpose**: Displays all power cut announcements
- **Data Source**: Retrieves announcements from PowerCutContext
- **Scrollable Container**: Maximum height of 300px with vertical scrolling
- **Bootstrap Styling**: Uses `border`, `p-2`, `rounded`, `mb-2` classes
- **Empty State**: Shows helpful message when no announcements exist

**Key Features**:
- Scrollable list for better UX
- Individual announcement cards with clear information hierarchy
- Total announcement counter
- Responsive design for all screen sizes

### 4. PowerCutApp.jsx
**Purpose**: Main application container that combines all components
- **Context Provider**: Wraps entire app with PowerCutProvider
- **Component Layout**: Organizes SendAnnouncement and AnnouncementList
- **Page Structure**: Clear visual hierarchy with titles and descriptions

## Component Architecture

```
PowerCutApp (Main Container)
├── PowerCutProvider (Context Provider)
│   ├── SendAnnouncement (Form Component)
│   └── AnnouncementList (Display Component)
```

## State Flow

1. **User Input**: Admin fills out announcement form
2. **Form Submission**: `handleSubmit` validates and calls context function
3. **Context Update**: `addAnnouncement` adds new announcement to state
4. **UI Update**: All components automatically re-render with new data
5. **Real-time Display**: New announcement appears immediately in the list

## Bootstrap Classes Used

### Forms
- `form-control`: Input field styling
- `btn btn-primary`: Primary button styling
- `row g-3`: Grid layout with gutters
- `col-12`: Full-width columns

### Layout
- `container-fluid`: Full-width container
- `mt-4`, `mb-3`: Margin utilities
- `text-center`: Text alignment
- `py-4`: Padding utilities

### Components
- `border rounded`: Card-like appearance
- `p-2`, `p-3`: Padding utilities
- `mb-2`: Margin bottom
- `table-responsive`: Responsive table container

## Usage Instructions

1. **Start the Application**: Run `npm start` in the myapp directory
2. **Add Announcement**: Fill in street name and message, click "Send Announcement"
3. **View Announcements**: All announcements appear in the scrollable list below
4. **Real-time Updates**: New announcements appear immediately without page refresh

## Technical Requirements Met

- ✅ **React Context API**: Implemented with `createContext` and `useContext`
- ✅ **Bootstrap Styling**: Consistent use of Bootstrap classes throughout
- ✅ **Component Architecture**: Modular, reusable components
- ✅ **State Management**: Global state using Context API
- ✅ **Form Handling**: Controlled components with validation
- ✅ **Responsive Design**: Mobile-friendly Bootstrap layout

## Future Enhancements

- **Priority Levels**: Add urgent/normal announcement types
- **Street Filtering**: Filter announcements by specific streets
- **Search Functionality**: Search through announcement history
- **Notification System**: Browser notifications for new announcements
- **Data Persistence**: Save announcements to localStorage or backend

## Dependencies

- React 18.3.1
- Bootstrap 5.3.2 (via CDN)
- React Context API (built-in)
- React Hooks (useState, useEffect, useContext)

## File Structure

```
myapp/src/REACTFILES/
├── PowerCutContext.jsx      # Context and Provider
├── SendAnnouncement.jsx     # Announcement Form
├── AnnouncementList.jsx     # Announcements Display
└── PowerCutApp.jsx         # Main App Container
```

This implementation successfully demonstrates React Context API usage, Bootstrap integration, and component-based architecture as required by the lab assignment.
