package nl.frej.dea.spotitube.services;

import jakarta.enterprise.context.ApplicationScoped;
import nl.frej.dea.spotitube.services.dto.UserDTO;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {
    private List<UserDTO> users = new ArrayList<>();

    public UserService(){
        users.add(new UserDTO("frej", "papzak"));
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
                token.append(secureRandom.nextInt(10)); // Generate a random digit [0-9]
            }
            if (i < TOKEN_SEGMENTS - 1) {
                token.append('-');
            }
        }
        return token.toString();
    }

    private boolean checkCredentials(UserDTO userDTO) {
        for (UserDTO storedUser : users) {
            if (storedUser.getUser().equals(userDTO.getUser()) &&
                    storedUser.getPassword().equals(userDTO.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
