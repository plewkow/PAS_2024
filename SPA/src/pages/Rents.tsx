import ItemsTable from "@/components/ItemsTable";
import { Item } from "@/types";
import { useEffect, useState } from "react";

const Rents = () => {
  const [items, setItems] = useState<Item[]>([]);

  useEffect(() => {
    const fetchItems = async () => {
      const response = await fetch("/api/items");
      const result = await response.json();
      setItems(result);
    };

    fetchItems();
  }, []);

  const musicItems = items.filter(item => item.itemType === "music");
  const comicsItems = items.filter(item => item.itemType === "comics");
  const movieItems = items.filter(item => item.itemType === "movie");

  return (
    <div>
      <h1 className="text-bold text-5xl text-center my-8">List of Items</h1>
      <ItemsTable
        musicItems={musicItems}
        comicsItems={comicsItems}
        movieItems={movieItems}
      />
    </div>
  );
};

export default Rents;
