package registration;

import common.*;

public class RegistrationController {
    private Profile profile = null;
    private final ServiceArea serviceArea;
    private final SpecialtyCatalog specialtyCatalog;
    private final Profiles profiles;
    private final Office office;

    public RegistrationController(ServiceArea serviceArea,
                                  SpecialtyCatalog specialtyCatalog,
                                  Profiles profiles,
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

    public boolean record(int exp){
        return profile.setExperience(exp);
    }

    public boolean saveProfile(){
        if (profile!=null){
            profiles.add(profile);
            profile.printCertification(office);
            clear();
            return true;
        }
        return false;
    }

    public boolean register(RegInput input){
        newProfile();
        if (!isServiceArea(input.address)) return false;
        if (isRegistered(input.passport)) return false;
        if (!isRealSpecialty(input.specialty)) return false;
        if (!record(input.experience)) return false;

        return saveProfile();
    }

    public void clear(){profile = null;}
}
