public class VehicleCompare extends ParamCompare {

    int brandParam;
    int modelParam;
    int registrationPlateParam;
    int colorParam;
    int mileageParam;
    int produceYearParam;

    private Vehicle vehicle;

    public VehicleCompare(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void copy(VehicleCompare copy){
        brandParam = copy.brandParam;
        modelParam = copy.modelParam;
        registrationPlateParam = copy.registrationPlateParam;
        colorParam = copy.colorParam;
        mileageParam = copy.mileageParam;
        produceYearParam = copy.produceYearParam;
    }

    public boolean compare(Vehicle fromDatabase){
        return stringCompare(vehicle.brand,fromDatabase.brand,brandParam)
                && stringCompare(vehicle.model,fromDatabase.model,modelParam)
                && stringCompare(vehicle.registrationPlate,fromDatabase.registrationPlate,registrationPlateParam)
                && stringCompare(vehicle.color,fromDatabase.color,colorParam)
                && intCompare(vehicle.mileage,fromDatabase.mileage,mileageParam)
                && intCompare(vehicle.produceYear,fromDatabase.produceYear,produceYearParam);
    }
}
