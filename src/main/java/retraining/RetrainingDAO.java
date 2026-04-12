package retraining;

import base.EntityDAO;

//A collection of Retrainings
public class RetrainingDAO extends EntityDAO<Retraining> {
    public RetrainingDAO() {
        super(Retraining.class);
    }
    public RetrainingDAO(Retraining[] retrainings) {
        super(retrainings, Retraining.class);
    }
}
