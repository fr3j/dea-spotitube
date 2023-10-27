package dea.spotitube.utils;

import nl.frej.dea.spotitube.utils.DatabaseProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabasePropertiesTest {

    @Test
    public void testConnectionString() {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        assertNotNull(databaseProperties.connectionString());
    }
}

