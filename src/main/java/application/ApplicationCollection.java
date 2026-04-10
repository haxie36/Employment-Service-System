package application;

import base.EntityCollection;

//A collection of Applications
public class ApplicationCollection extends EntityCollection<Application> {
    public ApplicationCollection() {
        super(Application.class);
    }
    public ApplicationCollection(Application[] applications) {
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
