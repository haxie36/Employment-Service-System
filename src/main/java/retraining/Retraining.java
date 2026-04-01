package retraining;

import common.Profile;
import common.SpecialtyCatalog;
import registration.Profiles;

import java.time.LocalDate;

public class Retraining {
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

    public boolean isRealSpecialty(String specialty, SpecialtyCatalog specialtyCatalog) {
        boolean is = specialtyCatalog.isRealSpecialty(specialty);
        if (is){setSpecialty(specialty);}
        return is;
    }

    public boolean findProfile(String profileId, Profiles profiles) {
        Profile profile = profiles.getProfile(id);
        if(profile == null) return false;
        this.profile = profile;
        this.profileId = profileId;
        return true;
    }

    public boolean isValidPeriod(LocalDate startDate, LocalDate endDate){
        boolean result = startDate != null && endDate != null &&
                !startDate.isBefore(LocalDate.now()) &&
                !endDate.isBefore(startDate);
        if(result){
            setStartDate(startDate);
            setEndDate(endDate);
            assignStatus();
        }return result;
    }

    private void assignStatus() {
        if (startDate == null || endDate == null) {status=RetrainingStatus.NEW;}
        else if (startDate.isBefore(LocalDate.now()) && !endDate.isBefore(LocalDate.now())) {status=RetrainingStatus.IN_PROGRESS;}
        else if (endDate.isBefore(LocalDate.now())) {status=RetrainingStatus.COMPLETED;}
    }

    public String getId() {return id;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getSpecialty() {return specialty;}
    public String getProfileId() {return profileId;}
    public RetrainingStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public void setProfileId(String profileId) {this.profileId = profileId;}
    public void setStatus(int status) {this.status = RetrainingStatus.fromId(status);}
    public void setStatus(RetrainingStatus status) {this.status = status;}
}
