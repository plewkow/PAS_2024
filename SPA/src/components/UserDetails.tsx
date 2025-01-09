import { User } from "@/types";
import {
  Table,
  TableBody,
  TableCell,
  TableRow,
  TableHead,
  TableCaption,
  TableHeader
} from "@/components/ui/table";

interface UserDetailsProps {
  user: User | null;
}

const UserDetailTable = ({ user }: UserDetailsProps) => {
  return (
    <div className="my-6">
      <Table>
        <TableCaption>User Details</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead>Field</TableHead>
            <TableHead>Value</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {user ? (
            <>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>{user.id}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>First Name</TableCell>
                <TableCell>{user.firstName}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>Last Name</TableCell>
                <TableCell>{user.lastName}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>Login</TableCell>
                <TableCell>{user.login}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>Role</TableCell>
                <TableCell>{user.role}</TableCell>
              </TableRow>
            </>
          ) : (
            <TableRow>
              <TableCell colSpan={2}>No user data available</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
};

export default UserDetailTable;