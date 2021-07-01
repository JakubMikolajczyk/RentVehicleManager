import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button(Object listener,String text, String actionText){
        addActionListener((ActionListener) listener);
        setText(text);
        setActionCommand(actionText);
        setFont(new Font("",Font.PLAIN,16));
//        setSize(100,100);

    }

}
