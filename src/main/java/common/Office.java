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
import registration.*;
import retraining.RetrainingController;
import retraining.RetrainingCollection;
import vacancy.VacancyCollection;
import vacancy.VacancyController;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

public class Office {
    public void start() {
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
        RegistrationController registrationController = new RegistrationController(serviceArea,
                specialtyCatalog, profileCollection, this);
        ApplicationController applicationController = new ApplicationController(vacancyCollection,
                applicationCollection, profileCollection, retrainingCollection, recSystem);
        VacancyController vacancyController = new VacancyController(vacancyCollection, applicationCollection,
                specialtyCatalog);
        RetrainingController retrainingController = new RetrainingController(profileCollection,
                specialtyCatalog, retrainingCollection);

        //GUI
        UIManager.put("Default_font", new Font("Arial", Font.BOLD, 12));
        MainWindow mainWindow = new MainWindow();

        //GUI Controllers
        ProfileUIController  profileUIController = new ProfileUIController(mainWindow, registrationController, profileCollection);
        ApplicationUIController applicationUIController = new ApplicationUIController(mainWindow, applicationController, applicationCollection, profileCollection);
        VacancyUIController vacancyUIController = new VacancyUIController(mainWindow, vacancyController, vacancyCollection);
        RetrainingUIController retrainingUIController = new RetrainingUIController(mainWindow, retrainingController, retrainingCollection, profileCollection);
        MainUIController mainUIController = new MainUIController(mainWindow,
                profileUIController, applicationUIController,
                vacancyUIController, retrainingUIController);
    }

    //Prints certification (formates profile's info)
    public void printCertificate(Profile profile) {
        String certificate = """
                CERTIFICATE OF REGISTRATION

                Full Name: %s
                Date of Birth: %s
                Passport Number: %s
                RNOKPP: %s

                Specialty: %s
                Experience: %d years

                Registration ID: %s

                Issued: %s

                Signature: _______________________
                """.formatted(
                profile.getName(),
                profile.getBirthDate(),
                profile.getPassportNumber(),
                profile.getRNOKPP(),
                profile.getSpecialty(),
                profile.getExperience(),
                profile.getId(),
                java.time.LocalDate.now()
        );
        //Open the window
        CertificateWindow window = new CertificateWindow(certificate);
        //Printing dialog
//        try {
//            window.getTextArea().print();
//        } catch (PrinterException e) {
//            throw new RuntimeException("Printing failed", e);
//        }
    }

    public static void main(String[] args) {
        new Office().start();
    }
}
