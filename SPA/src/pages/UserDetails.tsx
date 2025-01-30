import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router";
import { Rent, Item, User } from "@/types";
import ActiveUserRentsTable from "@/components/ActiveUserRentsTable";
import InactiveUserRentsTable from "@/components/InactiveUserRentsTable";
import Profile from "@/components/Profile";
import { useToast } from "@/hooks/use-toast";
import apiClient from "@/lib/apiClient";

const UserDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { toast } = useToast();
  const [user, setUser] = useState<User | null>(null);
  const [activeRents, setActiveRents] = useState<Rent[]>([]);
  const [inactiveRents, setInactiveRents] = useState<Rent[]>([]);
  const [items, setItems] = useState<Item[]>([]);

  useEffect(() => {
    if (!id) return;

    const fetchUserDetails = async () => {
      try {
        const { data } = await apiClient.get(`/users/${id}`);
        setUser(data);
      } catch (err) {
        console.error("Error fetching user details:", err);
      }
    };

    const fetchActiveRents = async () => {
      try {
        const { data } = await apiClient.get(`/rents/active/client/${id}`);
        setActiveRents(data);
      } catch (err) {
        console.error("Error fetching active rents:", err);
      }
    };

    const fetchInactiveRents = async () => {
      try {
        const { data } = await apiClient.get(`/rents/inactive/client/${id}`);
        setInactiveRents(data);
      } catch (error) {
        console.error("Error fetching inactive rents:", error);
      }
    };

    const fetchItems = async () => {
      try {
        const {data} = await apiClient.get("/items");
        setItems(data);
      } catch (error) {
        console.error("Error fetching items:", error);
      }
    };

    fetchUserDetails();
    fetchActiveRents();
    fetchInactiveRents();
    fetchItems();
  }, [id, navigate, toast]);

  return (
    <div className="px-16 py-8">
      {user && <Profile user={user} />}

      <ActiveUserRentsTable rents={activeRents} items={items} />

      <InactiveUserRentsTable rents={inactiveRents} items={items} />
    </div>
  );
};

export default UserDetails;
