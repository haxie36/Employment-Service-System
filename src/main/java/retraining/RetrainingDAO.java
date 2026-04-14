package retraining;

import base.EntityDAO;
import common.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RetrainingDAO extends EntityDAO<Retraining> {
    private static final String ADD =
            "INSERT INTO retraining " +
                    "(start_date, end_date, specialty_number, profile_id, status_id) " +
                    "VALUES (?,?,?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM retraining WHERE retraining_id = ?";
    private static final String UPDATE =
            "UPDATE retraining " +
                    "SET start_date = ?, end_date = ?, status_id = ? " +
                    "WHERE retraining_id = ?";
    private static final String DELETE = "DELETE FROM retraining WHERE retraining_id = ?";
    private static final String GET_ALL = "SELECT * FROM retraining  ORDER BY retraining_id";

    @Override
    protected Retraining map(ResultSet rs) throws SQLException {
        Date sDate = rs.getDate("start_date");
        LocalDate startDate = sDate != null ? sDate.toLocalDate() : null;
        Date eDate = rs.getDate("end_date");
        LocalDate endDate = eDate != null ? eDate.toLocalDate() : null;
        return new Retraining(
                rs.getInt("retraining_id"),
                startDate,
                endDate,
                rs.getString("specialty_number"),
                rs.getInt("profile_id"),
                RetrainingStatus.fromId(rs.getInt("status_id"))
        );
    }

    @Override
    public boolean add(Retraining item) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(ADD)) {
                statement.setDate(1, Date.valueOf(item.getStartDate()));
                statement.setDate(2, Date.valueOf(item.getEndDate()));
                statement.setString(3, item.getSpecialty());
                statement.setInt(4, item.getProfileId());
                statement.setInt(5, item.getStatus().getId());
                int rows = statement.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to add retraining", e);
        }
    }

    @Override
    public Retraining getById(int id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return map(rs);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get retraining", e);
        }
    }

    @Override
    public boolean update(Retraining item) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                if (item.getStartDate() != null && item.getEndDate() != null) {
                    statement.setDate(1, Date.valueOf(item.getStartDate()));
                    statement.setDate(2, Date.valueOf(item.getEndDate()));
                } else {
                    statement.setNull(1, Types.DATE);
                    statement.setNull(2, Types.DATE);
                }
                statement.setInt(3, item.getStatus().getId());
                statement.setInt(4, item.getId());
                int rows = statement.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to update retraining", e);
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to delete retraining", e);
        }
    }

    @Override
    public Retraining[] getAll() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            ArrayList<Retraining> retrainings = new ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery(GET_ALL)) {
                    while (rs.next()) {
                        retrainings.add(map(rs));
                    }
                    return retrainings.toArray(new Retraining[0]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to get all retrainings", e);
        }
    }
}
