package application;

import vacancy.Vacancy;

public class AppInput {
    private final String profileId;
    private final Vacancy vacancy;

    public AppInput(String profileId, Vacancy vacancy) {
        this.profileId = profileId;
        this.vacancy = vacancy;
    }

    public String getProfileId() {return profileId;}
    public Vacancy getVacancy() {return vacancy;}
}
