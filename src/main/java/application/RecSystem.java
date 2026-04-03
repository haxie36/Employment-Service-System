package application;

import retraining.Retraining;
import retraining.RetrainingStatus;
import retraining.Retrainings;
import vacancy.Vacancies;
import common.Vacancy;
import common.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

//Recommendations...
public class RecSystem {
    public Vacancy[] getRecommendations(Profile profile, Vacancies vacancies, Retrainings retrainings) {
        Vacancy[] vacs = vacancies.getAll(); //Real vacancies

        String[] skillSet = getFullSkillSet(profile, retrainings);

        List<Vacancy> recommendations = new ArrayList<>(); //A collection of recommendations
        for (int i=0; i<vacs.length; i++) {
            Vacancy vacancy = vacs[i];
            //If profile has less experience than required, skip
            if(vacancy.getMinExperience() > profile.getExperience()) {continue;}

            String vacancySpecialty = vacancy.getSpecialty();
            //Check for a vacancy's specialty being in the skill set of the profile
            for (int j=0;j<skillSet.length;j++) {
                //If the profile meets requirements
                if (vacancySpecialty.equals(skillSet[i])) {
                    recommendations.add(vacancy); //Add a vacancy to recommended
                    break; //And continue
                }
            }
        }
        return recommendations.toArray(new Vacancy[0]);
    }

    private String[] getFullSkillSet(Profile profile, Retrainings retrainings) {
        List<String> specialties = new ArrayList<>(); //A collection of profile's specialties
        specialties.add(profile.getSpecialty()); //Add the primary specialty

        Stream.of(retrainings.getAll()) //Convert retrainings array into a stream
                .filter(r -> r.getProfileId().equals(profile.getId())) //Filter by the profile id
                .filter(r -> r.getStatus() == RetrainingStatus.COMPLETED) //Filter by COMPLETED
                .forEach(r->specialties.add(r.getSpecialty())); //And add all the specialties to the list

        return specialties.toArray(new String[0]);
    }
}
