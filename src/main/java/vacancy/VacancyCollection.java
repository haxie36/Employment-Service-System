package vacancy;

import application.ApplicationCollection;
import application.Application;
import base.EntityCollection;

import java.util.Objects;
import java.util.stream.Stream;

//A collection of Vacancies
public class VacancyCollection extends EntityCollection<Vacancy> {
    private final ApplicationCollection applicationCollection;

    public VacancyCollection(ApplicationCollection applicationCollection) {
        this.applicationCollection = applicationCollection;
    }
    public VacancyCollection(Vacancy[] vacancies, ApplicationCollection applicationCollection) {
        super(vacancies);
        this.applicationCollection = applicationCollection;
    }

    @Override
    public boolean delete(String id) {
        if(getById(id)==null) return false;
        //Delete all the vacancy's applications
        applicationCollection.setAll(
                Stream.of(applicationCollection.getAll())
                        .filter(a -> !Objects.equals(a.getVacancyId(), id))
                        .toArray(Application[]::new));

        return super.delete(id);
    }
}
