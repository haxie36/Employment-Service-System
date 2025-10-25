public class Registration {
    private Profile profile = null;
    private ServiceArea serviceArea;
    private ClientList clientList;
    private SpecialtyCatalog specialtyCatalog;
    private Profiles profiles;
    private Office office;

    public Registration(ServiceArea serviceArea, ClientList clientList,
                        SpecialtyCatalog specialtyCatalog, Profiles profiles,
                        Office office) {
        this.serviceArea = serviceArea;
        this.clientList = clientList;
        this.specialtyCatalog = specialtyCatalog;
        this.profiles = profiles;
        this.office = office;
    }

    public Profile newProfile(){
        profile = new Profile();
        return profile;
    }

    public boolean isServiceArea(String area){
        return profile.isServiceArea(area, serviceArea);
    }

    public boolean isRegistered(ID ID){
        return profile.isRegistered(ID, clientList);
    }

    public boolean isRealSpecialty(String specialty){
        return profile.isRealSpecialty(specialty, specialtyCatalog);
    }

    public void record(short exp){
        profile.setExperience(exp);
    }

    public void saveProfile(){
        if (profile!=null){
            profiles.add(profile);
            clientList.add(profile);
            profile.printCertification(office);
            profile = null;
        }
    }
}
