import React, { useState } from 'react';
import { Box, Card, TextField, Button, Typography, ToggleButton,
  ToggleButtonGroup, Grid, Container, InputAdornment, MenuItem, Alert } from '@mui/material';
import {
   Person, ArrowForward, Phone,
  AccountCircle, Verified, MonitorHeart, SupervisedUserCircle,
  ContactPhone, Business, History
} from '@mui/icons-material';
import Navbar from '../components/layout/Navbar';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const Register = () => {
  const navigate = useNavigate();
  const [role, setRole] = useState('doctor');
  const [error, setError] = useState('');

  const [formData, setFormData] = useState({
    firstName: '', lastName: '', email: '', password: '', phoneNumber: '',
    specialization: '', licenseNumber: '', experienceYears: '',
    hospitalName: '', hospitalPhone: '', hospitalAddress: '',
    bloodGroup: '', emergencyContact: ''
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setError('');

    // --- CLIENT SIDE VALIDATION (To match your Backend Constraints) ---
    if (formData.password.length < 6) {
      setError("Password must be at least 6 characters long.");
      return;
    }

    const requestData = {
      name: `${formData.firstName} ${formData.lastName}`,
      email: formData.email,
      password: formData.password,
      role: role.toUpperCase(),
      phone: formData.phoneNumber,
      // Doctor Entity Fields - Ensure these match your RegistrationRequestDTO field names!
      specialization: formData.specialization,
      licenseNumber: formData.licenseNumber, // Check if backend DTO uses 'licenseNumber' or 'medicalLicenseNumber'
      experienceYears: formData.experienceYears ? parseInt(formData.experienceYears) : 0,
      hospitalName: formData.hospitalName,
      hospitalPhone: formData.hospitalPhone,
      hospitalAddress: formData.hospitalAddress,
      // Patient Entity Fields
      bloodGroup: formData.bloodGroup,
      emergencyContact: formData.emergencyContact
    };

    try {
      const response = await api.post('/auth/register', requestData);
      console.log("Success:", response.data);
      navigate('/login');
    } catch (err) {
      // Better Error Extraction: Shows exactly what the Backend says is wrong
      const backendError = err.response?.data?.message || err.response?.data?.errors?.[0]?.defaultMessage;
      setError(backendError || "Registration failed. Please check your inputs.");
    }
  };

  const sectionHeaderStyle = { mb: 2, mt: 3, display: 'flex', alignItems: 'center', gap: 1.5, color: '#0062ff', borderBottom: '1px solid #e2e8f0', pb: 1, textTransform: 'uppercase', letterSpacing: 1, fontSize: '0.8rem', fontWeight: 800 };

  return (
    <Box sx={{ minHeight: '100vh', pt: 10, pb: 8, background: 'linear-gradient(135deg, #f8fafc 0%, #eff6ff 100%)' }}>
      <Navbar />
      <Container maxWidth="md">
        <Card sx={{ p: { xs: 3, md: 5 }, borderRadius: 6, boxShadow: '0 20px 40px rgba(0,0,0,0.08)' }}>
          <Box sx={{ textAlign: 'center', mb: 4 }}>
            <Typography variant="h4" fontWeight={900} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: 1.5 }}>
              <LocalHospital sx={{ fontSize: 40, color: '#0062ff' }} /> MediCare
            </Typography>
            <Typography color="text.secondary">Register your account</Typography>
          </Box>

          {error && <Alert severity="error" sx={{ mb: 3 }}>{error}</Alert>}

          <Box sx={{ display: 'flex', justifyContent: 'center', mb: 4 }}>
            <ToggleButtonGroup value={role} exclusive onChange={(e, r) => r && setRole(r)} color="primary" fullWidth>
              <ToggleButton value="doctor" sx={{ fontWeight: 700 }}><Person sx={{ mr: 1 }} /> Doctor</ToggleButton>
              <ToggleButton value="patient" sx={{ fontWeight: 700 }}><AccountCircle sx={{ mr: 1 }} /> Patient</ToggleButton>
            </ToggleButtonGroup>
          </Box>

          <form onSubmit={handleRegister}>
            <Typography variant="subtitle2" sx={sectionHeaderStyle}><SupervisedUserCircle /> Core Identity</Typography>
            <Grid container spacing={2}>
              <Grid item xs={12} md={6}><TextField fullWidth label="First Name" name="firstName" onChange={handleChange} required /></Grid>
              <Grid item xs={12} md={6}><TextField fullWidth label="Last Name" name="lastName" onChange={handleChange} required /></Grid>
              <Grid item xs={12} md={6}><TextField fullWidth label="Email" name="email" type="email" onChange={handleChange} required /></Grid>
              <Grid item xs={12} md={6}><TextField fullWidth label="Password" name="password" type="password" onChange={handleChange} required placeholder="At least 6 characters" /></Grid>
              <Grid item xs={12}>
                <TextField fullWidth label="Phone Number" name="phoneNumber" onChange={handleChange} InputProps={{ startAdornment: <InputAdornment position="start"><Phone fontSize="small" /></InputAdornment> }} />
              </Grid>
            </Grid>

            {role === 'doctor' ? (
              <Box>
                <Typography variant="subtitle2" sx={sectionHeaderStyle}><Verified /> Professional Details</Typography>
                <Grid container spacing={2}>
                  <Grid item xs={12} md={6}><TextField fullWidth label="Specialization" name="specialization" onChange={handleChange} required /></Grid>
                  <Grid item xs={12} md={6}><TextField fullWidth label="License Number" name="licenseNumber" onChange={handleChange} required /></Grid>
                  <Grid item xs={12} md={4}>
                    <TextField fullWidth label="Years of Experience" name="experienceYears" type="number" onChange={handleChange} InputProps={{ startAdornment: <InputAdornment position="start"><History fontSize="small" /></InputAdornment> }} />
                  </Grid>
                  <Grid item xs={12} md={8}>
                    <TextField fullWidth label="Hospital Name" name="hospitalName" onChange={handleChange} InputProps={{ startAdornment: <InputAdornment position="start"><Business fontSize="small" /></InputAdornment> }} />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField fullWidth label="Hospital Phone" name="hospitalPhone" onChange={handleChange} InputProps={{ startAdornment: <InputAdornment position="start"><ContactPhone fontSize="small" /></InputAdornment> }} />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField fullWidth label="Hospital Address" name="hospitalAddress" multiline rows={2} onChange={handleChange} />
                  </Grid>
                </Grid>
              </Box>
            ) : (
              <Box>
                <Typography variant="subtitle2" sx={sectionHeaderStyle}><MonitorHeart /> Health Profile</Typography>
                <Grid container spacing={2}>
                  <Grid item xs={12} md={6}>
                    <TextField fullWidth select label="Blood Group" name="bloodGroup" value={formData.bloodGroup} onChange={handleChange}>
                      {['A+', 'A-', 'B+', 'B-', 'O+', 'O-', 'AB+', 'AB-'].map(bg => <MenuItem key={bg} value={bg}>{bg}</MenuItem>)}
                    </TextField>
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField fullWidth label="Emergency Contact" name="emergencyContact" onChange={handleChange} InputProps={{ startAdornment: <InputAdornment position="start"><ContactPhone fontSize="small" /></InputAdornment> }} />
                  </Grid>
                </Grid>
              </Box>
            )}

            <Button fullWidth type="submit" variant="contained" size="large" endIcon={<ArrowForward />} sx={{ mt: 5, py: 2, borderRadius: 3, fontWeight: 800 }}>
              Create Account
            </Button>
          </form>
        </Card>
      </Container>
    </Box>
  );
};

export default Register;