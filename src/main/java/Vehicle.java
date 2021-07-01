public class Vehicle {

        String registrationPlate = "Registration plate";
        String brand = "Brand";
        String model = "Model";
        String color = "Color";
        int mileage = 0;
        int produceYear = 0;

        public void copy(Vehicle copy){
                registrationPlate = copy.registrationPlate;
                brand = copy.brand;
                model = copy.model;
                color = copy.color;
                mileage = copy.mileage;
                produceYear = copy.produceYear;
        }
}
