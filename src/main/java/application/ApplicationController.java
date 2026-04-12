package application;

import base.LogicController;
import registration.Profile;
import retraining.RetrainingDAO;
import vacancy.VacancyDAO;
import vacancy.Vacancy;
import registration.ProfileDAO;

public class ApplicationController extends LogicController<Application, AppInput, ApplicationDAO> {
    private final VacancyDAO vacancyDAO;
    private final ProfileDAO profileDAO;
    private final RetrainingDAO retrainingDAO;
    private final RecSystem recSystem;

    public ApplicationController(VacancyDAO vacancyDAO, ApplicationDAO applicationDAO, ProfileDAO profileDAO, RetrainingDAO retrainingDAO, RecSystem recSystem) {
        super(applicationDAO);
        this.vacancyDAO = vacancyDAO;
        this.profileDAO = profileDAO;
        this.retrainingDAO = retrainingDAO;
        this.recSystem = recSystem;
    }

    //Create a new empty Application
    public void newCreation() {creation = new Application();}

    //Get the recommendations for the application's profile
    public Vacancy[] getRecommendations(Profile profile){
        return recSystem.getRecommendations(profile,
                vacancyDAO, retrainingDAO);
    }


    //All in one
    public void create(AppInput input) {
        Profile profile = profileDAO.getByPassport(input.getPassportNumber());
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
        //Changing status throws exception on its own (if status is out of range)
        app.setStatus(ApplicationStatus.fromId(status));
    }

    //Cloning check
    public boolean hasActiveApplications(String profileId, String vacancyId) {
        return (DAO.hasActiveApplications(profileId, vacancyId));
    }

    public Profile getByPassport(String passportNumber) {
        return profileDAO.getByPassport(passportNumber);
    }
}
