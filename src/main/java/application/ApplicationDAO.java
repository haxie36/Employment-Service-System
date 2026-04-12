package application;

import base.EntityDAO;

//A collection of Applications
public class ApplicationDAO extends EntityDAO<Application> {
    public ApplicationDAO() {
        super(Application.class);
    }
    public ApplicationDAO(Application[] applications) {
        super(applications, Application.class);
    }

    public boolean hasActiveApplications(String profileId, String vacancyId) {
        for (Application item : items) {
            if (
                    item.getProfileId().equals(profileId)
                    && item.getVacancyId().equals(vacancyId)
                    && item.getStatus().equals(ApplicationStatus.ACTIVE)
            ) return true;
        }
        return false;
    }
}
