import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPanel extends JPanel implements ActionListener {



    private TextField nameField;
    private Box nameBox;
    private ComboBox nameCombo;
    private int nameIndex;

    private TextField surnameField;
    private Box surnameBox;
    private ComboBox surnameCombo;
    private int surnameIndex;

    private TextField idField;
    private Box idBox;
    private ComboBox idCombo;
    private int idIndex;

    private DateBox dateComboBox;
    private Box dateBox;
    private ComboBox dateCombo;
    private int dateIndex;

    private TextField zipCodeField;
    private Box zipCodeBox;
    private ComboBox zipCodeCombo;
    private int zipCodeIndex;

    private TextField streetField;
    private Box streetBox;
    private ComboBox streetCombo;
    private int streetIndex;

    private TextField cityField;
    private Box cityBox;
    private ComboBox cityCombo;
    private int cityIndex;

    private TextField buildingNumberField;
    private Box buildingNumberBox;
    private ComboBox buildingNumberCombo;
    private int buildingNumberIndex;

    private TextField apartmentNumberField;
    private Box apartmentNumberBox;
    private ComboBox apartmentNumberCombo;
    private int apartmentNumberIndex;

    public ClientPanel(Client client, boolean editable){

        setBorder(BorderFactory.createLineBorder(Color.black));


        nameField = new TextField("Name", client.name,editable);
        nameBox = new Box(new Label("Name"),nameField);

        surnameField = new TextField("Surname",client.surname,editable);
        surnameBox = new Box(new Label("Surname"),surnameField);

        idField = new TextField("Id", client.id,editable);
        idBox = new Box(new Label("Id"),idField);

        if (editable) {
            dateComboBox = new DateBox();
            dateBox = new Box(new Label("Birth date DD-MM-YYYY"),dateComboBox);
        }
        else
        {
            JPanel date = new JPanel();
            date.setLayout(new GridLayout(1,3));

            date.add(new TextField("Day",Integer.toString(client.birthDate.day),false),"split 3, width 50");
            date.add(new TextField("Month",Integer.toString(client.birthDate.month),false),"width 50");
            date.add(new TextField("Year",Integer.toString(client.birthDate.year),false)," width 100");

            dateBox = new Box(new Label("Birth date DD-MM-YYYY"),date);
        }

        cityField = new TextField("City",client.address.city,editable);
        cityBox = new Box(new Label("City"),cityField);

        zipCodeField = new TextField("ZIP code",client.address.zipCode,editable);
        zipCodeBox = new Box(new Label("ZIP Code"),zipCodeField);

        streetField = new TextField("Street",client.address.street,editable);
        streetBox = new Box(new Label("Street"),streetField);


        if (client.address.buildingNumber == 0 && editable)
            buildingNumberField = new TextField("No. building","No. building",true);
        else
            buildingNumberField = new TextField("No. building",Integer.toString(client.address.buildingNumber),false);

        buildingNumberBox = new Box(new Label("No. building"),buildingNumberField);


        if (client.address.apartmentNumber == 0 && editable)
            apartmentNumberField = new TextField("No. apartment","No. apartment",true);
        else
            apartmentNumberField = new TextField("No. apartment",Integer.toString(client.address.apartmentNumber),false);

        apartmentNumberBox = new Box(new Label("No. apartment"),apartmentNumberField);

    }

    public void addLayout(){
        removeAll();
        setLayout(new GridLayout(0,2));

        add(nameBox);
        add(surnameBox);

        add(idBox);
        add(dateBox);

        add(cityBox);
        add(zipCodeBox);

        add(streetBox);

        JPanel test= new JPanel();
        test.setLayout(new GridLayout(0,2));
        test.add(buildingNumberBox);
        test.add(apartmentNumberBox);
        add(test);
    }

    public void resultLayout(){
        removeAll();
        JPanel test= new JPanel();
        test.setLayout(new GridLayout(0,2));
        test.add(buildingNumberBox);
        test.add(apartmentNumberBox);

        setLayout(new GridLayout(0,4));
        add(nameBox);
        add(surnameBox);
        add(cityBox);
        add(zipCodeBox);

        add(idBox);
        add(dateBox);
        add(streetBox);
        add(test);

    }

    public void searchLayout(){
        String[] stringOptions = {"equal","begin","end","contain"};
        String[] intOptions = {"==","<","<=",">",">="};

        setLayout(new GridLayout(0,4));
        nameCombo = new ComboBox(this,stringOptions);
//        nameBox.setPreferredSize(new Dimension(150,20));
        add(new Box(new Label(""),nameCombo));
        add(nameBox);

        surnameCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),surnameCombo) );
        add(surnameBox);

        idCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),idCombo));
        add(idBox);

        dateCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),dateCombo));
        add(dateBox);

        cityCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),cityCombo));
        add(cityBox);

        zipCodeCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),zipCodeCombo));
        add(zipCodeBox);

        streetCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),streetCombo));
        add(streetBox);

        //TODO repair size
        JPanel box1 = new JPanel();
        box1.setLayout(new GridLayout(1,2));

        JPanel box2 = new JPanel();
        box2.setLayout(new GridLayout(1,2  ));
        buildingNumberCombo = new ComboBox(this,intOptions);

        box1.add(new Box(new Label(""),buildingNumberCombo));
        box1.add(buildingNumberBox);

        add(box1);

        apartmentNumberCombo = new ComboBox(this,intOptions);
        box2.add(new Box(new Label(""),apartmentNumberCombo));
        box2.add(apartmentNumberBox);
        add(box2);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ComboBox source = (ComboBox) e.getSource();

        if(source == nameCombo)
            nameIndex = source.getSelectedIndex();

        if(source == surnameCombo)
            surnameIndex = source.getSelectedIndex();

        if(source == idCombo)
            idIndex = source.getSelectedIndex();

        if(source == dateCombo)
            dateIndex = source.getSelectedIndex();

        if(source == cityCombo)
            cityIndex = source.getSelectedIndex();

        if(source == zipCodeCombo)
            zipCodeIndex = source.getSelectedIndex();

        if(source == streetCombo)
            streetIndex = source.getSelectedIndex();

        if(source == buildingNumberCombo)
            buildingNumberIndex = source.getSelectedIndex();

        if(source == apartmentNumberCombo)
            apartmentNumberIndex = source.getSelectedIndex();

    }

    public ClientCompare getCompare(){
        ClientCompare newCompareC = new ClientCompare(getClient());


        newCompareC.nameParam = nameIndex;
        newCompareC.surnameParam = surnameIndex;
        newCompareC.idParam = idIndex;
        newCompareC.birthDateParam = dateIndex;

        newCompareC.addressParam.cityParam = cityIndex;
        newCompareC.addressParam.zipCodeParam = zipCodeIndex;
        newCompareC.addressParam.streetParam = streetIndex;
        newCompareC.addressParam.buildingNumberParam = buildingNumberIndex;
        newCompareC.addressParam.apartmentNumberParam = apartmentNumberIndex;

        return newCompareC;
    }


    public void addToDatabase(Database database){
        if(validCheck(database)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to add a new client to the database?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0)
                database.addToDatabase(getClient());
        }
    }

    public Client getClient(){
        Client newC = new Client();
        newC.name = nameField.getTxt();
        newC.surname = surnameField.getTxt();
        newC.id = idField.getTxt();

        newC.birthDate = dateComboBox.getDate();

        newC.address.city = cityField.getTxt();
        newC.address.zipCode = zipCodeField.getTxt();
        newC.address.street = streetField.getTxt();
        newC.address.buildingNumber = Integer.parseInt(buildingNumberField.getTxt());
        newC.address.apartmentNumber = Integer.parseInt(apartmentNumberField.getTxt());
        return newC;

    }

    public boolean validCheck(Database database){
        boolean test = isInt(apartmentNumberField,"No. apartment")
                && isInt(buildingNumberField,"No. building");

        if (test){

            if(idField.getTxt().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Id must be fill.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (database.idCheck(idField.getTxt())) {
                JOptionPane.showMessageDialog(this, "Id must be unique. " + idField.getTxt() + " is in database", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }

        return test;

    }

    private boolean isInt(TextField field,String message){
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
