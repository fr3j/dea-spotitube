package nl.frej.dea.spotitube.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.utils.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PlaylistDAO implements Dao<PlaylistDTO> {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    @Inject
    public PlaylistDAO(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public PlaylistDAO(){}

    @Override
    public Optional<PlaylistDTO> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<PlaylistDTO> findAll() {
        List<PlaylistDTO> playlists = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from Playlist");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PlaylistDTO playlist = new PlaylistDTO();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getBoolean("owner"));

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
