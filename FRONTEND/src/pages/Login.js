import React, { useState } from 'react';
import { Box, Card, TextField, Button, Typography, Container, Alert } from '@mui/material';
import Navbar from '../components/layout/Navbar';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const Login = () => {
  const navigate = useNavigate();
  const [error, setError] = useState('');
  const [credentials, setCredentials] = useState({ email: '', password: '' });

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    try {
      // POSTING TO /auth/login
      const response = await api.post('/auth/login', {
          email: credentials.email,
          password: credentials.password
      });

      localStorage.setItem('token', response.data.token);
      navigate('/dashboard');
    } catch (err) {
      setError("Invalid email or password. Please try again.");
    }
  };

  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', alignItems: 'center', background: '#f8fafc' }}>
      <Navbar />
      <Container maxWidth="xs">
        <Card sx={{ p: 4, borderRadius: 4, boxShadow: 3 }}>
          <Typography variant="h5" fontWeight={800} textAlign="center" mb={3}>Welcome Back</Typography>
          {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
          <form onSubmit={handleLogin}>
            <TextField fullWidth label="Email Address" name="email" onChange={handleChange} margin="normal" required />
            <TextField fullWidth label="Password" name="password" type="password" onChange={handleChange} margin="normal" required />
            <Button fullWidth type="submit" variant="contained" size="large" sx={{ mt: 3, py: 1.5 }}>Login</Button>
          </form>
        </Card>
      </Container>
    </Box>
  );
};

export default Login;