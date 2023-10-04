package nl.frej.dea.spotitube.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.utils.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PlaylistDaoImpl implements PlaylistDaoInterface {
    private Logger logger = Logger.getLogger(getClass().getName());
    @Inject
    private UserService userService;

    private DatabaseProperties databaseProperties;

    @Inject
    public PlaylistDaoImpl(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public PlaylistDaoImpl() {
    }


    @Override
    public Optional<PlaylistDTO> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<PlaylistDTO> findAll() {
        return null;
    }


    public List<PlaylistDTO> findAll(String token) {
        String user = userService.getUserFromToken(token).orElse(null);

        List<PlaylistDTO> playlists = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from Playlist");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PlaylistDTO playlist = new PlaylistDTO();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));

                String ownerId = resultSet.getString("Owner");
                playlist.setOwner(ownerId.equals(user));  // Compare the owner ID to the user

                playlists.add(playlist);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return playlists;
    }


    @Override
    public void save(PlaylistDTO playlistDTO) {

    }

    @Override
    public void update(PlaylistDTO playlistDTO, String[] params) {

    }

    @Override
    public void delete(PlaylistDTO playlistDTO) {

    }

}


