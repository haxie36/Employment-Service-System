package drivers;

import registration.Profile;
import registration.ProfileDAO;

import java.util.Arrays;
import java.util.Scanner;

public class DRPs {
    public static void main(String[] args) {
        String id;
        ProfileDAO profileDAO;
        Scanner sc = new Scanner(System.in);

        // #1
        System.out.println("#1");
        String choice = sc.nextLine();
        if (choice.equals("Порожній") || choice.equals("порожній") || choice.equals("0")) {
            profileDAO = new ProfileDAO();
        } else {
            profileDAO = new ProfileDAO();
            for (int i = 0; i<3; i++){
                profileDAO.add(new Profile(Integer.toString(i)));
            }
        }

        print(profileDAO);
        if (profileDAO.getAll().length != 0) {
            profileDAO.clear();
            print(profileDAO);
        }
        System.out.println();

        // #2
        System.out.println("#2");
        for (int i = 0; i < 3; i++) {
            System.out.print("id: ");
            id = sc.nextLine();
            profileDAO.add(new Profile(id));
            System.out.println(profileDAO.isRegistered(id));
        }
        print(profileDAO);
        System.out.println();

        // #3
        System.out.println("#3");
        String id0 = profileDAO.getAll()[0].getId();
        profileDAO.delete(id0);
        System.out.println(profileDAO.isRegistered(id0));
        print(profileDAO);
    }

    private static void print(ProfileDAO profileDAO) {
        System.out.println("profiles: " + Arrays.toString(profileDAO.getAll()));
    }
}
