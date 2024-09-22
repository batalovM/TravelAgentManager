package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Туристы в группе
public class GroupTourists {
    private int groupTouristId;
    private int tripId;
    private int clientId;
    public GroupTourists() {}

    public GroupTourists(int groupTouristId, int tripId, int clientId) {
        this.groupTouristId = groupTouristId;
        this.tripId = tripId;
        this.clientId = clientId;
    }

    public int getGroupTouristId() {
        return groupTouristId;
    }

    public void setGroupTouristId(int groupTouristId) {
        this.groupTouristId = groupTouristId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
