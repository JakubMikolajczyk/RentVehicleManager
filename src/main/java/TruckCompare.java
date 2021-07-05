public class TruckCompare extends VehicleCompare{

    int capacityParam;
    int maxWeightParam;
    private Truck truck;

    public TruckCompare(Truck truck) {
        super(truck);
        this.truck = truck;
    }


    public boolean compare(Truck fromDatabase) {
        return super.compare(fromDatabase)
                && intCompare(truck.capacity,fromDatabase.capacity,capacityParam)
                && intCompare(truck.maxWeight,fromDatabase.maxWeight,maxWeightParam);
    }
}

