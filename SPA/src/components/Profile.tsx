import { User } from "@/types";
import { Button } from "@/components/ui/button";
import { useState, useEffect } from "react";
import { useToast } from "@/hooks/use-toast";
import jwt_decode from "jwt-decode";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogClose,
} from "@/components/ui/dialog";
import UserDetailsTable from "./UserDetailsTable";
import apiClient from "@/lib/apiClient";

interface UserDetailsProps {
  user: User | null;
}

const Profile = ({ user }: UserDetailsProps) => {
  const { toast } = useToast();

  const [userData, setUserData] = useState<User | null>(user);
  const [loading, setLoading] = useState(false);
  const [isUserActive, setIsUserActive] = useState(user?.isActive || false);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [actionType, setActionType] = useState<
    "activate" | "deactivate" | "save" | null
  >(null);

  const [userRole, setUserRole] = useState<string | null>(null);

  useEffect(() => {
    const token = window.localStorage.getItem("jwt");

    if (token) {
      try {
        const decoded: any = jwt_decode(token);
        setUserRole(decoded.role);
      } catch (error) {
        console.error("Error decoding token", error);
      }
    }
  }, []);

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

    if (userData) {
      let endpoint = "";
      let successMessage = "";
      let errorMessage = "";

      if (actionType === "activate") {
        endpoint = `/users/activate/${userData.id}`;
        successMessage = "User Activated";
        errorMessage = "Failed to activate the user.";
      } else if (actionType === "deactivate") {
        endpoint = `/users/deactivate/${userData.id}`;
        successMessage = "User Deactivated";
        errorMessage = "Failed to deactivate the user.";
      } else if (actionType === "save") {
        endpoint = `/users/${userData.id}`;
        successMessage = "User Details Updated";
        errorMessage = "Failed to save user details.";
      }

      try {
        await apiClient.put(
          endpoint,
          actionType === "save" ? userData : undefined
        );

        toast({
          title: successMessage,
          description:
            actionType === "save"
              ? "User details have been successfully updated."
              : actionType === "activate"
              ? "User has been successfully activated."
              : actionType === "deactivate"
              ? "User has been successfully deactivated."
              : null,
          variant: "success",
        });
        if (actionType === "activate") {
          setIsUserActive(true);
        } else if (actionType === "deactivate") {
          setIsUserActive(false);
        }
      } catch (err) {
        toast({
          title:
            actionType!.charAt(0).toUpperCase() +
            actionType?.slice(1) +
            " Error",
          description: errorMessage,
          variant: "destructive",
        });
      }
    }

    setLoading(false);
    setIsDialogOpen(false);
  };

  return (
    <div>
      {/* @ts-ignore */}
      <UserDetailsTable userData={userData} setUserData={setUserData} />

      {userData && (
        <div className="flex justify-center mt-4 space-x-4">
          {(userRole === "ADMIN" || userRole === "MANAGER") && (
            <>
              <Button disabled={isUserActive} onClick={onActivate}>
                Activate account
              </Button>
              <Button disabled={!isUserActive} onClick={onDeactivate}>
                Deactivate account
              </Button>
            </>
          )}
          <Button onClick={onSave}>Save changes</Button>
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
            <Button onClick={handleDialogConfirm} disabled={loading}>
              Confirm
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default Profile;
