import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from '@mui/material';
import theme from './theme/theme';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}
export default App;















// /*import React from 'react';
// import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
// import { ThemeProvider, CssBaseline, Typography } from '@mui/material';

// // Theme & Layout
// import theme from './theme/theme';
// import MainLayout from './components/layout/MainLayout';

// // Auth Pages
// import Login from './pages/Login';
// import Register from './pages/Register';

// // Feature Pages
// import Dashboard from './pages/Dashboard';
// import PrescriptionList from './pages/PrescriptionList';
// import UploadPrescription from './pages/UploadPrescription';
// import MedicationList from './pages/MedicationList';
// import Reminders from './pages/Reminders';
// import Appointments from './pages/Appointments';
// import Profile from './pages/Profile';

// // Simple Inline Component for About Us
// const About = () => (
//   <Typography variant="h4" sx={{ p: 4, fontWeight: 'bold' }}>
//     About MedTracker: Your Health, Simplified.
//   </Typography>
// );

// function App() {
//   // Set this to true to see the Dashboard immediately
//   const isAuthenticated = true;

//   return (
//     <ThemeProvider theme={theme}>
//       <CssBaseline />
//       <Router>
//         <Routes>
//           {/* Public Routes */}
//           <Route path="/login" element={<Login />} />
//           <Route path="/register" element={<Register />} />

//           {/* Protected Routes */}
//           <Route
//             path="/"
//             element={isAuthenticated ? <MainLayout /> : <Navigate to="/login" />}
//           >
//             <Route index element={<Navigate to="/dashboard" />} />
//             <Route path="dashboard" element={<Dashboard />} />
//             <Route path="prescriptions" element={<PrescriptionList />} />
//             <Route path="upload-prescription" element={<UploadPrescription />} />
//             <Route path="medications" element={<MedicationList />} />
//             <Route path="reminders" element={<Reminders />} />
//             <Route path="appointments" element={<Appointments />} />
//             <Route path="profile" element={<Profile />} />
//             <Route path="about" element={<About />} />
//           </Route>

//           {/* Fallback */}
//           <Route path="*" element={<Navigate to="/" />} />
//         </Routes>
//       </Router>
//     </ThemeProvider>
//   );
// }

// export default App;*/