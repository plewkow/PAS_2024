import { Navigate, Outlet } from "react-router";
import { useEffect, useState } from "react";
import jwtDecode from "jwt-decode";
import { DecodedToken } from "@/types";

const ProtectedRoute = ({ allowedRoles }: { allowedRoles?: string[] }) => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);
  const [role, setRole] = useState<string | null>(null);

  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem("jwt");
      const userRole = token ? await jwtDecode<DecodedToken>(token).role : null;
      setRole(userRole);
      setIsAuthenticated(!!token);
    };
    checkAuth();
  }, []);

  if (isAuthenticated === null) {
    return <div>Loading...</div>;
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  if (allowedRoles && role && !allowedRoles.includes(role)) {
    return <Navigate to="/unauthorized" replace />;
  }

  return <Outlet />;
};

export default ProtectedRoute;
