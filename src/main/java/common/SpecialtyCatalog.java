package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Checks if the specialty is real
public class SpecialtyCatalog {
    private static final String IS_REAL_SPECIALTY =
            "SELECT specialty_number " +
                    "FROM specialty " +
                    "WHERE specialty_number = ? " +
                    "LIMIT 1";

    public SpecialtyCatalog() {}

    public boolean isRealSpecialty(String specialty) {
        if (specialty == null) {return false;}
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(IS_REAL_SPECIALTY)) {
                statement.setString(1, specialty);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to reach specialties", e);
        }
    }
}
