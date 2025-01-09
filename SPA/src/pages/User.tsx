import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Rent, Item, User } from "@/types";
import ActiveUserRentsTable from "@/components/ActiveUserRentsTable";
import InactiveUserRentsTable from "@/components/InactiveUserRentsTable";
import UserDetailTable from "@/components/UserDetails";

const UserDetails = () => {
  const { id } = useParams();
  const [user, setUser] = useState<User | null>(null);
  const [activeRents, setActiveRents] = useState<Rent[]>([]);
  const [inactiveRents, setInactiveRents] = useState<Rent[]>([]);
  const [items, setItems] = useState<Item[]>([]);

  useEffect(() => {
    if (!id) return;

    const fetchUserDetails = async () => {
      const userResponse = await fetch(`/api/users/${id}`);
      const userData = await userResponse.json();
      setUser(userData);
    };

    const fetchActiveRents = async () => {
      const activeRentsResponse = await fetch(`/api/rents/active/client/${id}`);
      const activeRentsData = await activeRentsResponse.json();
      setActiveRents(activeRentsData);
    };

    const fetchInactiveRents = async () => {
      const inactiveRentsResponse = await fetch(`/api/rents/inactive/client/${id}`);
      const inactiveRentsData = await inactiveRentsResponse.json();
      setInactiveRents(inactiveRentsData);
    };

    const fetchItems = async () => {
      const itemsResponse = await fetch(`/api/items`);
      const itemsData = await itemsResponse.json();
      setItems(itemsData);
    };

    fetchUserDetails();
    fetchActiveRents();
    fetchInactiveRents();
    fetchItems();
  }, [id]);

  return (
    <div>
      {user && <UserDetailTable user={user} />}

      <ActiveUserRentsTable rents={activeRents} items={items} />

      <InactiveUserRentsTable rents={inactiveRents} items={items} />
    </div>
  );
};

export default UserDetails;