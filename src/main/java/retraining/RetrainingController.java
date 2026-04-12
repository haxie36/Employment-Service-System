package retraining;

import base.LogicController;
import common.DateUtils;
import common.SpecialtyCatalog;
import registration.Profile;
import registration.ProfileDAO;

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
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid specialty!");
        Profile profile = profileDAO.getByPassport(input.getPassportNumber());
        if (profile == null)
            throw new IllegalArgumentException("Profile doesn't exist!");

        newCreation();
        creation.setSpecialty(input.getSpecialty());
        creation.setProfile(profile);
        if (!save()) throw new RuntimeException("Save failed!");
    }

    //Planning
    public void planRetraining(Retraining retraining,
                               LocalDate startDate, LocalDate endDate) {
        if (!DateUtils.isValidPeriod(startDate, endDate)) {
            throw new IllegalArgumentException("Invalid dates");
        }
        retraining.plan(startDate, endDate);
    }

    //All-in-one for edition
    public void edit(Retraining retraining, PlanningInput editInput) {
        if (!specialtyCatalog.isRealSpecialty(editInput.getSpecialty())) {
            throw new IllegalArgumentException("Invalid specialty");
        }
        if (!DateUtils.isValidPeriod(editInput.getStartDate(), editInput.getEndDate())) {
            throw new IllegalArgumentException("Invalid dates");
        }
        //Changing status throws exception on its own (if status is out of range)
        retraining.update(editInput);
    }

    public Profile getByPassport(String passportNumber) {
        return profileDAO.getByPassport(passportNumber);
    }
}
