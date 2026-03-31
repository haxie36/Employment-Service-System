package common;

import application.ApplicationController;
import application.Applications;
import application.RecSystem;
import registration.Profiles;
import registration.RegistrationController;
import registration.ServiceArea;
import vacancy.Vacancies;
import vacancy.VacancyController;

public class Office {
    private Profiles profiles;
    private ServiceArea serviceArea;
    private SpecialtyCatalog specialtyCatalog;
    private RegistrationController registrationController;

    private Applications applications;
    private RecSystem recSystem;
    private ApplicationController applicationController;

    private Vacancies vacancies;
    private VacancyController vacancyController;

    public Office() {
        profiles = new Profiles();
        serviceArea = new ServiceArea(new String[]{"1"});
        specialtyCatalog = new SpecialtyCatalog(new String[]{"1"});
        registrationController = new RegistrationController(serviceArea, specialtyCatalog, profiles, this);

        vacancies = new Vacancies();
        applications = new Applications();
        recSystem = new RecSystem();
        applicationController = new ApplicationController(vacancies, applications, profiles, recSystem, this);

        vacancyController = new VacancyController(vacancies, applications, specialtyCatalog, this);
    }

    public void printCertification(Profile profile) {
        System.out.println("Printing Certification...");
        System.out.println("Imagine it's printed!");
    }

    public static void main(String[] args) {
        Office office = new Office();
        System.out.println("yes");
    }
}
