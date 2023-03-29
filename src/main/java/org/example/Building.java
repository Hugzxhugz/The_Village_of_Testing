package org.example;


public class Building {
    public enum BuildingType {
        HOUSE,
        WOODMILL,
        QUARRY,
        FARM,
        CASTLE
    }
    private String name;
    private BuildingType buildingType;
    public int woodCost;
    public int metalCost;
    public int daysToBuild;
    private int daysWorkedOn;
    private boolean complete;


    public Building(String name, BuildingType buildingType) {
        this.name = name;
        this.buildingType = buildingType;
        this.daysWorkedOn = 0;
        this.complete = false;

        if (this.buildingType == BuildingType.HOUSE) {
            this.woodCost = 5;
            this.daysToBuild = 3;
        }

        if (this.buildingType == BuildingType.WOODMILL) {
            this.woodCost = 5;
            this.metalCost = 1;
            this.daysToBuild = 5;
        }

        if (this.buildingType == BuildingType.QUARRY) {
            this.woodCost = 3;
            this.metalCost = 5;
            this.daysToBuild = 7;
        }

        if (this.buildingType == BuildingType.FARM) {
            this.woodCost = 5;
            this.metalCost = 2;
            this.daysToBuild = 5;
        }

        if (this.buildingType == BuildingType.CASTLE) {
            this.woodCost = 50;
            this.metalCost = 50;
            this.daysToBuild = 50;
        }
    }


    public String GetName() {
        return this.name;
    }

    public BuildingType GetBuildingType() {
        return this.buildingType;
    }

    public int GetWoodCost() {
        return this.woodCost;
    }

    public int GetMetalCost() {
        return this.metalCost;
    }

    public int GetDaysToBuild() {
        return this.daysToBuild;
    }

    public int GetDaysWorked() {
        return this.daysWorkedOn;
    }


    public int SetDaysWorkedOnStartingHouse(){
        this.daysWorkedOn = 3;
        return daysWorkedOn;
    }

    public void IncrementDaysWorkedOn() {
        this.daysWorkedOn = daysWorkedOn +1;
    }

    public void IsComplete() {
        this.complete = true;
    }

    public boolean CheckIfComplete(){
        return complete;
    }

}
