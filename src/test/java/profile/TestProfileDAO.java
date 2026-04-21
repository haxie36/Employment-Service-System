package profile;

import logic.profile.Profile;
import logic.profile.ProfileDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class TestProfileDAO extends ProfileDAO {
    private ArrayList<Profile> profiles;

    public TestProfileDAO() {
        profiles = new ArrayList<>(List.of(new Profile[]{
                new Profile(1),
                new Profile(2),
                new Profile(3)
        }));
    }

    @Override
    protected PreparedStatement setupUpdate(Profile item, String updateSQL) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return profiles.removeIf(p -> p.getId() == id);
    }

    @Override
    public Profile getByRNOKPP(String rnokpp) {
        for (Profile p : profiles) {
            if (p.getRNOKPP().equals(rnokpp)) {
                return p;
            }
        } return null;
    }

    @Override
    public Profile getByPassport(String passportNumber) {
        for (Profile profile : profiles) {
            if (profile.getPassportNumber().equals(passportNumber)) {
                return profile;
            }
        } return null;
    }

    @Override
    public boolean update(Profile item) {
        for (int i = 0; i < profiles.size(); i++) {
            Profile profile = profiles.get(i);
            if (profile.getId() == item.getId()) {
                profiles.set(i, item);
                return true;
            }
        } return false;
    }

    @Override
    public Profile getById(int id) {
        for (Profile profile : profiles) {
            if (profile.getId() == id) {
                return profile;
            }
        } return null;
    }

    @Override
    public boolean add(Profile item) {
        item.setId(profiles.size()+1);
        return profiles.add(item);
    }

    @Override
    protected Profile map(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public boolean existsByRNOKPPExcept(int id, String RNOKPP) {
        Profile profile = getByRNOKPP(RNOKPP);
        return profile != null && profile.getId() != id;
    }

    @Override
    public boolean existsByPassportExcept(int id, String passportNumber) {
        Profile profile = getByPassport(passportNumber);
        return profile != null && profile.getId() != id;
    }

    @Override
    public boolean isRegistered(String passportNumber, String rnokpp) {
        return getByPassport(passportNumber) != null || getByRNOKPP(rnokpp) != null;
    }

    @Override
    public Profile[] getAll() {
        return profiles.toArray(new Profile[0]);
    }
}
