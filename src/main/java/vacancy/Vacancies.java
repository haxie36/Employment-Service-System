package vacancy;

import application.Applications;
import common.Application;
import common.EntityCollection;
import common.Vacancy;

import java.util.Objects;
import java.util.stream.Stream;

//A collection of Vacancies
public class Vacancies extends EntityCollection<Vacancy> {
    private final Applications applications;

    public Vacancies(Applications applications) {
        this.applications = applications;
    }
    public Vacancies(Vacancy[] vacancies, Applications applications) {
        super(vacancies);
        this.applications = applications;
    }

    @Override
    public boolean delete(String id) {
        if(getById(id)==null) return false;
        //Delete all the vacancy's applications
        applications.setAll(
                Stream.of(applications.getAll())
                        .filter(a -> !Objects.equals(a.getVacancyId(), id))
                        .toArray(Application[]::new));

        return super.delete(id);
    }
}
