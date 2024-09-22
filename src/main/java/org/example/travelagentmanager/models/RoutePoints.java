package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Пункты маршрута
public class RoutePoints {
    private int id;
    private int excursionProgramId;
    private int pointName;
    private int durationAtPoint;
    public RoutePoints() {}

    public RoutePoints(int id, int excursionProgramId, int pointName, int durationAtPoint) {
        this.id = id;
        this.excursionProgramId = excursionProgramId;
        this.pointName = pointName;
        this.durationAtPoint = durationAtPoint;
    }

    public int getDurationAtPoint() {
        return durationAtPoint;
    }

    public void setDurationAtPoint(int durationAtPoint) {
        this.durationAtPoint = durationAtPoint;
    }

    public int getPointName() {
        return pointName;
    }

    public void setPointName(int pointName) {
        this.pointName = pointName;
    }

    public int getExcursionProgramId() {
        return excursionProgramId;
    }

    public void setExcursionProgramId(int excursionProgramId) {
        this.excursionProgramId = excursionProgramId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
