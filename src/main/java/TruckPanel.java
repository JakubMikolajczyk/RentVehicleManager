import javax.swing.*;

public class TruckPanel extends VehiclePanel{

    private TextField capacityField;
    private TextField maxWeightField;
    public TruckPanel( Truck truck, boolean editable){

        super(truck,editable);
        add(new Label("Capacity"));
        add(new Label("Max weight"),"wrap");

        if (truck.capacity == 0 && editable)
            capacityField = new TextField("Capacity","Capacity",true);
        else
            capacityField = new TextField("Capacity",Integer.toString(truck.capacity),false);

        add(capacityField,"width 200");

        if (truck.maxWeight == 0 && editable)
             maxWeightField = new TextField("Max weight","Max weight",true);
        else
            maxWeightField = new TextField("Max weight",Integer.toString(truck.maxWeight),false);
        add(maxWeightField,"width 200, wrap");
    }

    protected boolean validCheck(Database database){
        return super.validCheck(database)
                && isInt(capacityField,"Capacity")
                && isInt(maxWeightField,"Max weight");
    }


    public Truck getTruck() {

        Truck newT = new Truck();
        newT.copy(super.getVehicle());

        newT.capacity = Integer.parseInt(capacityField.getTxt());
        newT.maxWeight = Integer.parseInt(maxWeightField.getTxt());

        return newT;

    }

    public void addToDatabase(Database database){

        if(validCheck(database)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to add a new client to the database?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0)
                database.addToDatabase(getTruck());
        }
    }


}
