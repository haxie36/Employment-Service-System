package drivers;

import registration.Profile;
import registration.ProfileDAO;

import java.util.Arrays;

public class DRPsAdd {
    public static void main(String[] args) {
        // Test 1
        ProfileDAO profileDAO1 = new ProfileDAO();
        Profile profile1 = new Profile("1");
        profileDAO1.add(profile1);
        print(profileDAO1);

        // Test 2
        ProfileDAO profileDAO2 = new ProfileDAO(new Profile[]{new Profile("1")});
        Profile profile2 = new Profile("1");
        profileDAO2.add(profile2);
        print(profileDAO2);

        // Test 3
        ProfileDAO profileDAO3 = new ProfileDAO();
        for (int i = 0; i<3; i++){
            profileDAO3.add(new Profile(Integer.toString(i)));
        }
        Profile profile3 = new Profile("3");
        profileDAO3.add(profile3);
        print(profileDAO3);

        // Test 4
        ProfileDAO profileDAO4 = new ProfileDAO();
        for (int i = 0; i<3; i++){
            profileDAO4.add(new Profile(Integer.toString(i)));
        }
        Profile profile4 = new Profile("4");
        profileDAO4.add(profile4);
        print(profileDAO4);

        // Test 5
        ProfileDAO profileDAO5 = new ProfileDAO(new Profile[]{new Profile("1")});
        Profile profile5 = new Profile("2");
        profileDAO5.add(profile5);
        print(profileDAO5);
    }

    private static void print(ProfileDAO profileDAO) {
        System.out.println(Arrays.toString(profileDAO.getAll()));
    }
}
