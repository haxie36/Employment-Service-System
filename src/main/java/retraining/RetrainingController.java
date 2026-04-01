package retraining;

import common.SpecialtyCatalog;
import registration.Profiles;

import java.time.LocalDate;

public class RetrainingController {
    private Retraining retraining = null;
    private Retraining changedRetraining = null;
    private final Profiles profiles;
    private final SpecialtyCatalog specialtyCatalog;
    private final Retrainings retrainings;

    public RetrainingController(Profiles profiles, SpecialtyCatalog specialtyCatalog, Retrainings retrainings) {
        this.profiles = profiles;
        this.specialtyCatalog = specialtyCatalog;
        this.retrainings = retrainings;
    }

    public Retraining newRetraining(){
        return retraining = new Retraining();
    }

    public boolean isRealSpecialty(String specialty){
        return retraining.isRealSpecialty(specialty, specialtyCatalog);
    }

    public boolean findProfile(String profileId){
        return retraining.findProfile(profileId, profiles);
    }

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
    private void setRetrainingId(){retraining.setId(String.valueOf(retrainings.getRetrainings().length+1));}

    public boolean createRetraining(RetrInput input){
        newRetraining();
        if (!isRealSpecialty(input.specialty)) return false;
        if (!findProfile(input.profileId)) return false;

        return saveRetraining();
    }

    public Retraining chooseRetraining(String retrainingId){
        return changedRetraining = retrainings.getRetraining(retrainingId);
    }

    public boolean isValidPeriod(LocalDate startDate, LocalDate endDate){
        return changedRetraining.isValidPeriod(startDate, endDate);
    }

    public void changeRetainingStatus(int status){
        changedRetraining.setStatus(status);
    }

    public void clear(){
        retraining = null;
        changedRetraining = null;
    }
}
