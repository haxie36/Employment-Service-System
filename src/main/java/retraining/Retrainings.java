package retraining;

import common.EntityCollection;

//A collection of Retrainings
public class Retrainings extends EntityCollection<Retraining> {
    public Retrainings() {
        super();
    }
    public Retrainings(Retraining[] retrainings) {
        super(retrainings);
    }
}
