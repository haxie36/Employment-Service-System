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
}
