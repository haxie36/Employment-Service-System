package retraining;

import java.time.LocalDate;

public class PlanningInput {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String specialty;
    private final int status;

    public PlanningInput(LocalDate startDate, LocalDate endDate, String specialty, int status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialty = specialty;
        this.status = status;
    }

    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getSpecialty() {return specialty;}
    public int getStatus() {return status;}
}
