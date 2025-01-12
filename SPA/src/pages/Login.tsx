import LoginForm from "@/components/LoginForm";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Link } from "react-router";
import { MoveLeft } from "lucide-react";

const Login = () => {
  return (
    <div className="min-h-screen flex items-center justify-center">
      <MoveLeft
        className="ml-4 mt-4 cursor-pointer absolute top-0 left-0"
        onClick={() => window.history.back()}
      />
      <Card>
        <CardHeader>
          <CardTitle className="text-center">Login to your account!</CardTitle>
          <CardDescription>
            Please enter your email and password to start shoping!
          </CardDescription>
        </CardHeader>
        <CardContent>
          <LoginForm />
        </CardContent>
        <CardFooter className="justify-center">
          <Link to="/register">
            Doesn't have an account?{" "}
            <span className="text-blue-700">Register</span>
          </Link>
        </CardFooter>
      </Card>
    </div>
  );
};

export default Login;
