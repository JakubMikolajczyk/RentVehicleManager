import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ComboBox extends JComboBox<String> {

    public ComboBox(Object listener, String []list){
        super(list);
        addActionListener((ActionListener) listener);
        setSelectedIndex(0);

    }

    public ComboBox(String []list){
        super(list);
        setSelectedIndex(0);

    }


    public ComboBox(Object listener, String []list, String command){
        this(listener,list);
        setActionCommand(command);
    }
}
