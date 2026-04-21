package application;

import logic.application.Application;
import logic.application.ApplicationDAO;
import logic.application.ApplicationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestApplicationDAO extends ApplicationDAO {
    private ArrayList<Application> applications;

    public TestApplicationDAO() {
        applications = new ArrayList<>(List.of(new Application[]{
                new Application(1),
                new Application(2)
        }));
    }

    @Override
    public boolean hasActiveApplications(int profileId, int vacancyId) {
        for (Application application : applications) {
            if (application.getProfileId() == profileId && application.getVacancyId() == vacancyId) {
                return true;
            }
        } return false;
    }

    @Override
    public boolean retractApplicationsOfVacancy(int vacancyId) {
        boolean result = false;
        for (Application application : applications) {
            if (application.getVacancyId() == vacancyId) {
                application.setStatus(ApplicationStatus.RETRACTED);
                result = true;
            }
        } return result;
    }

    @Override
    protected Application map(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Application item) {
        item.setId(applications.size()+1);
        return applications.add(item);
    }

    @Override
    public Application getById(int id) {
        for (Application application : applications) {
            if (application.getId() == id)
                return application;
        } return null;
    }

    @Override
    public boolean update(Application item) {
        for (Application application : applications) {
            if (application.getId() == item.getId()) {
                application.setStatus(item.getStatus());
                return true;
            }
        } return false;
    }

    @Override
    public boolean delete(int id) {
        return applications.removeIf(a -> a.getId() == id);
    }

    @Override
    public Application[] getAll() {
        return applications.toArray(new Application[0]);
    }
}
