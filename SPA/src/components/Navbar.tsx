import {
  NavigationMenu,
  NavigationMenuLink,
} from "@/components/ui/navigation-menu";
import { NavLink, useNavigate  } from "react-router";
import { Home, Users, LogIn, LogOut } from "lucide-react";
import { useToast } from "@/hooks/use-toast";

const Navbar = () => {
  const navigate = useNavigate();
  const { toast } = useToast();

  const handleLogout = async () => {
    const response = await fetch("/api/users/logout", {
      method: "POST",
      headers: {
        "Authorization": `Bearer ${window.localStorage.getItem("jwt")}`,
      },
    });
  
    if (response.ok) {
      window.localStorage.removeItem("jwt");
      navigate("/login");
      toast({
        title: "Logout successful",
        description: "You are now logged out",
        variant: "default",
      });
    } else {
      const result = await response.text();
      toast({
        title: "Logout failed",
        description: result,
        variant: "destructive",
      });
    }
  };
  

  const isLoggedIn = Boolean(window.localStorage.getItem("jwt"));
  return (
    <NavigationMenu className="w-full bg-gradient-to-r from-blue-900 to-blue-300 p-4">
      <div className="max-w-7xl flex justify-between items-center w-full">
        <div className="flex space-x-4">
          <NavigationMenuLink asChild>
            <NavLink to="/" className="flex items-center space-x-2 text-white">
              <Home size={18} /> Home
            </NavLink>
          </NavigationMenuLink>
          <NavigationMenuLink asChild>
            <NavLink
              to="/users"
              className="flex items-center space-x-2 text-white"
            >
              <Users size={18} /> Users
            </NavLink>
          </NavigationMenuLink>
        </div>
        <div className="flex space-x-4">
          {!isLoggedIn ? (
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
          ) : (
            <NavigationMenuLink asChild>
              <button
                onClick={handleLogout}
                className="flex items-center space-x-2 text-white"
              >
                <LogOut size={18} /> Logout
              </button>
            </NavigationMenuLink>
          )}
        </div>
      </div>
    </NavigationMenu>
  );
};
export default Navbar;