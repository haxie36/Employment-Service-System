package logic.vacancy;

import logic.base.HasId;

public class Vacancy implements HasId {
    private int id;
    private String title;
    private String company;
    private String contact;
    private String specialty;
    private int minExperience;
    private String description;
    private VacancyStatus status;

    public Vacancy(){status = VacancyStatus.OPEN;}
    public Vacancy(int id, String title, String company, String contact, String specialty, int minExperience, String description, VacancyStatus status) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.contact = contact;
        this.specialty = specialty;
        this.minExperience = minExperience;
        this.description = description;
        this.status = status;
    }


    //Change own status and change own applications' statuses
    public void changeStatus(int status){
        setStatus(VacancyStatus.fromId(status));
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getCompany() {return company;}
    public String getContact() {return contact;}
    public String getSpecialty() {return specialty;}
    public int getMinExperience() {return minExperience;}
    public String getDescription() {return description;}
    public VacancyStatus getStatus() {return status;}
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setCompany(String company) {this.company = company;}
    public void setContact(String contact) {this.contact = contact;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}
    public void setMinExperience(int minExperience) {this.minExperience = minExperience;}
    public void setDescription(String description) {this.description = description;}
    public void setStatus(VacancyStatus status) {this.status = status;}

    @Override
    public String toString() {
        return String.format(
                "(%s) %s @ %s [%s]",
                id,
                title,
                company,
                status.getName()
        );
    }
}
