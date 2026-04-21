package application;

import logic.application.AppInput;
import logic.application.Application;
import logic.application.ApplicationController;
import profile.TestProfileDAO;
import vacancy.TestVacancyDAO;

import java.util.Arrays;

public class DrAC {
    private static ApplicationController init() {
        return new ApplicationController(
                new TestApplicationDAO(),
                new TestProfileDAO(),
                new TestVacancyDAO(),
                null,
                null
        );
    }

    private static void test(AppInput[] inputs) {
        ApplicationController controller = init();
        System.out.println(Arrays.toString(controller.getAll()));
        for (AppInput input : inputs) {
            try {
                controller.create(input);
            }  catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(Arrays.toString(controller.getAll()));
        Application lastAdded = controller.getById(5);

        System.out.println(lastAdded);
        controller.changeApplicationStatus(lastAdded, 3);
        System.out.println(Arrays.toString(controller.getAll()));

        Application firstAdded = controller.getById(3);
        controller.delete(firstAdded);
        System.out.println(Arrays.toString(controller.getAll()));
    }

    public static void main(String[] args) {
        //1
        AppInput[] inputs1 = new AppInput[]{
                new AppInput("33333333", 2),
                new AppInput("33333333", 1),
                new AppInput("22222222", 1)
        };
        System.out.println("#1");
        test(inputs1);
        System.out.println("\n".repeat(3));
        //2
        AppInput[] inputs2 = new AppInput[]{
                new AppInput("33333333", 2),
                new AppInput("33333333", 2),
                new AppInput("11111111", 2)
        };
        System.out.println("#2");
        test(inputs2);
        System.out.println("\n".repeat(3));
        //3
        AppInput[] inputs3 = new AppInput[]{
                new AppInput("33333333", 3),
                new AppInput("22222222", 2),
                null
        };
        System.out.println("#3");
        test(inputs3);
    }
}
