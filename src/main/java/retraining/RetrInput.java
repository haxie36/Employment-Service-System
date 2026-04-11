package retraining;

public class RetrInput {
    private final String specialty;
    private final String passportNumber;

    public RetrInput(String specialty, String passportNumber) {
        this.specialty = specialty;
        this.passportNumber = passportNumber;
    }

    public String getSpecialty() {return specialty;}
    public String getPassportNumber() {return passportNumber;}
}
