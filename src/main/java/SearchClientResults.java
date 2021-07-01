import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SearchClientResults extends JPanel {

    private Database database;

    public SearchClientResults(Database database){
        this.database = database;

    }

    public boolean newSearch(ArrayList<Client> clients){
        removeAll();

        setLayout(new GridLayout(clients.size(),1));
        for (Client client : clients){
//        Client client = clients.get(0);
            ClientPanel panel = new ClientPanel(client,false);
            panel.asSearchResult();
            panel.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e) {
                    Object[] options = {"Edit","Remove","Cancel"};
                    int n = JOptionPane.showOptionDialog(panel,"What would you do?","Action",
                            JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                    if (n == 0)
                        edit(client,panel);
                    if (n == 1)
                        remove(client,panel);
                }

            });
        add(panel);
        }

        return true;
    }

    private void edit(Client client, ClientPanel panel){

    }

    private void remove(Client client, ClientPanel panel){
//        database.
    }
}
