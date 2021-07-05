import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVehiclePanel extends JPanel implements ActionListener {


    private Database database;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CarPanel carPanel;
    private TruckPanel truckPanel;

    boolean show;

    public AddVehiclePanel(App listener,Database database){
        this.database = database;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        carPanel = new CarPanel(new Car(),true);
        carPanel.addLayout();
        truckPanel = new TruckPanel(new Truck(),true);
        truckPanel.addLayout();
        mainPanel.add(carPanel,"car");
        mainPanel.add(truckPanel,"truck");

        show = false;

        setLayout(new MigLayout());
        add(new Button(this,"Car","car"),"width 200");
        add(new Button(this,"Truck","truck"),"wrap, width 200");

        add(mainPanel,"wrap, span, width 400");

        add(new Button(this, "Add to database","addToDatabase"),"width 200");
        add(new Button(listener,"Return to menu","returnMenu"),"width 200");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String source = e.getActionCommand();

        if (source.equals("truck")) {
            cardLayout.show(mainPanel, "truck");
            show = true;
        }

        if (source.equals("car")) {
            cardLayout.show(mainPanel, "car");
            show = false;
        }

        if (source.equals("addToDatabase"))
            if (show)
                truckPanel.addToDatabase(database);
            else
                carPanel.addToDatabase(database);

    }
}
