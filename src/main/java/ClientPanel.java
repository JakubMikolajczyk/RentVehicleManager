import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel {



    private TextField nameField;
    private TextField surnameField;
    private TextField idField;
    private TextField zipCodeField;
    private TextField streetField;
    private TextField cityField;
    private TextField buildingNumberField;
    private TextField apartmentNumberField;
    private DateBox dateComboBox;

    protected Box nameBox;
    protected Box surnameBox;
    protected Box idBox;
    protected Box zipCodeBox;
    protected Box streetBox;
    protected Box cityBox;
    protected Box buildingNumberBox;
    protected Box apartmentNumberBox;
    protected Box dateBox;

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

    public void addClientLayout(){
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

    private boolean validCheck(Database database){
        boolean test = isInt(apartmentNumberField,"No. apartment")
                && isInt(buildingNumberField,"No. building");

        if (test){

            if(idField.getTxt().equals("0")) {
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
