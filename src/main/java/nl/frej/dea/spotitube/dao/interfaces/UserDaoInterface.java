package nl.frej.dea.spotitube.dao.interfaces;

import nl.frej.dea.spotitube.services.dto.UserDTO;

import java.util.Optional;

public interface UserDaoInterface extends Dao<UserDTO> {
    Optional<UserDTO> getByUsername(String username);

    void saveTokenToDatabase(String token, String user);

    String getUserByToken(String token);
}
