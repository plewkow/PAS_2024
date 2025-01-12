import UsersTable from "@/components/UsersTable";
import { User } from "@/types";
import { useEffect, useState } from "react";
import { Input } from "@/components/ui/input";

const Users = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [searchId, setSearchId] = useState<string>("");
  const [filteredUsers, setFilteredUsers] = useState<User[]>([]);

  useEffect(() => {
    const fetchUsers = async () => {
      const response = await fetch("/api/users");
      const result = await response.json();
      setUsers(result);
      setFilteredUsers(result);
    };

    fetchUsers();
  }, []);

  useEffect(() => {
    const filtered = users.filter((user) =>
      // user.id.toString().includes(searchId)
      user.id.toString().startsWith(searchId)
  );
    setFilteredUsers(filtered);
  }, [searchId, users]);

  const handleSearchIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchId(event.target.value);
  }

  return (
    <div className="px-16 pb-8">
      <h1 className="text-bold text-5xl text-center my-8">List of Users</h1>
      <div className="flex justify-center mb-4">
        <Input
            placeholder="Search by ID"
            value={searchId}
            onChange={handleSearchIdChange}
            className="border rounded px-2 py-1 w-1/3"
          />
      </div>
      <UsersTable users={filteredUsers} />
    </div>
  );
};

export default Users;