package application;

import common.Application;
import vacancy.Vacancies;
import common.Vacancy;
import registration.Profiles;

public class ApplicationController {
    private Application application = null;
    private final Applications applications;
    private final Vacancies vacancies;
    private final Profiles profiles;
    private final RecSystem recSystem;

    public ApplicationController(Vacancies vacancies, Applications applications, Profiles profiles, RecSystem recSystem) {
        this.vacancies = vacancies;
        this.applications = applications;
        this.profiles = profiles;
        this.recSystem = recSystem;
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
        application.setVacancy(vacancy);
        application.setVacancyId(vacancy.getId());
        return true;
    }
    public boolean setVacancy(String vacancyId){
        application.setVacancy(vacancies.getVacancy(vacancyId));
        application.setVacancyId(vacancyId);
        return true;
    }

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
        application.setId(String.valueOf(applications.getApplications().length+1));
    }

    public boolean apply(AppInput input){
        newApplication();
        if (!findProfile(input.profileId)) return false;
        if (!setVacancy(input.vacancyId)) return false;

        return saveApplication();
    }

    public void clear(){application = null;}
}
