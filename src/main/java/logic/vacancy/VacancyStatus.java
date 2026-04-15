package logic.vacancy;

public enum VacancyStatus {
    OPEN(0, "OPEN"),
    CLOSED(1, "CLOSED"),
    RETRACTED(2,"RETRACTED"),;

    private final int id;
    private final String name;

    VacancyStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {return name;}
    public String toString(){return name;}

    public static VacancyStatus fromId(int id) {
        for (VacancyStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status");
    }
}
