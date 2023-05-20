package tn.iit.bidbattle.adapter;

import tn.iit.bidbattle.dto.AuctionDto;
import tn.iit.bidbattle.model.auction.AuctionDetails;
import tn.iit.bidbattle.model.auction.AuctionStatus;
import tn.iit.bidbattle.model.product.Product;

import java.util.Date;

public class AuctionAdapter {

    public static AuctionDetails toAuctionDetails(AuctionDto auctionDto) {
        var productDto = auctionDto.getProduct();
        var product = Product.builder().name(productDto.getName()).category(productDto.getCategory()).build();
        var status = auctionDto.getEndDate().after(new Date()) ? AuctionStatus.ACTIVE : AuctionStatus.ENDED;
        return AuctionDetails.builder().id(auctionDto.getId()).product(product).minPrice(auctionDto.getMinPrice()).endDate(auctionDto.getEndDate()).auctionStatus(status).build();
    }
}
