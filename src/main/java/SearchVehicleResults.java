import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class SearchVehicleResults extends JPanel {

    private Database database;
    private VehicleCompare vehicleCompare;

    public SearchVehicleResults(Database database) {
        this.database = database;
    }

    private void makeRentResult(Vehicle vehicle) {

        VehiclePanel panel;

        if (vehicle instanceof Car)
            panel = new CarPanel((Car) vehicle, false);
        else
            panel = new TruckPanel((Truck) vehicle, false);

        panel.resultLayout();
        panel.makeRed(panel);
        panel.setBackground(Color.red);
        VehiclePanel finalPanel = panel;
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Object[] options = {"Edit", "Remove", "Return", "Cancel"};
                int n = JOptionPane.showOptionDialog(finalPanel, "What would you do?", "Action",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
//
                if (n == 0)
                    editVehicle(vehicle,panel);
                if (n == 1)
                    removeVehicle(vehicle,panel);
                if (n == 2)
                    returnVehicle(vehicle,panel);
            }

        });
        add(panel);
    }

    private void makeResult(Vehicle vehicle) {

        VehiclePanel panel;

        if (vehicle instanceof Car)
            panel = new CarPanel((Car) vehicle, false);
        else
            panel = new TruckPanel((Truck) vehicle, false);

        panel.resultLayout();
        VehiclePanel finalPanel = panel;
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Object[] options = {"Edit", "Remove", "Rent", "Cancel"};
                int n = JOptionPane.showOptionDialog(finalPanel, "What would you do?", "Action",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
//
                if (n == 0)
                    editVehicle(vehicle,panel);
                if (n == 1)
                    removeVehicle(vehicle,panel);
                if (n == 2)
                    rent(vehicle,panel);
            }

        });
        add(panel);
    }

    public void newSearch(ArrayList<Vehicle> vehicles,VehicleCompare vehicleCompare) {
        this.vehicleCompare=vehicleCompare;
        removeAll();
        setLayout(new GridLayout(0, 1));


        for (Vehicle vehicle : vehicles)
            if (database.isVehicleRent(vehicle.registrationPlate))
                makeRentResult(vehicle);
            else
                makeResult(vehicle);

    }

    private void refresh(){
        revalidate();
        repaint();
    }

    private void editVehicle(Vehicle vehicle,VehiclePanel panel){

        Object[] options = {"Save Edit","Cancel"};

        VehiclePanel newPanel;

        boolean isCar = vehicle instanceof Car;

        if (isCar)
            newPanel = new CarPanel((Car) vehicle,true);
        else
            newPanel = new TruckPanel((Truck) vehicle,true);

        newPanel.addLayout();
        int n = JOptionPane.showOptionDialog(null,newPanel,"Vehicle edit",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        if (n == 0
                && newPanel.intCheck()
                && (newPanel.getVehicle().registrationPlate.equals(vehicle.registrationPlate) || newPanel.registrationPlateCheck(database))
                && 0 == JOptionPane.showConfirmDialog(this, "Are you sure to save edit vehicle?", "Confirm", JOptionPane.YES_NO_OPTION))
        {
            remove(panel);      //remove old panel

            Vehicle edited = newPanel.getVehicle();
            database.editInDatabase(vehicle,edited);

            if (vehicleCompare.compare(edited))   //if compare to search params add to panel
                if (database.isVehicleRent(edited.registrationPlate))
                    makeRentResult(edited);
                else
                    makeResult(edited);

            refresh();

        }

    }

    private void removeVehicle(Vehicle vehicle, VehiclePanel panel) {

        int n = 0;

        if (database.isVehicleRent(vehicle.registrationPlate))
            n = JOptionPane.showConfirmDialog(this, "This vehicle is rent? Remove anyway?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to remove vehicle from database?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (confirm == 0) {
                database.removeVehicle(vehicle);
                remove(panel);
                refresh();
            }
        }

    }

    private void rent(Vehicle vehicle, VehiclePanel panel){

        String id = JOptionPane.showInputDialog("Client id");

        if(!database.idCheck(id))
            JOptionPane.showMessageDialog(this,id + " is not in database. Add new client first","Warning",JOptionPane.WARNING_MESSAGE);
        else {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to rent vehicle?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                database.rent(id, vehicle.registrationPlate);
                remove(panel);
                makeRentResult(vehicle);
                refresh();
            }
        }
    }

    private void returnVehicle(Vehicle vehicle,VehiclePanel panel){

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to return vehicle?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            database.returnVehicleRegistrationPlate(vehicle.registrationPlate);
            remove(panel);
            makeResult(vehicle);
            refresh();
        }

    }

}
