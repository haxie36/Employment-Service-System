package application;

import common.Application;
import retraining.Retrainings;
import vacancy.Vacancies;
import common.Vacancy;
import registration.Profiles;

public class ApplicationController {
    private Application application = null;
    private Application selectedApplication = null;
    private final Applications applications;
    private final Vacancies vacancies;
    private final Profiles profiles;
    private final Retrainings retrainings;
    private final RecSystem recSystem;

    public ApplicationController(Vacancies vacancies, Applications applications, Profiles profiles, Retrainings retrainings, RecSystem recSystem) {
        this.vacancies = vacancies;
        this.applications = applications;
        this.profiles = profiles;
        this.retrainings = retrainings;
        this.recSystem = recSystem;
    }

    //Create a new empty Application
    public void newApplication() {
        application = new Application();
    }

    //Check if profile is registered, if is, set as application's own
    public boolean isRegistered(String id){ //Find out if the profile exists, if it does set it as application's profile
        return application.findProfile(id, profiles);
    }

    //Get the recommendations for the application's profile
    public Vacancy[] getRecommendations(){ //Returns a list of vacancies
        return application.getRecommendations(recSystem, profiles, vacancies, retrainings);
    }

    //Set application's vacancy...
    public boolean setVacancy(Vacancy vacancy){
        application.setVacancy(vacancy);
        application.setVacancyId(vacancy.getId());
        return true;
    }

    //End the creation of the Application by saving it in the collection
    public boolean saveApplication(){
        if (application!=null){
            setApplicationId(); //temp
            applications.add(application);
            clear();
            return true;
        }
        return false;
    }
    //temp
    private void setApplicationId(){
        application.setId(String.valueOf(applications.getAll().length+1));
    }

    //All in one
    public boolean apply(AppInput input){
        newApplication();
        if (!isRegistered(input.profileId)) return false;
        if (!setVacancy(input.vacancy)) return false;

        return saveApplication();
    }

    //Select, edit and delete
    public Application selectApplication(String applicationId){
        return selectedApplication = applications.getById(applicationId);
    }

    public void changeApplicationStatus(int status){
        selectedApplication.setStatus(ApplicationStatus.fromId(status));
    }

    public boolean deleteApplication(){
        boolean result = applications.delete(selectedApplication.getId());
        clear();
        return result;
    }

    public void clear(){
        application = null;
        selectedApplication = null;
    }
}
