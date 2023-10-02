package nl.frej.dea.spotitube.dao;

import nl.frej.dea.spotitube.services.dto.UserDTO;

import java.util.Optional;

public interface UserDaoInterface extends Dao<UserDTO> {
    Optional<UserDTO> getByUsername(String username);
}
