package drivers;

import common.Profile;
import registration.Profiles;

import java.util.Arrays;
import java.util.Scanner;

public class DRPs {
    public static void main(String[] args) {
        String id;
        Profiles profiles;
        Scanner sc = new Scanner(System.in);

        // #1
        System.out.println("#1");
        String choice = sc.nextLine();
        if (choice.equals("Порожній") || choice.equals("порожній") || choice.equals("0")) {
            profiles = new Profiles();
        } else {
            profiles = new Profiles();
            for (int i = 0; i<3; i++){
                profiles.add(new Profile(Integer.toString(i)));
            }
        }

        print(profiles);
        if (profiles.getProfiles().length != 0) {
            profiles.clear();
            print(profiles);
        }
        System.out.println();

        // #2
        System.out.println("#2");
        for (int i = 0; i < 3; i++) {
            System.out.print("id: ");
            id = sc.nextLine();
            profiles.add(new Profile(id));
            System.out.println(profiles.isRegistered(id));
        }
        print(profiles);
        System.out.println();

        // #3
        System.out.println("#3");
        String id0 = profiles.getProfiles()[0].getId();
        profiles.delete(id0);
        System.out.println(profiles.isRegistered(id0));
        print(profiles);
    }

    private static void print(Profiles profiles) {
        System.out.println("profiles: " + Arrays.toString(profiles.getProfiles()));
    }
}
