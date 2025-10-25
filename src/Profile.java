import java.time.LocalDate;

public class Profile {
    private boolean isServed;
    private String id;
    private String RNKOPP;
    private String name;
    private LocalDate birthday;
    private String specialty;
    private short experience;

    public Profile() {}

    public boolean isServiceArea(String area, ServiceArea sa){
        return isServed = sa.isServiceArea(area);
    }

    public boolean isRegistered(ID ID, ClientList cl){
        boolean is = cl.isRegistered(ID);
        if(!is){
            id = ID.getId();
            RNKOPP = ID.getRNKOPP();
            name = ID.getName();
            birthday = ID.getBirthday();
        }
        return is;
    }

    public boolean isRealSpecialty(String specialty, SpecialtyCatalog sc){
        boolean is = sc.isRealSpecialty(specialty);
        if (is){setSpecialty(specialty);}
        return is;
    }

    public void printCertification(Office office){
        office.printCertification(this);
    }

    public boolean isServed() {return isServed;}
    public String getId() {return id;}
    public String getRNKOPP() {return RNKOPP;}
    public String getName() {return name;}
    public LocalDate getBirthday() {return birthday;}
    public String getSpecialty() {return specialty;}
    public short getExperience() {return experience;}
    public void setId(String id) {this.id = id;}
    public void setRNKOPP(String RNKOPP){this.RNKOPP = RNKOPP;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(LocalDate birthday) {this.birthday = birthday;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public void setExperience(short experience) {this.experience = experience;}
}
