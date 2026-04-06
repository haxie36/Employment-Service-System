package application;

public enum ApplicationStatus {
    ACTIVE(0, "ACTIVE"),
    ACCEPTED(1,"ACCEPTED"),
    REJECTED(2, "REJECTED"),
    RETRACTED(3, "RETRACTED");

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
    public String toString(){return name;}

    public static ApplicationStatus fromId(int id) {
        for (ApplicationStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
