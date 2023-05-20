package tn.iit.bidbattle.model.bid;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import tn.iit.bidbattle.model.auction.AuctionDetails;

@Data
@Builder
@Jacksonized
public class BidDetails {
    private AuctionDetails auction;
    private long price;
}
