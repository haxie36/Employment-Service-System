package main.java;

public class Profiles {
    private Profile[] profiles;

    public Profiles() {
        profiles = new Profile[0];
    }

    public Profiles(Profile[] clientList) {
        this.profiles = clientList;
    }

    public void add(Profile profile) {
        String id = profile.getId();
        boolean exists = false;

        for (int i=0; i<profiles.length; i++) {
            if (id.equals(profiles[i].getId())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            Profile[] newProfiles = new Profile[profiles.length+1];

            for (int i=0; i<profiles.length; i++) {
                newProfiles[i] = profiles[i];
            }

            newProfiles[profiles.length] = profile;
            profiles=newProfiles;
        }
    }

    public Profile getProfile(ID ID) {
        String id = ID.getId();

        for (int i=0; i<profiles.length; i++) {
            Profile profile = profiles[i];

            if (id.equals(profile.getId())) {
                return profile;
            }
        }
        return null;
    }
}
