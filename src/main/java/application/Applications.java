package application;

import common.Application;

public class Applications { //A collection of Applications
    private Application[] applications;

    public Applications() {
        applications = new Application[0];
    }
    public Applications(Application[] applications) {
        this.applications = applications;
    }

    public boolean add(Application application) {
        String id = application.getId();
        boolean exists = false;

        for (int i=0; i<applications.length; i++) {
            if (id.equals(applications[i].getId())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            Application[] newApplications = new Application[applications.length+1];

            for (int i=0; i<applications.length; i++) {
                newApplications[i] = applications[i];
            }

            newApplications[applications.length] = application;
            applications=newApplications;
        }
        return !exists;
    }

    public Application getApplication(String id) {
        for (int i=0; i<applications.length; i++) {
            Application application = applications[i];

            if (id.equals(application.getId())) {
                return application;
            }
        }
        return null;
    }

    public Application[] getApplications() { return applications; }
    public void clear(){ applications = new Application[0]; }

    public boolean delete(Application application) {
        String id = application.getId();
        return delete(id);
    }
    public boolean delete(String id){
        for (int i=0; i<applications.length; i++) {
            if (id.equals(applications[i].getId())) {
                Application[] newApplications = new Application[applications.length-1];
                for (int j=0; j<applications.length; j++) {
                    if (j==i) continue;
                    newApplications[j] = applications[i];
                }
                applications=newApplications;
                return true;
            }
        }
        return false;
    }
}
