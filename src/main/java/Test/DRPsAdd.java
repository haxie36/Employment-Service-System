package main.java.Test;

import main.java.Profile;

import java.util.Arrays;

public class DRPsAdd {
    public static void main(String[] args) {
        // Test 1
        Profiles profiles1 = new Profiles();
        Profile profile1 = new Profile("1");
        profiles1.add(profile1);
        print(profiles1);

        // Test 2
        Profiles profiles2 = new Profiles(new String[]{"1"});
        Profile profile2 = new Profile("1");
        profiles2.add(profile2);
        print(profiles2);

        // Test 3
        Profiles profiles3 = new Profiles(new String[]{"1","2","3"});
        Profile profile3 = new Profile("3");
        profiles3.add(profile3);
        print(profiles3);

        // Test 4
        Profiles profiles4 = new Profiles(new String[]{"1","2","3"});
        Profile profile4 = new Profile("4");
        profiles4.add(profile4);
        print(profiles4);

        // Test 5
        Profiles profiles5 = new Profiles(new String[]{"1"});
        Profile profile5 = new Profile("2");
        profiles5.add(profile5);
        print(profiles5);
    }

    private static void print(Profiles profiles) {
        System.out.println(Arrays.toString(profiles.getClientIds()));
    }
}
