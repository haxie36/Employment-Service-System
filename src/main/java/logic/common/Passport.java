package logic.common;

import java.time.LocalDate;

//Passport info...
public class Passport {
    private final String name;
    private final LocalDate birthday;
    public final String passportNumber;
    private final String RNOKPP;

    public Passport(String name, LocalDate birthday, String passportNumber, String RNOKPP) {
        this.name = name;
        this.birthday = birthday;
        this.passportNumber = passportNumber;
        this.RNOKPP = RNOKPP;
    }

    public String getName() {return name;}
    public LocalDate getBirthday() {return birthday;}
    public String getPassportNumber() {return passportNumber;}
    public String getRNOKPP() {return RNOKPP;}
}
