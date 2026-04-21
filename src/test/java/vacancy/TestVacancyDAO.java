package vacancy;

import logic.vacancy.Vacancy;
import logic.vacancy.VacancyDAO;
import logic.vacancy.VacancyStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestVacancyDAO  extends VacancyDAO {
    private ArrayList<Vacancy> vacancies;

    public TestVacancyDAO() {
        Vacancy closed = new Vacancy(3);
        closed.setStatus(VacancyStatus.CLOSED);
        vacancies = new ArrayList(List.of(new Vacancy[]{
                        new Vacancy(1),
                        new Vacancy(2),
                        closed}
        ));
    }

    @Override
    protected Vacancy map(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Vacancy item) {
        item.setId(vacancies.size()+1);
        return vacancies.add(item);
    }

    @Override
    public Vacancy getById(int id) {
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getId() == id) {
                return vacancy;
            }
        } return null;
    }

    @Override
    public boolean update(Vacancy item) {
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getId() == item.getId()) {
                vacancy.setStatus(item.getStatus());
                vacancy.setContact(item.getContact());
                vacancy.setDescription(item.getDescription());
                return true;
            }
        } return false;
    }

    @Override
    public boolean delete(int id) {
        return vacancies.removeIf(vacancy -> vacancy.getId() == id);
    }

    @Override
    public Vacancy[] getAll() {
        return vacancies.toArray(new Vacancy[0]);
    }

    @Override
    protected PreparedStatement setupUpdate(Vacancy item, String updateSQL) throws SQLException {
        return null;
    }
}
