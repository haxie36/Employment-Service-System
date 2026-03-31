package registration;

import common.ID;

public class RegInput {
    public String address;
    public ID passport;
    public String specialty;
    public int experience;

    public RegInput(String address, ID passport, String specialty, int experience) {
        this.address = address;
        this.passport = passport;
        this.specialty = specialty;
        this.experience = experience;
    }
}
