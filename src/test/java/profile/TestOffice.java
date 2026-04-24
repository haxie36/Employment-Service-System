package profile;

import logic.common.Office;
import logic.profile.ProfileDAO;

public class TestOffice extends Office {
    @Override
    public void start() {}

    @Override
    public void printCertificate(String profilePassportNumber, ProfileDAO profileDAO) {
        System.out.println("Certificate");
    }
}