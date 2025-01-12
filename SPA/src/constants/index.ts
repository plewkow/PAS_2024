import { Column } from "@/types";

export const musicColumns: Column[] = [
  { label: "ID", field: "id" },
  { label: "Item Name", field: "itemName" },
  { label: "Price", field: "basePrice" },
  { label: "Genre", field: "genre" },
  { label: "Vinyl", field: "vinyl" },
  { label: "Available", field: "available" },
];

export const comicsColumns: Column[] = [
  { label: "ID", field: "id" },
  { label: "Item Name", field: "itemName" },
  { label: "Price", field: "basePrice" },
  { label: "Pages", field: "pageNumber" },
  { label: "Available", field: "available" },
];

export const movieColumns: Column[] = [
  { label: "ID", field: "id" },
  { label: "Item Name", field: "itemName" },
  { label: "Price", field: "basePrice" },
  { label: "Duration (min)", field: "minutes" },
  { label: "Casette", field: "casette" },
  { label: "Available", field: "available" },
];

export const booleanFields = ["vinyl", "available", "casette"];
