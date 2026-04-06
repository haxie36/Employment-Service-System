package common;

import application.ApplicationController;
import application.ApplicationCollection;
import application.RecSystem;
import gui.application.ApplicationUIController;
import gui.main.MainUIController;
import gui.main.MainWindow;
import gui.profile.ProfileUIController;
import gui.retraining.RetrainingUIController;
import gui.vacancy.VacancyUIController;
import registration.Profile;
import registration.ProfileCollection;
import registration.RegistrationController;
import registration.ServiceArea;
import retraining.RetrainingController;
import retraining.RetrainingCollection;
import vacancy.VacancyCollection;
import vacancy.VacancyController;

public class Office {
    public Office() {
        //Collections
        ProfileCollection profileCollection = new ProfileCollection();
        ApplicationCollection applicationCollection = new ApplicationCollection();
        VacancyCollection vacancyCollection = new VacancyCollection(applicationCollection);
        RetrainingCollection retrainingCollection = new RetrainingCollection();

        //Validators and stuff
        ServiceArea serviceArea = new ServiceArea();
        SpecialtyCatalog specialtyCatalog = new SpecialtyCatalog();
        RecSystem recSystem = new RecSystem();

        //Controllers
        RegistrationController registrationController = new RegistrationController(serviceArea, specialtyCatalog, profileCollection, this);
        ApplicationController applicationController = new ApplicationController(vacancyCollection, applicationCollection, profileCollection, retrainingCollection, recSystem);
        VacancyController vacancyController = new VacancyController(vacancyCollection, applicationCollection, specialtyCatalog);
        RetrainingController retrainingController = new RetrainingController(profileCollection, specialtyCatalog, retrainingCollection);

        //GUI
        MainWindow mainWindow = new MainWindow();

        //GUI Controllers
        ProfileUIController  profileUIController = new ProfileUIController(mainWindow, registrationController, profileCollection);
        ApplicationUIController applicationUIController = new ApplicationUIController(mainWindow, applicationController, applicationCollection);
        VacancyUIController vacancyUIController = new VacancyUIController(mainWindow, vacancyController, vacancyCollection);
        RetrainingUIController retrainingUIController = new RetrainingUIController(mainWindow, retrainingController, retrainingCollection);
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
