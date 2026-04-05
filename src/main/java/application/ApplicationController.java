package application;

import common.Application;
import common.LogicController;
import retraining.Retrainings;
import vacancy.Vacancies;
import common.Vacancy;
import registration.Profiles;

public class ApplicationController extends LogicController<Application, AppInput> {
    private final Vacancies vacancies;
    private final Profiles profiles;
    private final Retrainings retrainings;
    private final RecSystem recSystem;

    public ApplicationController(Vacancies vacancies, Applications applications, Profiles profiles, Retrainings retrainings, RecSystem recSystem) {
        super(applications);
        this.vacancies = vacancies;
        this.profiles = profiles;
        this.retrainings = retrainings;
        this.recSystem = recSystem;
    }

    //Create a new empty Application
    public void newCreation() {creation = new Application();}

    //Check if profile is registered, if is, set as application's own
    public boolean isRegistered(String passportNumber){ //Find out if the profile exists, if it does set it as application's profile
        return creation.isRegistered(passportNumber, profiles);
    }

    //Get the recommendations for the application's profile
    public Vacancy[] getRecommendations(){ //Returns a list of vacancies
        return creation.getRecommendations(recSystem, profiles,
                vacancies, retrainings);
    }

    //Set application's vacancy...
    public boolean setVacancy(Vacancy vacancy){
        creation.setVacancy(vacancy);
        creation.setVacancyId(vacancy.getId());
        return true;
    }

    //All in one
    public boolean create(AppInput input){
        newCreation();
        if (!isRegistered(input.getProfileId())) return false;
        if (!setVacancy(input.getVacancy())) return false;

        return save();
    }

    //Edit
    public void changeApplicationStatus(Application app, int status){
        app.setStatus(ApplicationStatus.fromId(status));
    }
}
