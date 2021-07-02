import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

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

//        addClientLayout();

        String[] stringOptions = {"equal","begin","end","contain"};
        String[] intOptions = {"==","<","<=",">",">="};

//        setLayout(new GridLayout(0,4));

        setLayout(new MigLayout("wrap 4"));

        nameCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),nameCombo));
        add(nameBox);


        surnameCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),surnameCombo) );
        add(surnameBox);

        idCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),idCombo));
        add(idBox);

        dateCombo = new ComboBox(this,intOptions);
        add(new Box(new Label(""),dateCombo));
        add(dateBox);

        cityCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),cityCombo));
        add(cityBox);

        zipCodeCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),zipCodeCombo));
        add(zipCodeBox);


        streetCombo = new ComboBox(this,stringOptions);
        add(new Box(new Label(""),streetCombo));
        add(streetBox);



        JPanel box1 = new JPanel();
        box1.setLayout(new GridLayout(1,2));

        JPanel box2 = new JPanel();
        box2.setLayout(new GridLayout(1,2  ));
        buildingNumberCombo = new ComboBox(this,intOptions);

        box1.add(new Box(new Label(""),buildingNumberCombo));
        box1.add(buildingNumberBox);

        add(box1);

        apartmentNumberCombo = new ComboBox(this,intOptions);
        box2.add(new Box(new Label(""),apartmentNumberCombo));
        box2.add(apartmentNumberBox);
        add(box2);


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
