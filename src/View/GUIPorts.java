package View;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPorts extends JPanel {
    
    JPanel oPanelPortA = new JPanel();
    JPanel oPanelPortB = new JPanel();

    JPanel oPanelPortAPins = new JPanel();
    JPanel oPanelPortATris = new JPanel();
    JPanel oPanelPortBPins = new JPanel();
    JPanel oPanelPortBTris = new JPanel();

    JLabel oPortA = new JLabel("Port A");
    JLabel oPortB = new JLabel("Port B");
    JLabel oTrisA = new JLabel("Tris");
    JLabel oTrisB = new JLabel("Tris");
    JLabel oPinA = new JLabel("Pin");
    JLabel oPinB = new JLabel("Pin");

    JCheckBox oRA4 = new JCheckBox("RA4");
    JCheckBox oRA3 = new JCheckBox("RA3");
    JCheckBox oRA2 = new JCheckBox("RA2");
    JCheckBox oRA1 = new JCheckBox("RA1");
    JCheckBox oRA0 = new JCheckBox("RA0");

    JCheckBox oTRISA4 = new JCheckBox("TRISA4");
    JCheckBox oTRISA3 = new JCheckBox("TRISA3");
    JCheckBox oTRISA2 = new JCheckBox("TRISA2");
    JCheckBox oTRISA1 = new JCheckBox("TRISA1");
    JCheckBox oTRISA0 = new JCheckBox("TRISA0");
    
    JCheckBox oRB7 = new JCheckBox("RB7");
    JCheckBox oRB6 = new JCheckBox("RB6");
    JCheckBox oRB5 = new JCheckBox("RB5");
    JCheckBox oRB4 = new JCheckBox("RB4");
    JCheckBox oRB3 = new JCheckBox("RB3");
    JCheckBox oRB2 = new JCheckBox("RB2");
    JCheckBox oRB1 = new JCheckBox("RB1");
    JCheckBox oRB0 = new JCheckBox("RB0");

    JCheckBox oTRISB7 = new JCheckBox("TRISB7");
    JCheckBox oTRISB6 = new JCheckBox("TRISB6");
    JCheckBox oTRISB5 = new JCheckBox("TRISB5");
    JCheckBox oTRISB4 = new JCheckBox("TRISB4");
    JCheckBox oTRISB3 = new JCheckBox("TRISB3");
    JCheckBox oTRISB2 = new JCheckBox("TRISB2");
    JCheckBox oTRISB1 = new JCheckBox("TRISB1");
    JCheckBox oTRISB0 = new JCheckBox("TRISB0");

    public GUIPorts() {
        buildGUIPorts();
    }

    private void buildGUIPorts() {
        oPanelPortAPins.add(oRA4);
        oPanelPortAPins.add(oRA3);
        oPanelPortAPins.add(oRA2);
        oPanelPortAPins.add(oRA1);
        oPanelPortAPins.add(oRA0);

        oPanelPortATris.add(oTRISA4);
        oPanelPortATris.add(oTRISA3);
        oPanelPortATris.add(oTRISA2);
        oPanelPortATris.add(oTRISA1);
        oPanelPortATris.add(oTRISA0);

        oPanelPortBPins.add(oRB7);
        oPanelPortBPins.add(oRB6);
        oPanelPortBPins.add(oRB5);
        oPanelPortBPins.add(oRB4);
        oPanelPortBPins.add(oRB3);
        oPanelPortBPins.add(oRB2);
        oPanelPortBPins.add(oRB1);
        oPanelPortBPins.add(oRB0);

        oPanelPortBTris.add(oTRISB7);
        oPanelPortBTris.add(oTRISB6);
        oPanelPortBTris.add(oTRISB5);
        oPanelPortBTris.add(oTRISB4);
        oPanelPortBTris.add(oTRISB3);
        oPanelPortBTris.add(oTRISB2);
        oPanelPortBTris.add(oTRISB1);
        oPanelPortBTris.add(oTRISB0);

        oPanelPortA.setLayout(new GridBagLayout());
        oPanelPortB.setLayout(new GridBagLayout());

        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.anchor = GridBagConstraints.WEST;

        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oPanelPortA.add(oPortA, oConstraints);
        oConstraints.gridy = 1;
        oPanelPortA.add(oPinA, oConstraints);
        oConstraints.gridx = 1;
        oPanelPortA.add(oPanelPortAPins, oConstraints);
        oConstraints.gridx = 0;
        oConstraints.gridy = 2;
        oPanelPortA.add(oTrisA);
        oConstraints.gridx = 1;
        oPanelPortA.add(oPanelPortATris, oConstraints);

        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oPanelPortB.add(oPortB, oConstraints);
        oConstraints.gridy = 1;
        oPanelPortB.add(oPinB, oConstraints);
        oConstraints.gridx = 1;
        oPanelPortB.add(oPanelPortBPins, oConstraints);
        oConstraints.gridx = 0;
        oConstraints.gridy = 2;
        oPanelPortB.add(oTrisB);
        oConstraints.gridx = 1;
        oPanelPortB.add(oPanelPortBTris, oConstraints);

        this.setLayout(new GridBagLayout());

        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oPanelPortA, oConstraints);
        oConstraints.gridy = 1;
        this.add(oPanelPortB, oConstraints);
    }
}