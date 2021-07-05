import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddClientPanel extends JPanel implements ActionListener {

    private Database database;
    private ClientPanel clientPanel;
    public AddClientPanel(App listener, Database database){
        this.database = database;

        setLayout(new MigLayout());

        clientPanel = new ClientPanel(new Client(),true);
        clientPanel.addLayout();
        add(clientPanel,"span, wrap,width 400");
        add(new Button(this,"Add to database","addToDatabase"),"width 200");
        add(new Button(listener,"Return to menu","returnMenu"),"width 200");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addToDatabase"))
            clientPanel.addToDatabase(database);
    }



}
