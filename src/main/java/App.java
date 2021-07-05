import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class App extends JFrame implements ActionListener{

    private Database database;


    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuJPanel menuJPanel;
    private AddClientPanel addClientJPanel;
    private AddVehiclePanel addVehiclePanel;
    private SearchClientPanel searchClientPanel;
    private SearchVehiclePanel searchVehiclePanel;

    private SearchInfo searchInfo;

    public App(){

        setTitle("Menu");

        database = new Database();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuJPanel = new MenuJPanel(this);
        addClientJPanel = new AddClientPanel(this, database);
        addVehiclePanel = new AddVehiclePanel(this,database);
        searchClientPanel = new SearchClientPanel(this, database);
        SearchVehiclePanel searchVehiclePanel = new SearchVehiclePanel(this, database);

        mainPanel.add(menuJPanel,"menu");
        mainPanel.add(addClientJPanel,"addClient");
        mainPanel.add(addVehiclePanel,"addVehicle");
        mainPanel.add(searchClientPanel,"searchClient");
        mainPanel.add(searchVehiclePanel,"searchVehicle");
        add(mainPanel);
        setLocation(700, 300);
        setSize(new Dimension(200,300));

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {       //action after press exit button
            public void windowClosing(WindowEvent evt) {
                onExit();
            }
        });

        cardLayout.show(mainPanel,"menu");
        setVisible(true);


        searchInfo = new SearchInfo(this);
    }

    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();

        //MENU *********************************
        if (source.equals("addClient")) {
            cardLayout.show(mainPanel, "addClient");
            setSize(new Dimension(430,290));
            setTitle("Add new client");
        }

        if (source.equals("addVehicle")) {
            cardLayout.show(mainPanel, "addVehicle");
            setSize(new Dimension(430,320));
        }

        if (source.equals("searchClient")) {
            cardLayout.show(mainPanel, "searchClient");
            searchClientPanel.searchMode();
            searchInfo.info();
        }

        if (source.equals("searchVehicle")) {
            cardLayout.show(mainPanel, "searchVehicle");
            searchInfo.info();
        }

        if (source.equals("save"))
            saveDatabase();

        if (source.equals(("exit")))
            onExit();
       //************************************

        if (source.equals("returnMenu")) {
            cardLayout.show(mainPanel, "menu");
            setSize(new Dimension(200,300));
        }

    }

    private void onExit(){

        System.exit(1);

    }

    private void loadDatabase(){

    }

    private void saveDatabase(){

        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(database));

//        try {
//
//            Gson gson = new Gson();
//
//            ObjectOutputStream o = new ObjectOutputStream(
//                    new FileOutputStream("test.ser")
//            );
//
//            o.writeObject(gson.toJson(database));
//            o.close();
//            System.out.println("Zapisano do pliku");
//        } catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }

    }

}
