import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

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
        loadDatabase();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuJPanel = new MenuJPanel(this);
        addClientJPanel = new AddClientPanel(this, database);
        addVehiclePanel = new AddVehiclePanel(this,database);
        searchClientPanel = new SearchClientPanel(this, database);
        searchVehiclePanel = new SearchVehiclePanel(this, database);

        mainPanel.add(menuJPanel,"menu");
        mainPanel.add(addClientJPanel,"addClient");
        mainPanel.add(addVehiclePanel,"addVehicle");
        mainPanel.add(searchClientPanel,"searchClient");
        mainPanel.add(searchVehiclePanel,"searchVehicle");
        add(mainPanel);
        setLocation(550, 300);
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
            searchVehiclePanel.searchMode();
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

        if (database.saved)
            System.exit(1);
        else{
            Object[] options ={"Exit with save","Exit without save","Cancel"};
            int n = JOptionPane.showOptionDialog(this, "Database is not save. What would you do?", "Action",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

            if (n == 0){
                saveDatabase();
                System.exit(1);
            }

            if (n == 1)
                System.exit(1);
        }
    }

    private void loadDatabase(){


        try {

            Gson gson = new Gson();

            ObjectInputStream o = new ObjectInputStream(
                    new FileInputStream("database.json")
            );

            String json = (String) o.readObject();

            this.database = gson.fromJson(json,Database.class);
            database.saved = true;
            o.close();

        } catch (Exception ex) {

//                ex.printStackTrace();
        this.database = new Database();
        }

    }

    private void saveDatabase() {

//        Gson gson = new Gson();

        Gson gson = new Gson();


        String json = gson.toJson(database);

        try {

            ObjectOutputStream o = new ObjectOutputStream(
                    new FileOutputStream("database.json")
            );

            o.writeObject(json);
            o.close();
            JOptionPane.showMessageDialog(this,"Database save successfully");
            database.saved = true;
        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

}
