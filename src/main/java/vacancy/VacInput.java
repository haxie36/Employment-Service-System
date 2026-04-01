package vacancy;

public class VacInput {
    public String company;
    public String contact;
    public String specialty;
    public int minExperience;
    public String description;

    public VacInput(String company, String contact, String specialty, int minExperience, String description){
        this.company = company;
        this.contact = contact;
        this.specialty = specialty;
        this.minExperience = minExperience;
        this.description = description;
    }
}
