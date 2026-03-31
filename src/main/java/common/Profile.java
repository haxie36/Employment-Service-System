package common;

import registration.Profiles;
import registration.ServiceArea;

import java.time.LocalDate;

public class Profile {
    private String id;
    private String RNOKPP;
    private String name;
    private LocalDate birthday;
    private String specialty;
    private int experience;

    public Profile() {}
    public Profile(String id, String RNOKPP, String name, LocalDate birthday, String specialty, int experience) {
        this.id = id;
        this.RNOKPP = RNOKPP;
        this.name = name;
        this.birthday = birthday;
        this.specialty = specialty;
        this.experience = experience;
    }
    // Для тестів
    public Profile(String id) { this.id = id; }

    public boolean isServiceArea(String area, ServiceArea serviceArea){
        return serviceArea.isServiceArea(area);
    }

    public boolean isRegistered(ID ID, Profiles profiles){
        boolean is = profiles.isRegistered(ID);
        if(!is){
            setId(ID.getId());
            setRNOKPP(ID.getRNOKPP());
            setName(ID.getName());
            setBirthday(ID.getBirthday());
        }
        return is;
    }

    public boolean isRealSpecialty(String specialty, SpecialtyCatalog specialtyCatalog){
        boolean is = specialtyCatalog.isRealSpecialty(specialty);
        if (is){setSpecialty(specialty);}
        return is;
    }

    public void printCertification(Office office){
        office.printCertification(this);
    }

    public String getId() {return id;}
    public String getRNOKPP() {return RNOKPP;}
    public String getName() {return name;}
    public LocalDate getBirthday() {return birthday;}
    public String getSpecialty() {return specialty;}
    public int getExperience() {return experience;}
    public void setId(String id) {this.id = id;}
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

    public String toString(){
        return id;
    }
}
