package infrastructure;

import model.AnimalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalDaoTest {

    @BeforeEach
    void setupDatabase() throws SQLException, ClassNotFoundException {
        // bruk samme URL som AnimalDao
        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test1;", "sa", "");
             Statement st = conn.createStatement()) {

            // dropp tabell hvis den finnes, og opprett på nytt
            st.executeUpdate("DROP TABLE IF EXISTS nasjonaldyr");

            st.executeUpdate("""
                CREATE TABLE nasjonaldyr (
                    art VARCHAR(50) PRIMARY KEY,
                    stemmer INT
                )
                """);

            // legg inn noen dyr med 0 stemmer
            st.executeUpdate("INSERT INTO nasjonaldyr (art, stemmer) VALUES ('GREVLING', 0)");
            st.executeUpdate("INSERT INTO nasjonaldyr (art, stemmer) VALUES ('HARE', 0)");
        }
    }

    @Test
    @DisplayName("voteForAnAnimal oppdaterer stemmer i databasen")
    void voteForAnAnimal_oppdatererStemmer() throws SQLException, ClassNotFoundException {
        AnimalDao dao = new AnimalDao();

        boolean ok = dao.voteForAnAnimal(3, AnimalType.GREVLING);
        assertTrue(ok);

        // sjekk i databasen at GREVLING har fått 3 stemmer
        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test1;", "sa", "");
             PreparedStatement ps = conn.prepareStatement("SELECT stemmer FROM nasjonaldyr WHERE art = ?")) {

            ps.setString(1, "GREVLING");
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int stemmer = rs.getInt(1);
                assertEquals(3, stemmer);
            }
        }
    }

    @Test
    @DisplayName("getAllAnimals henter alle dyr fra databasen som AnimalType")
    void getAllAnimals_henterAlleDyr() {
        AnimalDao dao = new AnimalDao();

        List<AnimalType> alle = dao.getAllAnimals();

        // vi vet at vi la inn GREVLING og HARE i setupDatabase
        // rekkefølgen kan variere, så vi sjekker innhold
        assertTrue(alle.contains(AnimalType.GREVLING));
        assertTrue(alle.contains(AnimalType.HARE));
    }

    @Test
    @DisplayName("updateSQL oppdaterer stemmer i databasen")
    void updateSQL_oppdatererStemmer() throws SQLException, ClassNotFoundException {
        AnimalDao dao = new AnimalDao();

        dao.updateSQL(AnimalType.HARE, 5);

        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test1;", "sa", "");
             PreparedStatement ps = conn.prepareStatement("SELECT stemmer FROM nasjonaldyr WHERE art = ?")) {

            ps.setString(1, "HARE");
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int stemmer = rs.getInt(1);
                assertEquals(5, stemmer);
            }
        }
    }
}
