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

    //Check if profile is registered, if is, set as application's own
    public boolean isRegistered(String passportNumber){ //Find out if the profile exists, if it does set it as application's profile
        return creation.isRegistered(passportNumber, profileCollection);
    }

    //Get the recommendations for the application's profile
    public Vacancy[] getRecommendations(Profile profile){
        return recSystem.getRecommendations(profile,
                vacancyCollection, retrainingCollection);
    }

    //Set application's vacancy...
    public void setVacancy(Vacancy vacancy){
        creation.setVacancy(vacancy);
        creation.setVacancyId(vacancy.getId());
    }

    //All in one
    public boolean create(AppInput input) throws Exception {
        newCreation();
        if (!isRegistered(input.getPassportNumber())) throw new Exception("Profile not found!");
        if (hasActiveApplications(
                creation.getProfileId(),
                input.getVacancy().getId()
                )) throw new Exception("Active application exists!");
        setVacancy(input.getVacancy());
        return save();
    }

    //Edit
    public void changeApplicationStatus(Application app, int status){
        app.setStatus(ApplicationStatus.fromId(status));
    }

    //Cloning check
    public boolean hasActiveApplications(String profileId, String vacancyId) {
        return creation.hasActiveApplications(profileId, vacancyId, (ApplicationCollection) collection);
    }
}
