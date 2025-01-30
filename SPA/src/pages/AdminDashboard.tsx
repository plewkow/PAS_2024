import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardTitle,
  CardDescription,
} from "@/components/ui/card";
import { Link } from "react-router";

const AdminDashboard = () => {
  return (
    <div className="flex items-center justify-center mt-24">
      <Card className="max-w-lg w-full text-center p-4">
        <CardContent>
          <CardTitle className="text-3xl font-bold text-red-500">
          AdminDashboard
          </CardTitle>
          <CardDescription className="text-lg text-gray-500 mt-4">
            This page is only accessible to admin users.
          </CardDescription>
          <Button
            asChild
            className="mt-8 text-white bg-blue-500 hover:bg-blue-600"
            variant="outline"
          >
            <Link to="/" className="px-6 py-3">
              Go to Home
            </Link>
          </Button>
        </CardContent>
      </Card>
    </div>
  );
};

export default AdminDashboard;
