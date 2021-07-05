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

        search.add(new Button(this,"Car","car"),"width 200");
        search.add(new Button(this,"Truck","truck"),"wrap, width 200");
        search.add(vehiclePanel,"span 2, wrap");
        search.add(new Button(this, "Search in database","search"),"width 200");
        search.add(new Button(listener,"Return to menu","returnMenu"),"width 200");

        JPanel results = new JPanel();
        results.setLayout(new MigLayout());
        searchVehicleResults = new SearchVehicleResults(database);

        jsp = new JScrollPane(searchVehicleResults);
        results.add(jsp,"wrap, span 2");
        results.add(new Button(this,"Return to search","returnSearch"),"width 295");
        results.add(new Button(listener,"Return to menu","returnMenu"),"width 295");

        mainPanel.add(search,"search");
        mainPanel.add(results, "results");

        add(mainPanel,"wrap, span, width 400");


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
                System.out.println("WAR");  //TODO No result warning
            else {
                jsp.setPreferredSize(new Dimension(900, 120));
                if (show)
                    searchVehicleResults.newSearch(vehicles, truckPanel.getCompare());
                else
                    searchVehicleResults.newSearch(vehicles, carPanel.getCompare());
                cardLayout.show(mainPanel, "results");
                listener.setSize(1000, 300);  //TODO
            }

        }

        if (e.getActionCommand().equals("returnSearch")){
//            searchMode();
            cardLayout.show(mainPanel, "search");
        }

    }

    public void searchMode(){

    }

}
