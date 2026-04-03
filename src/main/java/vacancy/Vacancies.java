package vacancy;

import common.EntityCollection;
import common.Vacancy;

//A collection of Vacancies
public class Vacancies extends EntityCollection<Vacancy> {
    public Vacancies() {
        super();
    }
    public Vacancies(Vacancy[] vacancies) {
        super(vacancies);
    }
}
