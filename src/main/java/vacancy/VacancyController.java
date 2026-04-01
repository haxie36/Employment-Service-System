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

    public Vacancy newVacancy(){
        vacancy = new Vacancy();
        return vacancy;
    }

    public boolean setVacancyCompany(String company){
        return vacancy.setCompany(company);
    }

    public boolean setVacancyContact(String contact){
        vacancy.setContact(contact);
        return true;
    }

    public boolean isRealSpecialty(String specialty){
        return vacancy.isRealSpecialty(specialty, specialtyCatalog);
    }

    public boolean setVacancyExperience(int experience){
        return vacancy.setMinExperience(experience);
    }

    public boolean setVacancyDescription(String description){
        return vacancy.setDescription(description);
    }

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
        vacancy.setId(String.valueOf(vacancies.getVacancies().length+1));
    }

    public boolean createVacancy(VacInput input){
        newVacancy();
        if (!setVacancyCompany(input.company)) return false;
        if (!setVacancyContact(input.contact)) return false;
        if (!isRealSpecialty(input.specialty)) return false;
        if (!setVacancyExperience(input.minExperience)) return false;
        if (!setVacancyDescription(input.description)) return false;

        return saveVacancy();
    }

    public boolean changeVacancyStatus(String id, int status){
        return vacancies.getVacancy(id).changeStatus(status, applications);
    }

    public void clear(){vacancy = null;}
}
