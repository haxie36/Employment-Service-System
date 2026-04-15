package retraining;

import java.time.LocalDate;

public class PlanningInput {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int status;

    public PlanningInput(LocalDate startDate, LocalDate endDate, int status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public int getStatus() {return status;}
}
