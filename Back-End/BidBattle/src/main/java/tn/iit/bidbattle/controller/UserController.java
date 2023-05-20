package tn.iit.bidbattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.iit.bidbattle.adapter.AuctionAdapter;
import tn.iit.bidbattle.dao.AuctionRepository;
import tn.iit.bidbattle.dao.BidRepository;
import tn.iit.bidbattle.dao.UserRepository;
import tn.iit.bidbattle.dto.AuctionDto;
import tn.iit.bidbattle.dto.ProductDto;
import tn.iit.bidbattle.dto.UserDto;
import tn.iit.bidbattle.exception.ResourceNotFoundException;
import tn.iit.bidbattle.model.auction.Auction;
import tn.iit.bidbattle.model.auction.AuctionDetails;
import tn.iit.bidbattle.model.auction.AuctionStatus;
import tn.iit.bidbattle.model.bid.BidDetails;
import tn.iit.bidbattle.model.user.User;

import java.net.URI;
import java.util.List;

import static tn.iit.bidbattle.application.ApplicationProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserRepository userRepository, AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        var userDto = new UserDto();
        fillUserDto(userDto, user);
        userDto = userRepository.save(userDto);
        return ResponseEntity.created(URI.create(BASE_URL + "/users/" + userDto.getId())).body(userDto.getId());
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable long userId, @RequestBody User user) {
        var userDto = findUserById(userId);
        fillUserDto(userDto, user);
        userRepository.save(userDto);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        var userDto = findUserById(userId);
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    private UserDto findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userRepository.deleteById(userId);
    }

    @PostMapping("{userId}/auctions")
    public ResponseEntity<?> addAuction(@PathVariable long userId, @RequestBody Auction auction) {
        var userDto = findUserById(userId);

        var auctionDto = new AuctionDto();
        var productDto = new ProductDto();

        var product = auction.getProduct();
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setAuction(auctionDto);

        auctionDto.setUser(userDto);
        auctionDto.setProduct(productDto);
        auctionDto.setMinPrice(auction.getMinPrice());
        auctionDto.setEndDate(auction.getEndDate());
        auctionDto.setStatus(AuctionStatus.ACTIVE);
        auctionDto = auctionRepository.save(auctionDto);

        return ResponseEntity.created(URI.create(BASE_URL + "/users/" + userDto.getId() + "/auctions/" + auctionDto.getId())).body(auctionDto.getId());
    }

    @GetMapping("{userId}/auctions")
    public List<AuctionDetails> getAuctions(@PathVariable long userId) {
        var userDto = findUserById(userId);
        return userDto.getAuctions()
                .stream()
                .map(AuctionAdapter::toAuctionDetails)
                .toList();
    }

    @GetMapping("{userId}/bids")
    public List<BidDetails> getBids(@PathVariable long userId) {
        return bidRepository.findByUserId(userId)
                .stream()
                .map(bidDto -> BidDetails.builder()
                        .price(bidDto.getPrice())
                        .auction(AuctionAdapter.toAuctionDetails(bidDto.getAuction()))
                        .build())
                .toList();
    }

    private static void fillUserDto(UserDto userDto, User user) {
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
