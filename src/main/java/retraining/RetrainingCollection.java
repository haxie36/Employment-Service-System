package retraining;

import base.EntityCollection;

//A collection of Retrainings
public class RetrainingCollection extends EntityCollection<Retraining> {
    public RetrainingCollection() {
        super();
    }
    public RetrainingCollection(Retraining[] retrainings) {
        super(retrainings);
    }
}
