package retraining;

public enum RetrainingStatus {
    NEW(0, "NEW"),
    SCHEDULED(1, "SCHEDULED"),
    IN_PROGRESS(2, "IN_PROGRESS"),
    COMPLETED(3, "COMPLETED"),
    RETRACTED(4, "RETRACTED"),;

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
    public String toString(){return name;}

    public static RetrainingStatus fromId(int id) {
        for (RetrainingStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status");
    }
}
