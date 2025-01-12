export interface User {
  id: number;
  firstName: string;
  lastName: string;
  login: string;
  password: string;
  role: string;
  clientType?: { discount: number; maxArticles: number, clientTypeInfo: string }; // TODO: Wyjebac zeby moglo nie byc clientTypeInfo (backend)
}

export interface Item {
  id: number;
  basePrice: number;
  itemName: string;
  itemType: string;
  available: boolean;
  minutes?: number;
  casette?: boolean;
  pageNumber?: number;
  genre?: string;
  vinyl?: boolean;
}

export interface Rent {
  id?: number;
  clientId: number;
  itemId: number;
  beginTime?: Date;
  endTime?: Date;
  rentCost?: number;
  archive?: boolean;
}

export interface Column {
  label: string;
  field: keyof Item;
}