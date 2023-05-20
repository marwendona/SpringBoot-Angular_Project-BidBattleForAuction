package tn.iit.bidbattle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.iit.bidbattle.dto.AuctionDto;
import tn.iit.bidbattle.dto.BidDto;

import java.util.List;

@RepositoryRestResource
public interface BidRepository extends JpaRepository<BidDto, Long> {

    List<BidDto> findByUserId(long userId);
}
