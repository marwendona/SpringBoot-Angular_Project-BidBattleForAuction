package tn.iit.bidbattle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.iit.bidbattle.dto.AuctionDto;
import tn.iit.bidbattle.dto.UserDto;

@RepositoryRestResource
public interface AuctionRepository extends JpaRepository<AuctionDto, Long> {
}
