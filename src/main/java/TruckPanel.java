import javax.swing.*;
import java.awt.event.ActionEvent;

public class TruckPanel extends VehiclePanel{

    private TextField capacityField;
    private Box capacityBox;
    private ComboBox capacityCombo;
    private int capacityIndex;

    private TextField maxWeightField;
    private Box maxWeightBox;
    private ComboBox maxWeightCombo;
    private int maxWeightIndex;

    public TruckPanel( Truck truck, boolean editable){

        super(truck,editable);

        if (truck.capacity == 0 && editable)
            capacityField = new TextField("Capacity","Capacity",true);
        else
            capacityField = new TextField("Capacity",Integer.toString(truck.capacity),false);
        capacityBox = new Box(new Label("Capacity"),capacityField);


        if (truck.maxWeight == 0 && editable)
             maxWeightField = new TextField("Max weight","Max weight",true);
        else
            maxWeightField = new TextField("Max weight",Integer.toString(truck.maxWeight),false);
        maxWeightBox = new Box(new Label("Max weight"),maxWeightField);

    }

    @Override
    public void addLayout() {
        super.addLayout();
        add(capacityBox);
        add(maxWeightBox);
    }

    @Override
    public void resultLayout() {
        super.resultLayout();
        add(capacityBox,0,3);
        add(maxWeightBox);
    }

    protected boolean validCheck(Database database){
        return super.validCheck(database)
                && isInt(capacityField,"Capacity")
                && isInt(maxWeightField,"Max weight");
    }
    @Override
    public void searchLayout(){

        super.searchLayout();

        capacityCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),capacityCombo));
        add(capacityBox);

        maxWeightCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),maxWeightCombo));
        add(maxWeightBox);

    }

    public Truck getVehicle() {

        Truck newTruck = new Truck();
        newTruck.copy(super.getVehicle());

        newTruck.capacity = Integer.parseInt(capacityField.getTxt());
        newTruck.maxWeight = Integer.parseInt(maxWeightField.getTxt());

        return newTruck;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        ComboBox source = (ComboBox) e.getSource();

        if (source == capacityCombo)
            capacityIndex = source.getSelectedIndex();

        if (source == maxWeightCombo)
            maxWeightIndex = source.getSelectedIndex();

    }

    @Override
    public TruckCompare getCompare(){
        TruckCompare newCompare = new TruckCompare(getVehicle());
        newCompare.copy(super.getCompare());

        newCompare.capacityParam = capacityIndex;
        newCompare.maxWeightParam = maxWeightIndex;

        return newCompare;
    }

    public void addToDatabase(Database database){

        if(validCheck(database)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to add a new client to the database?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0)
                database.addToDatabase(getVehicle());
        }
    }

}
