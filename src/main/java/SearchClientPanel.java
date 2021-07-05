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


        clientSearchPanel = new ClientPanel(new Client(),true);
        clientSearchPanel.searchLayout();

        search.add(clientSearchPanel,"span 2, wrap");
        search.add(new Button(this,"Search in database","search"),"width 265");
        search.add(new Button(listener,"Return to menu","returnMenu"),"width 265");

        JPanel results = new JPanel();
        results.setLayout(new MigLayout());

        searchClientResults = new SearchClientResults(database);
        jsp = new JScrollPane(searchClientResults);
        results.add(jsp,"wrap, span 2");
        results.add(new Button(this,"Return to search","returnSearch"),"width 380");
        results.add(new Button(listener,"Return to menu","returnMenu"),"width 380");


        mainPanel.add(search,"search");
        mainPanel.add(results, "results");

        setLayout(new MigLayout());
        setSize(new Dimension(560,330));
        add(mainPanel,"alignX right");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("search")) {

            ArrayList<Client> clients = database.searchClientInDatabase(clientSearchPanel.getCompare());

            if (clients.size() == 0)
                JOptionPane.showMessageDialog(this,"Not result found");
            else {
                jsp.setPreferredSize(new Dimension(760, 130));
                searchClientResults.newSearch(clients,clientSearchPanel.getCompare());
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
        listener.setSize(new Dimension(560,330));
        jsp.setPreferredSize(new Dimension(100,100));
        cardLayout.show(mainPanel,"search");
    }

}
