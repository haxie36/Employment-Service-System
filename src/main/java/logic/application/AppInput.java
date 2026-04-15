package logic.application;

import logic.vacancy.Vacancy;

public class AppInput {
    private final String passportNumber;
    private final Vacancy vacancy;

    public AppInput(String passportNumber, Vacancy vacancy) {
        this.passportNumber = passportNumber;
        this.vacancy = vacancy;
    }

    public String getPassportNumber() {return passportNumber;}
    public Vacancy getVacancy() {return vacancy;}
}
