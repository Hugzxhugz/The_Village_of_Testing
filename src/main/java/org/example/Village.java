package org.example;
import java.util.ArrayList;


public class Village {
    private int food;
    private int wood;
    private int metal;
    private ArrayList<Worker> workersList;
    private ArrayList<Building> projectsList;
    private ArrayList<Building> buildingsList;
    private int daysPassed;
    private boolean gameOver;
    private int villageCapacity;
    private Randomizer randomizer;



    public Village() {
        food = 10;
        wood = 0;
        metal = 0;
        workersList = new ArrayList<>();
        buildingsList = new ArrayList<>();
        projectsList = new ArrayList<>();
        villageCapacity = 0;

        Create3Houses();
    }


    public void Create3Houses() {
        for (int i = 0; i < 3; i++) {
            Building house = new Building("House" + i, Building.BuildingType.HOUSE);
            AddFinishedHouses(house);
        }
    }

    public void AddFinishedHouses(Building building) {
        if (building.GetBuildingType() == Building.BuildingType.HOUSE) {
            villageCapacity += 2; // add 3 to village capacity for each house built
            building.IsComplete();
            building.SetDaysWorkedOnStartingHouse();
        }
        buildingsList.add(building);
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

    public void SetWorkersList(ArrayList<Worker> workersList) {
        this.workersList = workersList;
    }

    public ArrayList<Building> GetProjectsList() {
        return projectsList;
    }

    public void SetProjectList(ArrayList<Building> projectsList) {
        this.projectsList = projectsList;
    }

    public ArrayList<Building> GetBuildingsList() {
        return buildingsList;
    }

    public void SetBuildingsList(ArrayList<Building> buildingsList) {
        this.buildingsList = buildingsList;
    }

    public int GetDaysPassed() {
        return daysPassed;
    }

    public void SetDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    public int GetVillageCapacity() {
        return villageCapacity;
    }

    public void SetVillageCapacity(int villageCapacity) {
        this.villageCapacity = villageCapacity;
    }

    public void AddWorker(Worker worker) {
        if (workersList.size() >= villageCapacity) {
            System.out.println("Cannot add worker. Village has reached maximum capacity.");
            return;
        }
        workersList.add(worker);
        System.out.println("Successfully added "+ worker.GetName() + " as a "
                + worker.GetWorkerType() + ".");
    }

    public void AddProject(Building project) {
        if (wood >= project.GetWoodCost() && metal >= project.GetMetalCost()) {
            wood -= project.GetWoodCost();
            metal -= project.GetMetalCost();
            projectsList.add(project);
            System.out.println("Building a " + project.GetBuildingType() + ".");
        } else {
            System.out.println("Not enough resources to start constructing this building.");
        }
    }

    public void AddBuilding(Building building) {;
        for (Building project : projectsList) {
            if (project.GetDaysWorked() >= project.GetDaysToBuild()) {
                if (project.GetBuildingType() == Building.BuildingType.HOUSE) {
                    villageCapacity += 2; // add 3 to village capacity for each house built
                    project.IsComplete();
                    buildingsList.add(project);
                    projectsList.remove(project);
                    break;
                }
                else {
                    project.IsComplete();
                    buildingsList.add(project);
                    projectsList.remove(project);
                    break;
                }
            }
        }
    }

    public int AddWood() {
        return wood++;
    }

    public int SetWood(int wood) {
        this.wood = wood;
        return wood;
    }

    public int AddFood() {
        return food = food + 5;
    }

    public int SetFood(int food) {
        this.food = food;
        return food;
    }

    public int AddMetal() {
        return metal++;
    }

    public int SetMetal(int metal) {
        this.metal = metal;
        return metal;
    }

    public void Build() {
        if (!projectsList.isEmpty()) {
            Building project = projectsList.get(0);
            project.IncrementDaysWorkedOn();
            if (project.GetDaysWorked() >= project.GetDaysToBuild()) {
                AddBuilding(project);
                projectsList.remove(project);
                System.out.println("Finished constructing " + project.GetName() + " as a"
                        + project.GetBuildingType() + ".");
                if (project.GetBuildingType() == Building.BuildingType.CASTLE) {
                    IsGameOver();
                }
                else if (projectsList.isEmpty()){
                    System.out.println("Builders have no projects to work on.");
                }
                else {
                    System.out.println("Some projects are not yet finished.");
                    Build();
                }
            }
        }
    }

    public void BuryDead() {
        for (int i = workersList.size() - 1; i >= 0; i--) {
            Worker worker = workersList.get(i);
            if (!worker.IsAlive()) {
                workersList.remove(i);
            }
        }
    }

    public void IsGameOver() {
        this.gameOver = true;
        System.out.println("Thank you for playing village of testing!");
    }

    public void Day(){
        IncrementDaysPassed();
        FeedingWorker();
        BuryDead();
        GetResourceFromBuildings();
        CheckIfNoMoreWorkers();
    }

    public void IncrementDaysPassed(){
        this.daysPassed++;
    }

    public void FeedingWorker(){
        for (Worker worker: GetWorkersList()){
            if(food > 0 && worker.IsAlive()){
                worker.FeedWorker();
                food = food - 1;
                worker.DoWork(this);
            }
            else{
                worker.SetHungry();
                worker.IncrementDaysHungry();
            }
        }
    }

    public void CheckIfNoMoreWorkers(){
        if (this.workersList.isEmpty()) {
            System.out.println("You have no more workers.");
            IsGameOver();
        }
    }

    public void GetResourceFromBuildings(){
        for (Building building : buildingsList) {
            if (building.GetBuildingType() == Building.BuildingType.FARM) {
                for (Worker worker : workersList) {
                    if (worker.GetWorkerType() == Worker.WorkerType.FARMER) {
                        food += 10;
                    }
                }
            }
            if (building.GetBuildingType() == Building.BuildingType.WOODMILL) {

                for (Worker worker : workersList) {
                    if (worker.GetWorkerType() == Worker.WorkerType.WOODCUTTER) {
                        wood += 2;
                    }
                }
            }
            if (building.GetBuildingType() == Building.BuildingType.QUARRY) {

                for (Worker worker : workersList) {
                    if (worker.GetWorkerType() == Worker.WorkerType.MINER) {
                        metal += 2;
                    }
                }
            }
        }
    }

    public void SaveProgress(DatabaseConnection dbc) {
        dbc = new DatabaseConnection();
        int daysPassed = GetDaysPassed();
        int villageCapacity = GetVillageCapacity();
        int food = GetFood();
        int wood = GetWood();
        int metal = GetMetal();
        ArrayList<Worker> workersList = GetWorkersList();
        ArrayList<Building> projectsList = GetProjectsList();
        ArrayList<Building> buildingsList = GetBuildingsList();

        // call the Save method of DatabaseConnection to save the game data
        dbc.Save(daysPassed, villageCapacity, food, wood, metal,
                workersList, projectsList, buildingsList);
    }

    public void LoadProgress(DatabaseConnection dbc) {
        //dbc = new DatabaseConnection();
        // call the Load method of DatabaseConnection to retrieve saved values
        dbc.Load();

        // set the retrieved values to the relevant variables in the Village
        SetDaysPassed(dbc.GetDaysPassed());
        SetVillageCapacity(dbc.GetVillageCapacity());
        SetWood(dbc.GetWood());
        SetFood(dbc.GetFood());
        SetMetal(dbc.GetMetal());
        SetWorkersList(dbc.GetWorkersList());
        SetProjectList(dbc.GetProjectsList());
        SetBuildingsList(dbc.GetBuildingsList());

    }

    public Worker AddRandomWorker() {
        Randomizer randomizer = new Randomizer();
        int randNum = this.randomizer.GetRandomInt(4);
        Worker.WorkerType workerType;

        switch (randNum) {
            case 0:
                workerType = Worker.WorkerType.WOODCUTTER;
                break;
            case 1:
                workerType = Worker.WorkerType.MINER;
                break;
            case 2:
                workerType = Worker.WorkerType.FARMER;
                break;
            default:
                workerType = Worker.WorkerType.BUILDER;
                break;
        }

        Worker worker = new Worker("Random Worker", workerType);
        AddWorker(worker);
        System.out.println("Added new random worker: " + worker.GetName() + " as " + workerType);return worker;

    }

    public void SetRandomizer(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

}





