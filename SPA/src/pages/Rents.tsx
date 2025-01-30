import { useState, useEffect } from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogClose,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import ItemsTable from "@/components/ItemsTable";
import RentsTable from "@/components/RentsTable";
import { DecodedToken, Item, Rent } from "@/types";
import { useToast } from "@/hooks/use-toast";
import { comicsColumns, movieColumns, musicColumns } from "@/constants";
import { useNavigate } from "react-router";
import apiClient from "@/lib/apiClient";
import jwtDecode from "jwt-decode";

const Rents = () => {
  const [items, setItems] = useState<Item[]>([]);
  const [rents, setRents] = useState<Rent[]>([]);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [dialogAction, setDialogAction] = useState<"rent" | "return" | null>(
    null
  );
  const [selectedItemId, setSelectedItemId] = useState<number | null>(null);
  const [selectedRentId, setSelectedRentId] = useState<number | null>(null);
  const { toast } = useToast();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const { data } = await apiClient.get("/items");
        setItems(data);
      } catch (err) {
        console.error(err);
      }
    };

    const fetchRents = async () => {
      try {
        const { data } = await apiClient.get("/rents/active");
        setRents(data);
      } catch (err) {
        console.error(err);
      }
    };

    fetchRents();
    fetchItems();
  }, [navigate]);

  const handleOpenDialog = (
    action: "rent" | "return",
    itemId: number,
    rentId?: number
  ) => {
    setDialogAction(action);
    setSelectedItemId(itemId);
    setSelectedRentId(rentId || null);
    setIsDialogOpen(true);
  };

  const handleDialogConfirm = async () => {
    setIsDialogOpen(false);

    if (dialogAction === "rent" && selectedItemId !== null) {
      await handleRentItem(selectedItemId);
    } else if (dialogAction === "return" && selectedRentId !== null) {
      await handleReturnItem(selectedRentId, selectedItemId!);
    }
  };

  const handleRentItem = async (itemId: number) => {
    const userId = jwtDecode<DecodedToken>(localStorage.getItem("jwt")!).userId;

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
      const { data } = await apiClient.post("/rents", rentData);
      console.log(data);
      toast({
        title: "Success",
        description: `Item rented successfully! Rent ID: ${data.id}`,
        variant: "success",
      });

      setItems((prevItems) =>
        prevItems.map((item) =>
          item.id === itemId ? { ...item, available: false } : item
        )
      );

      setRents((prevRents) => [
        ...prevRents,
        {
          ...data,
          clientId: userId,
          itemId: itemId,
          beginTime: new Date(),
        },
      ]);
    } catch (err) {
      toast({
        title: "Error",
        description:
          // @ts-ignore
          err.response.data || "Something went wrong while renting the item.",
        variant: "destructive",
      });
    }
  };

  const handleReturnItem = async (rentId: number, itemId: number) => {
    const userId = jwtDecode<DecodedToken>(localStorage.getItem("jwt")!).userId;

    if (!userId) {
      toast({
        title: "Error",
        description: "You need to be logged in to return an item.",
        variant: "destructive",
      });
      return;
    }

    try {
      await apiClient.put(`/rents/return/${rentId}`);

      toast({
        title: "Success",
        description: "Item returned successfully.",
        variant: "success",
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
        description:
          // @ts-ignore
          err.response.data || "Something went wrong while returning the item.",
        variant: "destructive",
      });
    }
  };

  return (
    <div className="px-16 pb-8">
      <h1 className="text-bold text-3xl my-8">Items List</h1>
      <div className="space-y-12">
        <ItemsTable
          items={items.filter((item) => item.itemType === "music")}
          title="Music Items"
          columns={musicColumns}
          onRentItem={(itemId) => handleOpenDialog("rent", itemId)}
        />
        <ItemsTable
          items={items.filter((item) => item.itemType === "comics")}
          title="Comics Items"
          columns={comicsColumns}
          onRentItem={(itemId) => handleOpenDialog("rent", itemId)}
        />
        <ItemsTable
          items={items.filter((item) => item.itemType === "movie")}
          title="Movie Items"
          columns={movieColumns}
          onRentItem={(itemId) => handleOpenDialog("rent", itemId)}
        />
      </div>
      <h1 className="text-bold text-3xl my-8 mt-12">Rents List</h1>
      <RentsTable
        rents={rents}
        onReturnItem={async (rentId, itemId) => {
          handleOpenDialog("return", itemId, rentId);
          return Promise.resolve();
        }}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {dialogAction === "rent" ? "Rent Item" : "Return Item"}
            </DialogTitle>
            <DialogDescription>
              {dialogAction === "rent"
                ? "Are you sure you want to rent this item?"
                : "Are you sure you want to return this item?"}
            </DialogDescription>
          </DialogHeader>
          <div className="flex justify-between mt-4">
            <DialogClose asChild>
              <Button variant="outline">Cancel</Button>
            </DialogClose>
            <Button onClick={handleDialogConfirm}>Confirm</Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default Rents;
