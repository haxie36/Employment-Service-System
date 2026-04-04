package vacancy;

import application.Applications;
import common.SpecialtyCatalog;
import common.Vacancy;

public class VacancyController {
    private Vacancy vacancy = null;
    private final Vacancies vacancies;
    private final Applications applications;
    private final SpecialtyCatalog specialtyCatalog;

    public VacancyController(Vacancies vacancies, Applications applications, SpecialtyCatalog specialtyCatalog) {
        this.vacancies = vacancies;
        this.applications = applications;
        this.specialtyCatalog = specialtyCatalog;
    }

    //Create a new empty Vacancy
    public void newVacancy(){
        vacancy = new Vacancy();
    }

    //Set vacancy's company name...
    public boolean setVacancyCompany(String company){
        return vacancy.setCompany(company);
    }
    //Set vacancy's company contact number...
    public boolean setVacancyContact(String contact){
        vacancy.setContact(contact);
        return true;
    }

    //Check for specialty being real, if is, set as vacancy's own
    public boolean isRealSpecialty(String specialty){
        return vacancy.isRealSpecialty(specialty, specialtyCatalog);
    }

    //Check for experience being a valid number, if is, set as vacancy's own
    public boolean setVacancyExperience(int experience){
        return vacancy.setMinExperience(experience);
    }

    //Check if description is 3 or more signs long, if is, set as vacancy's own
    public boolean setVacancyDescription(String description){
        return vacancy.setDescription(description);
    }

    //End the creation of the Vacancy by saving it in the collection
    public boolean saveVacancy(){
        if (vacancy!=null){
            setVacancyId(); //temp
            vacancies.add(vacancy);
            clear();
            return true;
        }
        return false;
    }
    //temp
    private void setVacancyId(){
        vacancy.setId(String.valueOf(vacancies.getAll().length+1));
    }

    //All in one
    public boolean createVacancy(VacInput input){
        newVacancy();
        if (!setVacancyCompany(input.company)) return false;
        if (!setVacancyContact(input.contact)) return false;
        if (!isRealSpecialty(input.specialty)) return false;
        if (!setVacancyExperience(input.minExperience)) return false;
        if (!setVacancyDescription(input.description)) return false;

        return saveVacancy();
    }

    //Edit and delete
    public boolean changeVacancyStatus(Vacancy vac, int status) {
        return vac.changeStatus(status, applications);
    }

    public boolean deleteVacancy(Vacancy vac){
        return vacancies.delete(vac.getId());
    }

    public void clear(){
        vacancy = null;
    }
}
