package tn.iit.bidbattle.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "bid")
@Data
public class BidDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private UserDto user;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    @ToString.Exclude
    private AuctionDto auction;

    @CreationTimestamp
    @Column
    private Date bidDate;

    @Column
    private long price;

}
