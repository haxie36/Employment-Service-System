package logic.profile;

import logic.base.EntityDAO;
import logic.common.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class ProfileDAO extends EntityDAO<Profile> {
    private static final String ADD =
            "INSERT INTO profile " +
                    "(passport_number, rnokpp, full_name, birth_date, specialty_number, experience) " +
                    "VALUES (?,?,?,?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM profile WHERE profile_id = ?";
    private static final String GET_BY_PASSPORT = "SELECT * FROM profile WHERE passport_number = ?";
    private static final String GET_BY_RNOKPP = "SELECT * FROM profile WHERE rnokpp = ?";
    private static final String UPDATE =
            "UPDATE profile " +
                    "SET passport_number = ?, rnokpp = ?, " +
                    "full_name = ?, birth_date = ?, " +
                    "specialty_number = ?, experience = ? " +
                    "WHERE profile_id = ?";
    private static final String DELETE = "DELETE FROM profile WHERE profile_id = ?";
    private static final String GET_ALL = "SELECT * FROM profile ORDER BY profile_id";

    //Check for profile is registered
    public boolean isRegistered(String passportNumber, String rnokpp) {
        return getByPassport(passportNumber) != null && getByRNOKPP(rnokpp) != null;
    }

    public boolean existsByPassportExcept(int id, String passportNumber) {
        Profile profile = getByPassport(passportNumber);
        return profile.getId() != (id);
    }

    public boolean existsByRNOKPPExcept(int id, String RNOKPP) {
        Profile profile = getByRNOKPP(RNOKPP);
        return profile.getId() != (id);
    }

    @Override
    protected Profile map(ResultSet rs) throws SQLException {
        return new Profile(
                rs.getInt("profile_id"),
                rs.getString("passport_number"),
                rs.getString("rnokpp"),
                rs.getString("full_name"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getString("specialty_number"),
                rs.getInt("experience")
        );
    }

    @Override
    public boolean add(Profile item) {
        try (PreparedStatement statement = setupUpdate(item, ADD)) {
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to add profile", e);
        }
    }

    @Override
    public Profile getById(int id) {
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
            throw new RuntimeException("Failed to get profile", e);
        }
    }

    @Override
    public boolean update(Profile item) {
        try (PreparedStatement statement = setupUpdate(item, UPDATE)) {
            statement.setInt(7, item.getId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to update profile", e);
        }
    }

    public Profile getByPassport(String passportNumber) {
        return getProfileByStringAttribute(passportNumber, GET_BY_PASSPORT);
    }

    public Profile getByRNOKPP(String rnokpp) {
        return getProfileByStringAttribute(rnokpp, GET_BY_RNOKPP);
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
            throw new RuntimeException("Failed to delete profile", e);
        }
    }

    @Override
    public Profile[] getAll() {
        ArrayList<Profile> profiles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(GET_ALL)) {
                    while (resultSet.next()) {
                        profiles.add(map(resultSet));
                    }
                    return profiles.toArray(new Profile[0]);
                }
            }
        } catch (SQLException e)  {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get all profiles", e);
        }
    }

    //One method for all (just 2) updates
    protected PreparedStatement setupUpdate(Profile item, String updateSQL) throws SQLException {
        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setString(1, item.getPassportNumber());
            statement.setString(2, item.getRNOKPP());
            statement.setString(3, item.getName());
            statement.setDate(4, Date.valueOf(item.getBirthDate()));
            statement.setString(5, item.getSpecialty());
            statement.setInt(6, item.getExperience());
            return statement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to setup profile update", e);
        }
    }

    //One method for all getters
    private Profile getProfileByStringAttribute(String attribute, String getBySQL) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(getBySQL)) {
                statement.setString(1, attribute);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return map(resultSet);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get profile", e);
        }
    }
}
