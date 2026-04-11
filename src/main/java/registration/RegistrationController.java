package registration;

import base.LogicController;
import common.*;

public class RegistrationController extends LogicController<Profile, RegInput> {
    private final ServiceArea serviceArea;
    private final SpecialtyCatalog specialtyCatalog;
    private final Office office;

    public RegistrationController(ServiceArea serviceArea,
                                  SpecialtyCatalog specialtyCatalog,
                                  ProfileCollection profileCollection,
                                  Office office) {
        super(profileCollection);
        this.serviceArea = serviceArea;
        this.specialtyCatalog = specialtyCatalog;
        this.office = office;
    }

    @Override
    //New Profile
    public void newCreation() {creation = new Profile();}

    //Prints certification (formates profile's info)
    public void printCertification(){
        office.printCertificate(creation);
    }

    @Override
    public boolean save(){
        if (creation!=null){
            setCreationId(); //temp
            collection.add(creation);
            printCertification();
            clear();
            return true;
        }
        return false;
    }

    //All-in-one
    public void create(RegInput input) {
        if (!serviceArea.isServiceArea(input.getAddress()))
            throw new IllegalArgumentException("Address is not valid!");
        if (((ProfileCollection)collection).isRegistered(input.getPassport()))
            throw new IllegalArgumentException("Profile is already registered!");
        validateSkills(input);

        newCreation();
        creation.setPassportInfo(input.getPassport());
        creation.setSpecialty(input.getSpecialty());
        creation.setExperience(input.getExperience());
        if (!save()) throw new RuntimeException("Save failed!");
    }

    //Edit
    public void editProfile(Profile prof, RegInput input) {
        if (((ProfileCollection)collection).existsByPassportExcept(prof.getId(),
                input.getPassport().getPassportNumber()))
            throw new IllegalArgumentException("Profile with this passport already exists!");
        if (((ProfileCollection)collection).existsByRNOKPPExcept(prof.getId(),
                input.getPassport().getRNOKPP()))
            throw new IllegalArgumentException("Profile with this RNOKPP already exists!");
        validateSkills(input);

        prof.setPassportInfo(input.getPassport());
        prof.setExperience(input.getExperience());
    }

    private void validateSkills(RegInput input) {
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");
        if (input.getExperience() < 0)
            throw new IllegalArgumentException("Experience must be positive!");
    }
}
