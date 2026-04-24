package profile;

import common.TestSpecialtyCatalog;
import logic.common.Passport;
import logic.profile.RegInput;
import logic.profile.RegistrationController;

import java.time.LocalDate;

public class DrRegCCreate {
    private static RegistrationController init() {
        return new RegistrationController(
                new TestServiceArea(),
                new TestSpecialtyCatalog(),
                new TestProfileDAO(),
                new TestOffice());
    }

    private static void test(RegInput input) {
        try {
            RegistrationController controller = init();
            controller.create(input);
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("NOT PASS");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        test(null);
        test(new RegInput(
                "invalid",
                new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"),
                "121",
                0));
        test(new RegInput(
                "test",
                new Passport("te", LocalDate.parse("2026-01-01"), "44444444", "4444444444"),
                "121",
                0));
        test(new RegInput(
                "test",
                new Passport("test", LocalDate.parse("1999-01-01"), "33333333", "3333333333"),
                "121",
                0));
        test(new RegInput(
                "test",
                new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"),
                "000",
                -1));
        test(new RegInput(
                "test",
                new Passport("test", LocalDate.parse("1999-01-01"), "44444444", "4444444444"),
                "121",
                0));
    }
}
