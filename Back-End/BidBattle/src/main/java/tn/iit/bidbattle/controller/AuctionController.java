package tn.iit.bidbattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.bidbattle.adapter.AuctionAdapter;
import tn.iit.bidbattle.dao.AuctionRepository;
import tn.iit.bidbattle.dao.BidRepository;
import tn.iit.bidbattle.dao.UserRepository;
import tn.iit.bidbattle.dto.AuctionDto;
import tn.iit.bidbattle.dto.BidDto;
import tn.iit.bidbattle.exception.ResourceNotFoundException;
import tn.iit.bidbattle.model.auction.AuctionDetails;
import tn.iit.bidbattle.model.bid.Bid;

import java.net.URI;
import java.util.List;

import static tn.iit.bidbattle.adapter.AuctionAdapter.toAuctionDetails;
import static tn.iit.bidbattle.application.ApplicationProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/auctions")
@CrossOrigin
public class AuctionController {

    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    @Autowired
    public AuctionController(UserRepository userRepository, AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
    }

    @GetMapping
    public List<AuctionDetails> getAuctions() {
        return auctionRepository.findAll()
                .stream()
                .map(AuctionAdapter::toAuctionDetails)
                .toList();
    }

    @GetMapping("/{auctionId}")
    public AuctionDetails getAuction(@PathVariable long auctionId) {
        var auctionDto = findAuctionById(auctionId);
        return toAuctionDetails(auctionDto);
    }

    @DeleteMapping("/{auctionId}")
    public void deleteAuction(@PathVariable long auctionId) {
        auctionRepository.deleteById(auctionId);
    }

    @PostMapping("/{auctionId}/bids")
    public ResponseEntity<?> createBid(@PathVariable long auctionId, @RequestBody Bid bid) {
        var userDto = userRepository.findById(bid.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var auctionDto = findAuctionById(auctionId);

        var bidDto = new BidDto();
        bidDto.setUser(userDto);
        bidDto.setAuction(auctionDto);
        fillBidDto(bidDto, bid);
        bidDto = bidRepository.save(bidDto);

        return ResponseEntity.created(URI.create(BASE_URL + "auctions/" + auctionId + "/bids/" + bidDto.getId())).body(bidDto.getId());
    }

    @GetMapping("/{auctionId}/bids")
    public List<Bid> getBids(@PathVariable long auctionId) {
        var auctionDto = findAuctionById(auctionId);
        return auctionDto.getBids()
                .stream()
                .map(bidDto -> Bid.builder().userId(bidDto.getUser().getId()).price(bidDto.getPrice()).build())
                .toList();
    }

    private AuctionDto findAuctionById(long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
    }

    private static void fillBidDto(BidDto bidDto, Bid bid) {
        bidDto.setPrice(bid.getPrice());
    }
}
