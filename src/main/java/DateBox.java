import javax.crypto.spec.PSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.Arrays;

public class DateBox extends JPanel implements ActionListener {


    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    private ComboBox days28;
    private ComboBox days29;
    private ComboBox days30;
    private ComboBox days31;
    private ComboBox months;
    private ComboBox years;

    private JPanel mainDays;
    private CardLayout cardLayout;

    public DateBox() {

        setLayout(new GridLayout(1, 3));

        cardLayout = new CardLayout();
        mainDays = new JPanel(cardLayout);

        String[] days31String = new String[32];

        days31String[0] = "-";
        for (int i = 1; i < 32; i++)
            if (i < 10)
                days31String[i] = "0" + Integer.toString(i);
            else
                days31String[i] = Integer.toString(i);

        days31 = new ComboBox(this,days31String,"day");
        days30 = new ComboBox(this,Arrays.copyOf(days31String, 31),"day");
        days29 = new ComboBox(this,Arrays.copyOf(days31String, 30),"day");
        days28 = new ComboBox(this,Arrays.copyOf(days31String, 29),"day");

        months = new ComboBox(this, Arrays.copyOf(days31String, 13), "month");

        String[] years1900_2021 = new String[123];

        years1900_2021[0] = "-";

        for (int i = 1900; i < 2022; i++)
            years1900_2021[i - 1899] = Integer.toString(i);

        years = new ComboBox(this, years1900_2021, "year");

        mainDays.add(days31, "31");
        mainDays.add(days30, "30");
        mainDays.add(days29, "29");
        mainDays.add(days28, "28");

        add(mainDays);
        add(months);
        add(years);

        System.out.println();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    //TODO Refactor to source == ComboBox

        ComboBox source = (ComboBox) e.getSource();
        int index = source.getSelectedIndex();

        if (e.getActionCommand().equals("year")) {
            if (index == 0)
                selectedYear = 0;
            else
                selectedYear = 1899 + index;

        }

        if (selectedMonth == 2)
            if (selectedYear != 0 &&(selectedYear % 4 == 0))
                cardLayout.show(mainDays, "29");
            else
                cardLayout.show(mainDays, "28");


        if (e.getActionCommand().equals("month")) {

            String[] dayInMonth = {"31", "31", "0", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};
            selectedMonth = index;

            if (selectedMonth == 2)
                if ((selectedYear - 1) % 4 == 0)
                    cardLayout.show(mainDays, "29");
                else
                    cardLayout.show(mainDays, "28");
            else
                cardLayout.show(mainDays, dayInMonth[selectedMonth]);
        }

        if (e.getActionCommand().equals("day"))
            selectedDay = index;
    }

    public Date getDate(){
        return new Date(selectedDay,selectedMonth,selectedYear);
    }

}
