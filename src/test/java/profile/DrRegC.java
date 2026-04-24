package profile;

import common.TestSpecialtyCatalog;
import logic.common.Passport;
import logic.profile.Profile;
import logic.profile.RegInput;
import logic.profile.RegistrationController;

import java.time.LocalDate;
import java.util.Arrays;

public class DrRegC {
    private static RegistrationController init() {
        return new RegistrationController(
                new TestServiceArea(),
                new TestSpecialtyCatalog(),
                new TestProfileDAO(),
                new TestOffice());
    }

    private static void test(RegInput[] inputs, RegInput editInput) {
        RegistrationController controller = init();
        System.out.println(Arrays.toString(controller.getAll()));
        for (RegInput input : inputs) {
            try {
                controller.create(input);
            } catch (Exception e) {System.out.println(e.getMessage());}
        }
        System.out.println(Arrays.toString(controller.getAll()));
        Profile lastProfile = controller.getById(6);
        System.out.println(lastProfile);
        try {
            controller.edit(lastProfile, editInput);
        }  catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(Arrays.toString(controller.getAll()));
        Profile toBeDeleted = controller.getById(5);
        try {
            controller.delete(toBeDeleted);
        } catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(Arrays.toString(controller.getAll()));
    }

    public static void main(String[] args) {
        RegInput[] inputs1 = new RegInput[]{
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"), "121", 0),
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "55555555", "5555555555"), "121", 0),
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "66666666", "6666666666"), "121", 0)
        };
        RegInput editInput1 = new RegInput("test", new Passport("TEST", LocalDate.parse("2000-01-01"), "77777777", "7777777777"), "121", 1);
        System.out.println("#1");
        test(inputs1, editInput1);
        System.out.print("\n".repeat(3));
        //2
        RegInput[] inputs2 = new RegInput[]{
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"), "121", 0),
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"), "121", 0),
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "2Short", "TOOOOOO_LOOOONG"), "121", 0)
        };
        RegInput editInput2 = new RegInput("test", new Passport("TEST", LocalDate.parse("2000-01-01"), "77777777", "7777777777"), "121", 1);
        System.out.println("#2");
        test(inputs2, editInput2);
        System.out.print("\n".repeat(3));
        //3
        RegInput[] inputs3 = new RegInput[]{
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"), "121", 0),
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "55555555", "5555555555"), "121", 0),
                new RegInput("test", new Passport("test", LocalDate.parse("1999-01-01"), "66666666", "6666666666"), "121", 0)
        };
        System.out.println("#3");
        test(inputs3, null);
    }
}
