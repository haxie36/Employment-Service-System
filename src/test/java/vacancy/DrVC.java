package vacancy;

import application.TestApplicationDAO;
import common.TestSpecialtyCatalog;
import logic.application.AppInput;
import logic.vacancy.VacInput;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyController;

import java.util.Arrays;

public class DrVC {
    private static VacancyController init(){
        return new VacancyController(
                new TestVacancyDAO(),
                new TestApplicationDAO(),
                new TestSpecialtyCatalog()
        );
    }

    private static void test(VacInput[] inputs, VacInput editInput) {
        VacancyController controller = init();
        System.out.println(Arrays.toString(controller.getAll()));
        for (VacInput input : inputs) {
            try {
                controller.create(input);
            }  catch (Exception e) {System.out.println(e.getMessage());}
        }
        System.out.println(Arrays.toString(controller.getAll()));
        Vacancy vacancy = controller.getById(4);

        System.out.println(vacancy);
        try {
            controller.edit(vacancy, editInput, 2);
        } catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(vacancy);
        System.out.println(Arrays.toString(controller.getAll()));
        try {
            Vacancy toBeDeleted = controller.getById(5);
            controller.delete(toBeDeleted);
        } catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(Arrays.toString(controller.getAll()));
    }

    public static void main(String[] args) {
        VacInput[] inputs1 = new VacInput[]{
                new VacInput("test1", "company1", "contact_number",
                        "121", 0, "no description"),
                new VacInput("test2", "company2", "contact_number",
                        "121", 1, "no description"),
                new VacInput("test3", "company3", "contact_number",
                        "121", 2, "no description")
        };
        VacInput editInput1 = new VacInput("TESTED", "EVIL CORPORATION", "contact_number",
                        "121", 10, "junior java developer");
        System.out.println("#1");
        test(inputs1, editInput1);
        System.out.print("\n".repeat(3));
        //2
        VacInput[] inputs2 = new VacInput[]{
                new VacInput("test1", "company1", "contact_number",
                        "121", 0, "no description"),
                new VacInput("test2", "company2", "contact_number",
                        "121", 1, "no description"),
                new VacInput("test3", "company3", "contact_number",
                        "000", 2, "no description")
        };
        VacInput editInput2 = new VacInput("bad", "EVIL CORPORATION", "co",
                "121", 10, "junior java developer");
        System.out.println("#2");
        test(inputs2, editInput2);
        System.out.print("\n".repeat(3));
        //3
        VacInput[] inputs3 = new VacInput[]{
                new VacInput("test".repeat(26), "company1", "contact_number",
                        "121", 0, "no description"),
                new VacInput("test2", "company2", "contact_number",
                        "121", -1, "no description"),
                null
        };
        System.out.println("#3");
        test(inputs3, null);
    }
}
