package tn.iit.bidbattle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.iit.bidbattle.dto.UserDto;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository  extends JpaRepository<UserDto, Long> {

    Optional<UserDto> findByEmail(String email);
}
