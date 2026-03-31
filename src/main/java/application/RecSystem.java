package application;

import vacancy.Vacancies;
import common.Vacancy;
import common.Profile;

public class RecSystem {
    public Vacancy[] getRecommendations(Profile profile, Vacancies vacancies) {
        Vacancies recommendations = new Vacancies();
        Vacancy[] vacs = vacancies.getVacancies();
        for (int i=0; i<vacs.length; i++) {
            Vacancy vacancy = vacs[i];
            if (vacancy.getSpecialty().equals(profile.getSpecialty()) &&
                    vacancy.getMinExperience()<=profile.getExperience()) {
                recommendations.add(vacancy);
            }
        }
        return recommendations.getVacancies();
    }
}
