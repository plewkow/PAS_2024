import { Rent, Item } from "@/types";
import {
  Table,
  TableBody,
  TableCell,
  TableRow,
  TableHead,
  TableCaption,
  TableHeader
} from "@/components/ui/table";
import { format } from 'date-fns';

interface ActiveUserRentsTableProps {
  rents: Rent[];
  items: Item[];
}

const ActiveUserRentsTable = ({ rents, items }: ActiveUserRentsTableProps) => {
    return (
      <Table>
        <TableCaption>Active rents.</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Item ID</TableHead>
            <TableHead>Item Name</TableHead>
            <TableHead>Begin time</TableHead>
            <TableHead>End time</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {rents.length > 0 ? (
            rents.map((rent: Rent) => {
              const item = items.find(i => i.id === rent.itemId);
              return (
                <TableRow key={rent.id}>
                  <TableCell>{rent.id}</TableCell>
                  <TableCell>{rent.itemId}</TableCell>
                  <TableCell>{item ? item.itemName : "Item not found"}</TableCell>
                  <TableCell>{rent.beginTime ? format(new Date(rent.beginTime), 'yyyy-MM-dd HH:mm') : 'Not available'}</TableCell>
                  <TableCell>{rent.endTime ? format(new Date(rent.endTime), 'yyyy-MM-dd HH:mm') : 'Item is still rented'}</TableCell>
                </TableRow>
              );
            })
          ) : (
            <TableRow>
              <TableCell colSpan={6}>No rents found.</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    );
  };

export default ActiveUserRentsTable;