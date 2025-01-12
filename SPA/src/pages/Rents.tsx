import ItemsTable from "@/components/ItemsTable";
import RentsTable from "@/components/RentsTable";
import { Item, Rent } from "@/types";
import { useEffect, useState } from "react";
import { useToast } from "@/hooks/use-toast";
import { comicsColumns, movieColumns, musicColumns } from "@/constants";

const Rents = () => {
  const [items, setItems] = useState<Item[]>([]);
  const [rents, setRents] = useState<Rent[]>([]);
  const { toast } = useToast();

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await fetch("/api/items");
        if (!response.ok) {
          toast({
            title: "Error",
            description: `Failed to fetch items: ${response.statusText}`,
            variant: "destructive",
          });
          return;
        }
        const result = await response.json();
        setItems(result);
      } catch (err) {
        toast({
          title: "Error",
          description:
            "Something went wrong while fetching items. Please try again later.",
          variant: "destructive",
        });
        console.error(err);
      }
    };
    const fetchRents = async () => {
      try {
        const response = await fetch("/api/rents/active");
        if (!response.ok) {
          toast({
            title: "Error",
            description: `Failed to fetch rents: ${response.statusText}`,
            variant: "destructive",
          });
          return;
        }
        const result = await response.json();
        setRents(result);
      } catch (err) {
        toast({
          title: "Error",
          description:
            "Something went wrong while fetching rents. Please try again later.",
          variant: "destructive",
        });
        console.error(err);
      }
    };

    fetchRents();
    fetchItems();
  }, []);

  const musicItems = items.filter((item) => item.itemType === "music");
  const comicsItems = items.filter((item) => item.itemType === "comics");
  const movieItems = items.filter((item) => item.itemType === "movie");

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

    try {
      const response = await fetch("/api/rents", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(rentData),
      });

      if (!response.ok) {
        toast({
          title: "Rent failed",
          description: "Fail to rent this item.",
          variant: "destructive",
        });
        return;
      }

      const result = await response.json();
      toast({
        title: "Success",
        description: `Item rented successfully! Rent ID: ${result.id}`,
        variant: "default",
      });

      setItems((prevItems) =>
        prevItems.map((item) =>
          item.id === itemId ? { ...item, available: false } : item
        )
      );

      setRents((prevRents) => [
        ...prevRents,
        {
          ...result,
          clientId: userId,
          itemId: itemId,
          beginTime: new Date(),
        },
      ]);
    } catch (err) {
      toast({
        title: "Error",
        description: "Something went wrong while renting the item.",
        variant: "destructive",
      });
      console.error(err);
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
    try {
      const response = await fetch(`/api/rents/return/${rentId}`, {
        method: "PUT",
      });

      if (!response.ok) {
        const result = await response.json();
        toast({
          title: "Error",
          description: result.message || "Something went wrong.",
          variant: "destructive",
        });
        return;
      }

      toast({
        title: "Success",
        description: "Item returned successfully.",
        variant: "default",
      });

      setRents((prevRents) => prevRents.filter((rent) => rent.id !== rentId));

      setItems((prevItems) =>
        prevItems.map((item) =>
          item.id === itemId ? { ...item, available: true } : item
        )
      );
    } catch (err) {
      toast({
        title: "Error",
        description: "Something went wrong while returning the item.",
        variant: "destructive",
      });
      console.error(err);
    }
  };

  return (
    <div className="px-16 pb-8">
      <h1 className="text-bold text-3xl my-8">Lista przedmiotów</h1>

      <ItemsTable
        items={musicItems}
        title="Music Items"
        columns={musicColumns}
        onRentItem={handleRentItem}
      />
      <ItemsTable
        items={comicsItems}
        title="Music Items"
        columns={comicsColumns}
        onRentItem={handleRentItem}
      />
      <ItemsTable
        items={movieItems}
        title="Music Items"
        columns={movieColumns}
        onRentItem={handleRentItem}
      />

      <h1 className="text-bold text-3xl my-8 mt-12">
        Lista wypożyczeń
      </h1>
      <RentsTable rents={rents} onReturnItem={handleReturnItem} />
    </div>
  );
};

export default Rents;
