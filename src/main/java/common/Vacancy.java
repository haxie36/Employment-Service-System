package common;

import application.ApplicationStatus;
import application.Applications;
import vacancy.VacancyStatus;

public class Vacancy {
    private String id;
    private String company;
    private String contact;
    private String specialty;
    private int minExperience;
    private String description;
    private VacancyStatus status;

    public Vacancy(){
        status = VacancyStatus.OPEN;
    }
    public Vacancy(String id, String company, String contact, String specialty, int minExperience, String description, VacancyStatus status) {
        this.id = id;
        this.company = company;
        this.contact = contact;
        this.specialty = specialty;
        this.minExperience = minExperience;
        this.description = description;
        this.status = status;
    }

    public boolean isRealSpecialty(String specialty, SpecialtyCatalog specialtyCatalog){
        boolean is = specialtyCatalog.isRealSpecialty(specialty);
        if (is){setSpecialty(specialty);}
        return is;
    }

    public boolean changeStatus(int status, Applications applications){
        setStatus(VacancyStatus.fromId(status));
        Application[] apps = applications.getApplications();
        if (status!=0){
            for (int i=0; i<apps.length; i++){
                if (apps[i].getStatus()==ApplicationStatus.ACTIVE){
                    apps[i].setStatus(ApplicationStatus.RETRACTED);
                }
            }
        } else{
            for (int i=0; i<apps.length; i++){
                if (apps[i].getStatus()==ApplicationStatus.RETRACTED){
                    apps[i].setStatus(ApplicationStatus.ACTIVE);
                }
            }
        }

        return false;
    }

    public String getId() {return id;}
    public String getCompany() {return company;}
    public String getContact() {return contact;}
    public String getSpecialty() {return specialty;}
    public int getMinExperience() {return minExperience;}
    public String getDescription() {return description;}
    public VacancyStatus getStatus() {return status;}
    public void setId(String id) {this.id = id;}
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
}
