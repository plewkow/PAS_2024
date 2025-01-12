import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Rent, Item, User } from "@/types";
import ActiveUserRentsTable from "@/components/ActiveUserRentsTable";
import InactiveUserRentsTable from "@/components/InactiveUserRentsTable";
import Profile from "@/components/Profile";

const UserDetails = () => {
  const { id } = useParams();
  const [user, setUser] = useState<User | null>(null);
  const [activeRents, setActiveRents] = useState<Rent[]>([]);
  const [inactiveRents, setInactiveRents] = useState<Rent[]>([]);
  const [items, setItems] = useState<Item[]>([]);

  useEffect(() => {
    if (!id) return;

    const fetchUserDetails = async () => {
      try {
        const response = await fetch(`/api/users/${id}`);
        if (!response.ok) {
          throw new Error("Failed to fetch user details");
        }
        const userData = await response.json();
        setUser(userData);
      } catch (err) {
        console.error("Error fetching user details:", err);
      }
    };

    const fetchActiveRents = async () => {
      try {
        const response = await fetch(`/api/rents/active/client/${id}`);
        if (!response.ok) {
          throw new Error("Failed to fetch active rents");
        }
        const activeRentsData = await response.json();
        setActiveRents(activeRentsData);
      } catch (err) {
        console.error("Error fetching active rents:", err);
      }
    };

    const fetchInactiveRents = async () => {
      try {
        const response = await fetch(`/api/rents/inactive?clientId=${id}`);
        if (!response.ok) {
          throw new Error("Failed to fetch inactive rents");
        }
        const inactiveRentsData = await response.json();
        setInactiveRents(inactiveRentsData);
      } catch (error) {
        console.error("Error fetching inactive rents:", error);
      }
    };

    const fetchItems = async () => {
      try {
        const response = await fetch(`/api/items`);
        if (!response.ok) {
          throw new Error("Failed to fetch items");
        }
        const itemsData = await response.json();
        setItems(itemsData);
      } catch (error) {
        console.error("Error fetching items:", error);
      }
    };

    fetchUserDetails();
    fetchActiveRents();
    fetchInactiveRents();
    fetchItems();
  }, [id]);

  return (
    <div className="px-16 py-8">
      {user && <Profile user={user} />}

      <ActiveUserRentsTable rents={activeRents} items={items} />

      <InactiveUserRentsTable rents={inactiveRents} items={items} />
    </div>
  );
};

export default UserDetails;
