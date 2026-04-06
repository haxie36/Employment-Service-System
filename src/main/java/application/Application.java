package application;

import base.HasId;
import registration.Profile;
import vacancy.Vacancy;
import retraining.RetrainingCollection;
import vacancy.VacancyCollection;
import registration.ProfileCollection;

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

    //Checks if the profile is registered, if is, set its as own
    public boolean isRegistered(String passportNumber, ProfileCollection profileCollection) {
        Profile profile = profileCollection.getByPassport(passportNumber);
        if(profile == null) return false;
        this.profile = profile;
        this.profileId = profile.getId();
        return true;
    }

    //Gets the recommendations for the profile
    public Vacancy[] getRecommendations(RecSystem recSystem, ProfileCollection profileCollection, VacancyCollection vacancyCollection, RetrainingCollection retrainingCollection) {
        return recSystem.getRecommendations(profile, vacancyCollection, retrainingCollection);
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

    public static String getClassName() {return "Application";}

    public String toString(){
        return "("+id+") "+profile.getName()+"--"+vacancy.getTitle()
                +" ["+status.getName()+"]";
    }
}
