package logic.vacancy;

import logic.application.ApplicationDAO;
import logic.base.LogicController;
import logic.common.SpecialtyCatalog;

public class VacancyController extends LogicController<Vacancy, VacInput, VacancyDAO> {
    private final ApplicationDAO applicationDAO;
    private final SpecialtyCatalog specialtyCatalog;

    public VacancyController(VacancyDAO vacancyDAO, ApplicationDAO applicationDAO, SpecialtyCatalog specialtyCatalog) {
        super(vacancyDAO);
        this.applicationDAO = applicationDAO;
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
        if (input.getContact().length() < 3 || input.getContact().length() > 100)
            throw new IllegalArgumentException("Invalid Contact!");
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");
        if (input.getMinExperience() < 0 || input.getMinExperience() > 100)
            throw new IllegalArgumentException("Invalid Experience!");

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
    public void edit(Vacancy vacancy, VacInput input, int status) {
        if (input.getContact().length() < 3 || input.getContact().length() > 100)
            throw new IllegalArgumentException("Invalid Contact!");
        //Changing status throws exception on its own (if status is out of range)
        vacancy.changeStatus(status);
        vacancy.setContact(input.getContact());
        vacancy.setDescription(input.getDescription());
        DAO.update(vacancy);
        if (vacancy.getStatus() != VacancyStatus.OPEN)
            applicationDAO.retractApplicationsOfVacancy(vacancy.getId());
    }
}