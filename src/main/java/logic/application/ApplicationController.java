package logic.application;

import logic.base.LogicController;
import logic.profile.Profile;
import logic.profile.ProfileDAO;
import logic.retraining.RetrainingDAO;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyDAO;
import logic.vacancy.VacancyStatus;

public class ApplicationController extends LogicController<Application, AppInput, ApplicationDAO> {
    private final ProfileDAO profileDAO;
    private final VacancyDAO vacancyDAO;
    private final RetrainingDAO retrainingDAO;
    private final RecSystem recSystem;

    public ApplicationController(ApplicationDAO applicationDAO, ProfileDAO profileDAO,
                                 VacancyDAO vacancyDAO, RetrainingDAO retrainingDAO,
                                 RecSystem recSystem) {
        super(applicationDAO);
        this.profileDAO = profileDAO;
        this.vacancyDAO = vacancyDAO;
        this.retrainingDAO = retrainingDAO;
        this.recSystem = recSystem;
    }

    //Create a new empty Application
    public void newCreation() {creation = new Application();}

    //Get the recommendations for the application's profile
    public Vacancy[] getRecommendations(Profile profile){
        return recSystem.getRecommendations(profile, vacancyDAO.getAll(), retrainingDAO.getAll());
    }


    //All in one
    public void create(AppInput input) {
        Profile profile = profileDAO.getByPassport(input.getPassportNumber());
        if (profile == null) {
            throw new IllegalArgumentException("Profile not found!");
        }
        Vacancy vacancy = vacancyDAO.getById(input.getVacancyId());
        if (vacancy == null || vacancy.getStatus() != VacancyStatus.OPEN) {
            throw new IllegalArgumentException("Vacancy is not open!");
        }

        newCreation();
        if (hasActiveApplications(
                profile.getId(),
                input.getVacancyId())) {
            throw new IllegalArgumentException("Active Application exists!");
        }
        creation.setProfileId(profile.getId());
        creation.setVacancyId(input.getVacancyId());
        if (!save()) throw new IllegalArgumentException("Save failed!");
    }

    //Edit
    public void changeApplicationStatus(Application application, int status) {
        //Changing status throws exception on its own (if status is out of range)
        application.setStatus(ApplicationStatus.fromId(status));
        DAO.update(application);
    }

    //Cloning check
    public boolean hasActiveApplications(int profileId, int vacancyId) {
        return (DAO.hasActiveApplications(profileId, vacancyId));
    }

    public Profile getByPassport(String passportNumber) {
        return profileDAO.getByPassport(passportNumber);
    }
}
