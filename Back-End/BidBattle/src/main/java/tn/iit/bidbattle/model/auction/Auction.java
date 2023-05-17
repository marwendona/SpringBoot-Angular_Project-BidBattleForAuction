package tn.iit.bidbattle.model.auction;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import tn.iit.bidbattle.model.product.Product;

import java.util.Date;

@Data
@Builder
@Jacksonized
public class Auction {
    private Product product;
    private int minPrice;
    private Date endDate;
}
