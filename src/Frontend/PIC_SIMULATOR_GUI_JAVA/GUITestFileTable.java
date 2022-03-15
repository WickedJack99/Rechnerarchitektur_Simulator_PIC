package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GUITestFileTable extends JScrollPane {
    String sLine;
    ArrayList<JCheckBox> oCheckboxes = new ArrayList<JCheckBox>();
    JPanel oTable;
    int iTheme = 0;

    /**
     * Color oWhite = new Color(255, 253, 250);
     * Color oDarkGray = new Color(76, 78, 82);
     * First Color == TextColor
     * Second Color == BackgroundColor
     */
    Color[] aoDarkTheme = {new Color(255, 253, 250), new Color(76, 78, 82)};
    Color[] aoLightTheme = {new Color(76, 78, 82), new Color(255, 253, 250)};

    /**
     * Constructor which initializes a filler.
     */
    public GUITestFileTable() {
        oTable = new JPanel();
        JTextField oFill = new JTextField("0");
        oFill.setEditable(false);
        JPanel oTestPanel = new JPanel();
        oTestPanel.setLayout(new GridLayout(1, 3));
        oTestPanel.add(oFill);
        oTestPanel.add(new JCheckBox());
        oFill = new JTextField("Please load testfile!");
        oFill.setEditable(false);
        oTestPanel.add(oFill);
        this.setViewportView(oTestPanel);
    }

    /**
     * Creates scrollable panel which contains information of testfile.
     * @param data arraylist which contains the data of the testfile as strings. (testfile has x lines, data has x entries)
     */
    public void setData(ArrayList<String> data) {
        oTable.removeAll(); //Clear table.
        oCheckboxes.clear(); //Clear arraylist containing checkboxes.

        //Component which will include numbers, breakpoints and lines from testfile.
        JPanel oLines = new JPanel();
        oLines.setLayout(new GridBagLayout());

        //Constraint for position of components at oLines.
        GridBagConstraints l = new GridBagConstraints();
        l.gridx = 0; 

        int iMaxLength = getMaxLen(data); //MaxLen for displaying line.
        int iNumberOfLines = data.size() + 1;
        int iVerticalCount = 0; //Variable for adding components to right line.
        for (int i = 1; i < iNumberOfLines; i++) {
            //Component which will be filled with three components (represents one line).
            JPanel oLine = new JPanel();
            oLine.setLayout(new GridBagLayout());

            //Constraint for position of components at oLine.
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0; //row 0

            c.gridx = 0; //collumn 0
            //Component displays number of testfileline
            JTextField oNumber = new JTextField(i + "", 3);
            oNumber.setForeground(aoLightTheme[0]);
            oNumber.setBackground(aoLightTheme[1]);
            oNumber.setEditable(false);
            oLine.add(oNumber, c);

            c.gridx = 1; //collumn 1
            //Component displays whether breakpoint is set or reset.
            JCheckBox oCheckbox = new JCheckBox();
            oCheckbox.setEnabled(false);      
            oCheckboxes.add(oCheckbox);
            oLine.add(oCheckbox, c);

            c.gridx = 2; //collumn 2
            //Component displays comment to specific line.
            JTextField oTestLine = new JTextField(data.get(i - 1), (int)(iMaxLength * 0.7));
            oTestLine.setEditable(false);
            oLine.add(oTestLine, c);
            
            l.gridy = iVerticalCount; //row iVerticalcount
            l.anchor = GridBagConstraints.WEST; //Set orientation of elements to left (west)
            oLines.add(oLine, l);
            iVerticalCount++; //increment for putting next entry at next line.
        }

        oTable.add(oLines);

        this.setPreferredSize(new Dimension(600, 700));
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setWheelScrollingEnabled(true);
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.setViewportView(oTable);
    }

    /**
     * @returns ArrayList containing all checkboxes which represent the breakpoints at testfile.
     */
    public ArrayList<JCheckBox> getCheckboxes() {
        return oCheckboxes;
    }

    /**
     * @param data ArrayList to check.
     * @returns length of longest string at ArrayList.
     */
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

    public void setDarkTheme() {
        iTheme = 1;
    }

    public void setLightTheme() {
        iTheme = 0;
    }

    public void markLine(int iLineToMark) {

    }

    public void unmarkLine(int iLineToUnmark) {

    }

    /**
     * @return Color to set for testfiletable
     */
    private Color[] getTheme() {
        Color[] oReturnColor = {new Color(76, 78, 82), new Color(255, 253, 250)};
        switch (iTheme) {
            case 0: {
                oReturnColor = aoLightTheme;
            }break;
            case 1: {
                oReturnColor = aoDarkTheme;
            }break;
        }
        return oReturnColor;
    }
}