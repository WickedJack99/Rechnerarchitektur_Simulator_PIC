package Frontend.PIC_SIMULATOR_GUI_JAVA;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;

public class GUIRegistersDetailed extends JPanel {

    JTable oStatusTable;
    JTable oOptionTable = new JTable();
    JTable oIntconTable = new JTable();

    JPanel oStatusPanel = new JPanel();
    JPanel oOptionPanel = new JPanel();
    JPanel oIntconPanel = new JPanel();

    JLabel oStatusLabel = new JLabel("STATUS");
    JLabel oOptionLabel = new JLabel("OPTION");
    JLabel oIntconLabel = new JLabel("INTCON");

    public GUIRegistersDetailed() {
        buildGUIRegistersDetailed();
    }

    private void buildGUIRegistersDetailed() {
        this.setLayout(new GridBagLayout());

        oStatusPanel.setLayout(new GridBagLayout());
        oOptionPanel.setLayout(new GridBagLayout());
        oIntconPanel.setLayout(new GridBagLayout());

        String[][] asDataStatus = {{"IRP", "RP1", "RP0", "TO", "PD", "Z", "DC", "C"}, {"0", "0", "0", "0", "0", "0", "0", "0"}};
        String[] asHeadersStatus = {"0", "1", "2", "3", "4", "5", "6", "7"};
        oStatusTable = new JTable(asDataStatus, asHeadersStatus);
        oStatusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        GridBagConstraints oConstraintsStatus = new GridBagConstraints();
        oConstraintsStatus.anchor = GridBagConstraints.WEST;
        oConstraintsStatus.gridx = 0;
        oConstraintsStatus.gridy = 0;
        oStatusPanel.add(oStatusLabel, oConstraintsStatus);
        oConstraintsStatus.gridy = 1;
        oStatusPanel.add(oStatusTable, oConstraintsStatus);

        for (int i = 0; i < 8; i++) {
            oStatusTable.getColumn(i + "").setMaxWidth(50);
        }
        oStatusTable.setEnabled(false);

        String[][] asDataOption = {{"RBPU", "INTEDG", "T0CS", "T0SE", "PSA", "PS2", "PS1", "PS0"}, {"0", "0", "0", "0", "0", "0", "0", "0"}};
        String[] asHeadersOption = {"0", "1", "2", "3", "4", "5", "6", "7"};
        oOptionTable = new JTable(asDataOption, asHeadersOption);
        oOptionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        GridBagConstraints oConstraintsOption = new GridBagConstraints();
        oConstraintsOption.anchor = GridBagConstraints.WEST;
        oConstraintsOption.gridx = 0;
        oConstraintsOption.gridy = 0;
        oOptionPanel.add(oOptionLabel, oConstraintsOption);
        oConstraintsOption.gridy = 1;
        oOptionPanel.add(oOptionTable, oConstraintsOption);

        for (int i = 0; i < 8; i++) {
            oOptionTable.getColumn(i + "").setMaxWidth(50);
        }
        oOptionTable.setEnabled(false);

        String[][] asDataIntcon = {{"GIE", "EEIE", "T0IE", "INTE", "RBIE", "T0IF", "INTF", "RBIF"}, {"0", "0", "0", "0", "0", "0", "0", "0"}};
        String[] asHeadersIntcon = {"0", "1", "2", "3", "4", "5", "6", "7"};
        oIntconTable = new JTable(asDataIntcon, asHeadersIntcon);
        oIntconTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        GridBagConstraints oConstraintsIntcon = new GridBagConstraints();
        oConstraintsIntcon.anchor = GridBagConstraints.WEST;
        oConstraintsIntcon.gridx = 0;
        oConstraintsIntcon.gridy = 0;
        oIntconPanel.add(oIntconLabel, oConstraintsIntcon);
        oConstraintsIntcon.gridy = 1;
        oIntconPanel.add(oIntconTable, oConstraintsIntcon);

        for (int i = 0; i < 8; i++) {
            oIntconTable.getColumn(i + "").setMaxWidth(50);
        }
        oIntconTable.setEnabled(false);

        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oStatusPanel, oConstraints);
        oConstraints.gridy = 1;
        this.add(oOptionPanel, oConstraints);
        oConstraints.gridy = 2;
        this.add(oIntconPanel, oConstraints);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}