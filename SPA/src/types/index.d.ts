export interface User {
  id: number;
  firstName: string;
  lastName: string;
  login: string;
  password: string;
  role: string;
  clientType?: { discount: number; maxArticles: number, clientTypeInfo: string }; // TODO: Wyjebac zeby moglo nie byc clientTypeInfo (backend)
}
