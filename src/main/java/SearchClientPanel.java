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
   private ClientPanel clientSearchPanel;
   private SearchClientResults searchClientResults;
   private JScrollPane jsp;

    public SearchClientPanel(App listener, Database database){

        this.listener = listener;
        this.database = database;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel search = new JPanel();
        search.setLayout(new MigLayout());

        //TODO repair searchClientPanel resolution, trouble with jsp.setPreferredSize

        clientSearchPanel = new ClientPanel(new Client(),true);
        clientSearchPanel.searchLayout();

        search.add(clientSearchPanel,"span 2, wrap");
        search.add(new Button(this,"Search in database","search"),"width 295");
        search.add(new Button(listener,"Return to menu","returnMenu"),"width 295");

        JPanel results = new JPanel();
        results.setLayout(new MigLayout());


        searchClientResults = new SearchClientResults(database);
        jsp = new JScrollPane(searchClientResults);
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

            ArrayList<Client> clients = database.searchClientInDatabase(clientSearchPanel.getCompare());

            if (clients.size() == 0)
                System.out.println("WAR");  //TODO No result warning
            else {
                jsp.setPreferredSize(new Dimension(900, 120));
                searchClientResults.newSearch(clients,clientSearchPanel.getCompare());
                cardLayout.show(mainPanel, "results");
                listener.setSize(1000, 300);  //TODO
            }

        }

        if (e.getActionCommand().equals("returnSearch")){
            searchMode();
            cardLayout.show(mainPanel, "search");
        }
    }

    public void searchMode(){
        listener.setSize(640,350);
        jsp.setPreferredSize(new Dimension(100,100));
        cardLayout.show(mainPanel,"search");
    }

}
