import ItemsTable from "@/components/ItemsTable";
import RentsTable from "@/components/RentsTable"; 
import { Item, Rent } from "@/types";
import { useEffect, useState } from "react";
import { useToast } from "@/hooks/use-toast"; 

const Rents = () => {
  const [items, setItems] = useState<Item[]>([]);
  const [rents, setRents] = useState<Rent[]>([]); 
  const { toast } = useToast(); 

  useEffect(() => {
    const fetchItems = async () => {
      const response = await fetch("/api/items");
      const result = await response.json();
      setItems(result);
    };

    fetchItems();
  }, []);

  useEffect(() => {
    const fetchRents = async () => {
      const response = await fetch("/api/rents/active");
      const result = await response.json();
      setRents(result); 
    };

    fetchRents();
  }, []);

  const musicItems = items.filter(item => item.itemType === "music");
  const comicsItems = items.filter(item => item.itemType === "comics");
  const movieItems = items.filter(item => item.itemType === "movie");

  const handleRentItem = async (itemId: number) => {
    const userId = localStorage.getItem("userID");

    if (!userId) {
      toast({
        title: "Error",
        description: "You need to be logged in to rent an item.",
        variant: "destructive",
      });
      return;
    }

    const rentData = {
      clientId: userId,
      itemId: itemId,
    };

    const response = await fetch("/api/rents", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(rentData),
    });

    if (response.status === 409) {
      toast({
        title: "Error",
        description: "This item is already rented.",
        variant: "destructive",
      });
    } else if (!response.ok) {
      const result = await response.json();
      toast({
        title: "Rent failed",
        description: result.message || "Something went wrong.",
        variant: "destructive",
      });
    } else {
      const result = await response.json();
      toast({
        title: "Success",
        description: `Item rented successfully! Rent ID: ${result.id}`,
        variant: "default",
      });

      setItems(prevItems =>
        prevItems.map(item =>
          item.id === itemId ? { ...item, available: false } : item
        )
      );

      setRents(prevRents => [
        ...prevRents,
        { ...result, clientId: userId, itemId: itemId, beginTime: new Date() }
      ]);
    }
  };

  const handleReturnItem = async (rentId: number, itemId: number) => {
    const userId = localStorage.getItem("userID");

    if (!userId) {
      toast({
        title: "Error",
        description: "You need to be logged in to rent an item.",
        variant: "destructive",
      });
      return;
    }


    const response = await fetch(`/api/rents/return/${rentId}`, {
      method: "PUT",
    });
  
    if (response.ok) {
      toast({
        title: "Success",
        description: "Item returned successfully.",
        variant: "default",
      });
  
      setRents(prevRents => prevRents.filter(rent => rent.id !== rentId));
  
      setItems(prevItems =>
        prevItems.map(item =>
          item.id === itemId
            ? { ...item, available: true }
            : item
        )
      );
    } else {
      const result = await response.json();
      toast({
        title: "Error",
        description: result.message || "Something went wrong.",
        variant: "destructive",
      });
    }
  };
  
  return (
    <div>
      <h1 className="text-bold text-5xl text-center my-8">Lista przedmiotów</h1>
      <ItemsTable
        musicItems={musicItems}
        comicsItems={comicsItems}
        movieItems={movieItems}
        onRentItem={handleRentItem} 
      />

      <h1 className="text-bold text-5xl text-center my-8 mt-12">Lista wypożyczeń</h1>
      <RentsTable rents={rents} onReturnItem={handleReturnItem}/>
    </div>
  );
};

export default Rents;