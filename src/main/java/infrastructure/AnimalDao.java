package infrastructure;


import model.AnimalType;

import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 10.09.15.
 */
public class AnimalDao implements Animal {

    private Connection connection;

    public AnimalDao() {
        openConnectionToDB();
    }

    public Connection openConnectionToDB() {

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test1;", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    @PreDestroy
    private void closeConnectionToDB() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean voteForAnAnimal(int vote, Animal animal) {
        String sqlUpdate = "UPDATE nasjonaldyr SET stemmer = ? WHERE art = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setInt(1, vote);
            preparedStatement.setString(2, String.valueOf(animal));

            int rows = preparedStatement.executeUpdate();
            System.out.println("Updated animal " + String.valueOf(animal) + "\tHas now : (" + vote + ") votes!");
            if (rows != 1) {
                throw new IllegalStateException("Wrong number of rows updated: " + rows + "\nStemmer: "
                        + vote + "\nArt:" + String.valueOf(animal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Animal> getAllAnimals() {
        List<String> animalList = new ArrayList<>();
        String sqlListe = "SELECT art FROM nasjonaldyr";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlListe)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                animalList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSQL(AnimalType animal, int vote) {
        String sqlUpdate = "UPDATE nasjonaldyr SET stemmer = ? WHERE art = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setInt(1, vote);
            preparedStatement.setString(2, animal.name());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Updated animal " + animal.name() + "\tHas now : (" + vote + ") votes!");
            if (rows != 1) {
                throw new IllegalStateException("Wrong number of rows updated: " + rows + "\nStemmer: "
                        + vote + "\nArt:" + animal.name());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

