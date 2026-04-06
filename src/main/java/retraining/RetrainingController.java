package retraining;

import base.LogicController;
import common.SpecialtyCatalog;
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

    //Check for the specialty being real, if is, set as retraining's own
    public boolean isRealSpecialty(String specialty){
        return creation.isRealSpecialty(specialty, specialtyCatalog);
    }

    //Check for profile being registered, if is, set as retraining's own
    public boolean isRegistered(String profileId){
        return creation.findProfile(profileId, profileCollection);
    }

    //All in one
    public boolean create(RetrInput input){
        newCreation();
        if (!isRealSpecialty(input.getSpecialty())) return false;
        if (!isRegistered(input.getProfileId())) return false;

        return save();
    }

    //Check for dates being valid and set as retraining's own
    public boolean isValidPeriod(Retraining retr,
                                 LocalDate startDate, LocalDate endDate){
        return retr.isValidPeriod(startDate, endDate);
    }

    //Change the retraining's status (cancel or retract it)
    public void changeRetainingStatus(Retraining retr, int status){
        retr.setStatus(status);
    }
}
