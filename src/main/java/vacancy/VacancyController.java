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

    //All-in-one
    public void create(VacInput input) {
        if (input.getTitle().length() < 3 || input.getTitle().length() > 100)
            throw new IllegalArgumentException("Invalid Title!");
        if (input.getCompany().length() < 3 || input.getCompany().length() > 100)
            throw new IllegalArgumentException("Company Name must be 3 characters long or longer!");
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");
        if (input.getMinExperience() < 0 || input.getMinExperience() > 100)
            throw new IllegalArgumentException("Invalid Experience!");
        validateInfo(input);

        newCreation();
        creation.setTitle(input.getTitle());
        creation.setCompany(input.getCompany());
        creation.setContact(input.getContact());
        creation.setSpecialty(input.getSpecialty());
        creation.setMinExperience(input.getMinExperience());
        creation.setDescription(input.getDescription());
        if (!save()) throw new RuntimeException("Save failed!");
    }

    //Edit
    public void editVacancy(Vacancy vacancy, VacInput input, int status) {
        validateInfo(input);

        vacancy.changeStatus(status, applicationCollection);
        vacancy.setContact(input.getContact());
        vacancy.setDescription(input.getDescription());
    }

    private static void validateInfo(VacInput input) {
        if (input.getContact().length() < 3 || input.getContact().length() > 100)
            throw new IllegalArgumentException("Invalid Contact!");
        if (input.getDescription().length() < 3 || input.getDescription().length() > 2000)
            throw new IllegalArgumentException("Invalid Description!");
    }
}