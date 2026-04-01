package retraining;

public class Retrainings {
    private Retraining[] retrainings;

    public Retrainings() {
        retrainings = new Retraining[0];
    }
    public Retrainings(Retraining[] retrainings) {
        this.retrainings = retrainings;
    }

    public boolean add(Retraining retraining) {
        String id = retraining.getId();
        boolean exists = false;

        for (int i=0; i<retrainings.length; i++) {
            if (id.equals(retrainings[i].getId())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            Retraining[] newRetrainings = new Retraining[retrainings.length+1];

            for (int i=0; i<retrainings.length; i++) {
                newRetrainings[i] = retrainings[i];
            }

            newRetrainings[retrainings.length] = retraining;
            retrainings=newRetrainings;
        }
        return !exists;
    }

    public Retraining getRetraining(String id) {
        for (int i=0; i<retrainings.length; i++) {
            Retraining retraining = retrainings[i];

            if (id.equals(retraining.getId())) {
                return retraining;
            }
        }
        return null;
    }

    public Retraining[] getRetrainings() { return retrainings; }
    public void clear(){ retrainings = new Retraining[0]; }

    public boolean delete(Retraining retraining) {
        String id = retraining.getId();
        return delete(id);
    }
    public boolean delete(String id){
        for (int i=0; i<retrainings.length; i++) {
            if (id.equals(retrainings[i].getId())) {
                Retraining[] newRetrainings = new Retraining[retrainings.length-1];
                for (int j=0; j<retrainings.length; j++) {
                    if (j==i) continue;
                    newRetrainings[j] = retrainings[i];
                }
                retrainings=newRetrainings;
                return true;
            }
        }
        return false;
    }
}
