export interface User {
  id: string;
  firstName: string;
  lastName: string;
  login: string;
  password: string;
  role: string;
  isActive: boolean;
  clientType?: { discount: number; maxArticles: number, clientTypeInfo: string };
}

export interface Item {
  id: number;
  basePrice: number;
  itemName: string;
  itemType: string;
  available: boolean;
  minutes?: number;
  casette?: boolean;
  pagesNumber?: number;
  publisher?: string;
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
  width?: number;
}

export interface DecodedToken {
  sub: string;
  role: string;
  userId: string;
  iat: number;
  exp: number;
}