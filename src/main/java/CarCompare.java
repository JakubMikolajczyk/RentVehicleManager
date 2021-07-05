public class CarCompare extends VehicleCompare{

    int seatsParam;
    int doorsParam;

    private Car car;

    public CarCompare(Car car) {
        super(car);
        this.car = car;
    }

    public boolean compare(Car fromDatabase) {
        return super.compare(fromDatabase)
                && intCompare(car.seats,fromDatabase.seats,seatsParam)
                && intCompare(car.doors,fromDatabase.doors,doorsParam);
    }
}
