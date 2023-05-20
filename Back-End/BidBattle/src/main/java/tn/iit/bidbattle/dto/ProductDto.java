package tn.iit.bidbattle.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import tn.iit.bidbattle.model.product.Category;

@Entity
@Table(name = "product")
@Data
public class ProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    @ToString.Exclude
    private AuctionDto auction;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private Category category;

//    @Column(name = "photo")
//    private String photo;
}
