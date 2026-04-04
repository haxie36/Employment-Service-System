package application;

public enum ApplicationStatus {
    ACTIVE(0, "Active"),
    ACCEPTED(1,"Accepted"),
    REJECTED(2, "Rejected"),
    RETRACTED(3, "Retracted");

    private final int id;
    private final String name;

    ApplicationStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {return name;}

    public static ApplicationStatus fromId(int id) {
        for (ApplicationStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
