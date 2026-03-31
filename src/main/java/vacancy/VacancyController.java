package vacancy;

import application.Applications;
import common.Office;
import common.SpecialtyCatalog;
import common.Vacancy;

public class VacancyController {
    private Vacancy vacancy = null;
    private Vacancies vacancies;
    private Applications applications;
    private SpecialtyCatalog specialtyCatalog;
    private Office office;

    public VacancyController(Vacancies vacancies, Applications applications, SpecialtyCatalog specialtyCatalog, Office office) {
        this.vacancies = vacancies;
        this.applications = applications;
        this.specialtyCatalog = specialtyCatalog;
        this.office = office;
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
            vacancy = null;
            return true;
        }
        return false;
    }
    //temp
    private void setVacancyId(){
        vacancy.setId(String.valueOf(vacancies.getVacancies().length+1));
    }

    public boolean createVacancy(VacancyInput input){
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
}
