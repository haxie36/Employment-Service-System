package logic.application;

import logic.vacancy.Vacancy;

public class AppInput {
    private final String passportNumber;
    private final int vacancyId;

    public AppInput(String passportNumber, int vacancyId) {
        this.passportNumber = passportNumber;
        this.vacancyId = vacancyId;
    }

    public String getPassportNumber() {return passportNumber;}
    public int getVacancyId() {return vacancyId;}
}
