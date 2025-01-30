import { useState } from "react";
import {
  NavigationMenu,
  NavigationMenuLink,
} from "@/components/ui/navigation-menu";
import { NavLink, useNavigate } from "react-router";
import { Home, Users, LogIn, LogOut, FileText } from "lucide-react";
import { useToast } from "@/hooks/use-toast";
import ChangePasswordDialog from "./ChangePasswordDialog";
import apiClient from "@/lib/apiClient";

const Navbar = () => {
  const navigate = useNavigate();
  const { toast } = useToast();
  const [isOpen, setIsOpen] = useState(false);
  const handleLogout = async () => {
    try {
      await apiClient.post("/users/logout");

      window.localStorage.removeItem("jwt");
      toast({
        title: "Logout successful",
        description: "You are now logged out",
        variant: "success",
      })
      navigate("/login");
    } catch (err) {
      toast({
        title: "Logout failed",
        // @ts-ignore
        description: err.response.data || "Something went wrong",
        variant: "destructive",
      });
    }
  };

  const isLoggedIn = !!window.localStorage.getItem("jwt");

  return (
    <NavigationMenu className="w-full bg-gradient-to-r from-blue-900 to-blue-300 p-4">
      <div className="max-w-7xl flex justify-between items-center w-full">
        <div className="flex space-x-4">
          <NavigationMenuLink asChild>
            <NavLink to="/" className="flex items-center space-x-2 text-white">
              <Home size={18} /> Home
            </NavLink>
          </NavigationMenuLink>

          {isLoggedIn && (
            <>
              <NavigationMenuLink asChild>
                <NavLink
                  to="/users"
                  className="flex items-center space-x-2 text-white"
                >
                  <Users size={18} /> Users
                </NavLink>
              </NavigationMenuLink>

              <NavigationMenuLink asChild>
                <NavLink
                  to="/rents"
                  className="flex items-center space-x-2 text-white"
                >
                  <FileText size={18} /> Rents
                </NavLink>
              </NavigationMenuLink>
            </>
          )}
        </div>

        <div className="flex space-x-4">
          {isLoggedIn ? (
            <>
              <NavigationMenuLink asChild>
                <button
                  onClick={handleLogout}
                  className="flex items-center space-x-2 text-white"
                >
                  <LogOut size={18} /> Logout
                </button>
              </NavigationMenuLink>

              <NavigationMenuLink asChild>
                <button
                  onClick={() => setIsOpen(true)}
                  className="text-blue-900 bg-white px-4 py-1 rounded-full text-sm"
                >
                  Change Password
                </button>
              </NavigationMenuLink>
            </>
          ) : (
            <>
              <NavigationMenuLink asChild>
                <NavLink
                  to="/login"
                  className="flex items-center space-x-2 text-white"
                >
                  <LogIn size={18} /> Login
                </NavLink>
              </NavigationMenuLink>
              <NavigationMenuLink asChild>
                <NavLink
                  to="/register"
                  className="text-blue-900 bg-white px-4 py-1 rounded-full text-sm"
                >
                  Register
                </NavLink>
              </NavigationMenuLink>
            </>
          )}
        </div>
      </div>
      <ChangePasswordDialog isOpen={isOpen} setIsOpen={setIsOpen} />
    </NavigationMenu>
  );
};
export default Navbar;