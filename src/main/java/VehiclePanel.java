import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehiclePanel extends JPanel implements ActionListener {

    private TextField brandField;
    private Box brandBox;
    private ComboBox brandCombo;
    private int brandIndex;

    private TextField modelField;
    private Box modelBox;
    private ComboBox modelCombo;
    private int modelIndex;

    private TextField registrationPlateField;
    private Box registrationPlateBox;
    private ComboBox registrationPlateCombo;
    private int registrationPlateIndex;

    private TextField colorField;
    private Box colorBox;
    private ComboBox colorCombo;
    private int colorIndex;

    private TextField mileageField;
    private Box mileageBox;
    private ComboBox mileageCombo;
    private int mileageIndex;

    private TextField produceYearField;
    private Box produceYearBox;
    private ComboBox produceYearCombo;
    private int produceYearIndex;
    
    
    protected String[] stringOptions = {"equal","begin","end","contain"};
    protected String[] intOptions = {"==","<","<=",">",">="};
    
    public VehiclePanel(Vehicle vehicle,boolean editable){

        setBorder(BorderFactory.createLineBorder(Color.black));

        brandField = new TextField("Brand",vehicle.brand,editable);
        brandBox = new Box(new Label("Brand"),brandField);

        modelField = new TextField("Model",vehicle.model,editable);
        modelBox = new Box(new Label("Model"),modelField);

        registrationPlateField = new TextField("Registration plate",vehicle.registrationPlate,editable);
        registrationPlateBox = new Box(new Label("Registration plate"),registrationPlateField);

        colorField = new TextField("Color",vehicle.color,editable);
        colorBox = new Box(new Label("Color"),colorField);

        if (vehicle.mileage == 0 && editable)
            mileageField = new TextField("Mileage","Mileage",true);
        else
            mileageField = new TextField("Mileage",Integer.toString(vehicle.mileage),false);

        mileageBox = new Box(new Label("Mileage"),mileageField);

        if (vehicle.produceYear == 0 && editable)
            produceYearField = new TextField("Produce year","Produce year",true);
        else
            produceYearField = new TextField("Produce year",Integer.toString(vehicle.produceYear),false);

        produceYearBox = new Box(new Label("Produce year"),produceYearField );

    }

    public void addLayout(){
        removeAll();
        setLayout(new GridLayout(0,2));
        add(brandBox);
        add(modelBox);
        add(registrationPlateBox);
        add(colorBox);
        add(mileageBox);
        add(produceYearBox);
    }

    public void resultLayout(){
        setLayout(new GridLayout(2,0));
        add(brandBox);
        add(registrationPlateBox);
        add(mileageBox);

        add(modelBox);
        add(colorBox);
        add(produceYearBox);
    }

    public Vehicle getVehicle(){

        Vehicle newVehicle = new Vehicle();

        newVehicle.registrationPlate = registrationPlateField.getTxt();
        newVehicle.brand = brandField.getTxt();
        newVehicle.model = modelField.getTxt();
        newVehicle.color = colorField.getTxt();
        newVehicle.mileage = Integer.parseInt(mileageField.getTxt());
        newVehicle.produceYear = Integer.parseInt(produceYearField.getTxt());

        return newVehicle;

    }

    public void searchLayout(){
        removeAll();
        setLayout(new GridLayout(0,4));

        brandCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),brandCombo));
        add(brandBox);

        modelCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),modelCombo));
        add(modelBox);

        registrationPlateCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),registrationPlateCombo));
        add(registrationPlateBox);

        colorCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),colorCombo));
        add(colorBox);

        mileageCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),mileageCombo));
        add(mileageBox);

        produceYearCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),produceYearCombo));
        add(produceYearBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ComboBox source = (ComboBox) e.getSource();

        if (source == brandCombo)
            brandIndex = source.getSelectedIndex();

        if (source == modelCombo)
            modelIndex = source.getSelectedIndex();

        if (source == registrationPlateCombo)
            registrationPlateIndex = source.getSelectedIndex();

        if (source == colorCombo)
            colorIndex = source.getSelectedIndex();

        if(source == mileageCombo)
            mileageIndex = source.getSelectedIndex();

        if (source == produceYearCombo)
            produceYearIndex = source.getSelectedIndex();
    }

    public VehicleCompare getCompare(){
        VehicleCompare newCompare = new VehicleCompare(getVehicle());

        newCompare.brandParam = brandIndex;
        newCompare.modelParam = modelIndex;
        newCompare.registrationPlateParam = registrationPlateIndex;
        newCompare.colorParam = colorIndex;
        newCompare.mileageParam = mileageIndex;
        newCompare.produceYearParam = produceYearIndex;

        return newCompare;
    }


    public void makeRed(Container container){

        for (Component component : container.getComponents()) {

                component.setBackground(Color.red);
                if (component instanceof TextField)
                    component.setForeground(Color.white);
                else
                    component.setForeground(Color.yellow);

            makeRed((Container) component);
            revalidate();
            repaint();
        }

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
