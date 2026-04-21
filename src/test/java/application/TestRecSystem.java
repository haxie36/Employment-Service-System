package application;

import logic.application.RecSystem;
import logic.profile.Profile;
import logic.retraining.Retraining;
import logic.vacancy.Vacancy;

public class TestRecSystem extends RecSystem {
    @Override
    public Vacancy[] getRecommendations(Profile profile, Vacancy[] vacs, Retraining[] retrs) {
        return profile.getId() < 4 ? vacs : new Vacancy[0];
    }
}
