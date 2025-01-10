import { User } from "@/types";
import {
  Table,
  TableBody,
  TableCell,
  TableRow,
  TableHead,
  TableHeader,
  TableCaption,
} from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { useState } from "react";
import { useToast } from "@/hooks/use-toast";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogClose,
} from "@/components/ui/dialog";

interface UserDetailsProps {
  user: User | null;
}

const UserDetailTable = ({ user }: UserDetailsProps) => {
  const { toast } = useToast();
  const [isEditing, setIsEditing] = useState<{ [key: string]: boolean }>({
    firstName: false,
    lastName: false,
  });

  const [userData, setUserData] = useState<User | null>(user);
  const [loading, setLoading] = useState(false);

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [actionType, setActionType] = useState<"activate" | "deactivate" | "save" | null>(null);

  const toggleEdit = (field: string) => {
    setIsEditing((prev) => ({
      ...prev,
      [field]: !prev[field],
    }));
  };

  const handleInputChange = (field: string, value: string) => {
    if (userData) {
      setUserData({
        ...userData,
        [field]: value,
      });
    }
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

  const onActivate = () => {
    if (userData?.role === "ACTIVE") {
      toast({
        title: "Activation Error",
        description: "This account is already active!",
        variant: "destructive",
      });
      return;
    }
    setActionType("activate");
    setIsDialogOpen(true);
  };

  const onDeactivate = () => {
    if (userData?.role === "INACTIVE") {
      toast({
        title: "Deactivation Error",
        description: "This account is already inactive!",
        variant: "destructive",
      });
      return;
    }
    setActionType("deactivate");
    setIsDialogOpen(true);
  };

  const onSave = () => {
    setActionType("save");
    setIsDialogOpen(true);
  };
  
  const handleDialogConfirm = async () => {
    setLoading(true);

    if (actionType === "activate" && userData) {
      try {
        const response = await fetch(`/api/users/activate/${userData.id}`, {
          method: "PUT",
        });
        if (!response.ok) throw new Error("Failed to activate the user.");
        toast({
          title: "User Activated",
          description: "User has been successfully activated.",
          variant: "default",
        });
      } catch (error) {
        toast({
          title: "Activation Error",
          description: "Failed to activate the user.",
          variant: "destructive",
        });
      }
    } else if (actionType === "deactivate" && userData) {
      try {
        const response = await fetch(`/api/users/deactivate/${userData.id}`, {
          method: "PUT",
        });
        if (!response.ok) throw new Error("Failed to deactivate the user.");
        toast({
          title: "User Deactivated",
          description: "User has been successfully deactivated.",
          variant: "default",
        });
      } catch (error) {
        toast({
          title: "Deactivation Error",
          description: "Failed to deactivate the user.",
          variant: "destructive",
        });
      }
    } else if (actionType === "save" && userData) {
      try {
        const response = await fetch(`/api/users/${userData.id}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(userData),
        });
        if (!response.ok) throw new Error("Failed to save user details.");
        toast({
          title: "User Details Updated",
          description: "User details have been successfully updated.",
          variant: "default",
        });
      } catch (error) {
        toast({
          title: "Error",
          description: "Failed to save user details.",
          variant: "destructive",
        });
      }
    }

    setLoading(false);
    setIsDialogOpen(false);
  };

  return (
    <div className="">
      <Table>
        <TableCaption>User Details</TableCaption>
        <TableHeader>
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
                <TableCell>
                  {renderEditField("login", userData.login)}
                </TableCell>
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

      {userData && (
        <div className="flex justify-center mt-4 space-x-4">
          <Button onClick={onActivate}>
            Activate account
          </Button>
          <Button onClick={onDeactivate}>
            Deactivate account
          </Button>
          <Button onClick={onSave}>
            Save changes
          </Button>
        </div>
      )}

      <Dialog open={isDialogOpen} onOpenChange={() => setIsDialogOpen(false)}>
        <DialogTrigger />
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {actionType === "activate"
                ? "Activate User"
                : actionType === "deactivate"
                ? "Deactivate User"
                : actionType === "save"
                ? "Save Changes"
                : null}
            </DialogTitle>
            <DialogDescription>
              {actionType === "activate"
                ? "This action will activate the user."
                : actionType === "deactivate"
                ? "This action will deactivate the user."
                : actionType === "save"
                ? "This action will save the changes to the user details."
                : null}
            </DialogDescription>
          </DialogHeader>
          <div className="flex justify-between mt-4">
            <DialogClose asChild>
              <Button variant="outline">Cancel</Button>
            </DialogClose>
            <Button
              onClick={handleDialogConfirm}
              disabled={loading}
            >
              Confirm
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default UserDetailTable;