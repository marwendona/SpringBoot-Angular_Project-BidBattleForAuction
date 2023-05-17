import { Product } from "./Product";

export class Auction {
    id !: number;
    product!: {
        name: string;
        category: string;
      };
    minPrice!: number;
    endDate!: Date;
    auctionStatus: string="";

  }

 