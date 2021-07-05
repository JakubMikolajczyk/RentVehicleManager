import javax.swing.*;
import java.awt.*;

public class SearchInfo extends JOptionPane {

    private boolean disableInfo = false;
    Component frame;

    public SearchInfo(Object frame){
        this.frame = (Component) frame;
    }

    public void info(){

        String message ="Information about search \n" +
                "If you want ignore field leave it empty/default \n" +
                "String searching:\n" +
                "equal - search for string equal to input\n" +
                "begin - search for string beginning with input\n" +
                "end - search for string ending with input\n" +
                "contain - search for string contain input\n" +
                "Int searching \n" +
                "== - search for int equal to input\n" +
                "< - search for int less than input\n" +
                "<= - search for int less than or equal to input\n" +
                "> - search for int above than input\n" +
                ">= - search for int above than or equal to input";


        Object[] options = {"Ok","Disable information"};
        int n = 0;
        if (!disableInfo)
            n = showOptionDialog(frame,message,"Search info",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

        if (n == 1)
            disableInfo = true;
    }
}
