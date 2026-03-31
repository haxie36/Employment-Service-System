package vacancy;

import common.Vacancy;

public class Vacancies {
    private Vacancy[] vacancies;

    public Vacancies() {
        vacancies = new Vacancy[0];
    }
    public Vacancies(Vacancy[] vacancies) {
        this.vacancies = vacancies;
    }

    public boolean add(Vacancy vacancy) {
        String id = vacancy.getId();
        boolean exists = false;

        for (int i=0; i<vacancies.length; i++) {
            if (id.equals(vacancies[i].getId())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            Vacancy[] newVacancies = new Vacancy[vacancies.length+1];

            for (int i=0; i<vacancies.length; i++) {
                newVacancies[i] = vacancies[i];
            }

            newVacancies[vacancies.length] = vacancy;
            vacancies=newVacancies;
        }
        return !exists;
    }

    public Vacancy getVacancy(String id) {
        for (int i=0; i<vacancies.length; i++) {
            Vacancy vacancy = vacancies[i];

            if (id.equals(vacancy.getId())) {
                return vacancy;
            }
        }
        return null;
    }

    public Vacancy[] getVacancies() { return vacancies; }
    public void clear(){ vacancies = new Vacancy[0]; }

    public boolean delete(Vacancy vacancy) {
        String id = vacancy.getId();
        return delete(id);
    }
    public boolean delete(String id){
        for (int i=0; i<vacancies.length; i++) {
            if (id.equals(vacancies[i].getId())) {
                Vacancy[] newVacancies = new Vacancy[vacancies.length-1];
                for (int j=0; j<vacancies.length; j++) {
                    if (j==i) continue;
                    newVacancies[j] = vacancies[i];
                }
                vacancies=newVacancies;
                return true;
            }
        }
        return false;
    }
}
