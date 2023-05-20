import { Product } from "./Product";

export class AuctionDetails {
    id : number | undefined;
    product!: {
        name: string;
        category: string;
      };
    minPrice!: number;
    endDate!: Date;
    auctionStatus: string="";
  }