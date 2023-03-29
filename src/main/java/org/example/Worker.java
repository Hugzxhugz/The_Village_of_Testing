package org.example;


public class Worker {

    public enum WorkerType {
        WOODCUTTER,
        MINER,
        FARMER,
        BUILDER
    }
    private String name;
    private WorkerType workerType;
    private int daysHungry;
    private boolean hungry;
    private boolean alive;
    public interface WorkerFunction {
        void DoWork(Village x);
    }
    private WorkerFunction workerFunction;


    public Worker(String name, WorkerType workerType) {
        this.name = name;
        this.workerType = workerType;
        this.daysHungry = 0;
        this.hungry = false;
        this.alive = true;
        this.workerFunction = workerFunction;
    }

    public String GetName() {
        return this.name;
    }

    public WorkerType GetWorkerType() {
        return this.workerType;
    }

    public void IncrementDaysHungry() {
        this.daysHungry++;
        if (this.daysHungry >= 40) {
            IsDead();
        }
    }

    public boolean IsHungry() {
        return this.hungry;
    }

    public void SetHungry() {
        this.hungry = true;
    }

    public int GetDaysHungry(){
        return this.daysHungry;
    }

    public void SetDaysHungry(int daysHungry) {
        this.daysHungry = daysHungry;
    }

    public boolean IsAlive() {
        return this.alive;
    }

    public boolean IsDead(){
        return this.alive = false;
    }

    public void FeedWorker() {
        this.hungry = false;
        this.daysHungry = 0;
    }

    public void DoWork(Village village) {
        if(!IsHungry()){
            WorkerFunction workerFunction = WorkerFunction(GetWorkerType());
            workerFunction.DoWork(village);
        }
    }

    public static WorkerFunction WorkerFunction(WorkerType workerType) {
        switch (workerType) {
            case WOODCUTTER:
                return (Village x) -> x.AddWood();
            case MINER:
                return (Village x) -> x.AddMetal();
            case FARMER:
                return (Village x) -> x.AddFood();
            case BUILDER:
                return (Village x) -> x.Build();
            default:
                return null;
        }
    }
}

