package logic.application;

import logic.profile.Profile;
import logic.retraining.Retraining;
import logic.retraining.RetrainingStatus;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//Recommendations...
public class RecSystem {
    public Vacancy[] getRecommendations(Profile profile, Vacancy[] vacs, Retraining[] retrs) {
        String[] skillSet = getFullSkillSet(profile, retrs);

        List<Vacancy> recommendations = new ArrayList<>(); //A collection of recommendations
        for (Vacancy vacancy : vacs) {
            //If vacancy isn't active, skip
            if (vacancy.getStatus() != VacancyStatus.OPEN) continue;
            //If profile has less experience than required, skip
            if (vacancy.getMinExperience() > profile.getExperience()) {
                continue;
            }

            String vacancySpecialty = vacancy.getSpecialty();
            //Check for a vacancy's specialty being in the skill set of the profile
            for (String skill : skillSet) {
                //If the profile meets requirements
                if (vacancySpecialty.equals(skill)) {
                    recommendations.add(vacancy); //Add a vacancy to recommended
                    break; //And continue
                }
            }
        }
        return recommendations.toArray(new Vacancy[0]);
    }

    private String[] getFullSkillSet(Profile profile, Retraining[] retrs) {
        List<String> specialties = new ArrayList<>(); //A collection of profile's specialties
        specialties.add(profile.getSpecialty()); //Add the primary specialty

        Stream.of(retrs) //Convert retrainings array into a stream
                .filter(r -> r.getProfileId() == (profile.getId())) //Filter by the profile id
                .filter(r -> r.getStatus() == RetrainingStatus.COMPLETED) //Filter by COMPLETED
                .forEach(r->specialties.add(r.getSpecialty())); //And add all the specialties to the list

        return specialties.toArray(new String[0]);
    }
}
