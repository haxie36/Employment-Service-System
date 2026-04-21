package retraining;

import logic.retraining.Retraining;
import logic.retraining.RetrainingDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestRetrainingDAO extends RetrainingDAO {
    ArrayList<Retraining> retrainings;

    public TestRetrainingDAO() {
        retrainings = new ArrayList<>(List.of(new Retraining[]{
                new Retraining(1),
                new Retraining(2),
        }));
    }

    @Override
    protected Retraining map(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Retraining item) {
        item.setId(retrainings.size()+1);
        return retrainings.add(item);
    }

    @Override
    public Retraining getById(int id) {
        for (Retraining retraining : retrainings) {
            if (retraining.getId() == id) {
                return retraining;
            }
        } return null;
    }

    @Override
    public boolean update(Retraining item) {
        for (int i = 0; i < retrainings.size(); i++) {
            Retraining retraining = retrainings.get(i);
            if (retraining.getId() == item.getId()) {
                retrainings.set(i, item);
                return true;
            }
        } return false;
    }

    @Override
    public boolean delete(int id) {
        return retrainings.remove(getById(id));
    }

    @Override
    public Retraining[] getAll() {
        return retrainings.toArray(new Retraining[0]);
    }

    @Override
    public boolean hasSameRetrainings(int profileId, String specialty) {
        for (Retraining retraining : retrainings) {
            if (retraining.getSpecialty().equals(specialty)
                    && retraining.getProfileId() == profileId) {
                return true;
            }
        } return false;
    }
}
