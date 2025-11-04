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

        for (int i=0; i<clientIds.length; i++) {
            if (id.equals(clientIds[i]))
                return true;
        }
        return false;
    }

    public void add(Profile profile) {
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
    }
}
