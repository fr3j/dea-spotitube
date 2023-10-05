package nl.frej.dea.spotitube.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.services.dto.UserDTO;
import nl.frej.dea.spotitube.utils.DatabaseProperties;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped

public class UserDao implements UserDaoInterface {

    private Logger logger = Logger.getLogger(getClass().getName());
    @Inject

    private DatabaseProperties databaseProperties;

    @Override
    public Optional<UserDTO> get(long id) {
        UserDTO user = null;
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from User WHERE id = ?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserDTO();
                user.setId(resultSet.getInt("id"));
                user.setUser(resultSet.getString("user"));
                user.setPassword(resultSet.getString("password"));

            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public void update(UserDTO userDTO, String[] params) {

    }

    @Override
    public void delete(UserDTO userDTO) {

    }

    @Override
    public Optional<UserDTO> getByUsername(String username) {
        UserDTO user = null;
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from User WHERE user = ?");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserDTO();
                user.setId(resultSet.getInt("id"));
                user.setUser(resultSet.getString("user"));
                user.setPassword(resultSet.getString("password"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }


    public Optional<UserDTO> getByToken(String token) {
        UserDTO user = null;
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from user_tokens WHERE token = ?");
            statement.setString(1, token);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserDTO();
                user.setId(resultSet.getInt("id"));
                user.setUser(resultSet.getString("user"));
                user.setPassword(resultSet.getString("password"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void saveTokenToDatabase(String token, String username) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_tokens (token, user) VALUES (?, ?)");
            statement.setString(1, token);
            statement.setString(2, username);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // Handle exception
        }

    }

}
