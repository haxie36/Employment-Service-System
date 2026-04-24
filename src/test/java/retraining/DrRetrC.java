package retraining;

import common.TestSpecialtyCatalog;
import logic.retraining.PlanningInput;
import logic.retraining.RetrInput;
import logic.retraining.Retraining;
import logic.retraining.RetrainingController;
import profile.TestProfileDAO;

import java.time.LocalDate;
import java.util.Arrays;

public class DrRetrC {
    private static RetrainingController init() {
        return new RetrainingController(
                new TestProfileDAO(),
                new TestSpecialtyCatalog(),
                new TestRetrainingDAO()
        );
    }

    private static void test(RetrInput[] inputs, PlanningInput planningInput, PlanningInput editInput) {
        RetrainingController controller = init();
        System.out.println(Arrays.toString(controller.getAll()));
        for (RetrInput input : inputs) {
            try {
                controller.create(input);
            } catch (Exception e) {System.out.println(e.getMessage());}
        }
        System.out.println(Arrays.toString(controller.getAll()));
        Retraining retraining = controller.getById(3);
        System.out.println(retraining);
        try {
            controller.plan(retraining, planningInput);
        } catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(retraining);
        try {
            controller.edit(retraining, editInput);
        } catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(retraining);
        System.out.println(Arrays.toString(controller.getAll()));
        try {
            controller.delete(retraining);
        } catch (Exception e) {System.out.println(e.getMessage());}
        System.out.println(Arrays.toString(controller.getAll()));
    }

    public static void main(String[] args) {
        RetrInput[] inputs1 = new RetrInput[]{
                new RetrInput("121", "22222222"),
                new RetrInput("121", "33333333")};
        PlanningInput planningInput1 = new PlanningInput(LocalDate.of(2026,5,1), LocalDate.of(2026,5,10), -1);
        PlanningInput editInput1 = new PlanningInput(LocalDate.of(2026,5,1), LocalDate.of(2026,5,10), 3);
        System.out.println("#1");
        test(inputs1, planningInput1, editInput1);
        System.out.print("\n".repeat(3));
        //2
        RetrInput[] inputs2 = new RetrInput[]{
                new RetrInput("121", "22222222"),
                new RetrInput("121", "11111111"),
                new RetrInput("000", "33333333"),
                new RetrInput("121", "33333333")};
        PlanningInput planningInput2 = new PlanningInput(LocalDate.of(2026,6,1), LocalDate.of(2026,5,10), -1);
        PlanningInput editInput2 = new PlanningInput(LocalDate.of(2026,5,1), LocalDate.of(2026,5,10), 9);
        System.out.println("#2");
        test(inputs2, planningInput2, editInput2);
        System.out.print("\n".repeat(3));
        //3
        RetrInput[] inputs3 = new RetrInput[]{
                new RetrInput("121", "22222222"),
                new RetrInput("121", "66666666"),
                null};
        System.out.println("#3");
        test(inputs3, null, null);
    }
}
