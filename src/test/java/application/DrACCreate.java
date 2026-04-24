package application;

import logic.application.AppInput;
import logic.application.ApplicationController;
import profile.TestProfileDAO;
import retraining.TestRetrainingDAO;
import vacancy.TestVacancyDAO;

public class DrACCreate {
    private static ApplicationController init() {
        return new ApplicationController(
                new TestApplicationDAO(),
                new TestProfileDAO(),
                new TestVacancyDAO(),
                null,
                null);
    }

    private static void test(AppInput input) {
        try {
            ApplicationController controller = init();
            controller.create(input);
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("NOT PASS");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        test(null);
        test(new AppInput("44444444", 1));
        test(new AppInput("33333333", 3));
        test(new AppInput("11111111", 2));
        test(new AppInput("11111111", 1));
        test(new AppInput("33333333", 2));
    }
}