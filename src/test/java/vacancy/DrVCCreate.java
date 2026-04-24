package vacancy;

import application.TestApplicationDAO;
import common.TestSpecialtyCatalog;
import logic.vacancy.VacInput;
import logic.vacancy.VacancyController;

public class DrVCCreate {
    private static VacancyController init(){
        return new VacancyController(
                new TestVacancyDAO(),
                new TestApplicationDAO(),
                new TestSpecialtyCatalog()
        );
    }

    private static void test(VacInput input) {
        VacancyController controller = init();
        try {
            controller.create(input);
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("NOT PASS");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        test(new VacInput("te",
                "testCompany", "testContact",
                "121", 0,
                "this is a test vacancy"));
        test(new VacInput("test",
                "te", "testContact",
                "121", 0,
                "this is a test vacancy"));
        test(new VacInput("test",
                "testCompany", "te",
                "121", 0,
                "this is a test vacancy"));
        test(new VacInput("test",
                "testCompany", "testContact",
                "000", 0,
                "this is a test vacancy"));
        test(new VacInput("test",
                "testCompany", "testContact",
                "121", -1,
                "this is a test vacancy"));
        test(new VacInput("test", 
                "testCompany", "testContact",
                "121", 0,
                "this is a test vacancy"));
    }
}
