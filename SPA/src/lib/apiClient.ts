import axios from "axios";
import { useNavigate } from "react-router";

const getToken = () => localStorage.getItem("jwt");

export const authClient = axios.create({
  baseURL: "/api",
  headers: {
    "Content-Type": "application/json",
  },
});

const apiClient = axios.create({
  baseURL: "/api",
  headers: {
    "Content-Type": "application/json",
  },
});

apiClient.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response?.status === 401) {
      console.error("Błąd autoryzacji - zaloguj się ponownie.");
      const navigate = useNavigate();
      navigate("/login");
    }
    return Promise.reject(error);
  }
);

export default apiClient;
