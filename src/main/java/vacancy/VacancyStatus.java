package vacancy;

public enum VacancyStatus {
    OPEN(0),
    CLOSED(1),
    RETRACTED(2);

    private final int id;

    VacancyStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static VacancyStatus fromId(int id) {
        for (VacancyStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
