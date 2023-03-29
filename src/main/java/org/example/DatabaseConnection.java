package org.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DatabaseConnection {
    private int daysPassed;
    private int villageCapacity;
    private int food;
    private int wood;
    private int metal;
    private ArrayList<Worker> workersList;
    private ArrayList<Building> projectsList;
    private ArrayList<Building> buildingsList;

    private DatabaseConnection dbc;



    public void Save( int daysPassed, int villageCapacity, int food, int wood, int metal,
                      ArrayList<Worker> workersList, ArrayList<Building> projectsList,
                      ArrayList<Building> buildingsList) {

        String fileName = SetFilePath();

        this.daysPassed = daysPassed;
        this.villageCapacity = villageCapacity;
        this.food = food;
        this.wood = wood;
        this.metal = metal;
        this.workersList = workersList;
        this.projectsList = projectsList;
        this.buildingsList = buildingsList;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(this, writer);
            writer.close();
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Load() {
        Gson gson = new Gson();
        try {
            String fileName = SetFilePath();
            FileReader reader = new FileReader(fileName);
            DatabaseConnection data = gson.fromJson(reader, DatabaseConnection.class);
            reader.close();
            this.daysPassed = data.daysPassed;
            this.villageCapacity = data.villageCapacity;
            this.food = data.food;
            this.wood = data.wood;
            this.metal = data.metal;
            this.workersList = data.workersList;
            this.projectsList = data.projectsList;
            this.buildingsList = data.buildingsList;

            System.out.println("Game loaded successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String SetFilePath(){
        String fileName = "C:/Users/james/IdeaProjects/The_Village_of_Testing/game_data.json";
        return fileName;
    }

    public int GetFood() {
        return food;
    }

    public int GetWood() {
        return wood;
    }

    public int GetMetal() {
        return metal;
    }

    public ArrayList<Worker> GetWorkersList() {
        return workersList;
    }

    public ArrayList<Building> GetBuildingsList() {
        return buildingsList;
    }

    public int GetDaysPassed() {
        return daysPassed;
    }

    public int GetVillageCapacity() {
        return villageCapacity;
    }

    public ArrayList<Building> GetProjectsList() {
        return projectsList;
    }
}

