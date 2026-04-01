package retraining;

public enum RetrainingStatus {
    NEW(0),
    SCHEDULED(1),
    IN_PROGRESS(2),
    COMPLETED(3),
    RETRACTED(4);

    private final int id;

    RetrainingStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RetrainingStatus fromId(int id) {
        for (RetrainingStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
