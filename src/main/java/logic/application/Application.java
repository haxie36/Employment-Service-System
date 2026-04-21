package logic.application;

import logic.base.HasId;

import java.time.LocalDate;

//The application for the vacancy
public class Application implements HasId {
    int id;
    int profileId;
    int vacancyId;
    LocalDate applicationDate;
    ApplicationStatus status;

    public Application() {
        applicationDate = LocalDate.now();
        status = ApplicationStatus.ACTIVE;
    }
    public Application(int id, int profileId, int vacancyId, LocalDate applicationDate, ApplicationStatus status) {
        this.id = id;
        this.profileId = profileId;
        this.vacancyId = vacancyId;
        this.applicationDate = applicationDate;
        this.status = status;
    }
    // Tests
    public Application(int id){
        this();
        this.id=id;
        profileId=id;
        vacancyId=id;
    }

    public int getId() {return id;}
    public int getProfileId() {return profileId;}
    public int getVacancyId() {return vacancyId;}
    public LocalDate getApplicationDate() {return applicationDate;}
    public ApplicationStatus getStatus() {return status;}
    public void setId(int id) {this.id = id;}
    public void setProfileId(int profileId) {this.profileId = profileId;}
    public void setVacancyId(int vacancyId) {this.vacancyId = vacancyId;}
    public void setApplicationDate(LocalDate applicationDate) {this.applicationDate = applicationDate;}
    public void setStatus(ApplicationStatus status) {this.status = status;}

    @Override
    public String toString() {
        return String.format(
                "(%s) Profile: %s -- Vacancy: %s | %s [%s]",
                id,
                profileId,
                vacancyId,
                applicationDate,
                status
        );
    }
}
