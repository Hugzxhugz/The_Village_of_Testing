package org.example;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class VillageTest {

    @Test
    public void testAdd1Worker_shouldBe1() {
        Village village = new Village();
        village.AddWorker(new Worker("James", Worker.WorkerType.FARMER));
        int expected = 1;

        int actual = village.GetWorkersList().size();
        for (Worker worker : village.GetWorkersList()) {
            System.out.println("Worker name: " + worker.GetName());
            System.out.println("Worker occupation: " + worker.GetWorkerType());
        }


        assertEquals(expected, actual);
        System.out.println("Expected:" + expected);
        System.out.println("Actual:" + actual);
        System.out.println(village.GetVillageCapacity());

    }

    @Test
    public void testAdd2Worker_shouldBe2() {
        Village village = new Village();
        village.AddWorker(new Worker("James", Worker.WorkerType.WOODCUTTER));
        village.AddWorker(new Worker("Kent", Worker.WorkerType.MINER));
        int expected = 2;

        int actual = village.GetWorkersList().size();
        for (Worker worker : village.GetWorkersList()) {
            System.out.println("Worker name: " + worker.GetName());
            System.out.println("Worker occupation: " + worker.GetWorkerType());
        }

        assertEquals(expected, actual);
        System.out.println("Expected:" + expected);
        System.out.println("Actual:" + actual);
    }

    @Test
    public void testAdd3Worker_shouldBe3() {
        Village village = new Village();
        village.AddWorker(new Worker("James", Worker.WorkerType.FARMER));
        village.AddWorker(new Worker("Kent", Worker.WorkerType.MINER));
        village.AddWorker(new Worker("Pot", Worker.WorkerType.WOODCUTTER));
        int expected = 3;

        int actual = village.GetWorkersList().size();
        for (Worker worker : village.GetWorkersList()) {
            System.out.println("Worker name: " + worker.GetName());
            System.out.println("Worker occupation: " + worker.GetWorkerType());
        }

        assertEquals(expected, actual);
        System.out.println("Expected:" + expected);
        System.out.println("Actual:" + actual);
    }

    @Test
    public void testAdd1WorkerWhenCapacityIs0_shouldBe0() {
        Village village = new Village();
        village.SetVillageCapacity(0);
        village.AddWorker(new Worker("James", Worker.WorkerType.FARMER));

        int expected = 0;

        int actual = village.GetWorkersList().size();
        for (Worker worker : village.GetWorkersList()) {
            System.out.println("Worker name: " + worker.GetName());
            System.out.println("Worker occupation: " + worker.GetWorkerType());
        }


        assertEquals(expected, actual);
        System.out.println("Expected:" + expected);
        System.out.println("Actual:" + actual);
        System.out.println(village.GetVillageCapacity());

    }


    @Test
    public void testNotEnoughVillageCapacity_shouldNotAdd() {
        Village village = new Village();
        village.GetBuildingsList().clear();
        village.SetVillageCapacity(0);
        int expected = village.GetWorkersList().size();

        village.AddWorker(new Worker("James", Worker.WorkerType.WOODCUTTER));
        int actual = village.GetWorkersList().size();



        assertEquals(expected,actual);
    }


    @Test
    public void testNotEnoughVillageCapacity_ShouldBeTrue() {
        Village village = new Village();
        village.GetBuildingsList().clear();
        village.SetVillageCapacity(0);
        int initialWorkersCount = village.GetWorkersList().size();
        boolean testPassed = false;

        village.AddWorker(new Worker("Bob", Worker.WorkerType.FARMER));
        int newWorkersCount = village.GetWorkersList().size();
        if (initialWorkersCount == newWorkersCount) {
            testPassed = true;
        }

        assertTrue(testPassed);
    }

    @Test
    public void checkVillageCapacityUponCreation_shouldReturn9(){
        Village village = new Village();
        for(Building building : village.GetBuildingsList()) {
            System.out.println(building.GetName());
        }
        System.out.println(village.GetVillageCapacity());
    }

    @Test
    public void testAddAMiner_shouldMineMetal() {
        Village village = new Village();
        village.AddWorker(new Worker("Kent", Worker.WorkerType.MINER));
        int expectedMetal = 1;

        village.Day();
        int actualMetal = village.GetMetal();

        assertEquals(expectedMetal, actualMetal);
        System.out.println("Expected:" + expectedMetal);
        System.out.println("Actual:" + actualMetal);
    }

    @Test
    public void testAddAFarmer_shouldAddFood() {
        Village village = new Village();
        village.AddWorker(new Worker("Kent", Worker.WorkerType.FARMER));
        int expectedFood = 14;

        village.Day();
        int actualFood = village.GetFood();

        assertEquals(expectedFood, actualFood);
        System.out.println("Expected:" + expectedFood);
        System.out.println("Actual:" + actualFood);
    }

    @Test
    public void testNoWorkersAfter1Day_shouldNotHaveAdditionalResource() {
        Village village = new Village();
        ArrayList<Worker> workersList = village.GetWorkersList();
        int expectedFood = 10;
        int expectedWood = 0;
        int expectedMetal = 0;

        village.Day();
        int actualFood = village.GetFood();
        int actualWood = village.GetWood();
        int actualMetal = village.GetMetal();

        assertEquals(expectedFood, actualFood);
        assertEquals(expectedWood, actualWood);
        assertEquals(expectedMetal, actualMetal);
    }

    @Test
    public void testIfFoodIsDecreasingAfter1DayWith1Worker_foodShouldBe9() {
        Village village = new Village();
        village.SetVillageCapacity(10);
        System.out.println("Starting food is: " + village.GetFood());
        Worker worker = new Worker("John", Worker.WorkerType.WOODCUTTER);
        village.AddWorker(worker);
        int expected = 9;

        village.Day();
        int actual = village.GetFood();

        assertEquals(expected, actual);

    }

    @Test
    public void testWoodIfIncreasedAfter1DayWith1Woodcutter_woodShouldIncreaseBy1() {
        Village village = new Village();
        village.SetVillageCapacity(10);
        System.out.println("Starting wood is: " + village.GetWood());
        Worker worker = new Worker("John", Worker.WorkerType.WOODCUTTER);
        village.AddWorker(worker);
        int expected = 1;

        village.Day();
        int actual = village.GetWood();
        System.out.println("After 1 day wood should be: " + expected);

        assertEquals(expected, actual);
        System.out.println("Actual wood is: " + village.GetWood());

    }

    @Test
    public void testStartWith10FoodAdd3Workers1IsAFarmer_After1Day3FoodShouldBeConsumedAnd5FoodIsFarmed() {
        Village village = new Village();
        village.SetFood(10);
        System.out.println("Starting food is: " + village.GetFood());

        Worker worker1 = new Worker("John", Worker.WorkerType.WOODCUTTER);
        Worker worker2 = new Worker("Jane", Worker.WorkerType.FARMER);
        Worker worker3 = new Worker("Bob", Worker.WorkerType.MINER);
        village.AddWorker(worker1);
        village.AddWorker(worker2);
        village.AddWorker(worker3);

        int expected = 12;
        village.Day();
        int actual = village.GetFood();
        System.out.println("After 1 day food should be: " + expected);

        assertEquals(expected, actual);
        System.out.println("Actual food is: " + village.GetFood());
    }

    @Test
    public void testStartWithNoFoodAdd1Worker_After1DayFoodShouldBeInsufficient() {
        Village village = new Village();
        village.SetFood(0);
        System.out.println("Starting food is: " + village.GetFood());
        Worker worker1 = new Worker("John", Worker.WorkerType.WOODCUTTER);
        village.AddWorker(worker1);
        boolean expected = true;

        village.Day();
        boolean actual = worker1.IsHungry();
        System.out.println("After 1 day, food should be insufficient.");

        assertEquals(expected,actual);
        System.out.println("Worker is hungry: " + worker1.IsHungry());
    }

    @Test
    public void testStartWithNoFoodAdd1Worker_After1DayFoodShouldBeInsufficientAndWorkerIsHungry() {
        Village village = new Village();
        village.SetFood(0);
        System.out.println("Starting food is: " + village.GetFood());
        Worker worker1 = new Worker("John", Worker.WorkerType.WOODCUTTER);
        village.AddWorker(worker1);

        village.Day();
        boolean actual = worker1.IsHungry();
        System.out.println("After 1 day, worker should be hungry.");

        assertTrue(actual);
        System.out.println("Worker is hungry: " + worker1.IsHungry());
    }

    @Test
    public void TestaddProjectWithNoResource_shouldReturnFalse() {
        Village village = new Village();
        Building quarry = new Building("Quarry", Building.BuildingType.QUARRY);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());

        village.AddProject(quarry);

        assertFalse(village.GetProjectsList().contains(quarry));

    }

    @Test
    public void TestaddProjectWithSomeResource_shouldReturnFalse() {
        Village village = new Village();
        Building quarry = new Building("Quarry", Building.BuildingType.QUARRY);
        village.SetWood(3);
        village.SetMetal(3);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());

        village.AddProject(quarry);

        assertFalse(village.GetProjectsList().contains(quarry));
    }

    @Test
    public void TestaddProjectWithEnoughResource_shouldReturnTrue() {
        Village village = new Village();
        Building quarry = new Building("Quarry", Building.BuildingType.QUARRY);
        village.SetWood(5);
        village.SetMetal(5);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());

        village.AddProject(quarry);

        assertTrue(village.GetProjectsList().contains(quarry));
    }

    @Test
    public void TestaddProjectAndCheckResourcesAfterWoodmillIsDone_shouldCountainWoodmillAndAddDesignatedResource() {
        Village village = new Village();
       Building woodmill = new Building("Woodmill", Building.BuildingType.WOODMILL);
        Worker builder = new Worker("James", Worker.WorkerType.BUILDER);
        village.AddWorker(builder);
        Worker woodcutter = new Worker("John", Worker.WorkerType.WOODCUTTER);
        village.AddWorker(woodcutter);
        int expectedWood = 10;

        village.SetFood(100);
        village.SetWood(5);
        village.SetMetal(10);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(woodmill);

        for (int i = 1; i <= 6; i++) {
            System.out.println("");
            village.Day();

        }
        int actualWood = village.GetWood();

        assertTrue(village.GetBuildingsList().contains(woodmill));
        System.out.println("If pass, building list containts the new woodmill.");

        assertEquals(expectedWood,actualWood);

    }

    @Test
    public void TestaddProjectWithEnoughResource_shouldReturnExpectedAmountOfResources() {
        Village village = new Village();
        Building quarry = new Building("Quarry", Building.BuildingType.QUARRY);
        Building farm = new Building("house", Building.BuildingType.FARM);
        Worker builder = new Worker("James", Worker.WorkerType.BUILDER);
        village.AddWorker(builder);
        Worker miner = new Worker("John", Worker.WorkerType.MINER);
        village.AddWorker(miner);
        Worker farmer = new Worker("Josh", Worker.WorkerType.FARMER);
        village.AddWorker(farmer);
        village.SetFood(100);
        village.SetWood(10);
        village.SetMetal(10);
        int expectedFood = 156;
        int expectedWood = 2;
        int expectedMetal = 30;

        village.AddProject(quarry);
        village.AddProject(farm);
        for (int i = 1; i <= 12; i++) {
            System.out.println("");
            village.Day();

        }

        village.Day();
        int actualFood = village.GetFood();
        int actualWood = village.GetWood();
        int actualMetal = village.GetMetal();

        assertTrue(village.GetBuildingsList().contains(quarry));
        assertTrue(village.GetBuildingsList().contains(farm));
        assertEquals(expectedFood,actualFood);
        assertEquals(expectedWood,actualWood);
        assertEquals(expectedMetal,actualMetal);

    }

    @Test
    public void TestaddProjectWithEnoughResource_shouldReturnTrueAfterEnoughDays() {
        Village village = new Village();
        Building woodmill = new Building("Woodmill", Building.BuildingType.WOODMILL);
        Building house = new Building("house", Building.BuildingType.HOUSE);
        Worker builder = new Worker("James", Worker.WorkerType.BUILDER);
        village.AddWorker(builder);
        Worker woodcutter = new Worker("John", Worker.WorkerType.WOODCUTTER);
        village.AddWorker(woodcutter);

        village.SetFood(100);
        village.SetWood(10);
        village.SetMetal(10);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(woodmill);
        village.AddProject(house);

        for (int i = 1; i <= 12; i++) {
            System.out.println("");
            village.Day();

        }

        assertTrue(village.GetBuildingsList().contains(woodmill));
        System.out.println("Building list containts the new woodmill.");

        assertTrue(village.GetBuildingsList().contains(house));
        System.out.println("Building list containts the new house.");

    }

    @Test
    public void testWorkerDoesNotWorkIfNotFed_shouldReturnHungerAndWorkerDidNotWork() {
        Village village = new Village();
        Worker worker = new Worker("John", Worker.WorkerType.MINER);
        village.SetFood(0);
        village.AddWorker(worker);
        int expected = 0;
        village.Day();
        int actual = village.GetMetal();;
        assertTrue(worker.IsHungry());
        assertEquals(expected,actual);
    }

    @Test
    public void testWorkerIfNotFedAfter40Days_shouldReturnFalse() {
        Village village = new Village();
        Worker worker = new Worker("John", Worker.WorkerType.MINER);
        village.SetFood(0);
        village.AddWorker(worker);

        for (int i = 1; i <= 40; i++) {
            System.out.println("");
            village.Day();
        }

        assertFalse(worker.IsAlive());

    }

    @Test
    public void testCannotFeedDeadWorker_shouldReturnNoChangesOnFood() {
        Village village = new Village();
        Worker worker = new Worker("John", Worker.WorkerType.BUILDER);
        village.AddWorker(worker);
        worker.IsDead();
        int expected = 1;

        village.SetFood(1);
        village.Day();
        int actual = village.GetFood();

        assertEquals(expected, actual);

    }

    @Test
    public void testCannotFeedDeadWorker_shouldReturnNoChangesOnFoodsAndNoMoreWorkerInWorkerList() {
        Village village = new Village();
        Worker worker = new Worker("John", Worker.WorkerType.BUILDER);
        village.AddWorker(worker);
        village.SetFood(1);
        worker.IsDead();
        int expectedFood = 1;


        village.BuryDead();
        int actualFood = village.GetFood();

        assertFalse(village.GetWorkersList().contains(worker));
        assertEquals(expectedFood,actualFood);

    }

    @Test
    public void TestAddHouseWithEnoughResourceAnd1Builder_should3Days() {
        Village village = new Village();
        Building house = new Building("House", Building.BuildingType.HOUSE);
        Worker builder = new Worker("James", Worker.WorkerType.BUILDER);
        village.AddWorker(builder);
        int expectedDays= 3;

        village.SetFood(100);
        village.SetWood(50);
        village.SetMetal(50);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(house);

        for (int i = 1; i <= 3; i++) {

            village.Day();

        }

        int actual = village.GetDaysPassed();

        assertTrue(village.GetBuildingsList().contains(house));
        assertEquals(expectedDays,actual);

    }

    @Test
    public void TestAddHouseWithEnoughResourceAnd1Builder_shouldBe1Day() {
        Village village = new Village();
        Building house = new Building("House", Building.BuildingType.HOUSE);
        Worker builder1 = new Worker("James", Worker.WorkerType.BUILDER);
        village.AddWorker(builder1);
        Worker builder2 = new Worker("John", Worker.WorkerType.BUILDER);
        village.AddWorker(builder2);
        Worker builder3 = new Worker("Josh", Worker.WorkerType.BUILDER);
        village.AddWorker(builder3);
        int expectedDays= 1;

        village.SetFood(100);
        village.SetWood(50);
        village.SetMetal(50);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(house);

        village.Day();

        int actual = village.GetDaysPassed();

        assertTrue(village.GetBuildingsList().contains(house));
        assertEquals(expectedDays,actual);

    }

    @Test
    public void TestAddCastleWithEnoughResourceAnd1Builder_should50Days() {
        Village village = new Village();
        Building castle = new Building("Woodmill", Building.BuildingType.CASTLE);
        Worker builder = new Worker("James", Worker.WorkerType.BUILDER);
        village.AddWorker(builder);
        int expectedDays= 50;

        village.SetFood(100);
        village.SetWood(50);
        village.SetMetal(50);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(castle);

        for (int i = 1; i <= 50; i++) {

            village.Day();

        }

        int actual = village.GetDaysPassed();

        assertTrue(village.GetBuildingsList().contains(castle));

        assertEquals(expectedDays,actual);

    }

    @Test
    public void TestAddCastleWithEnoughResourceAnd2Builder_should25Days() {
        Village village = new Village();
        Building castle = new Building("Woodmill", Building.BuildingType.CASTLE);
        Worker builder1 = new Worker("James", Worker.WorkerType.BUILDER);
        Worker builder2 = new Worker("John", Worker.WorkerType.BUILDER);
        village.AddWorker(builder1);
        village.AddWorker(builder2);
        int expectedDays= 25;

        village.SetFood(100);
        village.SetWood(50);
        village.SetMetal(50);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(castle);

        for (int i = 1; i <= 25; i++) {

            village.Day();

        }

        int actual = village.GetDaysPassed();

        assertTrue(village.GetBuildingsList().contains(castle));

        assertEquals(expectedDays,actual);

    }

    @Test
    public void TestAddCastleWithEnoughResourceAnd4Builder_should13Days() {
        Village village = new Village();
        Building castle = new Building("Woodmill", Building.BuildingType.CASTLE);
        Worker builder1 = new Worker("James", Worker.WorkerType.BUILDER);
        Worker builder2 = new Worker("John", Worker.WorkerType.BUILDER);
        Worker builder3 = new Worker("Jameson", Worker.WorkerType.BUILDER);
        Worker builder4 = new Worker("Johnson", Worker.WorkerType.BUILDER);
        village.AddWorker(builder1);
        village.AddWorker(builder2);
        village.AddWorker(builder3);
        village.AddWorker(builder4);
        int expectedDays= 13;

        village.SetFood(100);
        village.SetWood(50);
        village.SetMetal(50);
        System.out.println("Wood: " + village.GetWood());
        System.out.println("Metal: " + village.GetMetal());
        village.AddProject(castle);

        for (int i = 1; i <= 13; i++) {
            village.Day();
        }

        int actual = village.GetDaysPassed();

        assertTrue(village.GetBuildingsList().contains(castle));
        assertEquals(expectedDays,actual);

    }

    @Test
    public void testLoadProgress() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);
        Village village = new Village();
        int expectedWood = 50;
        int expectedFood = 100;
        int expectedMetal = 20;
        int expectedDaysPassed = 10;

        String mockFileName = "mock_game_data.json";


        when(mockDbConnection.SetFilePath()).thenReturn(mockFileName);
        when(mockDbConnection.GetVillageCapacity()).thenReturn(9);
        when(mockDbConnection.GetDaysPassed()).thenReturn(10);
        when(mockDbConnection.GetWood()).thenReturn(50);
        when(mockDbConnection.GetFood()).thenReturn(100);
        when(mockDbConnection.GetMetal()).thenReturn(20);
        when(mockDbConnection.GetWorkersList()).thenReturn(new ArrayList<>());
        when(mockDbConnection.GetProjectsList()).thenReturn(new ArrayList<>());
        when(mockDbConnection.GetBuildingsList()).thenReturn(new ArrayList<>());


        village.LoadProgress(mockDbConnection);
        int actualDaysPassed = village.GetDaysPassed();
        int actualWood = village.GetWood();
        int actualFood = village.GetFood();
        int actualMetal = village.GetMetal();

        assertEquals(expectedWood, actualWood);
        assertEquals(expectedFood, actualFood);
        assertEquals(expectedMetal, actualMetal);
        assertEquals(new ArrayList<>(), village.GetWorkersList());
        assertEquals(new ArrayList<>(), village.GetProjectsList());
        assertEquals(new ArrayList<>(), village.GetBuildingsList());
        assertEquals(expectedDaysPassed, actualDaysPassed);

    }

    @Test
    public void testAddRandomWorker_shouldAddRandomWorkerToWorkersList() {
        Randomizer randomizer = new Randomizer();
        Village village = new Village();
        int expectedNumberOfWorker = 1;
        assertTrue(village.GetWorkersList().isEmpty());

        village.SetRandomizer(randomizer);
        Worker randomWorker = village.AddRandomWorker();
        int actualNumberOfWorker = village.GetWorkersList().size();

        assertEquals(expectedNumberOfWorker, actualNumberOfWorker);
        assertTrue(village.GetWorkersList().contains(randomWorker));

    }

    @Test
    public void testAddRandomWorkerWhileMockingRandomizer_shouldReturnFarmerForAllWorkers() {
        Randomizer mockRandomizer = mock(Randomizer.class);
        when(mockRandomizer.GetRandomInt(4)).thenReturn(2);
        var expectedIsFarmer = Worker.WorkerType.FARMER;


        Village village = new Village();
        System.out.println("Starting Food: " + village.GetFood());
        System.out.println("Starting Wood: " + village.GetWood());
        System.out.println("Starting Metal: " + village.GetMetal());
        village.SetRandomizer(mockRandomizer);


        Worker worker1 = village.AddRandomWorker();
        Worker worker2 = village.AddRandomWorker();
        Worker worker3 = village.AddRandomWorker();

        var actual1 = worker1.GetWorkerType();
        var actual2 = worker2.GetWorkerType();
        var actual3 = worker3.GetWorkerType();

        assertEquals(expectedIsFarmer, actual1);
        assertEquals(expectedIsFarmer, actual2);
        assertEquals(expectedIsFarmer, actual3);

        System.out.println("Workers in workerslist:");
        for (Worker worker : village.GetWorkersList()){
            System.out.println(worker.GetName()+ ": " + worker.GetWorkerType());
        }

        village.Day();
        int expectedFood = 22;
        int actualFood = village.GetFood();

        assertEquals(expectedFood,actualFood);
        System.out.println("ACTUAL FOOD: " + village.GetFood());
    }

    @Test
    public void testAddRandomWorkerWhileMockingRandomizer_shouldReturnMinerForAllWorkers() {

        Randomizer mockRandomizer = mock(Randomizer.class);
        when(mockRandomizer.GetRandomInt(4)).thenReturn(1);
        var expectedIsMiner = Worker.WorkerType.MINER;

        Village village = new Village();
        village.SetRandomizer(mockRandomizer);

        for (int i = 0; i < 3; i++) {
            village.AddRandomWorker();
        }

        for (Worker worker : village.GetWorkersList()) {
            assertEquals(expectedIsMiner, worker.GetWorkerType());
        }

        village.Day();

        int expectedFood = 7;
        int actualFood = village.GetFood();
        assertEquals(expectedFood, actualFood);
    }


    @Test
    public void testAddQuarryWith2Miners(){
        Village village = new Village();
        village.SetFood(100);
        village.SetWood(3);
        village.SetMetal(5);

        Worker worker1 = new Worker("james", Worker.WorkerType.BUILDER);
        Worker worker2 = new Worker("jan", Worker.WorkerType.MINER);
        Worker worker3 = new Worker("john", Worker.WorkerType.MINER);
        village.AddWorker(worker1);
        village.AddWorker(worker2);
        village.AddWorker(worker3);
        Building quarry = new Building("quarry", Building.BuildingType.QUARRY);
        village.AddProject(quarry);
        int expected = 18;

        for (int i = 0; i < 7; i++) {
            village.Day();
        }
        int actual = village.GetMetal();

        assertTrue(village.GetBuildingsList().contains(quarry));
        assertEquals(expected,actual);
    }


}

