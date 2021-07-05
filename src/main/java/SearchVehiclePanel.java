import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchVehiclePanel extends JPanel implements ActionListener {

    private Database database;
    private App listener;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CardLayout vehicleLayout;
    private JPanel vehiclePanel;
    private CarPanel carPanel;
    private TruckPanel truckPanel;
    private SearchVehicleResults searchVehicleResults;
    private JScrollPane jsp;

    boolean show = false; //which one is chose 0 - Car , 1 - Truck

    public SearchVehiclePanel(App listener,Database database){

        this.listener = listener;
        this.database = database;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        carPanel = new CarPanel(new Car(),true);
        carPanel.searchLayout();

        truckPanel = new TruckPanel(new Truck(),true);
        truckPanel.searchLayout();

        vehicleLayout = new CardLayout();
        vehiclePanel = new JPanel(vehicleLayout);

        vehiclePanel.add(carPanel,"car");
        vehiclePanel.add(truckPanel,"truck");

        JPanel search = new JPanel();
        search.setLayout(new MigLayout());

        search.add(new Button(this,"Car","car"),"width 235");
        search.add(new Button(this,"Truck","truck"),"wrap, width 235");
        search.add(vehiclePanel,"span 2, wrap");
        search.add(new Button(this, "Search in database","search"),"width 235");
        search.add(new Button(listener,"Return to menu","returnMenu"),"width 235");

        JPanel results = new JPanel();
        results.setLayout(new MigLayout());

        searchVehicleResults = new SearchVehicleResults(database);
        jsp = new JScrollPane(searchVehicleResults);
        results.add(jsp,"wrap, span 2");
        results.add(new Button(this,"Return to search","returnSearch"),"width 430");
        results.add(new Button(listener,"Return to menu","returnMenu"),"width 430");

        mainPanel.add(search,"search");
        mainPanel.add(results, "results");

        setLayout(new MigLayout());
        setSize(new Dimension(560,330));
        add(mainPanel,"alignX right");


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String source = e.getActionCommand();

        if (source.equals("truck")) {
            vehicleLayout.show(vehiclePanel, "truck");
            show = true;
        }

        if (source.equals("car")) {
            vehicleLayout.show(vehiclePanel, "car");
            show = false;
        }

        if (e.getActionCommand().equals("search")) {

            ArrayList<Vehicle> vehicles;

            if (show)
                if (truckPanel.getVehicle().capacity == 0 && truckPanel.getVehicle().maxWeight == 0)
                    vehicles = database.searchVehicleInDatabase((VehicleCompare) truckPanel.getCompare());
                else
                    vehicles = database.searchVehicleInDatabase(truckPanel.getCompare());
            else
                if (carPanel.getVehicle().seats == 0 && carPanel.getVehicle().doors == 0)
                   vehicles = database.searchVehicleInDatabase((VehicleCompare) carPanel.getCompare());
                else
                    vehicles = database.searchVehicleInDatabase(carPanel.getCompare());


            if (vehicles.size() == 0)
                JOptionPane.showMessageDialog(this,"Not result found");
            else {
                jsp.setPreferredSize(new Dimension(760, 130));
                if (show)
                    searchVehicleResults.newSearch(vehicles, truckPanel.getCompare());
                else
                    searchVehicleResults.newSearch(vehicles, carPanel.getCompare());
                cardLayout.show(mainPanel, "results");
                listener.setSize(760, 220);
            }

        }

        if (e.getActionCommand().equals("returnSearch")){
            searchMode();
            cardLayout.show(mainPanel, "search");
        }

    }

    public void searchMode(){
        listener.setSize(470,350);
        jsp.setPreferredSize(new Dimension(100,100));
        cardLayout.show(mainPanel,"search");
    }

}
