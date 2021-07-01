import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.HashSet;

public class TextField extends JTextField implements FocusListener {

    String text;
    String nameField;
    boolean editable;
    HashSet<String> keyWordsInt;
    HashSet<String> keyWordsString;

    public TextField(String nameField, String text, boolean editable) {
        String[] keyIntInit = {"No. building","No. apartment","Seats","Doors","Capacity","Max weight","Mileage","Produce year","Day", "Month","Year"};
        keyWordsInt = new HashSet<>(Arrays.asList(keyIntInit));
        String[] keyInitString = {"Id","Name","Surname","ZIP code", "Street", "City", "Registration plate","Brand","Model","Color"};
        keyWordsString = new HashSet<>(Arrays.asList(keyInitString));

        try {
            if (!(keyWordsString.contains(nameField) || keyWordsInt.contains(nameField)))
                throw new Exception("Wrong field name");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        this.editable = editable;
        this.text = text;
        this.nameField = nameField;


        if (!editable && text.equals("0") || text.isEmpty())
            setText("");
        else
            setText(text);


        addFocusListener(this);
        setFont(new Font("",Font.PLAIN,16));
        setEditable(editable);



    }

    public void clear(){
        setText(nameField);
    }


    @Override
    public void focusGained(FocusEvent e) {
        if ((keyWordsString.contains(getText()) || keyWordsInt.contains(getText())) && editable)
            setText("");
    }

    public String getTxt(){
        if (keyWordsInt.contains(getText()))
            return "0";

        if (keyWordsString.contains(getText()))
            return "";

        return getText();
    }

    @Override
    public void focusLost(FocusEvent e) {
            if (getText().isEmpty())
                setText(text);
    }
}
