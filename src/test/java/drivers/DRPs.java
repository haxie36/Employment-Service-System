package drivers;

import registration.Profile;
import registration.ProfileCollection;

import java.util.Arrays;
import java.util.Scanner;

public class DRPs {
    public static void main(String[] args) {
        String id;
        ProfileCollection profileCollection;
        Scanner sc = new Scanner(System.in);

        // #1
        System.out.println("#1");
        String choice = sc.nextLine();
        if (choice.equals("Порожній") || choice.equals("порожній") || choice.equals("0")) {
            profileCollection = new ProfileCollection();
        } else {
            profileCollection = new ProfileCollection();
            for (int i = 0; i<3; i++){
                profileCollection.add(new Profile(Integer.toString(i)));
            }
        }

        print(profileCollection);
        if (profileCollection.getAll().length != 0) {
            profileCollection.clear();
            print(profileCollection);
        }
        System.out.println();

        // #2
        System.out.println("#2");
        for (int i = 0; i < 3; i++) {
            System.out.print("id: ");
            id = sc.nextLine();
            profileCollection.add(new Profile(id));
            System.out.println(profileCollection.isRegistered(id));
        }
        print(profileCollection);
        System.out.println();

        // #3
        System.out.println("#3");
        String id0 = profileCollection.getAll()[0].getId();
        profileCollection.delete(id0);
        System.out.println(profileCollection.isRegistered(id0));
        print(profileCollection);
    }

    private static void print(ProfileCollection profileCollection) {
        System.out.println("profiles: " + Arrays.toString(profileCollection.getAll()));
    }
}
