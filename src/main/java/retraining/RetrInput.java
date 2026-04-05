package retraining;

public class RetrInput {
    private final String specialty;
    private final String profileId;

    public RetrInput(String specialty, String profileId) {
        this.specialty = specialty;
        this.profileId = profileId;
    }

    public String getSpecialty() {return specialty;}
    public String getProfileId() {return profileId;}
}
