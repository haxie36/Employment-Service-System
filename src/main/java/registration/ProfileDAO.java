package registration;

import base.EntityDAO;
import common.Passport;

//A collection of Profiles
public class ProfileDAO extends EntityDAO<Profile> {
    public ProfileDAO() {
        super(Profile.class);
    }
    public ProfileDAO(Profile[] profiles) {
        super(profiles, Profile.class);
    }

    //Check for profile is registered
    public boolean isRegistered(Passport Passport) {
        String passportNumber = Passport.getPassportNumber();
        return isRegistered(passportNumber);
    }
    public boolean isRegistered(String passportNumber) {
        for (Profile profile : items) {
            if (passportNumber.equals(profile.getPassportNumber()))
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
