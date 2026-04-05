package retraining;

import common.EntityCollection;
import common.Retraining;

//A collection of Retrainings
public class Retrainings extends EntityCollection<Retraining> {
    public Retrainings() {
        super();
    }
    public Retrainings(Retraining[] retrainings) {
        super(retrainings);
    }
}
