package dea.spotitube.dao;
import nl.frej.dea.spotitube.dao.PlaylistDaoImpl;
import nl.frej.dea.spotitube.utils.DatabaseProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlaylistDaoImplTest {

    private PlaylistDaoImpl playlistDao;
    private DatabaseProperties databasePropertiesMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    public void setup() throws SQLException {
        databasePropertiesMock = mock(DatabaseProperties.class);
        connectionMock = mock(Connection.class);
        statementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        when(databasePropertiesMock.connectionString()).thenReturn("testConnectionString");
        when(DriverManager.getConnection(anyString())).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);

        playlistDao = new PlaylistDaoImpl(databasePropertiesMock);
    }

    @Test
    public void testFindPlaylists() throws SQLException {
        when(resultSetMock.next()).thenReturn(true, false);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("TestPlaylist");
        when(resultSetMock.getString("Owner")).thenReturn("testUser");

        var playlists = playlistDao.findPlaylists("testUser");

        assertEquals(1, playlists.size());
        assertEquals(1, playlists.get(0).getId());
        assertEquals("TestPlaylist", playlists.get(0).getName());
    }
}

