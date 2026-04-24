package logic.profile;

import logic.base.LogicController;
import logic.common.Office;
import logic.common.SpecialtyCatalog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RegistrationController extends LogicController<Profile, RegInput, ProfileDAO> {
    private final ServiceArea serviceArea;
    private final SpecialtyCatalog specialtyCatalog;
    private final Office office;

    public RegistrationController(ServiceArea serviceArea,
                                  SpecialtyCatalog specialtyCatalog,
                                  ProfileDAO profileDAO,
                                  Office office) {
        super(profileDAO);
        this.serviceArea = serviceArea;
        this.specialtyCatalog = specialtyCatalog;
        this.office = office;
    }

    @Override
    //New Profile
    public void newCreation() {creation = new Profile();}

    //Prints certification (formates profile's info)
    public void printCertification(){
        office.printCertificate(creation.getPassportNumber(), DAO);
    }

    @Override
    public void save(){
        if (creation == null) {
            throw new IllegalArgumentException("Save is imposible!");
        }
        DAO.add(creation);
        printCertification();
        clear();
    }

    //All-in-one
    public void create(RegInput input) {
        if (input == null)
            throw new IllegalArgumentException("Invalid Input!");
        if (!serviceArea.isServiceArea(input.getAddress()))
            throw new IllegalArgumentException("Address is not valid!");
        validatePassportInfo(input);
        if (DAO.isRegistered(
                input.getPassport().getPassportNumber(),
                input.getPassport().getRNOKPP()))
            throw new IllegalArgumentException("Profile is already registered!");
        validateSkills(input);

        newCreation();
        creation.setPassportInfo(input.getPassport());
        creation.setSpecialty(input.getSpecialty());
        creation.setExperience(input.getExperience());
        save();
    }

    //Edit
    public void edit(Profile profile, RegInput input) {
        if (profile == null)
            throw new IllegalArgumentException("Object is null!");
        if (input == null)
            throw new IllegalArgumentException("Invalid Input!");
        validatePassportInfo(input);
        if (DAO.existsByPassportExcept(profile.getId(),
                input.getPassport().getPassportNumber()))
            throw new IllegalArgumentException("Profile already exists!");
        if (DAO.existsByRNOKPPExcept(profile.getId(),
                input.getPassport().getRNOKPP()))
            throw new IllegalArgumentException("Profile already exists!");
        validateSkills(input);

        profile.setPassportInfo(input.getPassport());
        profile.setExperience(input.getExperience());
        DAO.update(profile);
    }

    private static void validatePassportInfo(RegInput input) {
        if (input.getPassport().getPassportNumber().length() < 8
                || input.getPassport().getPassportNumber().length() > 9)
            throw new IllegalArgumentException("Invalid Passport Number!");
        if (input.getPassport().getRNOKPP().length() != 10)
            throw new IllegalArgumentException("Invalid RNOKPP!");
        if (input.getPassport().getName().length() < 3
                || input.getPassport().getName().length() > 100)
            throw new IllegalArgumentException("Invalid Name!");
        if (ChronoUnit.YEARS.between(input.getPassport().getBirthday(), LocalDate.now()) > 120
        || ChronoUnit.YEARS.between(input.getPassport().getBirthday(), LocalDate.now()) < 18)
            throw new IllegalArgumentException("Invalid Birthday!");
    }

    private void validateSkills(RegInput input) {
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");
        if (input.getExperience() < 0 || input.getExperience() > 100
        || ChronoUnit.YEARS.between(
                input.getPassport().getBirthday(),
                LocalDate.now()
        ) - 16 < input.getExperience())
            throw new IllegalArgumentException("Invalid Experience!");
    }
}
