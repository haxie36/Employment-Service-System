package main.java;

public class ClientList {
    private String[] clientIds;

    public ClientList() {
        clientIds = new String[0];
    }
    public ClientList(String[] clientIds) {
        this.clientIds = clientIds;
    }

    public boolean isRegistered(ID ID) {
        String id = ID.getId();
        return isRegistered(id);
    }
    public boolean isRegistered(String id) {
        for (int i=0; i<clientIds.length; i++) {
            if (id.equals(clientIds[i]))
                return true;
        }
        return false;
    }

    public boolean add(Profile profile) {
        String id = profile.getId();
        boolean exists = false;

        for (int i=0; i<clientIds.length; i++) {
            if (id.equals(clientIds[i])) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            String[] newClientIds = new String[clientIds.length+1];


            for (int i=0; i<clientIds.length; i++) {
                newClientIds[i] = clientIds[i];
            }

            newClientIds[clientIds.length] = id;
            clientIds=newClientIds;
        }
        return !exists;
    }

    public String[] getClientIds() {return clientIds;}
    public void clear() {clientIds = new String[0];}

    public void delete(Profile profile) {
        String id = profile.getId();
        delete(id);
    }
    public boolean delete(String id) {
        for (int i=0; i<clientIds.length; i++) {
            if (id.equals(clientIds[i])) {
                String[] newClientIds = new String[clientIds.length-1];
                for (int j=0; j<clientIds.length; j++) {
                    if (j==i) continue;
                    if (j>i) { newClientIds[j-1]=clientIds[j]; continue;}
                    newClientIds[j] = clientIds[j];
                }
                clientIds=newClientIds;
                return true;
            }
        }
        return false;
    }
}
