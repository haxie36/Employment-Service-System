package vacancy;

import application.Applications;
import common.LogicController;
import common.SpecialtyCatalog;
import common.Vacancy;

public class VacancyController extends LogicController<Vacancy, VacInput> {
    private final Applications applications;
    private final SpecialtyCatalog specialtyCatalog;

    public VacancyController(Vacancies vacancies, Applications applications, SpecialtyCatalog specialtyCatalog) {
        super(vacancies);
        this.applications = applications;
        this.specialtyCatalog = specialtyCatalog;
    }

    @Override
    //Create a new empty Vacancy
    public void newCreation(){
        creation = new Vacancy();
    }

    //Set vacancy's company name...
    public boolean setVacancyCompany(String company){
        return creation.setCompany(company);
    }
    //Set vacancy's company contact number...
    public boolean setVacancyContact(String contact){
        creation.setContact(contact);
        return true;
    }

    //Check for specialty being real, if is, set as vacancy's own
    public boolean isRealSpecialty(String specialty){
        return creation.isRealSpecialty(specialty, specialtyCatalog);
    }

    //Check for experience being a valid number, if is, set as vacancy's own
    public boolean setVacancyExperience(int experience){
        return creation.setMinExperience(experience);
    }

    //Check if description is 3 or more signs long, if is, set as vacancy's own
    public boolean setVacancyDescription(String description){
        return creation.setDescription(description);
    }

    //End the creation of the Vacancy by saving it in the collection
    public boolean save(){
        if (creation !=null){
            setCreationId(); //temp
            collection.add(creation);
            clear();
            return true;
        }
        return false;
    }
    //temp
    private void setCreationId(){
        creation.setId(String.valueOf(collection.getAll().length+1));
    }

    //All in one
    public boolean create(VacInput input){
        newCreation();
        if (!setVacancyCompany(input.getCompany())) return false;
        if (!setVacancyContact(input.getContact())) return false;
        if (!isRealSpecialty(input.getSpecialty())) return false;
        if (!setVacancyExperience(input.getMinExperience())) return false;
        if (!setVacancyDescription(input.getDescription())) return false;

        return save();
    }

    //Edit
    public boolean changeVacancyStatus(Vacancy vac, int status) {
        return vac.changeStatus(status, applications);
    }
}