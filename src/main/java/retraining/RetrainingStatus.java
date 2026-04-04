package retraining;

public enum RetrainingStatus {
    NEW(0, "New"),
    SCHEDULED(1, "Scheduled"),
    IN_PROGRESS(2, "In Progress"),
    COMPLETED(3, "Completed"),
    RETRACTED(4, "Retracted"),;

    private final int id;
    private final String name;

    RetrainingStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {return name;}

    public static RetrainingStatus fromId(int id) {
        for (RetrainingStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
