package application;

import base.LogicController;
import registration.Profile;
import retraining.RetrainingCollection;
import vacancy.VacancyCollection;
import vacancy.Vacancy;
import registration.ProfileCollection;

public class ApplicationController extends LogicController<Application, AppInput> {
    private final VacancyCollection vacancyCollection;
    private final ProfileCollection profileCollection;
    private final RetrainingCollection retrainingCollection;
    private final RecSystem recSystem;

    public ApplicationController(VacancyCollection vacancyCollection, ApplicationCollection applicationCollection, ProfileCollection profileCollection, RetrainingCollection retrainingCollection, RecSystem recSystem) {
        super(applicationCollection);
        this.vacancyCollection = vacancyCollection;
        this.profileCollection = profileCollection;
        this.retrainingCollection = retrainingCollection;
        this.recSystem = recSystem;
    }

    //Create a new empty Application
    public void newCreation() {creation = new Application();}

    //Get the recommendations for the application's profile
    public Vacancy[] getRecommendations(Profile profile){
        return recSystem.getRecommendations(profile,
                vacancyCollection, retrainingCollection);
    }


    //All in one
    public void create(AppInput input) {
        Profile profile = profileCollection.getByPassport(input.getPassportNumber());
        if (profile == null) {
            throw new IllegalArgumentException("Profile not found!");
        }

        newCreation();
        if (hasActiveApplications(
                profile.getId(),
                input.getVacancy().getId())) {
            throw new IllegalArgumentException("Active application exists!");
        }
        creation.setProfile(profile);
        creation.setVacancy(input.getVacancy());
        if (!save()) throw new IllegalArgumentException("Save failed!");
    }

    //Edit
    public void changeApplicationStatus(Application app, int status) {
        app.setStatus(ApplicationStatus.fromId(status));
    }

    //Cloning check
    public boolean hasActiveApplications(String profileId, String vacancyId) {
        return ((ApplicationCollection)collection).hasActiveApplications(profileId, vacancyId);
    }
}
