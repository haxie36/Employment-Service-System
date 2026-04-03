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
        for (int i=0; i<items.length; i++) {
            if (id.equals(items[i].getId()))
                return true;
        }
        return false;
    }

    public Profile getById(ID ID) {
        String id = ID.getPassportNumber();
        return getById(id);
    }
}
