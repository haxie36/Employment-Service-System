package registration;

import common.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Checks if an address is serviced or not
public class ServiceArea {
    private static final String IS_SERVICE_AREA =
            "SELECT 1 FROM service_area WHERE community = ? AND street = ? LIMIT 1";

    public boolean isServiceArea(String area) {
        String[] parts = area.split(",\\s*", 2);
        if (parts.length < 2) return false;
        String community = parts[0];
        String street = parts[1];

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(IS_SERVICE_AREA)) {
            ps.setString(1, community);
            ps.setString(2, street);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}
