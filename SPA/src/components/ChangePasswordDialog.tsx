import { FC } from "react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogClose,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { useToast } from "@/hooks/use-toast";
import { useState } from "react";
import apiClient from "@/lib/apiClient";

interface ChangePasswordDialogProps {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

const ChangePasswordDialog: FC<ChangePasswordDialogProps> = ({
  isOpen,
  setIsOpen,
}) => {
  const { toast } = useToast();
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const handlePasswordChange = async () => {
    if (newPassword !== confirmPassword) {
      toast({
        title: "Error",
        description: "New passwords do not match",
        variant: "destructive",
      });
      return;
    }

    setLoading(true);

    try {
      await apiClient.put("/users/me/changePassword", {
        currentPassword,
        newPassword,
      });

      toast({
        title: "Success",
        description: "Password changed successfully!",
        variant: "success",
      });

      setIsOpen(false);
      setNewPassword("");
      setCurrentPassword("");
      setConfirmPassword("");
    } catch (error) {
      toast({
        title: "Error",
        description: "Failed to change password",
        variant: "destructive",
      });
    }

    setLoading(false);
  };

  return (
    <Dialog open={isOpen} onOpenChange={setIsOpen}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Change Password</DialogTitle>
          <DialogDescription>
            Enter your current and new password
          </DialogDescription>
        </DialogHeader>
        <div className="space-y-2">
          <Input
            type="password"
            placeholder="Current Password"
            value={currentPassword}
            onChange={(e) => setCurrentPassword(e.target.value)}
          />
          <Input
            type="password"
            placeholder="New Password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
          <Input
            type="password"
            placeholder="Confirm New Password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        <div className="flex justify-between mt-4">
          <DialogClose asChild>
            <Button variant="outline">Cancel</Button>
          </DialogClose>
          <Button onClick={handlePasswordChange} disabled={loading}>
            {loading ? "Changing..." : "Confirm"}
          </Button>
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default ChangePasswordDialog;
