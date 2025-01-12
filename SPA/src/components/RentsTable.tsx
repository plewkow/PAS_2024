import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Rent } from "@/types";
import { Button } from "@/components/ui/button";
import { format } from "date-fns";

interface RentsTableProps {
  rents: Rent[];
  onReturnItem: (rentId: number, itemId: number) => Promise<void>;
}

const RentsTable = ({ rents, onReturnItem }: RentsTableProps) => {
  return (
    <Table>
      <TableCaption>A list of rents.</TableCaption>
      <TableHeader className="bg-blue-50">
        <TableRow>
          <TableHead className="w-[100px]">ID</TableHead>
          <TableHead>Client ID</TableHead>
          <TableHead>Item ID</TableHead>
          <TableHead>Begin Time</TableHead>
          {/* <TableHead>End Time</TableHead> */}
          <TableHead>Action</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {rents.length > 0 ? (
          rents.map((rent: Rent) => (
            <TableRow key={rent.id}>
              <TableCell>{rent.id}</TableCell>
              <TableCell>{rent.clientId}</TableCell>
              <TableCell>{rent.itemId}</TableCell>
              <TableCell>
                {rent.beginTime
                  ? format(new Date(rent.beginTime), "yyyy-MM-dd HH:mm")
                  : "Not available"}
              </TableCell>
              {/* <TableCell>{rent.endTime ? format(new Date(rent.endTime), 'yyyy-MM-dd HH:mm') : 'Not available'}</TableCell> */}
              <TableCell>
                <Button
                  onClick={() => onReturnItem(rent.id!, rent.itemId)}
                >
                  Return
                </Button>
              </TableCell>
            </TableRow>
          ))
        ) : (
          <TableRow>
            <TableCell colSpan={7}>No rents found.</TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  );
};

export default RentsTable;
