package dea.spotitube.dao;

import nl.frej.dea.spotitube.dao.UserDaoImpl;
import nl.frej.dea.spotitube.utils.DatabaseProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.mockito.Mockito.*;

public class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private DatabaseProperties databaseProperties;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(databaseProperties.connectionString()).thenReturn("someConnectionString");
        when(DriverManager.getConnection(anyString())).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGet() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("user")).thenReturn("testUser");
        when(resultSet.getString("password")).thenReturn("testPassword");

        userDao.get(1);

        verify(preparedStatement).setLong(1, 1);
    }

    // Add similar tests for other methods in UserDaoImpl
}

