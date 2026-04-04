package registration;

import common.EntityCollection;
import common.ID;
import common.Profile;

//A collection of Profiles
public class Profiles extends EntityCollection<Profile> {
    public Profiles() {
        super();
    }
    public Profiles(Profile[] profiles) {
        super(profiles);
    }

    //Check for profile is registered
    public boolean isRegistered(ID ID) {
        String id = ID.getPassportNumber();
        return isRegistered(id);
    }
    public boolean isRegistered(String id) {
        for (Profile profile : items) {
            if (id.equals(profile.getId()))
                return true;
        }
        return false;
    }

    public Profile getByPassport(String passportNumber) {
        for (Profile profile : items) {
            if (passportNumber.equals(profile.getPassportNumber()))
                return profile;
        }
        return null;
    }
}
