package registration;

import common.Passport;

public class RegInput {
    private final String address;
    private final Passport passport;
    private final String specialty;
    private final int experience;

    public RegInput(String address, Passport passport, String specialty, int experience) {
        this.address = address;
        this.passport = passport;
        this.specialty = specialty;
        this.experience = experience;
    }

    public String getAddress() {return address;}
    public Passport getPassport() {return passport;}
    public String getSpecialty() {return specialty;}
    public int getExperience() {return experience;}
}
