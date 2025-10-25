import java.time.LocalDate;

public class ID {
    private String name;
    private LocalDate birthday;
    public String id;
    private String RNKOPP;

    public ID(String name, LocalDate birthday, String id, String RNKOPP) {
        this.name = name;
        this.birthday = birthday;
        this.id = id;
        this.RNKOPP = RNKOPP;
    }

    public String getName() {return name;}
    public LocalDate getBirthday() {return birthday;}
    public String getId() {return id;}
    public String getRNKOPP() {return RNKOPP;}
}
