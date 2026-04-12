package application;

import base.HasId;
import registration.Profile;
import vacancy.Vacancy;

import java.time.LocalDate;

//The application for the vacancy
public class Application implements HasId {
    String id;
    String profileId;
    Profile profile;
    String vacancyId;
    Vacancy vacancy;
    LocalDate applicationDate;
    ApplicationStatus status;

    public Application() {
        applicationDate = LocalDate.now();
        status = ApplicationStatus.ACTIVE;
    }
    public Application(String id, Profile profile, Vacancy vacancy, LocalDate applicationDate, ApplicationStatus status) {
        this.id = id;
        this.profile = profile;
        this.profileId = profile.getId();
        this.vacancy = vacancy;
        this.vacancyId = vacancy.getId();
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public String getId() {return id;}
    public String getProfileId() {return profileId;}
    public Profile getProfile() {return profile;}
    public String getVacancyId() {return vacancyId;}
    public Vacancy getVacancy() {return vacancy;}
    public LocalDate getApplicationDate() {return applicationDate;}
    public ApplicationStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
    public void setProfile(Profile profile) {
        this.profile = profile;
        this.profileId = profile.getId();
    }
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
        this.vacancyId = vacancy.getId();
    }
    public void setApplicationDate(LocalDate applicationDate) {this.applicationDate = applicationDate;}
    public void setStatus(ApplicationStatus status) {this.status = status;}

    public String toString(){
        return "("+id+") "+profile.getName()+" -- "+vacancy.getTitle()
                +" ["+status.getName()+"]";
    }
}
