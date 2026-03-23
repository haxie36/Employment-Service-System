package main.java;

public class Registration {
    private Profile profile = null;
    private final ServiceArea serviceArea;
    private final SpecialtyCatalog specialtyCatalog;
    private final Profiles profiles;
    private final Office office;

    public Registration(ServiceArea serviceArea,
                        SpecialtyCatalog specialtyCatalog, Profiles profiles,
                        Office office) {
        this.serviceArea = serviceArea;
        this.specialtyCatalog = specialtyCatalog;
        this.profiles = profiles;
        this.office = office;
    }

    public Profile newProfile(){
        profile = new Profile();
        return profile;
    }

    public boolean isServiceArea(String area){
        return profile.isServiceArea(area, serviceArea);
    }

    public boolean isRegistered(ID ID){
        return profile.isRegistered(ID, profiles);
    }

    public boolean isRealSpecialty(String specialty){
        return profile.isRealSpecialty(specialty, specialtyCatalog);
    }

    public boolean record(short exp){
        profile.setExperience(exp);
        return true;
    }

    public boolean saveProfile(){
        if (profile!=null){
            profiles.add(profile);
            profile.printCertification(office);
            profile = null;
            return true;
        }
        return false;
    }
}
