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

    private DateBox dateBox;

    private JPanel top;
    private JPanel down;

    public ClientPanel(Client client, boolean editable){

        top = new JPanel();
        top.setLayout(new MigLayout());


        String height = "height 25, ";

        top.add(new Label("Name"),height + "width 200");
        top.add(new Label("Surname"),height + "wrap, " + "width 200");

        nameField = new TextField("Name", client.name,editable);
        surnameField = new TextField("Surname",client.surname,editable);
        top.add(nameField,height + "width 200");
        top.add(surnameField,height + "wrap, width 200");

        top.add(new Label("Id"),height);
        top.add(new Label("Birth date DD-MM-YYYY"),height + "wrap");

        idField = new TextField("Id", client.id,editable);
        top.add(idField,height+ "width 200");

        if (editable) {
            dateBox = new DateBox();
            top.add(dateBox, height + "width 200");
        }
        else
        {
            top.add(new TextField("Day",Integer.toString(client.birthDate.day),false),"split 3, width 50");
            top.add(new TextField("Month",Integer.toString(client.birthDate.month),false),"width 50");
            top.add(new TextField("Year",Integer.toString(client.birthDate.year),false)," width 100");
        }


        down = new JPanel();
        down.setLayout(new MigLayout());

        down.add(new Label("City"),height);
        down.add(new Label("ZIP code"),height + "wrap");

        cityField = new TextField("City",client.address.city,editable);
        down.add(cityField,height + "width 200");

        zipCodeField = new TextField("ZIP code",client.address.zipCode,editable);
        down.add(zipCodeField,height + "wrap, width 200");

        down.add(new Label("Street"),height);
        down.add(new Label("No. building"),height + "split 2");
        down.add(new Label("No. apartment"), height + "wrap");

        streetField = new TextField("Street",client.address.street,editable);
        down.add(streetField,height + "width 200");

        if (client.address.buildingNumber == 0 && editable)
            buildingNumberField = new TextField("No. building","No. building",true);
        else
            buildingNumberField = new TextField("No. building",Integer.toString(client.address.buildingNumber),false);

        down.add(buildingNumberField,height + "split 2, width 100");

        if (client.address.apartmentNumber == 0 && editable)
            apartmentNumberField = new TextField("No. apartment","No. apartment",true);
        else
            apartmentNumberField = new TextField("No. apartment",Integer.toString(client.address.apartmentNumber),false);

        down.add(apartmentNumberField,height + "width 100, wrap");

        setLayout(new MigLayout());

        add(top,"wrap 0");
        add(down);

        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void asSearchResult(){
        removeAll();
        add(top);
        add(down);
        repaint();
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

        newC.birthDate = dateBox.getDate();

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
