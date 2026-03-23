package main.java;

public class Profiles {
    private Profile[] profiles;

    public Profiles() {
        profiles = new Profile[0];
    }

    public Profiles(Profile[] profiles) {
        this.profiles = profiles;
    }

    public boolean isRegistered(ID ID) {
        String id = ID.getId();
        return isRegistered(id);
    }
    public boolean isRegistered(String id) {
        for (int i=0; i<profiles.length; i++) {
            if (id.equals(profiles[i].getId()))
                return true;
        }
        return false;
    }

    public boolean add(Profile profile) {
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
        return !exists;
    }

    public Profile getProfile(ID ID) {
        String id = ID.getId();
        return getProfile(id);
    }
    public Profile getProfile(String id) {
        for (int i=0; i<profiles.length; i++) {
            Profile profile = profiles[i];

            if (id.equals(profile.getId())) {
                return profile;
            }
        }
        return null;
    }

    public Profile[] getProfiles() { return profiles; }
    public void clear(){ profiles = new Profile[0]; }

    public boolean delete(Profile profile) {
        String id = profile.getId();
        return delete(id);
    }
    public boolean delete(String id){
        for (int i=0; i<profiles.length; i++) {
            if (id.equals(profiles[i].getId())) {
                Profile[] newProfiles = new Profile[profiles.length-1];
                for (int j=0; j<profiles.length; j++) {
                    if (j==i) continue;
                    newProfiles[j] = profiles[i];
                }
                profiles=newProfiles;
                return true;
            }
        }
        return false;
    }
}
