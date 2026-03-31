package application;

import common.Application;

public class Applications {
    private Application[] vacancies;

    public Applications() {
        vacancies = new Application[0];
    }
    public Applications(Application[] vacancies) {
        this.vacancies = vacancies;
    }

    public boolean add(Application application) {
        String id = application.getId();
        boolean exists = false;

        for (int i=0; i<vacancies.length; i++) {
            if (id.equals(vacancies[i].getId())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            Application[] newApplications = new Application[vacancies.length+1];

            for (int i=0; i<vacancies.length; i++) {
                newApplications[i] = vacancies[i];
            }

            newApplications[vacancies.length] = application;
            vacancies=newApplications;
        }
        return !exists;
    }

    public Application getApplication(String id) {
        for (int i=0; i<vacancies.length; i++) {
            Application application = vacancies[i];

            if (id.equals(application.getId())) {
                return application;
            }
        }
        return null;
    }

    public Application[] getApplications() { return vacancies; }
    public void clear(){ vacancies = new Application[0]; }

    public boolean delete(Application application) {
        String id = application.getId();
        return delete(id);
    }
    public boolean delete(String id){
        for (int i=0; i<vacancies.length; i++) {
            if (id.equals(vacancies[i].getId())) {
                Application[] newApplications = new Application[vacancies.length-1];
                for (int j=0; j<vacancies.length; j++) {
                    if (j==i) continue;
                    newApplications[j] = vacancies[i];
                }
                vacancies=newApplications;
                return true;
            }
        }
        return false;
    }
}
