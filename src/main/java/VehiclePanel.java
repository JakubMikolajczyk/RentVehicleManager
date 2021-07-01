import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class VehiclePanel extends JPanel {

    private TextField brandField;
    private TextField modelField;
    private TextField registrationPlateField;
    private TextField colorField;
    private TextField mileageField;
    private TextField produceYearField;

    public VehiclePanel(Vehicle vehicle,boolean editable){
        setLayout(new MigLayout());

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(new Label("Brand"));
        add(new Label("Model"),"wrap");

        brandField = new TextField("Brand",vehicle.brand,editable);
        add(brandField,"width 200");

        modelField = new TextField("Model",vehicle.model,editable);
        add(modelField,"wrap, width 200");

        add(new Label("Registration plate"));
        add(new Label("Color"),"wrap");

        registrationPlateField = new TextField("Registration plate",vehicle.registrationPlate,editable);
        add(registrationPlateField,"width 200");

        colorField = new TextField("Color",vehicle.color,editable);
        add(colorField,"width 200, wrap");

        add(new Label("Mileage"));
        add(new Label("Produce year"),"wrap");

        if (vehicle.mileage == 0 && editable)
            mileageField = new TextField("Mileage","Mileage",true);
        else
            mileageField = new TextField("Mileage",Integer.toString(vehicle.mileage),false);

        add(mileageField, "width 200");

        if (vehicle.produceYear == 0 && editable)
            produceYearField = new TextField("Produce year","Produce year",true);
        else
            produceYearField = new TextField("Produce year",Integer.toString(vehicle.produceYear),false);
        add(produceYearField,"width 200, wrap");

    }

    public Vehicle getVehicle(){

        Vehicle newV = new Vehicle();

        newV.registrationPlate = registrationPlateField.getTxt();
        newV.brand = brandField.getTxt();
        newV.model = modelField.getTxt();
        newV.color = colorField.getTxt();
        newV.mileage = Integer.parseInt(mileageField.getTxt());
        newV.produceYear = Integer.parseInt(produceYearField.getTxt());

        return newV;

    }

    protected boolean validCheck(Database database){
        boolean test = isInt(mileageField,"Mileage")
                && isInt(produceYearField,"Produce year");


        if (test){

            if(registrationPlateField.getTxt().equals("0")) {
                JOptionPane.showMessageDialog(this, "Registry plate must be fill.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (database.registrationPlateCheck(registrationPlateField.getTxt())) {
                JOptionPane.showMessageDialog(this, "Registry plate must be unique. " + registrationPlateField.getTxt() + " is in database", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }

        return test;

    }

    protected boolean isInt(TextField field,String message){
        try {
            if (Integer.parseInt(field.getTxt()) < 0)
                throw new Exception();
            return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this,message + " must be POSITIVE INTEGER","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
