import javax.swing.*;
import java.awt.*;

public class SearchInfo extends JOptionPane {

    private boolean distableInfo = true;
    Component frame;

    public SearchInfo(Object frame){
        this.frame = (Component) frame;
    }

    public void info(){
        if (!distableInfo)
            showMessageDialog(frame,"Test");
    }
}
