import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientSearchPanel extends ClientPanel implements ActionListener {

    private ComboBox nameCombo;
    private int nameIndex;

    private ComboBox surnameCombo;
    private int surnameIndex;

    private ComboBox idCombo;
    private int idIndex;

    private ComboBox dateCombo;
    private int dateIndex;

    private ComboBox cityCombo;
    private int cityIndex;

    private ComboBox zipCodeCombo;
    private int zipCodeIndex;

    private ComboBox streetCombo;
    private int streetIndex;

    private ComboBox buildingNumberCombo;
    private int buildingNumberIndex;

    private ComboBox apartmentNumberCombo;
    private int apartmentNumberIndex;

    public ClientSearchPanel(Client client, boolean editable) {
        super(client, editable);

        String[] stringOptions = {"equal","begin","end","contain"};
        String[] intOptions = {"==","<","<=",">",">="};
        String label = "wrap, height 25, ";
        String box = "wrap, height 25, ";

        JPanel left = new JPanel();
        left.setLayout(new MigLayout());

        left.add(new Label(""),label);
        nameCombo = new ComboBox(this,stringOptions);
        left.add(nameCombo,box);

        left.add(new Label(""),label);
        idCombo = new ComboBox(this,stringOptions);
        left.add(idCombo,box);

        left.add(new Label(""),label);
        cityCombo = new ComboBox(this,stringOptions);
        left.add(cityCombo,box);

        left.add(new Label(""),label);
        streetCombo = new ComboBox(this,stringOptions);
        left.add(streetCombo,box);

        add(left,"west");

        JPanel right = new JPanel();
        right.setLayout(new MigLayout());

        right.add(new Label(""),label);
        surnameCombo = new ComboBox(this,stringOptions);
        right.add(surnameCombo,box + "width 30");

        right.add(new Label(""),label);
        dateCombo = new ComboBox(this,intOptions);
        right.add(dateCombo,box+ "width 30");

        right.add(new Label(""),label);
        zipCodeCombo = new ComboBox(this,stringOptions);
        right.add(zipCodeCombo,box+ "width 30");

        right.add(new Label(""),label);
        buildingNumberCombo = new ComboBox(this,intOptions);
        right.add(buildingNumberCombo,"height 25 , split 2");

//        right.add(new Label(""),label);
        apartmentNumberCombo = new ComboBox(this,intOptions);
        right.add(apartmentNumberCombo,box+ "width 30");

        add(right,"east");


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ComboBox source = (ComboBox) e.getSource();

        if(source == nameCombo)
            nameIndex = source.getSelectedIndex();

        if(source == surnameCombo)
            surnameIndex = source.getSelectedIndex();

        if(source == idCombo)
            idIndex = source.getSelectedIndex();

        if(source == dateCombo)
            dateIndex = source.getSelectedIndex();

        if(source == cityCombo)
            cityIndex = source.getSelectedIndex();

        if(source == zipCodeCombo)
            zipCodeIndex = source.getSelectedIndex();

        if(source == streetCombo)
            streetIndex = source.getSelectedIndex();

        if(source == buildingNumberCombo)
            buildingNumberIndex = source.getSelectedIndex();

        if(source == apartmentNumberCombo)
            apartmentNumberIndex = source.getSelectedIndex();

    }

    public ClientCompare getClientCompare(){
        ClientCompare newCompareC = new ClientCompare(getClient());

        newCompareC.nameParam = nameIndex;
        newCompareC.surnameParam = surnameIndex;
        newCompareC.idParam = idIndex;
        newCompareC.birthDateParam = dateIndex;

        newCompareC.addressParam.cityParam = cityIndex;
        newCompareC.addressParam.zipCodeParam = zipCodeIndex;
        newCompareC.addressParam.streetParam = streetIndex;
        newCompareC.addressParam.buildingNumberParam = buildingNumberIndex;
        newCompareC.addressParam.apartmentNumberParam = apartmentNumberIndex;

        return newCompareC;
    }

}
