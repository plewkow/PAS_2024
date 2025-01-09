import {
  NavigationMenu,
  NavigationMenuLink,
} from "@/components/ui/navigation-menu";
import { NavLink } from "react-router";

const Navbar = () => {
  return (
    <NavigationMenu className="border px-4 py-4 rounded-full border-black mx-auto mt-2 space-x-6">
      <NavigationMenuLink asChild>
        <NavLink to="/">Home</NavLink>
      </NavigationMenuLink>
      <NavigationMenuLink asChild>
        <NavLink to="/login">Login</NavLink>
      </NavigationMenuLink>
      <NavigationMenuLink asChild>
        <NavLink to="/register">Register</NavLink>
      </NavigationMenuLink>
      <NavigationMenuLink asChild>
        <NavLink to="/users">Users</NavLink>
      </NavigationMenuLink>
      <NavigationMenuLink asChild>
        <NavLink to="/rents">Rents</NavLink>
      </NavigationMenuLink>
    </NavigationMenu>
  );
};

export default Navbar;
