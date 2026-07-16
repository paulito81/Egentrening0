package infrastructure;

import model.AnimalType;
import repository.AnimalRepository;

import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDao implements AnimalRepository {
    private Connection connection;

    public AnimalDao() {
        openConnectionToDB();
    }

    // Ny konstruktør for tester: hopper over å åpne DB
    public AnimalDao(boolean skipOpen) {
        if (!skipOpen) {
            openConnectionToDB();
        }
    }

    private void openConnectionToDB() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test1;", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Kunne ikke åpne databaseforbindelse", e);
        }
    }

    @PreDestroy
    private void closeConnectionToDB() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean voteForAnAnimal(int vote, AnimalType animal) {
        return updateVotes(animal, vote);
    }

    @Override
    public List<AnimalType> getAllAnimals() {
        List<AnimalType> animalList = new ArrayList<>();
        String sqlListe = "SELECT art FROM nasjonaldyr";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlListe);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String art = rs.getString(1);
                // antar at art-kolonnen inneholder navnet til enumen, for eksempel "GREVLING"
                animalList.add(AnimalType.valueOf(art));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }

    public void updateSQL(AnimalType animal, int vote) {
        updateVotes(animal, vote);
    }

    private boolean updateVotes(AnimalType animal, int vote) {
        String sqlUpdate = "UPDATE nasjonaldyr SET stemmer = ? WHERE art = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setInt(1, vote);
            preparedStatement.setString(2, animal.name());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Updated animal " + animal.name() + "\tHas now : (" + vote + ") votes!");

            if (rows != 1) {
                throw new IllegalStateException(
                        "Wrong number of rows updated: " + rows +
                                "\nStemmer: " + vote +
                                "\nArt: " + animal.name()
                );
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
