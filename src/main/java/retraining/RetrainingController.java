package retraining;

import base.LogicController;
import common.DateUtils;
import common.SpecialtyCatalog;
import registration.Profile;
import registration.ProfileCollection;

import java.time.LocalDate;

public class RetrainingController extends LogicController<Retraining, RetrInput> {
    private final ProfileCollection profileCollection;
    private final SpecialtyCatalog specialtyCatalog;

    public RetrainingController(ProfileCollection profileCollection, SpecialtyCatalog specialtyCatalog, RetrainingCollection retrainingCollection) {
        super(retrainingCollection);
        this.profileCollection = profileCollection;
        this.specialtyCatalog = specialtyCatalog;
        this.collection = retrainingCollection;
    }

    @Override
    //Create a new empty Retraining
    public void newCreation(){creation = new Retraining();}

    //All-in-one
    public void create(RetrInput input){
        if (!specialtyCatalog.isRealSpecialty(input.getSpecialty()))
            throw new IllegalArgumentException("Invalid specialty!");
        Profile profile = profileCollection.getByPassport(input.getPassportNumber());
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
        retraining.update(editInput);
    }
}
