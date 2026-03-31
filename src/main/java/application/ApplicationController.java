package application;

import common.Application;
import vacancy.Vacancies;
import common.Vacancy;
import common.Office;
import registration.Profiles;

public class ApplicationController {
    private Application application = null;
    private Applications applications;
    private Vacancies vacancies;
    private Profiles profiles;
    private RecSystem recSystem;
    private Office office;

    public ApplicationController(Vacancies vacancies, Applications applications, Profiles profiles, RecSystem recSystem, Office office) {
        this.vacancies = vacancies;
        this.applications = applications;
        this.profiles = profiles;
        this.recSystem = recSystem;
        this.office = office;
    }

    public Application newApplication() {
        application = new Application();
        return application;
    }

    public boolean findProfile(String id){
        return application.findProfile(id, profiles);
    }

    public Vacancy[] getRecommendations(){
        return application.getRecommendations(recSystem, profiles, vacancies);
    }

    public boolean setVacancy(Vacancy vacancy){
        application.setVacancyId(vacancy.getId());
        return true;
    }
    public boolean setVacancy(String vacancyId){
        application.setVacancyId(vacancyId);
        return true;
    }

    public boolean saveApplication(){
        if (application!=null){
            setApplicationId(); //temp
            applications.add(application);
            application = null;
            return true;
        }
        return false;
    }
    //temp
    private void setApplicationId(){
        application.setId(String.valueOf(applications.getApplications().length+1));
    }

    public boolean apply(ApplicationInput input){
        newApplication();
        if (!findProfile(input.profileId)) return false;
        if (!setVacancy(input.vacancyId)) return false;

        return saveApplication();
    }
}
