import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJPanel extends JPanel {

    public MenuJPanel(App listener){
        setLayout(new GridLayout(6,1));

        add(new Button(listener,"Add new client","addClient"));
        add(new Button(listener,"Add new Vehicle","addVehicle"));
        add(new Button(listener,"Search client","searchClient"));
        add(new Button(listener,"Search Vehicle","searchVehicle"));
        add(new Button(listener,"Save","save"));
        add(new Button(listener,"Exit","exit"));

//        setSize(1000,1000);

    }


}
