package vacancy;

public class VacInput {
    private String company;
    private String contact;
    private String specialty;
    private int minExperience;
    private String description;

    public VacInput(String company, String contact, String specialty, int minExperience, String description){
        this.company = company;
        this.contact = contact;
        this.specialty = specialty;
        this.minExperience = minExperience;
        this.description = description;
    }

    public String getCompany() {return company;}
    public String getContact() {return contact;}
    public String getSpecialty() {return specialty;}
    public int getMinExperience() {return minExperience;}
    public String getDescription() {return description;}
}
