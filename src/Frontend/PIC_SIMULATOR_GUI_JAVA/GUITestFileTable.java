package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class GUITestFileTable extends JPanel {
    String sLine;
    ArrayList<JCheckBox> oCheckboxes = new ArrayList<JCheckBox>();

    public GUITestFileTable() {
        JTextField oFill = new JTextField("19");
        oFill.setEditable(false);
        this.setLayout(new GridLayout(1, 3));
        this.add(oFill);
        this.add(new JCheckBox());
        oFill = new JTextField("Test");
        oFill.setEditable(false);
        this.add(oFill);
    }

    public void setData(ArrayList<String> data) {
        this.removeAll();
        JScrollBar oScrollBar = new JScrollBar(JScrollBar.VERTICAL, 30, 1, 0, 100);
        int iNumberOfLines = data.size() + 1;
        JPanel oLines = new JPanel();
        oLines.add(oScrollBar);//TODO
        oLines.setLayout(new GridBagLayout());
        int iHorizontalCount = 0;
        int iVerticalCount = 0;
        System.out.println(1);
        GridBagConstraints l = new GridBagConstraints();
        l.gridx = iHorizontalCount;

        int iMaxLength = getMaxLen(data);

        for (int i = 1; i < iNumberOfLines; i++) {
            JPanel oPanel = new JPanel();
            oPanel.setLayout(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;

            JTextField oNumber = new JTextField(i + "", 3);
            oNumber.setEditable(false);
            oPanel.add(oNumber, c);

            c.gridx = 1;

            JCheckBox oCheckbox = new JCheckBox();
            oCheckbox.setEnabled(false);      
            oCheckboxes.add(oCheckbox);
            oPanel.add(oCheckbox, c);

            c.gridx = 2;

            JTextField oTestLine = new JTextField(data.get(i - 1), iMaxLength);
            oTestLine.setEditable(false);
            oPanel.add(oTestLine, c);
            
            l.gridy = iVerticalCount;
            l.anchor = GridBagConstraints.WEST;
            oLines.add(oPanel, l);
            iVerticalCount++;
        }
        System.out.println(iMaxLength);
        this.add(oLines);
    }

    public ArrayList<JCheckBox> getCheckboxes() {
        return oCheckboxes;
    }

    private int getMaxLen(ArrayList<String> data) {
        int iSize = data.size();
        int iMaxLength = 0;
        for (int i = 0; i < iSize; i++) {
            if (data.get(i).length() > iMaxLength) {
                iMaxLength = data.get(i).length();
            }
        }
        return iMaxLength;
    }
}
