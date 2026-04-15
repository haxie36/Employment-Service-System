package logic.vacancy;

import logic.base.EntityDAO;
import logic.common.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class VacancyDAO extends EntityDAO<Vacancy> {
    private static final String ADD =
            "INSERT INTO vacancy " +
                "(vacancy_title, company_name, contact_number, specialty_number, min_experience, description, status_id) " +
                "VALUES (?,?,?,?,?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM vacancy WHERE vacancy_id = ?";
    private static final String UPDATE =
            "UPDATE vacancy " +
                "SET vacancy_title = ?, company_name = ?, " +
                "contact_number = ?, specialty_number = ?, " +
                "min_experience = ?, description = ?, status_id = ? " +
                "WHERE vacancy_id = ?";
    private static final String DELETE = "DELETE FROM vacancy WHERE vacancy_id = ?";
    private static final String GET_ALL = "SELECT * FROM vacancy  ORDER BY  vacancy_id";

    @Override
    protected Vacancy map(ResultSet rs) throws SQLException {
        return new Vacancy(
                rs.getInt("vacancy_id"),
                rs.getString("vacancy_title"),
                rs.getString("company_name"),
                rs.getString("contact_number"),
                rs.getString("specialty_number"),
                rs.getInt("min_experience"),
                rs.getString("description"),
                VacancyStatus.fromId(rs.getInt("status_id"))
        );
    }

    @Override
    public boolean add(Vacancy item) {
        try (PreparedStatement statement = setupUpdate(item, ADD)) {
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to add vacancy", e);
        }
    }

    @Override
    public Vacancy getById(int id) {
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
            throw new RuntimeException("Failed to get vacancy", e);
        }
    }

    @Override
    public boolean update(Vacancy item) {
        try (PreparedStatement statement = setupUpdate(item, UPDATE)) {
            statement.setInt(8, item.getId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to update vacancy", e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
                statement.setInt(1, id);
                int rows = statement.executeUpdate();
                statement.close();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to delete vacancy", e);
        }
    }

    @Override
    public Vacancy[] getAll() {
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(GET_ALL)) {
                    while (resultSet.next()) {
                        vacancies.add(map(resultSet));
                    }
                    return vacancies.toArray(new Vacancy[0]);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get all vacancies", e);
        }
    }

    //One method for all 2 updates
    protected PreparedStatement setupUpdate(Vacancy item, String updateSQL) throws SQLException {
        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getCompany());
            statement.setString(3, item.getContact());
            statement.setString(4, item.getSpecialty());
            statement.setInt(5, item.getMinExperience());
            statement.setString(6, item.getDescription());
            statement.setInt(7, item.getStatus().getId());
            return statement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to setup update", e);
        }
    }
}
