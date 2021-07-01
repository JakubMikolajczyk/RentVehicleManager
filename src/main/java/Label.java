import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    public Label(String text){
        setText(text);
        setForeground(Color.BLUE);
        setFont(new Font("",Font.PLAIN,16));
    }
}
