package nl.frej.dea.spotitube.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.dao.interfaces.TrackDaoInterface;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;
import nl.frej.dea.spotitube.utils.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class TrackDaoImpl implements TrackDaoInterface {

    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;


    @Inject
    public TrackDaoImpl(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public TrackDaoImpl() {
    }
    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void save(TrackDTO trackDTO) {

    }

    @Override
    public void update(TrackDTO trackDTO, String[] params) {

    }

    @Override
    public void delete(TrackDTO trackDTO) {

    }

    @Override
    public void addTrackToPlaylist(TrackDTO trackDTO, PlaylistDTO playlistDTO) {

    }

//    @Override
//    public void addTrackToPlaylist(TrackDTO trackDTO, PlaylistDTO playlistDTO) {
//        try {
//            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO Track (id, name, Owner) VALUES (?, ?, ?)");
//
//            statement.setInt(1, playlistDTO.getId());
//            statement.setString(2, playlistDTO.getName());
//            statement.setString(3, user);
//
//            statement.executeUpdate();
//
//            statement.close();
//            connection.close();
//
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
//        }
//    }
}
