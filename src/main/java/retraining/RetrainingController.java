package retraining;

import common.SpecialtyCatalog;
import registration.Profiles;

import java.time.LocalDate;

public class RetrainingController {
    private Retraining retraining = null;
    private final Profiles profiles;
    private final SpecialtyCatalog specialtyCatalog;
    private final Retrainings retrainings;

    public RetrainingController(Profiles profiles, SpecialtyCatalog specialtyCatalog, Retrainings retrainings) {
        this.profiles = profiles;
        this.specialtyCatalog = specialtyCatalog;
        this.retrainings = retrainings;
    }

    //Create a new empty Retraining
    public void newRetraining(){
        retraining = new Retraining();
    }

    //Check for the specialty being real, if is, set as retraining's own
    public boolean isRealSpecialty(String specialty){
        return retraining.isRealSpecialty(specialty, specialtyCatalog);
    }

    //Check for profile being registered, if is, set as retraining's own
    public boolean isRegistered(String profileId){
        return retraining.findProfile(profileId, profiles);
    }

    //End the creation of the retraining by saving it in the collection
    public boolean saveRetraining(){
        if (retraining!=null){
            setRetrainingId(); //temp
            retrainings.add(retraining);
            clear();
            return true;
        }
        return false;
    }
    //temp
    private void setRetrainingId(){retraining.setId(String.valueOf(retrainings.getAll().length+1));}

    //All in one
    public boolean createRetraining(RetrInput input){
        newRetraining();
        if (!isRealSpecialty(input.specialty)) return false;
        if (!isRegistered(input.profileId)) return false;

        return saveRetraining();
    }

    //Edit and delete

    //Check for dates being valid and set as retraining's own
    public boolean isValidPeriod(Retraining retr, LocalDate startDate, LocalDate endDate){
        return retr.isValidPeriod(startDate, endDate);
    }

    //Change the retraining's status (cancel or retract it)
    public void changeRetainingStatus(Retraining retr, int status){
        retr.setStatus(status);
    }

    public boolean deleteRetraining(Retraining retr){
        return retrainings.delete(retr.getId());
    }

    public void clear(){
        retraining = null;
    }
}
