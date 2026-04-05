package common;

import application.ApplicationStatus;
import application.Applications;
import interfaces.HasId;
import vacancy.VacancyStatus;

public class Vacancy implements HasId {
    private String id;
    private String title;
    private String company;
    private String contact;
    private String specialty;
    private int minExperience;
    private String description;
    private VacancyStatus status;

    public Vacancy(){
        status = VacancyStatus.OPEN;
    }
    public Vacancy(String id, String title, String company, String contact, String specialty, int minExperience, String description, VacancyStatus status) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.contact = contact;
        this.specialty = specialty;
        this.minExperience = minExperience;
        this.description = description;
        this.status = status;
    }

    //Check if the specialty is real, if is, set as own
    public boolean isRealSpecialty(String specialty, SpecialtyCatalog specialtyCatalog){
        boolean is = specialtyCatalog.isRealSpecialty(specialty);
        if (is){setSpecialty(specialty);}
        return is;
    }

    //Change own status and change own applications' statuses
    public boolean changeStatus(int status, Applications applications){
        setStatus(VacancyStatus.fromId(status));
        Application[] apps = applications.getAll();
        if (status!=0){
            for (Application app : apps) {
                if (app.getStatus() == ApplicationStatus.ACTIVE) {
                    app.setStatus(ApplicationStatus.RETRACTED);
                }
            }
        } else{
            for (Application app : apps) {
                if (app.getStatus() == ApplicationStatus.RETRACTED) {
                    app.setStatus(ApplicationStatus.ACTIVE);
                }
            }
        }

        return false;
    }

    public String getId() {return id;}
    public String getTitle() {return title;}
    public String getCompany() {return company;}
    public String getContact() {return contact;}
    public String getSpecialty() {return specialty;}
    public int getMinExperience() {return minExperience;}
    public String getDescription() {return description;}
    public VacancyStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public boolean setCompany(String company) {
        if (company.length()>=3){
        this.company = company;
        return true;
        }return false;
    }
    public void setContact(String contact) {this.contact = contact;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public boolean setMinExperience(int minExperience) {
        if (minExperience >= 0) {
            this.minExperience = minExperience;
            return true;
        }return false;
    }
    public boolean setDescription(String description) {
        if (description.length()>=3){
            this.description = description;
            return true;
        }return false;
    }
    public void setStatus(VacancyStatus status) {this.status = status;}

    public static String getClassName() {return "Vacancy";}

    public String toString() {
        return "("+id+") "+title+" ["+status.getName()+"]";
    }
}
