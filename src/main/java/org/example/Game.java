package org.example;

import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    private Village village;
    private DatabaseConnection dbc;



    public Game(){
        village = new Village();
    }

    public void StartGame(){
        System.out.println("Welcome to the Village game!");
        System.out.println("Press 1 to start new game");
        System.out.println("Press 2 to continue old game");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                // start a new game
                village = new Village();
                ShowStartingPoint();
                MainMenu();
                break;
            case 2:
                dbc = new DatabaseConnection();
                village.LoadProgress(dbc);
                DisplayStatus();
                MainMenu();
                break;
            default:
                System.out.println("Invalid input. Please choose 1 or 2.");
        }
    }

    public void ShowStartingPoint() {
        System.out.println("You are in the starting point of the game.");
        System.out.println("You have 3 houses.");
        System.out.println("Current Resources:");
        System.out.println("- Food: " + village.GetFood());
        System.out.println("- Wood: " + village.GetWood());
        System.out.println("- Metal: " + village.GetMetal());
        System.out.println("Village Capacity: " + village.GetVillageCapacity());
    }

    public void MainMenu() {
        boolean stillPlaying = true;

        while (stillPlaying) {
            System.out.println("\nChoose an option:\n1. Add a worker\n2. Add a project" +
                    "\n3. Simulate a day\n4. Save and continue game\n5. Save and Quit game");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    AddWorker();
                    stillPlaying = true;
                    break;
                case "2":
                    AddProject();
                    stillPlaying = true;
                    break;
                case "3":
                    SimulateDay();
                    stillPlaying = true;
                    break;
                case "4":
                    stillPlaying = true;
                    dbc = new DatabaseConnection();
                    village.SaveProgress(dbc);
                    MainMenu();
                case "5":
                    dbc = new DatabaseConnection();
                    village.SaveProgress(dbc);
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please choose 1, 2, 3, 4 or 5.");
            }
        }
    }

    private void AddWorker() {
        String name;
        System.out.println("\nChoose a worker type:\n1. Builder\n2. Farmer\n3. Woodcutter\n4. Miner");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("What name would you like your builder to have?");
                name = scanner.nextLine();
                village.AddWorker(new Worker(name, Worker.WorkerType.BUILDER));
                break;
            case "2":
                System.out.println("What name would you like your farmer to have?");
                name = scanner.nextLine();
                village.AddWorker(new Worker(name, Worker.WorkerType.FARMER));
                break;
            case "3":
                System.out.println("What name would you like your woodcutter to have?");
                name = scanner.nextLine();
                village.AddWorker(new Worker(name, Worker.WorkerType.WOODCUTTER));
                break;
            case "4":
                System.out.println("What name would you like your miner to have?");
                name = scanner.nextLine();
                village.AddWorker(new Worker(name, Worker.WorkerType.MINER));
                break;
            default:
                System.out.println("Invalid input. Please choose 1, 2, 3, or 4.");
        }
    }

    private void AddProject() {
        String projectName;
        System.out.println("\nChoose a project type:\n1. Build a house\n2. Build a farm" +
                "\n3. Build a woodmill\n4. Build a quarry\n5. Build a castle");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("What name would you like your house to have?");
                projectName = scanner.nextLine();
                village.AddProject(new Building(projectName, Building.BuildingType.HOUSE));
                break;
            case "2":
                System.out.println("What name would you like your farm to have?");
                projectName = scanner.nextLine();
                village.AddProject(new Building(projectName, Building.BuildingType.FARM));
                break;
            case "3":
                System.out.println("What name would you like your woodmill to have?");
                projectName = scanner.nextLine();
                village.AddProject(new Building(projectName, Building.BuildingType.WOODMILL));
                break;
            case "4":
                System.out.println("What name would you like your quarry to have?");
                projectName = scanner.nextLine();
                village.AddProject(new Building(projectName, Building.BuildingType.QUARRY));
                break;
            case "5":
                village.AddProject(new Building("Castle", Building.BuildingType.CASTLE));
                break;
            default:
                System.out.println("Invalid input. Please choose 1, 2, 3, 4 or 5.");
        }
    }

    private void SimulateDay() {
        village.Day();
        DisplayStatus();
    }

    public void DisplayStatus() {
        System.out.println("\nDays Passed: " + village.GetDaysPassed());
        System.out.println("Village Capacity: " + village.GetVillageCapacity());
        System.out.println("Number of Workers: " + village.GetWorkersList().size());
        System.out.println("Workers: ");
        for (Worker worker : village.GetWorkersList()) {
            System.out.println("- " + worker.GetName() + " (" + worker.GetWorkerType() + ")");
        }
        System.out.println("Number of Projects: " + village.GetProjectsList().size());
        System.out.println("On going projects: ");
        for (Building project : village.GetProjectsList()) {
            System.out.println("- " + project.GetName()+ " - " +  project.GetBuildingType() +
                    " (" +
                    (project.GetDaysToBuild() - project.GetDaysWorked()  ) + " days left to finish)");
        }
        System.out.println("Number of Finished Buildings: " + village.GetBuildingsList().size());
        System.out.println("Finished Buildings: ");
        for (Building building : village.GetBuildingsList()) {
            System.out.println("- " + building.GetName()+ " - " + building.GetBuildingType());
        }
        System.out.println("Current Resources:");
        System.out.println("- Food: " + village.GetFood());
        System.out.println("- Wood: " + village.GetWood());
        System.out.println("- Metal: " + village.GetMetal());

    }

}
