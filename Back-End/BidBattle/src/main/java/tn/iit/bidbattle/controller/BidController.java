package tn.iit.bidbattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.iit.bidbattle.dao.AuctionRepository;
import tn.iit.bidbattle.dao.BidRepository;
import tn.iit.bidbattle.dao.UserRepository;

import static tn.iit.bidbattle.application.ApplicationProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/bids")
@CrossOrigin
public class BidController {

    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    @Autowired
    public BidController(UserRepository userRepository, AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
    }


}
