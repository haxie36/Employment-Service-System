package drivers;

import registration.Profile;
import registration.ProfileCollection;

import java.util.Arrays;

public class DRPsAdd {
    public static void main(String[] args) {
        // Test 1
        ProfileCollection profileCollection1 = new ProfileCollection();
        Profile profile1 = new Profile("1");
        profileCollection1.add(profile1);
        print(profileCollection1);

        // Test 2
        ProfileCollection profileCollection2 = new ProfileCollection(new Profile[]{new Profile("1")});
        Profile profile2 = new Profile("1");
        profileCollection2.add(profile2);
        print(profileCollection2);

        // Test 3
        ProfileCollection profileCollection3 = new ProfileCollection();
        for (int i = 0; i<3; i++){
            profileCollection3.add(new Profile(Integer.toString(i)));
        }
        Profile profile3 = new Profile("3");
        profileCollection3.add(profile3);
        print(profileCollection3);

        // Test 4
        ProfileCollection profileCollection4 = new ProfileCollection();
        for (int i = 0; i<3; i++){
            profileCollection4.add(new Profile(Integer.toString(i)));
        }
        Profile profile4 = new Profile("4");
        profileCollection4.add(profile4);
        print(profileCollection4);

        // Test 5
        ProfileCollection profileCollection5 = new ProfileCollection(new Profile[]{new Profile("1")});
        Profile profile5 = new Profile("2");
        profileCollection5.add(profile5);
        print(profileCollection5);
    }

    private static void print(ProfileCollection profileCollection) {
        System.out.println(Arrays.toString(profileCollection.getAll()));
    }
}
