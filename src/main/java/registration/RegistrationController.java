package registration;

import common.*;

public class RegistrationController {
    private Profile profile = null;
    private Profile selectedProfile = null;
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

    //Create a new empty Profile
    public void newProfile(){
        profile = new Profile();
    }

    //Check if the address being serviced
    public boolean isServiceArea(String area){
        return profile.isServiceArea(area, serviceArea);
    }

    //Check for profile being dy registered, if isn't, set the Passport info (ID) as profiles
    public boolean isRegistered(ID ID){
        return profile.isRegistered(ID, profiles);
    }

    //Check if the specialty is real, if is, set
    public boolean isRealSpecialty(String specialty){
        return profile.isRealSpecialty(specialty, specialtyCatalog);
    }

    public boolean record(int exp){
        return profile.setExperience(exp);
    }

    //End the registrations by saving the profile in the collection
    public boolean saveProfile(){
        if (profile!=null){
            setProfileId(); //temp
            profiles.add(profile);
            clear();
            return true;
        }
        return false;
    }
    //temp
    private void setProfileId(){
        profile.setId(String.valueOf(profiles.getAll().length+1));
    }

    //Prints certification (formates profile's info)
    public String printCertification(){
        if (profile!=null){
            return profile.printCertification(office);
        }
        return null;
    }

    //All in one
    public boolean register(RegInput input){
        newProfile();
        if (!isServiceArea(input.address)) return false;
        if (isRegistered(input.passport)) return false;
        if (!isRealSpecialty(input.specialty)) return false;
        if (!record(input.experience)) return false;

        return saveProfile();
    }

    //Select, edit and delete
    public Profile selectProfile(String profileId){
        return selectedProfile = profiles.getById(profileId);
    }

    //Change the retraining's status (cancel or retract it)
    public boolean editProfile(RegInput input){
        selectedProfile.setPassportInfo(input.passport);
        selectedProfile.setSpecialty(input.specialty);
        return selectedProfile.setExperience(input.experience);
    }

    public boolean deleteRetraining(){
        boolean result = profiles.delete(selectedProfile.getId());
        clear();
        return result;
    }

    public void clear(){
        profile = null;
        selectedProfile = null;
    }
}
