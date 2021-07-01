import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchClientPanel extends JPanel implements ActionListener {

   private Database database;
   private App listener;
   private CardLayout cardLayout;
   private JPanel mainPanel;
   private ClientSearchPanel clientSearchPanel;
   private SearchClientResults searchClientResults;


    public SearchClientPanel(App listener, Database database){

        this.listener = listener;
        this.database = database;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel search = new JPanel();
        search.setLayout(new MigLayout());

        clientSearchPanel = new ClientSearchPanel(new Client(),true);
        search.add(clientSearchPanel,"span 2, wrap");
//        search.setPreferredSize(new Dimension(4, 400));
        search.add(new Button(this,"Search in database","search"),"width 295");
        search.add(new Button(listener,"Return to menu","returnMenu"),"width 295");

        JPanel results = new JPanel();
        results.setLayout(new MigLayout());


        searchClientResults = new SearchClientResults(database);
        JScrollPane jsp = new JScrollPane(searchClientResults);
//        jsp.setPreferredSize(new Dimension(900, 200));
        results.add(jsp,"wrap, span 2");
        results.add(new Button(this,"Return to search","returnSearch"),"width 295");
        results.add(new Button(listener,"Return to menu","returnMenu"),"width 295");


        mainPanel.add(search,"search");
        mainPanel.add(results, "results");

        add(mainPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("search")) {

            ArrayList<Client> clients = database.searchClientInDatabase(clientSearchPanel.getClientCompare());

            if (clients.size() == 0)
                System.out.println("WAR");  //TODO No result warning
            else {
                searchClientResults.newSearch(clients);
                cardLayout.show(mainPanel, "results");
                listener.setSize(1000, 300);
            }

        }

        if (e.getActionCommand().equals("returnSearch"))
            cardLayout.show(mainPanel,"search");

    }

    public void searchMode(){
        cardLayout.show(mainPanel,"search");
        listener.setSize(640,350);
    }

}