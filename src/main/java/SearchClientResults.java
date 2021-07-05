import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SearchClientResults extends JPanel {

    private Database database;

    private ClientCompare clientCompare;

    private Object[] options = {"Edit", "Remove", "Cancel"};

    public SearchClientResults(Database database) {
        this.database = database;
    }

    private void makeResult(Client client){
        ClientPanel panel = new ClientPanel(client, false);
        panel.resultLayout();
        panel.resultLayout();
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int n = JOptionPane.showOptionDialog(panel, "What would you do?", "Action",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

                if (n == 0)
                    edit(client,panel);
                if (n == 1)
                    remove(client,panel);
            }

        });

        add(panel);
    }

    public void newSearch(ArrayList<Client> clients, ClientCompare clientCompare) {

        removeAll();

        this.clientCompare = clientCompare;

        setLayout(new GridLayout(0, 1));
        for (Client client : clients)
            makeResult(client);


    }

    private void edit(Client client, ClientPanel panel) {

        Object[] options = {"Save edit","Cancel"};

        ClientPanel newPanel = new ClientPanel(client,true);
        newPanel.addLayout();
        int n = JOptionPane.showOptionDialog(null,newPanel,"Client edit",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        if (n == 0 && newPanel.validCheck(database) &&
                0 ==   JOptionPane.showConfirmDialog(this, "Are you sure to edit client?", "Confirm", JOptionPane.YES_NO_OPTION))
        {
            remove(panel);      //remove old panel

            Client edited = newPanel.getClient();
            database.editInDatabase(client,edited);

            if (clientCompare.compare(edited))   //if compare to search params add to panel
                makeResult(edited);

            refresh();

        }

    }

    private void remove(Client client, ClientPanel panel) {

        int n = 0;

        if (database.hasRentVehicle(client.id))
            n = JOptionPane.showConfirmDialog(this, "This client has rent vehicle? Remove anyway?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to remove client from database?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (confirm == 0) {
                database.removeClient(client);
                remove(panel);
                refresh();
            }
        }

    }

    private void refresh(){
        revalidate();
        repaint();
    }

}