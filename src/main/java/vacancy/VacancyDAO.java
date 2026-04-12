package vacancy;

import application.ApplicationDAO;
import application.Application;
import base.EntityDAO;

import java.util.Objects;
import java.util.stream.Stream;

//A collection of Vacancies
public class VacancyDAO extends EntityDAO<Vacancy> {
    private final ApplicationDAO applicationDAO;

    public VacancyDAO(ApplicationDAO applications) {
        super(Vacancy.class);
        this.applicationDAO = applications;
    }
    public VacancyDAO(Vacancy[] vacancies, ApplicationDAO applications) {
        super(vacancies, Vacancy.class);
        this.applicationDAO = applications;
    }

    @Override
    public boolean delete(String id) {
        if(getById(id)==null) return false;
        //Delete all the vacancy's applications
        applicationDAO.setAll(
                Stream.of(applicationDAO.getAll())
                        .filter(a -> !Objects.equals(a.getVacancyId(), id))
                        .toArray(Application[]::new));

        return super.delete(id);
    }
}
