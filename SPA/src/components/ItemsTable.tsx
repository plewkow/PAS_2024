import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table";
  import { Item } from "@/types";

  interface ItemsTableProps {
    musicItems: Item[];
    movieItems: Item[];
    comicsItems: Item[];
    onRentItem: (itemId: number) => void;
  }
  
  const ItemsTable: React.FC<ItemsTableProps> = ({ musicItems, comicsItems, movieItems, onRentItem }) => {    
    return (
      <div>
      <Table>
        <TableCaption>Music Items</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Item Name</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Genre</TableHead>
            <TableHead>Vinyl</TableHead>
            <TableHead>Available</TableHead>
            <TableHead>Action</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {musicItems.length > 0 ? (
            musicItems.map((item) => (
              <TableRow key={item.id}>
                <TableCell>{item.id}</TableCell>
                <TableCell>{item.itemName}</TableCell>
                <TableCell>{item.basePrice} PLN</TableCell>
                <TableCell>{item.genre}</TableCell>
                <TableCell>{item.vinyl ? "Yes" : "No"}</TableCell>
                <TableCell>{item.available ? "Yes" : "No"}</TableCell>
                <TableCell>
                  <button onClick={() => onRentItem(item.id)}>Rent</button>
                </TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={5}>No music items found.</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>

      <Table>
        <TableCaption>Comics Items</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Item Name</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Pages</TableHead>
            <TableHead>Available</TableHead>
            <TableHead>Action</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {comicsItems.length > 0 ? (
            comicsItems.map((item) => (
              <TableRow key={item.id}>
                <TableCell>{item.id}</TableCell>
                <TableCell>{item.itemName}</TableCell>
                <TableCell>{item.basePrice} PLN</TableCell>
                <TableCell>{item.pageNumber}</TableCell>
                <TableCell>{item.available ? "Yes" : "No"}</TableCell>
                <TableCell>
                  <button onClick={() => onRentItem(item.id)}>Rent</button>
                </TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={4}>No comics items found.</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>

      <Table>
        <TableCaption>Movie Items</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Item Name</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Duration (min)</TableHead>
            <TableHead>Casette</TableHead>
            <TableHead>Available</TableHead>
            <TableHead>Action</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {movieItems.length > 0 ? (
            movieItems.map((item) => (
              <TableRow key={item.id}>
                <TableCell>{item.id}</TableCell>
                <TableCell>{item.itemName}</TableCell>
                <TableCell>{item.basePrice} PLN</TableCell>
                <TableCell>{item.minutes} min</TableCell>
                <TableCell>{item.casette ? "Yes" : "No"}</TableCell>
                <TableCell>{item.available ? "Yes" : "No"}</TableCell>
                <TableCell>
                  <button onClick={() => onRentItem(item.id)}>Rent</button>
                </TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={5}>No movie items found.</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
      </div>
    );
  };
  
  export default ItemsTable;