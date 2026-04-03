package application;

import common.Application;
import common.EntityCollection;

//A collection of Applications
public class Applications extends EntityCollection<Application> {
    public Applications() {
        super();
    }
    public Applications(Application[] applications) {
        super(applications);
    }
}
