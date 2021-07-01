import javax.swing.*;

public class CarPanel extends VehiclePanel{

    private TextField seatsField;
    private TextField doorsField;

    public CarPanel( Car car,boolean editable){

        super(car,editable);

        add(new Label("Seats"));
        add(new Label("Doors"),"wrap");

        if (car.seats == 0 && editable)
            seatsField = new TextField("Seats","Seats",true);
        else
            seatsField = new TextField("Seats",Integer.toString(car.seats),false);

        add(seatsField,"width 200");

        if (car.doors == 0 && editable)
            doorsField = new TextField("Doors","Doors",true);
        else
            doorsField = new TextField("Doors",Integer.toString(car.doors),false);

        add(doorsField,"width 200, wrap");
    }

    protected boolean validCheck(Database database){
        return super.validCheck(database)
                && isInt(seatsField,"Seats")
                && isInt(doorsField,"Doors");
    }

    public Car getCar() {

        Car newC = new Car();
        newC.copy(super.getVehicle());

        newC.seats = Integer.parseInt(seatsField.getTxt());
        newC.doors = Integer.parseInt(doorsField.getTxt());

        return newC;

    }

    public void addToDatabase(Database database){

        if(validCheck(database)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to add a new client to the database?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0)
                database.addToDatabase(getCar());
        }
    }


}
