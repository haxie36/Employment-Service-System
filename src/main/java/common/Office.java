package common;

import application.ApplicationController;
import application.ApplicationDAO;
import application.RecSystem;
import gui.application.ApplicationUIController;
import gui.main.MainUIController;
import gui.main.MainWindow;
import gui.profile.ProfileUIController;
import gui.retraining.RetrainingUIController;
import gui.vacancy.VacancyUIController;
import registration.*;
import retraining.RetrainingController;
import retraining.RetrainingDAO;
import vacancy.VacancyDAO;
import vacancy.VacancyController;

import javax.swing.*;
import java.awt.*;

public class Office {
    public void start() {
        //DAOs
        ProfileDAO profileDAO = new ProfileDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        VacancyDAO vacancyDAO = new VacancyDAO();
        RetrainingDAO retrainingDAO = new RetrainingDAO();

        //Validators and stuff
        ServiceArea serviceArea = new ServiceArea();
        SpecialtyCatalog specialtyCatalog = new SpecialtyCatalog();
        RecSystem recSystem = new RecSystem();

        //Controllers
        RegistrationController registrationController = new RegistrationController(serviceArea,
                specialtyCatalog, profileDAO, this);
        ApplicationController applicationController = new ApplicationController(applicationDAO,
                profileDAO, vacancyDAO, retrainingDAO, recSystem);
        VacancyController vacancyController = new VacancyController(vacancyDAO,
                applicationDAO, specialtyCatalog);
        RetrainingController retrainingController = new RetrainingController(profileDAO,
                specialtyCatalog, retrainingDAO);

        //GUI
        UIManager.put("Default_font", new Font("Arial", Font.BOLD, 12));
        MainWindow mainWindow = new MainWindow();

        //GUI Controllers
        ProfileUIController  profileUIController = new ProfileUIController(mainWindow,
                registrationController);
        ApplicationUIController applicationUIController = new ApplicationUIController(mainWindow,
                applicationController, registrationController, vacancyController);
        VacancyUIController vacancyUIController = new VacancyUIController(mainWindow,
                vacancyController);
        RetrainingUIController retrainingUIController = new RetrainingUIController(mainWindow,
                retrainingController, registrationController);
        MainUIController mainUIController = new MainUIController(mainWindow,
                profileUIController, applicationUIController,
                vacancyUIController, retrainingUIController);
    }

    //Prints certification (formates profile's info)
    public void printCertificate(String profilePassportNumber, ProfileDAO profileDAO) {
        Profile profile = profileDAO.getByPassport(profilePassportNumber);
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
