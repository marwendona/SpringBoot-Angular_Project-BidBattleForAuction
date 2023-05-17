package tn.iit.bidbattle.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

//    @Column(name = "region")
//    private String region;
//
//    @Column(name = "phone")
//    private int phone;
//
//    @Column(name = "birthDate")
//    private Date birthDate;
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "photo")
//    private String photo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BidDto> bids = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<AuctionDto> auctions = new ArrayList<>();
}

