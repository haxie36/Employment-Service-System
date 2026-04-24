package retraining;

import common.TestSpecialtyCatalog;
import logic.retraining.RetrInput;
import logic.retraining.RetrainingController;
import profile.TestProfileDAO;

public class DrRetrCCreate {
    private static RetrainingController init() {
        return new RetrainingController(
                new TestProfileDAO(),
                new TestSpecialtyCatalog(),
                new TestRetrainingDAO());
    }

    private static void test(RetrInput input) {
        try {
            RetrainingController controller = init();
            controller.create(input);
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("NOT PASS");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        test(null);
        test(new RetrInput("000", "11111111"));
        test(new RetrInput("121", "77777777"));
        test(new RetrInput("121", "11111111"));
        test(new RetrInput("121", "33333333"));
    }
}
