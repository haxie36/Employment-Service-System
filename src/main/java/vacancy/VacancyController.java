package vacancy;

import application.ApplicationCollection;
import base.LogicController;
import common.SpecialtyCatalog;

public class VacancyController extends LogicController<Vacancy, VacInput> {
    private final ApplicationCollection applicationCollection;
    private final SpecialtyCatalog specialtyCatalog;

    public VacancyController(VacancyCollection vacancyCollection, ApplicationCollection applicationCollection, SpecialtyCatalog specialtyCatalog) {
        super(vacancyCollection);
        this.applicationCollection = applicationCollection;
        this.specialtyCatalog = specialtyCatalog;
    }

    @Override
    //Create a new empty Vacancy
    public void newCreation(){
        creation = new Vacancy();
    }

    //Set vacancy's title...
    public boolean setVacancyTitle(String title){return creation.setTitle(title);}
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
        if (!setVacancyTitle(input.getTitle()))
            throw new IllegalArgumentException("Invalid Title!");
        if (!setVacancyCompany(input.getCompany()))
            throw new IllegalArgumentException("Company Name must be 3 characters long or longer!");
        if (!isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");
        if (!setVacancyExperience(input.getMinExperience()))
            throw new IllegalArgumentException("Invalid Experience!");
        if (!setVacancyDescription(input.getDescription()))
            throw new IllegalArgumentException("Invalid Description!");
        if (!setVacancyContact(input.getContact()))
            throw new IllegalArgumentException("Invalid Contact!");

        return save();
    }

    //Edit
    public void changeVacancyStatus(Vacancy vac, int status) {
        if(!vac.changeStatus(status, applicationCollection))
            throw new IllegalArgumentException("Invalid Status!");
    }
}