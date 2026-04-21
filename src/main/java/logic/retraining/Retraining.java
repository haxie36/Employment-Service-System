package logic.retraining;

import logic.base.HasId;

import java.time.LocalDate;

public class Retraining implements HasId {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String specialty;
    private int profileId;
    private RetrainingStatus status;

    public Retraining(){
        startDate = null;
        endDate = null;
        status = RetrainingStatus.NEW;
    }
    public Retraining(int id, LocalDate startDate, LocalDate endDate, String specialty, int profileId, RetrainingStatus status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialty = specialty;
        this.profileId = profileId;
        this.status = status;
    }
    // Tests
    public Retraining(int id){
        this.id = id;
        startDate = LocalDate.now();
        endDate = startDate;
        specialty = "121";
        profileId = id;
        status = RetrainingStatus.NEW;
    }

    //Set dates and assign status
    public void plan(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
        assignStatus();
    }

    //Auto-assign statuses based on start and end dates
    private void assignStatus() {
        if (startDate == null || endDate == null) {status=RetrainingStatus.NEW;}
        else if (startDate.isBefore(LocalDate.now()) && !endDate.isBefore(LocalDate.now())) {status=RetrainingStatus.IN_PROGRESS;}
        else if (endDate.isBefore(LocalDate.now())) {status=RetrainingStatus.COMPLETED;}
    }

    public void update(PlanningInput input) {
        plan(input.getStartDate(), input.getEndDate());
        this.status = RetrainingStatus.fromId(input.getStatus());
    }

    public int getId() {return id;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getSpecialty() {return specialty;}
    public int getProfileId() {return profileId;}
    public RetrainingStatus getStatus() {return status;}
    public void setId(int id) {this.id = id;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public void setProfileId(int profileId) {this.profileId = profileId;}
    public void setStatus(int status) {this.status = RetrainingStatus.fromId(status);}
    public void setStatus(RetrainingStatus status) {this.status = status;}

    @Override
    public String toString() {
        return String.format(
                "(%s) Profile: %s | %s [%s]",
                id,
                profileId,
                specialty,
                status.getName()
        );
    }
}
