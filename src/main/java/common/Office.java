package common;

import application.ApplicationController;
import application.Applications;
import application.RecSystem;
import gui.application.ApplicationUIController;
import gui.main.MainUIController;
import gui.main.MainWindow;
import gui.profile.ProfileUIController;
import gui.retraining.RetrainingUIController;
import gui.vacancy.VacancyUIController;
import registration.Profiles;
import registration.RegistrationController;
import registration.ServiceArea;
import retraining.RetrainingController;
import retraining.Retrainings;
import vacancy.Vacancies;
import vacancy.VacancyController;

public class Office {
    public Office() {
        //Collections
        Profiles profiles = new Profiles();
        Applications applications = new Applications();
        Vacancies vacancies = new Vacancies(applications);
        Retrainings retrainings = new Retrainings();

        //Validators and stuff
        ServiceArea serviceArea = new ServiceArea();
        SpecialtyCatalog specialtyCatalog = new SpecialtyCatalog();
        RecSystem recSystem = new RecSystem();

        //Controllers
        RegistrationController registrationController = new RegistrationController(serviceArea, specialtyCatalog, profiles, this);
        ApplicationController applicationController = new ApplicationController(vacancies, applications, profiles, retrainings, recSystem);
        VacancyController vacancyController = new VacancyController(vacancies, applications, specialtyCatalog);
        RetrainingController retrainingController = new RetrainingController(profiles, specialtyCatalog, retrainings);

        //GUI
        MainWindow mainWindow = new MainWindow();

        //GUI Controllers
        ProfileUIController  profileUIController = new ProfileUIController(mainWindow, registrationController, profiles);
        ApplicationUIController applicationUIController = new ApplicationUIController(mainWindow, applicationController,applications);
        VacancyUIController vacancyUIController = new VacancyUIController(mainWindow, vacancyController,vacancies);
        RetrainingUIController retrainingUIController = new RetrainingUIController(mainWindow, retrainingController,retrainings);
        MainUIController mainUIController = new MainUIController(mainWindow,
                profileUIController, applicationUIController,
                vacancyUIController, retrainingUIController);
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
