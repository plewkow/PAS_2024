import RegisterForm from "@/components/RegisterForm";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { MoveLeft } from "lucide-react";
import { Link } from "react-router";

const Register = () => {
  return (
    <div className="min-h-screen flex items-center justify-center">
      <MoveLeft
        className="ml-4 mt-4 cursor-pointer absolute top-0 left-0"
        onClick={() => window.history.back()}
      />
      <Card >
        <CardHeader>
          <CardTitle className="text-center">Create a new account</CardTitle>
          <CardDescription>Please enter your email and password to create a new account in our shop</CardDescription>
        </CardHeader>
        <CardContent >
          <RegisterForm />
        </CardContent>
        <CardFooter className="justify-center">
          <Link to="/login">Already have an account? <span className="text-blue-700">Login</span></Link>
        </CardFooter>
      </Card>
    </div>
  );
};

export default Register;
