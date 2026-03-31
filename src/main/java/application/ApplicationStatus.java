package application;

public enum ApplicationStatus {
    ACTIVE(0),
    ACCEPTED(1),
    REJECTED(2),
    RETRACTED(3);

    private final int id;
    ApplicationStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ApplicationStatus fromId(int id) {
        for (ApplicationStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
