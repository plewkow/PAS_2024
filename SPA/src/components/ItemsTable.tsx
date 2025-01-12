import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Column, Item } from "@/types";
import { Button } from "./ui/button";
import { booleanFields } from "@/constants";

interface ItemsTableProps {
  items: Item[];
  title: string;
  columns: Column[];
  onRentItem: (itemId: number) => void;
}

const ItemsTable: React.FC<ItemsTableProps> = ({
  items,
  title,
  columns,
  onRentItem,
}) => {
  return (
    <Table>
      <TableCaption>{title}</TableCaption>
      <TableHeader className="bg-blue-50">
        <TableRow>
          {columns.map((col) => (
            <TableHead key={col.field}>{col.label}</TableHead>
          ))}
        </TableRow>
      </TableHeader>
      <TableBody>
        {items.length > 0 ? (
          items.map((item) => (
            <TableRow key={item.id} className={`${item.available ? "bg-green-300" : "bg-red-300"}`}>
              {columns.map((col) => (
                <TableCell key={col.field}>
                  {booleanFields.includes(col.field)
                    ? item[col.field]
                      ? "Yes"
                      : "No"
                    : item[col.field]}
                </TableCell>
              ))}
              <TableCell>
                <Button
                  disabled={!item.available}
                  onClick={() => onRentItem(item.id)}
                >
                  Rent
                </Button>
              </TableCell>
            </TableRow>
          ))
        ) : (
          <TableRow>
            <TableCell colSpan={columns.length + 1}>
              No {title.toLowerCase()} items found.
            </TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  );
};

export default ItemsTable;
