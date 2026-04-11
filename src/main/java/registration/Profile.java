package registration;

import base.HasId;
import common.Passport;

import java.time.LocalDate;

public class Profile implements HasId {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String passportNumber;
    private String RNOKPP;
    private String specialty;
    private int experience;

    public Profile() {}
    public Profile(String id, String passportNumber, String RNOKPP, String name, LocalDate birthDate, String specialty, int experience) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.RNOKPP = RNOKPP;
        this.name = name;
        this.birthDate = birthDate;
        this.specialty = specialty;
        this.experience = experience;
    }
    // Tests
    public Profile(String passportNumber) { this.passportNumber = passportNumber; }

    public String getId() {return id;}
    public String getName() {return name;}
    public LocalDate getBirthDate() {return birthDate;}
    public String getPassportNumber() {return passportNumber;}
    public String getRNOKPP() {return RNOKPP;}
    public String getSpecialty() {return specialty;}
    public int getExperience() {return experience;}
    public void setId(String id) {this.id = id;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public void setExperience(int experience) {this.experience = experience;}

    public void setPassportInfo(Passport Passport){
        this.passportNumber = Passport.getPassportNumber();
        this.RNOKPP = Passport.getRNOKPP();
        this.name = Passport.getName();
        this.birthDate = Passport.getBirthday();
    }

    public String toString(){
        return "("+id+") "+name+" -- "+specialty;
    }
}
