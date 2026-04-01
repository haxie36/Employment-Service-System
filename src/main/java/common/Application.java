package common;

import application.ApplicationStatus;
import application.RecSystem;
import vacancy.Vacancies;
import registration.Profiles;

import java.time.LocalDate;

public class Application {
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

    public boolean findProfile(String profileId, Profiles profiles) {
        Profile profile = profiles.getProfile(profileId);
        if(profile == null) return false;
        this.profile = profile;
        this.profileId = profileId;
        return true;
    }

    public Vacancy[] getRecommendations(RecSystem recSystem, Profiles profiles, Vacancies vacancies) {
        Profile profile = profiles.getProfile(profileId);
        return recSystem.getRecommendations(profile, vacancies);
    }

    public String getId() {return id;}
    public String getProfileId() {return profileId;}
    public Profile getProfile() {return profile;}
    public String getVacancyId() {return vacancyId;}
    public Vacancy getVacancy() {return vacancy;}
    public LocalDate getApplicationDate() {return applicationDate;}
    public ApplicationStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
    public void setProfileId(String profileId) {this.profileId = profileId;}
    public void setProfile(Profile profile) {this.profile = profile;}
    public void setVacancyId(String vacancyId) {this.vacancyId = vacancyId;}
    public void setVacancy(Vacancy vacancy) {this.vacancy = vacancy;}
    public void setApplicationDate(LocalDate applicationDate) {this.applicationDate = applicationDate;}
    public void setStatus(ApplicationStatus status) {this.status = status;}
}
