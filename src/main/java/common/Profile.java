package common;

import interfaces.HasId;
import registration.Profiles;
import registration.ServiceArea;

import java.time.LocalDate;

public class Profile implements HasId {
    private String id;
    private String passportNumber;
    private String RNOKPP;
    private String name;
    private LocalDate birthday;
    private String specialty;
    private int experience;

    public Profile() {}
    public Profile(String id, String passportNumber, String RNOKPP, String name, LocalDate birthday, String specialty, int experience) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.RNOKPP = RNOKPP;
        this.name = name;
        this.birthday = birthday;
        this.specialty = specialty;
        this.experience = experience;
    }
    // Tests
    public Profile(String id) { this.id = id; }

    //Check for the address being serviced or not
    public boolean isServiceArea(String area, ServiceArea serviceArea){
        return serviceArea.isServiceArea(area);
    }

    //Checks if profile is already registered, if not, set info from ID as own
    public boolean isRegistered(ID ID, Profiles profiles){
        boolean is = profiles.isRegistered(ID);
        if(!is){
            setPassportInfo(ID);
        }
        return is;
    }

    //Is it a real specialty, if it is, set it as own
    public boolean isRealSpecialty(String specialty,
                                   SpecialtyCatalog specialtyCatalog){
        boolean is = specialtyCatalog.isRealSpecialty(specialty);
        if (is){setSpecialty(specialty);}
        return is;
    }

    //Prints certification (formates profile's info)
    public String printCertification(Office office){
        return office.printCertification(this);
    }

    public String getId() {return id;}
    public String getPassportNumber() {return passportNumber;}
    public String getRNOKPP() {return RNOKPP;}
    public String getName() {return name;}
    public LocalDate getBirthday() {return birthday;}
    public String getSpecialty() {return specialty;}
    public int getExperience() {return experience;}
    public void setId(String id) {this.id = id;}
    public void setPassportNumber(String passportNumber) {this.passportNumber = passportNumber;}
    public void setRNOKPP(String RNOKPP){this.RNOKPP = RNOKPP;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(LocalDate birthday) {this.birthday = birthday;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public boolean setExperience(int experience) {
        if (experience>=0) {
            this.experience = experience;
            return true;
        } return false;
    }

    public void setPassportInfo(ID ID){
        setPassportNumber(ID.getPassportNumber());
        setRNOKPP(ID.getRNOKPP());
        setName(ID.getName());
        setBirthday(ID.getBirthday());
    }

    public String toString(){
        return id;
    }
}
