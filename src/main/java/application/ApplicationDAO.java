package application;

import base.EntityDAO;
import common.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationDAO extends EntityDAO<Application> {
    private static final String ADD =
            "INSERT INTO application " +
                    "(profile_id, vacancy_id, status_id) " +
                    "VALUES (?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM application WHERE application_id = ?";
    private static final String UPDATE = "UPDATE application SET status_id = ? WHERE application_id = ?";
    private static final String DELETE = "DELETE FROM application WHERE application_id = ?";
    private static final String GET_ALL = "SELECT * FROM application  ORDER BY application_id";

    private static final String GET_ACTIVE_APPLICATIONS =
            "SELECT * FROM application " +
                    "WHERE status_id = 0 " +
                    "AND profile_id = ? " +
                    "AND vacancy_id = ?";

    public boolean hasActiveApplications(int profileId, int vacancyId) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(GET_ACTIVE_APPLICATIONS)) {
                statement.setInt(1, profileId);
                statement.setInt(2, vacancyId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Application map(ResultSet rs) throws SQLException {
        return new Application(
                rs.getInt("application_id"),
                rs.getInt("profile_id"),
                rs.getInt("vacancy_id"),
                rs.getDate("application_date").toLocalDate(),
                ApplicationStatus.fromId(rs.getInt("status_id"))
        );
    }

    @Override
    public boolean add(Application item) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(ADD)) {
                statement.setInt(1, item.getProfileId());
                statement.setInt(2, item.getVacancyId());
                statement.setInt(3, item.getStatus().getId());
                int rows = statement.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to add application", e);
        }
    }

    @Override
    public Application getById(int id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return map(resultSet);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get application", e);
        }
    }

    @Override
    public boolean update(Application item) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                statement.setInt(1, item.getStatus().getId());
                statement.setInt(2, item.getId());
                int rows = statement.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to update application", e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
                statement.setInt(1, id);
                int rows = statement.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to delete application", e);
        }
    }

    @Override
    public Application[] getAll() {
        ArrayList<Application> applications = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(GET_ALL)) {
                    while (resultSet.next()) {
                        applications.add(map(resultSet));
                    }
                    return applications.toArray(new Application[0]);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get all applications", e);
        }
    }
}
