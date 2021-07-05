import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {//make box 2x1


    public Box(Object obj1, Object obj2){

        setLayout(new GridLayout(0,1));
        add((Component) obj1);
        add((Component) obj2);
        setPreferredSize(new Dimension(200,20));

    }

}
