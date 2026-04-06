package registration;

import base.HasId;
import common.Office;
import common.Passport;
import common.SpecialtyCatalog;

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
    public Profile(String id) { this.id = id; }

    //Check for the address being serviced or not
    public boolean isServiceArea(String area, ServiceArea serviceArea){
        return serviceArea.isServiceArea(area);
    }

    //Checks if profile is already registered, if not, set info from ID as own
    public boolean isRegistered(Passport Passport, ProfileCollection profileCollection){
        boolean is = profileCollection.isRegistered(Passport);
        if(!is){
            setPassportInfo(Passport);
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
    public String getName() {return name;}
    public LocalDate getBirthDate() {return birthDate;}
    public String getPassportNumber() {return passportNumber;}
    public String getRNOKPP() {return RNOKPP;}
    public String getSpecialty() {return specialty;}
    public int getExperience() {return experience;}
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPassportNumber(String passportNumber) {this.passportNumber = passportNumber;}
    public void setRNOKPP(String RNOKPP){this.RNOKPP = RNOKPP;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public boolean setExperience(int experience) {
        if (experience>=0) {
            this.experience = experience;
            return true;
        } return false;
    }

    public boolean existsByPassportExcept(String passportNumber, ProfileCollection profileCollection){
        return profileCollection.existsByPassportExcept(id, passportNumber);
    }
    public boolean existsByRNOKPPExcept(String RNOKPP, ProfileCollection profileCollection){
        return profileCollection.existsByRNOKPPExcept(id, RNOKPP);
    }

    public void setPassportInfo(Passport Passport){
        setPassportNumber(Passport.getPassportNumber());
        setRNOKPP(Passport.getRNOKPP());
        setName(Passport.getName());
        setBirthDate(Passport.getBirthday());
    }

    public static String getClassName() {return "Profile";}

    public String toString(){
        return "("+id+") "+name+" -- "+specialty;
    }
}
