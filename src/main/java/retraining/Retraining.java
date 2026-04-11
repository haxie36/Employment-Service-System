package retraining;

import base.HasId;
import common.DateUtils;
import common.SpecialtyCatalog;
import registration.Profile;
import registration.ProfileCollection;

import java.time.LocalDate;

public class Retraining implements HasId {
    String id;
    LocalDate startDate;
    LocalDate endDate;
    String specialty;
    String profileId;
    Profile profile;
    RetrainingStatus status;

    public Retraining(){
        startDate = null;
        endDate = null;
        status = RetrainingStatus.NEW;
    }
    public Retraining(LocalDate startDate, LocalDate endDate, String specialty, Profile profile, int status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialty = specialty;
        this.profile = profile;
        this.profileId = profile.getId();
        this.status = RetrainingStatus.fromId(status);
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
        this.specialty = input.getSpecialty();
        this.status = RetrainingStatus.fromId(input.getStatus());
    }

    public String getId() {return id;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getSpecialty() {return specialty;}
    public String getProfileId() {return profileId;}
    public  Profile getProfile() {return profile;}
    public RetrainingStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public void setProfile(Profile profile) {
        this.profile = profile;
        this.profileId = profile.getId();
    }
    public void setStatus(int status) {this.status = RetrainingStatus.fromId(status);}
    public void setStatus(RetrainingStatus status) {this.status = status;}

    public String toString(){
        return "("+id+") "+specialty+" -- "+profile.getName()
                +" ["+status.getName()+"]";
    }
}
