package registration;

import common.*;

public class RegistrationController extends LogicController<Profile, RegInput>{
    private final ServiceArea serviceArea;
    private final SpecialtyCatalog specialtyCatalog;
    private final Office office;

    public RegistrationController(ServiceArea serviceArea,
                                  SpecialtyCatalog specialtyCatalog,
                                  Profiles profiles,
                                  Office office) {
        super(profiles);
        this.serviceArea = serviceArea;
        this.specialtyCatalog = specialtyCatalog;
        this.office = office;
    }

    @Override
    //New Profile
    public void newCreation() {creation = new Profile();}

    //Check if the address being serviced
    public boolean isServiceArea(String area){
        return creation.isServiceArea(area, serviceArea);
    }

    //Check for profile being dy registered, if isn't, set the Passport info (ID) as profiles
    public boolean isRegistered(Passport Passport){
        return creation.isRegistered(Passport, (Profiles) collection);
    }

    //Check if the specialty is real, if is, set
    public boolean isRealSpecialty(String specialty){
        return creation.isRealSpecialty(specialty, specialtyCatalog);
    }

    public boolean record(int exp){
        return creation.setExperience(exp);
    }

    //Prints certification (formates profile's info)
    public String printCertification(){
        if (creation !=null){
            return creation.printCertification(office);
        }
        return null;
    }

    //All in one
    public boolean create(RegInput input){
        newCreation();
        if (!isServiceArea(input.getAddress()))
            throw new IllegalArgumentException("Address is not valid!");
        if (isRegistered(input.getPassport()))
            throw new IllegalArgumentException("Profile is already registered!");
        if (!isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");;
        if (!record(input.getExperience()))
            throw new IllegalArgumentException("Invalid Experience!");;

        return save();
    }

    //Edit
    public boolean editProfile(Profile prof, RegInput input){
        if (prof.existsByPassportExcept(input.getPassport().getPassportNumber(), (Profiles) collection))
            throw new IllegalArgumentException("Profile with this passport already exists");
        if (prof.existsByRNOKPPExcept(input.getPassport().getRNOKPP(), (Profiles) collection))
            throw new IllegalArgumentException("Profile with this RNOKPP already exists");

        prof.setPassportInfo(input.getPassport());
        if (prof.isRealSpecialty(input.getSpecialty(), specialtyCatalog))
            throw new IllegalArgumentException("Invalid Specialty!");
        return prof.setExperience(input.getExperience());
    }
}
