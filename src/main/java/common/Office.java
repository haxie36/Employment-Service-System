package common;

import application.ApplicationController;
import application.Applications;
import application.RecSystem;
import registration.Profiles;
import registration.RegistrationController;
import registration.ServiceArea;
import retraining.RetrainingController;
import retraining.Retrainings;
import vacancy.Vacancies;
import vacancy.VacancyController;

public class Office {
    private final Profiles profiles;
    private final ServiceArea serviceArea;
    private final SpecialtyCatalog specialtyCatalog;
    private final RegistrationController registrationController;

    private final Applications applications;
    private final RecSystem recSystem;
    private final ApplicationController applicationController;

    private final Vacancies vacancies;
    private final VacancyController vacancyController;

    private final Retrainings retrainings;
    private final RetrainingController retrainingController;

    public Office() {
        //Collections
        profiles = new Profiles();
        vacancies = new Vacancies();
        applications = new Applications();
        retrainings = new Retrainings();

        //Validators and stuff
        serviceArea = new ServiceArea();
        specialtyCatalog = new SpecialtyCatalog();
        recSystem = new RecSystem();

        //Controllers
        registrationController = new RegistrationController(serviceArea, specialtyCatalog, profiles, this);
        applicationController = new ApplicationController(vacancies, applications, profiles, retrainings, recSystem);
        vacancyController = new VacancyController(vacancies, applications, specialtyCatalog);
        retrainingController = new RetrainingController(profiles, specialtyCatalog, retrainings);
    }

    //Prints certification (formates profile's info)
    public String printCertification(Profile profile) {
        System.out.println("Printing Certification...");
        System.out.println("Imagine it's printed!");
        return "";
    }

    public static void main(String[] args) {
        Office office = new Office();
        System.out.println("yes");
    }
}
