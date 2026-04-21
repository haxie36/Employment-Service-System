package logic.retraining;

import logic.base.LogicController;
import logic.common.DateUtils;
import logic.common.SpecialtyCatalog;
import logic.profile.Profile;
import logic.profile.ProfileDAO;

import java.time.LocalDate;

public class RetrainingController extends LogicController<Retraining, RetrInput, RetrainingDAO> {
    private final ProfileDAO profileDAO;
    private final SpecialtyCatalog specialtyCatalog;

    public RetrainingController(ProfileDAO profileDAO, SpecialtyCatalog specialtyCatalog, RetrainingDAO retrainingDAO) {
        super(retrainingDAO);
        this.profileDAO = profileDAO;
        this.specialtyCatalog = specialtyCatalog;
        this.DAO = retrainingDAO;
    }

    @Override
    //Create a new empty Retraining
    public void newCreation(){creation = new Retraining();}

    //All-in-one
    public void create(RetrInput input){
        if (input == null)
            throw new IllegalArgumentException("Invalid Input!");
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid Specialty!");
        Profile profile = profileDAO.getByPassport(input.getPassportNumber());
        if (profile == null)
            throw new IllegalArgumentException("Profile doesn't exist!");
        if (hasSameRetrainings(profile.getId(), input.getSpecialty()))
            throw new IllegalArgumentException("Retraining is a duplicate!");

        newCreation();
        creation.setSpecialty(input.getSpecialty());
        creation.setProfileId(profile.getId());
        if (!save()) throw new RuntimeException("Save failed!");
    }

    //Planning
    public void plan(Retraining retraining, PlanningInput input) {
        if (retraining == null) {return;}
        if (input == null)
            throw new IllegalArgumentException("Invalid Input!");
        LocalDate startDate = input.getStartDate();
        LocalDate endDate = input.getEndDate();
        if (!DateUtils.isValidPeriod(startDate, endDate)) {
            throw new IllegalArgumentException("Invalid Dates");
        }
        retraining.plan(startDate, endDate);
        DAO.update(retraining);
    }

    //All-in-one for edition
    public void edit(Retraining retraining, PlanningInput input) {
        if (retraining == null) {return;}
        if (input == null)
            throw new IllegalArgumentException("Invalid Input!");
        if (!DateUtils.isValidPeriod(input.getStartDate(), input.getEndDate())) {
            throw new IllegalArgumentException("Invalid Dates");
        }
        //Changing status throws exception on its own (if status is out of range)
        retraining.update(input);
        DAO.update(retraining);
    }

    public boolean hasSameRetrainings(int profileId, String specialty) {
        return DAO.hasSameRetrainings(profileId, specialty);
    }

    public Profile getByPassport(String passportNumber) {
        return profileDAO.getByPassport(passportNumber);
    }
}
