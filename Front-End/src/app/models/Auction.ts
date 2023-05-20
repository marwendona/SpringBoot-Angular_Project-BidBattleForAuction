import { Product } from "./Product";

export interface Auction {
  product: {
    name: string;
    category: string;
  };
  minPrice: number;
  endDate: Date;
}

