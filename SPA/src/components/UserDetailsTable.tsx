import {
  Table,
  TableBody,
  TableCell,
  TableRow,
  TableHead,
  TableHeader,
  TableCaption,
} from "@/components/ui/table";
import { Button } from "./ui/button";
import { useState } from "react";
import { User } from "@/types"
import { useToast } from "@/hooks/use-toast";

const UserDetailsTable = ({
  userData,
  setUserData,
}: {
  userData: User;
  setUserData: (user: User) => void;
}) => {
  const [isEditing, setIsEditing] = useState<{ [key: string]: boolean }>({
    firstName: false,
    lastName: false,
  });

  const toggleEdit = (field: string) => {
    setIsEditing((prev) => ({
      ...prev,
      [field]: !prev[field],
    }));
  };

  const { toast } = useToast();

  const handleInputChange = (field: string, value: string) => {
    if (value.length < 2 || value.length > 50) {
      toast({
        title: "Invalid input",
        description: "Value must be between 2 and 50 characters long.",
        variant: "destructive",
      });
      return;
    }
  
    setUserData({
      ...userData,
      [field]: value,
    });
  };

  
  const renderEditField = (field: string, value: string) => {
    return isEditing[field] ? (
      <input
        type="text"
        value={value}
        onChange={(e) => handleInputChange(field, e.target.value)}
        onBlur={() => toggleEdit(field)}
        className="border rounded p-1"
      />
    ) : (
      value
    );
  };
  return (
    <Table>
      <TableCaption>User Details</TableCaption>
      <TableHeader className="bg-blue-50">
        <TableRow>
          <TableHead>Field</TableHead>
          <TableHead>Value</TableHead>
          <TableHead>Actions</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {userData ? (
          <>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>{userData.id}</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>First Name</TableCell>
              <TableCell>
                {renderEditField("firstName", userData.firstName)}
              </TableCell>
              <TableCell>
                <Button onClick={() => toggleEdit("firstName")}>Edit</Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Last Name</TableCell>
              <TableCell>
                {renderEditField("lastName", userData.lastName)}
              </TableCell>
              <TableCell>
                <Button onClick={() => toggleEdit("lastName")}>Edit</Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Login</TableCell>
              <TableCell>{renderEditField("login", userData.login)}</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Role</TableCell>
              <TableCell>{userData.role}</TableCell>
            </TableRow>
          </>
        ) : (
          <TableRow>
            <TableCell colSpan={3}>No user data available</TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  );
};

export default UserDetailsTable;
