import UsersTable from "@/components/UsersTable";
import { User } from "@/types";
import { useEffect, useState } from "react";

const Users = () => {
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    const fetchUsers = async () => {
      const response = await fetch("/api/users");
      const result = await response.json();
      setUsers(result);
    };

    fetchUsers();
  }, []);

  return (
    <div>
      <h1 className="text-bold text-5xl text-center my-8">List of Users</h1>
      <UsersTable users={users} />
    </div>
  );
};

export default Users;
