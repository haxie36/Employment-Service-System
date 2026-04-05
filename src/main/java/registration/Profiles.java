package registration;

import common.EntityCollection;
import common.Passport;
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
    public boolean isRegistered(Passport Passport) {
        String id = Passport.getPassportNumber();
        return isRegistered(id);
    }
    public boolean isRegistered(String id) {
        for (Profile profile : items) {
            if (id.equals(profile.getId()))
                return true;
        } return false;
    }

    public Profile getByPassport(String passportNumber) {
        for (Profile profile : items) {
            if (passportNumber.equals(profile.getPassportNumber()))
                return profile;
        } return null;
    }

    public boolean existsByPassportExcept(String id, String passportNumber) {
        for (Profile profile : items) {
            if (!profile.getId().equals(id)
                    && passportNumber.equals(profile.getPassportNumber()))
                return true;
        } return false;
    }

    public boolean existsByRNOKPPExcept(String id, String RNOKPP) {
        for  (Profile profile : items) {
            if (!profile.getId().equals(id)
                    && RNOKPP.equals(profile.getRNOKPP()))
                return true;
        } return false;
    }
}
