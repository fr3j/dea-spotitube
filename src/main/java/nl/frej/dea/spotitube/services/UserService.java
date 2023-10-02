package nl.frej.dea.spotitube.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.dao.UserDaoInterface;
import nl.frej.dea.spotitube.services.dto.UserDTO;

import java.security.SecureRandom;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    private UserDaoInterface dao;
    @Inject
    public UserService(UserDaoInterface dao) {
        this.dao = dao;
    }

    public UserService(){
        ;
    }

    public String login(UserDTO userDTO) {
        if (checkCredentials(userDTO))
            return generateToken();
        return null;
    }

    public static String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        final int TOKEN_SEGMENT_LENGTH = 4;
        final int TOKEN_SEGMENTS = 3;
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < TOKEN_SEGMENTS; i++) {
            for (int j = 0; j < TOKEN_SEGMENT_LENGTH; j++) {
                token.append(secureRandom.nextInt(10));
            }
            if (i < TOKEN_SEGMENTS - 1) {
                token.append('-');
            }
        }
        return token.toString();
    }

    private boolean checkCredentials(UserDTO userDTO) {
        Optional<UserDTO> storedUserOpt = dao.getByUsername(userDTO.getUser());
        if (storedUserOpt.isPresent()){
            UserDTO storedUser = storedUserOpt.get();
            return storedUser.getPassword().equals(userDTO.getPassword());
        }
        return false;
    }

}
