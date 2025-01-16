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
import { useState } from "react";

interface RentsTableProps {
  rents: Rent[];
  onReturnItem: (rentId: number, itemId: number) => Promise<void>;
}

const RentsTable = ({ rents, onReturnItem }: RentsTableProps) => {
  const [loading, setLoading] = useState<number | null>(null);

  return (
    <Table>
      <TableCaption>A list of rents.</TableCaption>
      <TableHeader className="bg-blue-50">
        <TableRow>
          <TableHead className="w-[100px]">ID</TableHead>
          <TableHead>Client ID</TableHead>
          <TableHead>Item ID</TableHead>
          <TableHead>Begin Time</TableHead>
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
                  : "-"}
              </TableCell>
              <TableCell>
                <Button
                  disabled={loading === rent.id}
                  onClick={async () => {
                    if (rent.id == null) {
                      console.error("rent.id is null");
                      return;
                    }
                    setLoading(rent.id);
                    try {
                      await onReturnItem(rent.id, rent.itemId);
                    } catch (error) {
                      console.error("Error during return", error);
                    } finally {
                      setLoading(null);
                    }
                  }}
                >
                  {loading === rent.id ? "Returning..." : "Return"}
                </Button>
              </TableCell>
            </TableRow>
          ))
        ) : (
          <TableRow>
            <TableCell colSpan={5} className="text-center">
              No rents found.
            </TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  );
};

export default RentsTable;