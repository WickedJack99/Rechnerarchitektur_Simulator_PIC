package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUITestFileTable extends JPanel {
    String sLine;
    ArrayList<JCheckBox> oCheckboxes = new ArrayList<JCheckBox>();

    public GUITestFileTable() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints oGridBagConstraints = new GridBagConstraints();
        oGridBagConstraints.gridx = 0;
        oGridBagConstraints.gridy = 0;
        oGridBagConstraints.gridheight = 20;
        oGridBagConstraints.gridwidth = 20; //500, 10, 10, 480
        JTextField oFill = new JTextField("19");
        oFill.setEditable(false);
        this.add(oFill, oGridBagConstraints);
        oGridBagConstraints.gridx = 20;
        this.add(new JCheckBox(), oGridBagConstraints);
        oGridBagConstraints.gridx = 40;
        oGridBagConstraints.gridwidth = 460;
        oFill = new JTextField("Test");
        oFill.setEditable(false);
        this.add(oFill, oGridBagConstraints);
    }

    public void setData(ArrayList<String> data) {
        this.removeAll();

        int iNumberOfLines = data.size() + 1;

        this.setLayout(new GridBagLayout());

        JPanel oLineNumbers = new JPanel();
        oLineNumbers.setLayout(new GridLayout(iNumberOfLines, 1));
        GridBagConstraints oGridBagConstraints = new GridBagConstraints();
        oGridBagConstraints.gridx = 0;
        oGridBagConstraints.gridy = 0;
        oGridBagConstraints.gridwidth = 500;
        oGridBagConstraints.gridheight = 600;

        JPanel oBreakpoints = new JPanel();
        oBreakpoints.setLayout(new GridLayout(iNumberOfLines, 1));

        JPanel oFileLines = new JPanel();
        oFileLines.setLayout(new GridLayout(iNumberOfLines, 1));

        for (int i = 1; i < iNumberOfLines; i++) {
            JTextField oTestLine = new JTextField(data.get(i - 1));
            oTestLine.setEditable(false);
            oFileLines.add(oTestLine);
            JCheckBox oCheckbox = new JCheckBox();
            oCheckbox.setEnabled(false);
            oCheckboxes.add(oCheckbox);
            oBreakpoints.add(oCheckbox);
            JTextField oNumber = new JTextField(i + "");
            oNumber.setEditable(false);
            oLineNumbers.add(oNumber);
        }

        this.add(oLineNumbers, oGridBagConstraints);
        this.add(oBreakpoints);
        this.add(oFileLines);
    }

    public ArrayList<JCheckBox> getCheckboxes() {
        return oCheckboxes;
    }
}
