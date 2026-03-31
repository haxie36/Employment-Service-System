package common;

import application.ApplicationStatus;
import application.RecSystem;
import vacancy.Vacancies;
import registration.Profiles;

import java.time.LocalDate;

public class Application {
    String id;
    String profileId;
    String vacancyId;
    LocalDate applicationDate;
    ApplicationStatus status;

    public Application() {
        applicationDate = LocalDate.now();
        status = ApplicationStatus.ACTIVE;
    }
    public Application(String id, String profileId, String vacancyId, LocalDate applicationDate, ApplicationStatus status) {
        this.id = id;
        this.profileId = profileId;
        this.vacancyId = vacancyId;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public boolean findProfile(String id, Profiles profiles) {
        Profile profile = profiles.getProfile(id);
        if(profile == null) return false;

        profileId = profile.getId();
        return true;
    }

    public Vacancy[] getRecommendations(RecSystem recSystem, Profiles profiles, Vacancies vacancies) {
        Profile profile = profiles.getProfile(profileId);
        return recSystem.getRecommendations(profile, vacancies);
    }

    public String getId() {return id;}
    public String getProfileId() {return profileId;}
    public String getVacancyId() {return vacancyId;}
    public LocalDate getApplicationDate() {return applicationDate;}
    public ApplicationStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
    public void setProfileId(String profileId) {this.profileId = profileId;}
    public void setVacancyId(String vacancyId) {this.vacancyId = vacancyId;}
    public void setApplicationDate(LocalDate applicationDate) {this.applicationDate = applicationDate;}
    public void setStatus(ApplicationStatus status) {this.status = status;}
}
