import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CarPanel extends VehiclePanel{

    private TextField seatsField;
    private Box seatsBox;
    private ComboBox seatsCombo;
    private int seatsIndex;

    private TextField doorsField;
    private Box doorsBox;
    private ComboBox doorsCombo;
    private int doorsIndex;

    public CarPanel( Car car,boolean editable){

        super(car,editable);

        if (car.seats == 0 && editable)
            seatsField = new TextField("Seats","Seats",true);
        else
            seatsField = new TextField("Seats",Integer.toString(car.seats),false);
        seatsBox = new Box(new Label("Seats"),seatsField);

        if (car.doors == 0 && editable)
            doorsField = new TextField("Doors","Doors",true);
        else
            doorsField = new TextField("Doors",Integer.toString(car.doors),false);
        doorsBox = new Box(new Label("Doors"),doorsField);

    }

    @Override
    public void addLayout() {
        super.addLayout();
        add(seatsBox);
        add(doorsBox);
    }

    @Override
    public void resultLayout() {
        setLayout(new MigLayout("wrap 4","[150][150][150][150]"));

        add(brandBox);
        add(registrationPlateBox);
        add(mileageBox);
        add(seatsBox);

        add(modelBox);
        add(colorBox);
        add(produceYearBox);
        add(doorsBox);
    }

    @Override
    public void searchLayout(){
        super.searchLayout();

        seatsCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),seatsCombo));
        add(seatsBox);

        doorsCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),doorsCombo));
        add(doorsBox);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);

        ComboBox source = (ComboBox) e.getSource();

        if (source == seatsCombo)
            seatsIndex = source.getSelectedIndex();

        if (source == doorsCombo)
            doorsIndex = source.getSelectedIndex();
    }

    public CarCompare getCompare(){
        CarCompare newCompare = new CarCompare(getVehicle());
        newCompare.copy(super.getCompare());

        newCompare.seatsParam = seatsIndex;
        newCompare.doorsParam = doorsIndex;

        return newCompare;
    }

    public boolean intCheck(){
        return super.intCheck()
                && isInt(seatsField,"Seats")
                && isInt(doorsField,"Doors");
    }

    protected boolean validCheck(Database database){
        return super.validCheck(database)
                && intCheck();
    }

    public Car getVehicle() {

        Car newCar = new Car();
        newCar.copy(super.getVehicle());

        newCar.seats = Integer.parseInt(seatsField.getTxt());
        newCar.doors = Integer.parseInt(doorsField.getTxt());

        return newCar;

    }

    public void addToDatabase(Database database){

        if(validCheck(database)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to add a new client to the database?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0)
                database.addToDatabase(getVehicle());
        }
    }


}
