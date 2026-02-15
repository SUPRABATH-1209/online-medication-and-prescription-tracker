import axios from 'axios';

const api = axios.create({
  // Change this from the fake URL to your actual local backend port
  baseURL: 'http://localhost:8111/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor to add JWT token to every request
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;