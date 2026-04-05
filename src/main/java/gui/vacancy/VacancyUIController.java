package gui.vacancy;

import gui.main.MainWindow;
import vacancy.Vacancies;
import vacancy.VacancyController;

public class VacancyUIController {
    private MainWindow mainWindow;
    private VacancyController vacancyController;
    private Vacancies vacancies;

    public VacancyUIController(MainWindow mainWindow, VacancyController vacancyController,Vacancies vacancies) {
        this.mainWindow = mainWindow;
        this.vacancyController = vacancyController;
        this.vacancies = vacancies;
    }

    public void open(){

    }
}
