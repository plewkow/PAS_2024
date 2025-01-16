import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { User } from "@/types";
import { useNavigate } from "react-router";

const UsersTable = ({ users }: { users: User[] }) => {
  const navigate = useNavigate();
  const clickHandler = (id: number) => {
    console.log(`User with id ${id} has been clicked.`);
    navigate(`/users/${id}`);
  };

  return (
    <Table>
      <TableCaption>A list of users.</TableCaption>
      <TableHeader className="bg-blue-50">
        <TableRow>
          <TableHead className="w-[100px]">User ID</TableHead>
          <TableHead>Login</TableHead>
          <TableHead>First Name</TableHead>
          <TableHead>Last Name</TableHead>
          <TableHead>Role</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {users.length > 0 ? (
          users.map((user: User) => (
            <TableRow key={user.id} onClick={() => clickHandler(user.id)}>
              <TableCell>{user.id}</TableCell>
              <TableCell>{user.login}</TableCell>
              <TableCell>{user.firstName}</TableCell>
              <TableCell>{user.lastName}</TableCell>
              <TableCell>{user.role}</TableCell>
            </TableRow>
          ))
        ) : (
          <TableRow>
            <TableCell colSpan={4}>No users found.</TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  );
};

export default UsersTable;